package s8010027.kritchanon.catchtaxidriver.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class UserData {

    String mobile="";
    String name="";
    String email="";
    String taxiId="";

    private static UserData instance;

    public static UserData getInstance() {
        if (instance == null)
            instance = new UserData();
        return instance;
    }

    private Context mContext;

    private UserData() {
        mContext = Contextor.getInstance().getContext();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(String taxiId) {
        this.taxiId = taxiId;
    }
}
