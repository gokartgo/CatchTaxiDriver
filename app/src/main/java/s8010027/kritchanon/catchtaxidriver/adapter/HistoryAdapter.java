package s8010027.kritchanon.catchtaxidriver.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.manager.HistoryData;
import s8010027.kritchanon.catchtaxidriver.view.HistoryView;
import s8010027.kritchanon.catchtaxidriver.view.ImageTextButtonView;

/**
 * Created by ASUS-PC on 20/12/2560.
 */

public class HistoryAdapter extends BaseAdapter {

    /*String customer[] = {"","","",""};
    String from[] = res.getStringArray(R.array.From);
    String[] to = res.getStringArray(R.array.To);
    String pay[] = {310+"",292+""};
    String rate[] = {4.0+"",4.5+"",5.0+"",3.5+""};*/

    ArrayList<HistoryData> historyData;
    public HistoryAdapter(ArrayList<HistoryData> historyData){
        this.historyData = historyData;
    }

    @Override
    public int getCount() {
        return historyData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HistoryView item;
        if(convertView != null){
            item = (HistoryView) convertView;
        }
        else{
            item = new HistoryView(parent.getContext());
        }
        item.setTextTvCustomerName(historyData.get(position).getTvCustomerName());
        item.setTextTvDate(historyData.get(position).getTvDate());
        item.setTextTvTime(historyData.get(position).getTvTime());
        item.setTextTvFrom(historyData.get(position).getTvFrom());
        item.setTextTvTo(historyData.get(position).getTvTo());
        item.setTextTvCustomerPay(historyData.get(position).getTvCustomerPay());
        item.setImageIvRateStar(historyData.get(position).getIvRateStar());
        return item;
    }
}
