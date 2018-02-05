package s8010027.kritchanon.catchtaxidriver.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

import org.w3c.dom.Text;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.activity.CallCenterActivity;
import s8010027.kritchanon.catchtaxidriver.activity.CallPhoneActivity;

public class ChooseCustomerView extends BaseCustomViewGroup {

    TextView tvStartHead;
    TextView tvStart;
    TextView tvDestinationHead;
    TextView tvDestination;
    TextView tvCustomerName;
    TextView tvPhone;
    ImageView ivRateStar;
    LinearLayout linearLayoutCall;

    public ChooseCustomerView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ChooseCustomerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ChooseCustomerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ChooseCustomerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.view_choose_customer, this);
    }

    private void initInstances() {
        // findViewById here
        tvStartHead = (TextView)findViewById(R.id.tvStartHead);
        tvStart = (TextView)findViewById(R.id.tvStart);
        tvDestinationHead = (TextView)findViewById(R.id.tvDestinationHead);
        tvDestination = (TextView)findViewById(R.id.tvDestination);
        tvCustomerName = (TextView)findViewById(R.id.tvCustomerName);
        tvPhone = (TextView)findViewById(R.id.tvPhone);
        ivRateStar = (ImageView)findViewById(R.id.ivRateStar);
        linearLayoutCall = (LinearLayout)findViewById(R.id.linerLayoutCall);
        linearLayoutCall.setOnClickListener(layoutClick);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setTextTvStartHead(String text) {
        tvStartHead.setText(text);
    }

    public void setTextTvStart(String text) {
        tvStart.setText(text);
    }

    public void setTextTvDestinationHead(String text) {
        tvDestinationHead.setText(text);
    }

    public void setTextTvDestination(String text) {
        tvDestination.setText(text);
    }

    public void setTextTvCustomerName(String text) {
        tvCustomerName.setText(text);
    }

    public String getTextTvCustomerName() {
        return tvCustomerName.getText().toString();
    }

    public void setTextTvPhone(String text) {
        tvPhone.setText(text);
    }

    public void setImageIvRateStar(int resource){
        ivRateStar.setImageResource(resource);
    }

    /*********
     * listener zone
     */

    final View.OnClickListener layoutClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == linearLayoutCall){
                Intent intent = new Intent(getContext(), CallPhoneActivity.class);
                intent.putExtra("customer",0);
                getContext().startActivity(intent);
            }
        }
    };
}
