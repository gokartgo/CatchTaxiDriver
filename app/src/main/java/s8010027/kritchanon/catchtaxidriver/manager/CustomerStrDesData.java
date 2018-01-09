package s8010027.kritchanon.catchtaxidriver.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class CustomerStrDesData {

    // location customer start
    double[] strLatitudeCustomer;
    double[] strLongitudeCustomer;
    String[] strPlaceLatLngCustomer;
    // location customer destination
    double[] desLatitudeCustomer;
    double[] desLongitudeCustomer;
    String[] desPlaceLatLngCustomer;

    private static CustomerStrDesData instance;

    public static CustomerStrDesData getInstance() {
        if (instance == null)
            instance = new CustomerStrDesData();
        return instance;
    }

    private Context mContext;

    private CustomerStrDesData() {
        mContext = Contextor.getInstance().getContext();
    }

    public double[] getStrLatitudeCustomer() {
        strLatitudeCustomer = new double[]{13.689999, 13.764921, 13.746815, 13.913260};
        return strLatitudeCustomer;
    }

    public double[] getStrLongitudeCustomer() {
        strLongitudeCustomer = new double[]{100.750112, 100.538246, 100.535069, 100.604199};
        return strLongitudeCustomer;
    }

    public String[] getStrPlaceLatLngCustomer() {
        strPlaceLatLngCustomer = new String[]{"Suvarnabhumi Airport", "Victory Monument", "Siam Paragon", "Don Mueang International Airport"};
        return strPlaceLatLngCustomer;
    }

    public double[] getDesLatitudeCustomer() {
        desLatitudeCustomer = new double[]{13.729896, 13.746228, 13.741260, 13.799946};
        return desLatitudeCustomer;
    }

    public double[] getDesLongitudeCustomer() {
        desLongitudeCustomer = new double[]{100.779316, 100.539819, 100.508186, 100.550854};
        return desLongitudeCustomer;
    }

    public String[] getDesPlaceLatLngCustomer() {
        desPlaceLatLngCustomer = new String[]{"KMITL", "Central World", "Yaowarat Rd", "Chatuchak Market"};
        return desPlaceLatLngCustomer;
    }
}
