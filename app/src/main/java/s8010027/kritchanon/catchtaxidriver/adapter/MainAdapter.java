package s8010027.kritchanon.catchtaxidriver.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import s8010027.kritchanon.catchtaxidriver.util.ImageTextButton;
import s8010027.kritchanon.catchtaxidriver.view.ImageTextButtonView;

/**
 * Created by ASUS-PC on 16/12/2560.
 */

public class MainAdapter extends BaseAdapter {

    ArrayList<ImageTextButton> menuName = new ArrayList<ImageTextButton>();

    public MainAdapter(ArrayList<ImageTextButton> menuName){
        this.menuName = menuName;
    }

    @Override
    public int getCount() {
        return menuName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ImageTextButtonView item;
        if(convertView != null){
            item = (ImageTextButtonView) convertView;
        }
        else{
            item = new ImageTextButtonView(parent.getContext());
        }

        // set icon and string to drawer menu
        item.setIvImageTextButton(menuName.get(i).getIcon());
        item.setTvImageTextButton(parent.getContext().getResources().getString(menuName.get(i).getName()));
        // set color string of drawer menu
        item.setColorTvImageTextButton(0xffffffff);
        return item;
    }
}
