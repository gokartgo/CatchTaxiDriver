package s8010027.kritchanon.catchtaxidriver.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

import org.w3c.dom.Text;

import s8010027.kritchanon.catchtaxidriver.R;

public class HistoryView extends BaseCustomViewGroup {

    TextView tvCustomerName,tvDate,tvTime,tvFrom,tvTo,tvCustomerPay;
    ImageView ivRateStar;

    public HistoryView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public HistoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public HistoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public HistoryView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.history_user_view, this);
    }

    private void initInstances() {
        // findViewById here
        tvCustomerName = (TextView) findViewById(R.id.tvCustomerName);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvFrom = (TextView) findViewById(R.id.tvFrom);
        tvTo = (TextView) findViewById(R.id.tvTo);
        tvCustomerPay = (TextView) findViewById(R.id.tvCustomerPay);
        ivRateStar = (ImageView) findViewById(R.id.ivRateStar);
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

    /************
     * setTextView
     */

    public void setTextTvCustomerName(String text) {
        tvCustomerName.setText(text);
    }

    public void setTextTvDate(String text){
        tvDate.setText(text);
    }

    public void setTextTvTime(String text){
        tvTime.setText(text);
    }

    public void setTextTvFrom(String text) {
        tvFrom.setText(text);
    }

    public void setTextTvTo(String text) {
        tvTo.setText(text);
    }

    public void setTextTvCustomerPay(String text) {
        tvCustomerPay.setText(text);
    }

    public void setImageIvRateStar(int resource){ ivRateStar.setImageResource(resource);}

}
