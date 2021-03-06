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

public class CashbackEWalletView extends BaseCustomViewGroup {

    ImageView ivWallet;
    TextView tvWallet;
    TextView tvMoney;

    public CashbackEWalletView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CashbackEWalletView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CashbackEWalletView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CashbackEWalletView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.view_cash_back_e_wallet, this);
    }

    private void initInstances() {
        // findViewById here
        ivWallet = (ImageView) findViewById(R.id.ivWallet);
        tvWallet = (TextView) findViewById(R.id.tvWallet);
        tvMoney = (TextView) findViewById(R.id.tvMoney);
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

    public void setImageIvWallet(int resource) {
        ivWallet.setImageResource(resource);
    }

    public void setTextTvWallet(String text) {
        tvWallet.setText(text);
    }

    public void setTextTvMoney(String text) {
        tvMoney.setText(text);
    }

    public String getTextTvMoney(){
        return tvMoney.getText().toString();
    }
}
