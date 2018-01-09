package s8010027.kritchanon.catchtaxidriver.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

import org.w3c.dom.Text;

import s8010027.kritchanon.catchtaxidriver.R;

public class RewardView extends BaseCustomViewGroup {

    ImageView ivPromotion;
    TextView tvPromotionName;
    TextView tvPromotionPoint;
    private Button btnPromotionPurchase;

    public RewardView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public RewardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public RewardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public RewardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.view_reward, this);
    }

    private void initInstances() {
        // findViewById here
        ivPromotion = (ImageView)findViewById(R.id.ivPromotion);
        tvPromotionName = (TextView)findViewById(R.id.tvPromotionName);
        tvPromotionPoint = (TextView)findViewById(R.id.tvPromotionPoint);
        btnPromotionPurchase = (Button)findViewById(R.id.btnPromotionPurchase);
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

    public void setImageIvPromotion(int resource) {
        ivPromotion.setImageResource(resource);
    }

    public void setTextTvPromotionName(int text) {
        tvPromotionName.setText(text);
    }

    public String getTextTvPromotionPoint() {
        return tvPromotionPoint.getText().toString();
    }

    public void setTextTvPromotionPoint(String text) {
        tvPromotionPoint.setText(text);
    }


    public Button getBtnPromotionPurchase() {
        return btnPromotionPurchase;
    }

}
