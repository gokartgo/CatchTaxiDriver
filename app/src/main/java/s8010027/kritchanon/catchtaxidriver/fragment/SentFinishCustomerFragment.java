package s8010027.kritchanon.catchtaxidriver.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.manager.CustomerStrDesData;
import s8010027.kritchanon.catchtaxidriver.view.ChooseCustomerView;


@SuppressWarnings("unused")
public class SentFinishCustomerFragment extends Fragment implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener {

    public interface SentFinishCustomerFragmentListener{
        void sentFinishCustomerValue(int chooseCustomer,int pickup,int money);
    }

    private GoogleMap mMap;
    private MapView mapView;
    private static final String TAG = "LocationDemo";
    private GoogleApiClient googleApiClient;
    // location customer destination
    LatLng[] desLatLngCustomer;
    MarkerOptions[] desMarkerCustomer;
    Marker[] desMarkerCustomerChoose = new Marker[4];
    // Choose Customer Location
    int chooseCustomer = -1;
    // Dialog setup
    Dialog dialog;
    Dialog dialogFinish;
    EditText edMoney;
    Button btnMoney;
    // define pass value from fragment to mainactivity
    SentFinishCustomerFragmentListener finishCustomer;

    public SentFinishCustomerFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SentFinishCustomerFragment newInstance(int chooseCustomer) {
        SentFinishCustomerFragment fragment = new SentFinishCustomerFragment();
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

        dialog = new Dialog(getContext(),R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_arrived);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        edMoney = (EditText)dialog.findViewById(R.id.edMoney);
        btnMoney = (Button) dialog.findViewById(R.id.btnMoney);
        btnMoney.setOnClickListener(btnClick);
        dialog.show();
        // pass value from fragment to mainactivity
        finishCustomer = (SentFinishCustomerFragmentListener)getActivity();
        finishCustomer.sentFinishCustomerValue(chooseCustomer,1,0);
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
        desMarkerCustomer = setDesLatLngCustomer(desMarkerCustomer);

        // Create Marker

        /********* Set Marker Icon  **************/
        // BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_google);
        // markerUser.icon(icon);
        // Add Marker
        try {
            for (int i = 0; i < 4; i++) {
                desMarkerCustomerChoose[i] = mMap.addMarker(desMarkerCustomer[i]);
                if (i != chooseCustomer) {
                    desMarkerCustomerChoose[i].remove();
                }
                if (i == chooseCustomer) {
                    // set carema to destination
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(desLatLngCustomer[i], 11.5f));
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private MarkerOptions[] setDesLatLngCustomer(MarkerOptions[] desMarkerCustomer) {
        desLatLngCustomer = new LatLng[4];
        desMarkerCustomer = new MarkerOptions[4];
        for (int i = 0; i < 4; i++) {
            desLatLngCustomer[i] = new LatLng(CustomerStrDesData.getInstance().getDesLatitudeCustomer()[i]
                    ,CustomerStrDesData.getInstance().getDesLongitudeCustomer()[i]);
            desMarkerCustomer[i] = new MarkerOptions()
                    .position(desLatLngCustomer[i])
                    .title(CustomerStrDesData.getInstance().getDesPlaceLatLngCustomer()[i]);
            /********* Set Marker Icon  **************/
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.location_user);
            desMarkerCustomer[i].icon(icon);
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
     * Click On Listener
     */

    final View.OnClickListener btnClick = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view == btnMoney){
                if(edMoney.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Input Money",Toast.LENGTH_SHORT).show();
                }
                else {
                    dialog.cancel();
                    dialogFinish = new Dialog(getContext());
                    dialogFinish.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogFinish.setContentView(R.layout.dialog_arrived_finish);
                    dialogFinish.setCancelable(true);
                    dialogFinish.setCanceledOnTouchOutside(true);
                    dialogFinish.show();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, MapsFragment.newInstance(-1), "MapsFragment")
                            .commit();
                    // money from customer is minus card fees 25 baths
                    int money = Integer.parseInt(edMoney.getText().toString());
                    // sent value to mainactivity
                    finishCustomer.sentFinishCustomerValue(-1,0,money);
                }
            }
        }
    };
}
