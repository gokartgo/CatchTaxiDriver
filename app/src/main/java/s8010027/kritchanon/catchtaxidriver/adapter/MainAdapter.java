package s8010027.kritchanon.catchtaxidriver.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.view.ImageTextButtonView;

/**
 * Created by ASUS-PC on 16/12/2560.
 */

public class MainAdapter extends BaseAdapter {

    ImageTextButtonView[] menuNameView;
    Integer[] icon = {R.drawable.icon_history,R.drawable.icon_star,R.drawable.icon_catch_reward
            ,R.drawable.icon_check,R.drawable.icon_setting
            ,R.drawable.icon_callcenter,R.drawable.icon_block};
    Integer[] text = {R.string.menu_1,R.string.menu_2,R.string.menu_3
            ,R.string.menu_4,R.string.menu_5
            ,R.string.menu_6,R.string.menu_7};

    public MainAdapter(ImageTextButtonView[] menuNameView){
        this.menuNameView = menuNameView;
    }

    @Override
    public int getCount() {
        return menuNameView.length;
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
        // set color string of drawer menu
        item.setIvImageTextButton(icon[i]);
        item.setTvResourceImageTextButton(text[i]);
        return item;
    }
}
