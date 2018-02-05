package s8010027.kritchanon.catchtaxidriver.manager;

/**
 * Created by ASUS-PC on 20/12/2560.
 */

public class HistoryData {

    private String tvCustomerName;
    private String tvDate;
    private String tvTime;
    private String tvFrom;
    private String tvTo;
    private String tvCustomerPay;
    private String tvCustomerRate;
    private int ivRateStar;

    public HistoryData(String tvCustomerName,String tvDate,String tvTime,String tvFrom,String tvTo,String tvCustomerPay,int ivRateStar){
        this.tvCustomerName = tvCustomerName;
        this.tvDate = tvDate;
        this.tvTime = tvTime;
        this.tvFrom = tvFrom;
        this.tvTo = tvTo;
        this.tvCustomerPay = tvCustomerPay;
        this.ivRateStar = ivRateStar;
    }

    public String getTvCustomerName() {
        return tvCustomerName;
    }

    public String getTvDate(){
        return tvDate;
    }

    public String getTvTime(){
        return tvTime;
    }

    public String getTvFrom() {
        return tvFrom;
    }

    public String getTvTo() {
        return tvTo;
    }

    public String getTvCustomerPay() {
        return tvCustomerPay;
    }

    public int getIvRateStar(){
        return ivRateStar;
    }
}
