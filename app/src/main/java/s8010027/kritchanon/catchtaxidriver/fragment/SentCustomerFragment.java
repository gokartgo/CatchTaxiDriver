package s8010027.kritchanon.catchtaxidriver.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.manager.CustomerData;
import s8010027.kritchanon.catchtaxidriver.manager.CustomerStrDesData;
import s8010027.kritchanon.catchtaxidriver.view.ChooseCustomerView;


@SuppressWarnings("unused")
public class SentCustomerFragment extends Fragment implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private MapView mapView;
    private static final String TAG = "LocationDemo";
    private GoogleApiClient googleApiClient;
    // location user
    LatLng latLng;
    String strLatLng;
    MarkerOptions markerUser;
    // location customer start
    LatLng[] strLatLngCustomer;
    MarkerOptions[] strMarkerCustomer;
    Marker[] strMarkerCustomerChoose = new Marker[4];
    // location customer destination
    LatLng[] desLatLngCustomer;
    MarkerOptions[] desMarkerCustomer;
    Marker[] desMarkerCustomerChoose = new Marker[4];
    // Choose Customer Location
    int chooseCustomer = -1;
    // route map
    PolylineOptions route;
    ImageButton ivBtnRoute;

    public SentCustomerFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SentCustomerFragment newInstance(int chooseCustomer) {
        SentCustomerFragment fragment = new SentCustomerFragment();
        Bundle args = new Bundle();
        args.putInt("chooseCustomer", chooseCustomer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        chooseCustomer = getArguments().getInt("chooseCustomer");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mapView = (MapView) rootView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        ivBtnRoute = (ImageButton) rootView.findViewById(R.id.ivBtnRoute);
        ivBtnRoute.setOnClickListener(imageButtonClick);
        ivBtnRoute.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    /**************************
     *** google api service ***
     **************************/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        strMarkerCustomer = setStrLatLngCustomer(strMarkerCustomer);
        desMarkerCustomer = setDesLatLngCustomer(desMarkerCustomer);

        // Create Marker

        /********* Set Marker Icon  **************/
        // BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_google);
        // markerUser.icon(icon);
        // Add Marker
        try {
            for (int i = 0; i < 4; i++) {
                strMarkerCustomerChoose[i] = mMap.addMarker(strMarkerCustomer[i]);
                desMarkerCustomerChoose[i] = mMap.addMarker(desMarkerCustomer[i]);
                if (i != chooseCustomer) {
                    strMarkerCustomerChoose[i].remove();
                    desMarkerCustomerChoose[i].remove();
                }
                if (i == chooseCustomer) {
                    // route map
                    route = new PolylineOptions().add(strLatLngCustomer[i], desLatLngCustomer[i])
                            .width(10.0f).color(Color.BLUE);
                    mMap.addPolyline(route);
                    // set mid latitude longitude form start to destination
                    latLng = new LatLng((CustomerStrDesData.getInstance().getStrLatitudeCustomer()[i]
                            + CustomerStrDesData.getInstance().getDesLatitudeCustomer()[i]) / 2.0
                            , (CustomerStrDesData.getInstance().getStrLongitudeCustomer()[i]
                            + CustomerStrDesData.getInstance().getDesLongitudeCustomer()[i]) / 2.0);
                }
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11.5f));

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private MarkerOptions[] setStrLatLngCustomer(MarkerOptions[] strMarkerCustomer) {
        strLatLngCustomer = new LatLng[4];
        strMarkerCustomer = new MarkerOptions[4];
        for (int i = 0; i < 4; i++) {
            strLatLngCustomer[i] = new LatLng(CustomerStrDesData.getInstance().getStrLatitudeCustomer()[i]
                    ,CustomerStrDesData.getInstance().getStrLongitudeCustomer()[i]);
            strMarkerCustomer[i] = new MarkerOptions()
                    .position(strLatLngCustomer[i])
                    .title(CustomerStrDesData.getInstance().getStrPlaceLatLngCustomer()[i]);
            /********* Set Marker Icon  **************/
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.location_user);
            strMarkerCustomer[i].icon(icon);
        }
        return strMarkerCustomer;
    }

    private MarkerOptions[] setDesLatLngCustomer(MarkerOptions[] desMarkerCustomer) {
        desLatLngCustomer = new LatLng[4];
        desMarkerCustomer = new MarkerOptions[4];
        for (int i = 0; i < 4; i++) {
            desLatLngCustomer[i] = new LatLng(CustomerStrDesData.getInstance().getDesLatitudeCustomer()[i]
                    , CustomerStrDesData.getInstance().getDesLongitudeCustomer()[i]);
            desMarkerCustomer[i] = new MarkerOptions()
                    .position(desLatLngCustomer[i])
                    .title(CustomerStrDesData.getInstance().getDesPlaceLatLngCustomer()[i]);
        }
        return desMarkerCustomer;
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed:error code = "
                + connectionResult.getErrorCode());
    }

    /**********
     * listener zone
     */

    final View.OnClickListener imageButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == ivBtnRoute){
                openGoogleMap(strLatLngCustomer[chooseCustomer], desLatLngCustomer[chooseCustomer]);
            }
        }
    };

    /*********
     * open google map
     */
    private void openGoogleMap(LatLng src, LatLng dest) {
        String url = "http://maps.google.com/maps?saddr="+src.latitude+","+src.longitude+"&daddr="+dest.latitude+","+dest.longitude+"&mode=driving";
        Uri gmmIntentUri = Uri.parse(url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
