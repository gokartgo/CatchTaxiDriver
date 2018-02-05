package s8010027.kritchanon.catchtaxidriver.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import s8010027.kritchanon.catchtaxidriver.R;

public class CustomerData {

    private Context mContext;

    // Customer data
    String[] startHead;
    String[] start;
    String[] destinationHead;
    String[] destination;
    String[] customer;
    String phone;
    int[] rateStar;


    private static CustomerData instance;

    public static CustomerData getInstance() {
        if (instance == null)
            instance = new CustomerData();
        return instance;
    }

    private CustomerData() {
        mContext = Contextor.getInstance().getContext();
    }

    public String[] getStartHead() {
        startHead = mContext.getResources().getStringArray(R.array.CustomerStartHead);
        return startHead;
    }

    public String[] getStart() {
        start= mContext.getResources().getStringArray(R.array.CustomerStart);
        return start;
    }

    public String[] getDestinationHead() {
        destinationHead = mContext.getResources().getStringArray(R.array.CustomerDestinationHead);
        return destinationHead;
    }

    public String[] getDestination() {
        destination = mContext.getResources().getStringArray(R.array.CustomerDestination);
        return destination;
    }

    public String[] getCustomer() {
        customer = new String[]{"Frank White","Tina Martin","Justin Long","Anna Stewart"};
        return customer;
    }

    public String getPhone() {
        phone = "+6612345XXXX";
        return phone;
    }

    public int[] getRateStar(){
        rateStar = new int[]{R.drawable.star_5,R.drawable.star_4_half,R.drawable.star_4_half,R.drawable.star_3_half};
        return rateStar;
    }
}
