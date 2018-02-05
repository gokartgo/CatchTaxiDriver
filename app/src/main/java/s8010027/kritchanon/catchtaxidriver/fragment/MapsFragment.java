package s8010027.kritchanon.catchtaxidriver.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
public class MapsFragment extends Fragment implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener {

    public interface ActivityCommunicator{
        void passDataToActivity(int chooseCustomer);
    }

    private GoogleMap mMap;
    private MapView mapView;
    private static final String TAG = "LocationDemo";
    private GoogleApiClient googleApiClient;
    // location user
    double latitude;
    double longitude;
    LatLng latLng;
    String strLatLng;
    MarkerOptions markerUser;
    // location customer
    double[] latitudeCustomer={13.689999,13.764921,13.746815,13.913260};
    double[] longitudeCustomer={100.750112,100.538246,100.535069,100.604199};
    LatLng[] latLngCustomer;
    String[] strLatLngCustomer={"Suvarnabhumi Airport","Victory Monument","Siam Paragon","Don Mueang International Airport"};
    MarkerOptions[] markerCustomer;
    Marker[] markerCustomerChoose = new Marker[4];
    // Choose Customer Location
    int chooseCustomer = -1;
    // Choose Customer Location Dialog
    ChooseCustomerView chooseCustomerViews;
    Button btnChooseCustomer,btnCancelCustomer;
    // set dialog
    Dialog dialog;
    // route map
    PolylineOptions route;
    // fragment sent value to Mainactivity
    ActivityCommunicator activityCommunicator;

    public MapsFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MapsFragment newInstance(int chooseCustomer) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putInt("chooseCustomer",chooseCustomer);
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
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi
                .getLastLocation(googleApiClient);
        if(lastLocation != null){
            latitude = lastLocation.getLatitude();
            longitude = lastLocation.getLongitude();
            // instantiate the class Geocoder
            latLng = new LatLng(latitude, longitude);
            Geocoder geocoder = new Geocoder(getContext());
            try {
                List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                strLatLng = addressList.get(0).getLocality();
                strLatLng += " " + addressList.get(0).getCountryName();

                markerCustomer = setLatLngCustomer(markerCustomer);

                // Create Marker
                markerUser = new MarkerOptions().position(latLng).title(strLatLng);
                /********* Set Marker Icon  **************/
                // BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_google);
                // markerUser.icon(icon);
                // Add Marker
                mMap.addMarker(markerUser);
                if(chooseCustomer == -1) {
                    for (int i = 0; i < 4; i++) {
                        if(markerCustomerChoose[i] == null) {
                            markerCustomerChoose[i] = mMap.addMarker(markerCustomer[i]);
                        }
                    }
                }
                if(chooseCustomer > -1){
                    for (int i = 0; i < 4; i++) {
                        if(markerCustomerChoose[i] == null) {
                            markerCustomerChoose[i] = mMap.addMarker(markerCustomer[i]);
                        }
                        if(i != chooseCustomer) {
                            markerCustomerChoose[i].remove();
                        }
                        if(i == chooseCustomer){
                            // route map
                            route = new PolylineOptions().add(latLng, latLngCustomer[i]).width(10.0f).color(Color.BLUE);
                            mMap.addPolyline(route);
                            // set mid latitude longitude form start to destination
                            latLng = new LatLng((latitude+ CustomerStrDesData.getInstance().getStrLatitudeCustomer()[i])/2.0
                                    , (longitude+CustomerStrDesData.getInstance().getStrLongitudeCustomer()[i])/2.0);
                        }
                    }
                }
                // set marker customer click
                mMap.setOnMarkerClickListener(markerClick);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(getContext(),getString(R.string.open_location),Toast.LENGTH_SHORT).show();
        }


    }

    private MarkerOptions[] setLatLngCustomer(MarkerOptions[] markerCustomer) {
        latLngCustomer = new LatLng[4];
        markerCustomer = new MarkerOptions[4];
        for(int i=0;i<4;i++) {
            latLngCustomer[i] = new LatLng(CustomerStrDesData.getInstance().getStrLatitudeCustomer()[i]
                    , CustomerStrDesData.getInstance().getStrLongitudeCustomer()[i]);
            markerCustomer[i] = new MarkerOptions()
                    .position(latLngCustomer[i])
                    .title(CustomerStrDesData.getInstance().getStrPlaceLatLngCustomer()[i]);
            /********* Set Marker Icon  **************/
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.location_customer);
            markerCustomer[i].icon(icon);
        }
        return markerCustomer;
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG,"Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG,"Connection failed:error code = "
                + connectionResult.getErrorCode());
    }

    public int getChooseCustomer(){
        return chooseCustomer;
    }

    /**********************
     * dialog zone
     */

    private void showDialog(int index) {
        dialog = new Dialog(getContext(),R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_choose_customer);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        // set ChooseCustomerView in dialog
        chooseCustomerViews = new ChooseCustomerView(getContext());

        chooseCustomerViews = (ChooseCustomerView) dialog.findViewById(R.id.customViewChooseCustomer);

        // set ChooseCustomerView value
        chooseCustomerViews.setTextTvStartHead(CustomerData.getInstance().getStartHead()[index]);
        chooseCustomerViews.setTextTvStart(CustomerData.getInstance().getStart()[index]);
        chooseCustomerViews.setTextTvDestinationHead(CustomerData.getInstance().getDestinationHead()[index]);
        chooseCustomerViews.setTextTvDestination(CustomerData.getInstance().getDestination()[index]);
        chooseCustomerViews.setTextTvCustomerName(CustomerData.getInstance().getCustomer()[index]);
        chooseCustomerViews.setTextTvPhone(CustomerData.getInstance().getPhone());
        chooseCustomerViews.setImageIvRateStar(CustomerData.getInstance().getRateStar()[index]);
        // set button click choose customer
        btnChooseCustomer = (Button) dialog.findViewById(R.id.btnChooseCustomer);
        btnCancelCustomer = (Button) dialog.findViewById(R.id.btnCancelCustomer);
        btnChooseCustomer.setOnClickListener(buttonCustomer);
        btnCancelCustomer.setOnClickListener(buttonCustomer);

        dialog.show();
    }

    /************
     * set marker click
     */

    final GoogleMap.OnMarkerClickListener markerClick = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            if (marker.equals(markerCustomerChoose[0])) {
                showDialog(0);
            }
            if (marker.equals(markerCustomerChoose[1])) {
                showDialog(1);
            }
            if (marker.equals(markerCustomerChoose[2])) {
                showDialog(2);
            }
            if (marker.equals(markerCustomerChoose[3])) {
                showDialog(3);
            }
            return true;
        }
    };

    /************
     * set on click
     */
    final View.OnClickListener buttonCustomer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnChooseCustomer){
                for(int i=0;i<CustomerData.getInstance().getCustomer().length;i++){
                    if(CustomerData.getInstance().getCustomer()[i].equals(chooseCustomerViews.getTextTvCustomerName())){
                        chooseCustomer = i;
                    }
                }
                dialog.cancel();
                // sent value to activity
                activityCommunicator = (ActivityCommunicator)getActivity();
                activityCommunicator.passDataToActivity(chooseCustomer);
            }
            if(view == btnCancelCustomer){
                dialog.cancel();
                // refresh fragment from cancel customer
                chooseCustomer = -1;
                // sent value to activity
                activityCommunicator = (ActivityCommunicator)getActivity();
                activityCommunicator.passDataToActivity(chooseCustomer);
            }
        }
    };


}
