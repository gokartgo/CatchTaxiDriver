package s8010027.kritchanon.catchtaxidriver.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class CustomerRewardData {

    private int credit = 250;
    private int money;
    private int rewardPoint = 500;

    private static CustomerRewardData instance;

    public static CustomerRewardData getInstance() {
        if (instance == null)
            instance = new CustomerRewardData();
        return instance;
    }

    private Context mContext;

    private CustomerRewardData() {
        mContext = Contextor.getInstance().getContext();
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPointUse) {
        rewardPoint = rewardPoint-rewardPointUse;
    }
}
