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

import s8010027.kritchanon.catchtaxidriver.R;

public class TransferTopUpView extends BaseCustomViewGroup {

    ImageView ivFrom;
    ImageView ivTo;
    TextView tvFrom;
    TextView tvTo;
    TextView tvMoneyFrom;
    TextView tvMoneyTo;

    public TransferTopUpView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public TransferTopUpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public TransferTopUpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public TransferTopUpView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.view_transfer_top_up, this);
    }

    private void initInstances() {
        // findViewById here
        ivFrom = (ImageView) findViewById(R.id.ivFrom);
        ivTo = (ImageView) findViewById(R.id.ivTo);
        tvFrom = (TextView) findViewById(R.id.tvFrom);
        tvTo = (TextView) findViewById(R.id.tvTo);
        tvMoneyFrom = (TextView) findViewById(R.id.tvMoneyFrom);
        tvMoneyTo = (TextView) findViewById(R.id.tvMoneyTo);
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

    public void setImageIvFrom(int resource) {
        ivFrom.setImageResource(resource);
    }

    public void setImageIvTo(int resource) {
        ivTo.setImageResource(resource);
    }

    public void setTextTvFrom(String text) {
        tvFrom.setText(text);
    }

    public void setTextTvTo(String text) {
        tvTo.setText(text);
    }

    public void setTextTvMoneyFrom(String text) {
        tvMoneyFrom.setText(text);
    }

    public String getTextTvMoneyFrom() {
        return tvMoneyFrom.getText().toString();
    }

    public void setTextTvMoneyTo(String text) {
        tvMoneyTo.setText(text);
    }

    public String getTextTvMoneyTo() {
        return tvMoneyTo.getText().toString();
    }
}
