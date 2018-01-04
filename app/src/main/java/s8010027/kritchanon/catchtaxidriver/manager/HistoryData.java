package s8010027.kritchanon.catchtaxidriver.manager;

/**
 * Created by ASUS-PC on 20/12/2560.
 */

public class HistoryData {

    private String tvCustomerName;
    private String tvFrom;
    private String tvTo;
    private String tvCustomerPay;
    private String tvCustomerRate;

    public HistoryData(String tvCustomerName,String tvFrom,String tvTo,String tvCustomerPay,String tvCustomerRate){
        this.tvCustomerName = tvCustomerName;
        this.tvFrom = tvFrom;
        this.tvTo = tvTo;
        this.tvCustomerPay = tvCustomerPay;
        this.tvCustomerRate = tvCustomerRate;
    }

    public String getTvCustomerName() {
        return tvCustomerName;
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

    public String getTvCustomerRate() {
        return tvCustomerRate;
    }
}
