package s8010027.kritchanon.catchtaxidriver.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

import s8010027.kritchanon.catchtaxidriver.R;

public class ImageTextButtonView extends BaseCustomViewGroup {

    RelativeLayout relativeImageTextButton;
    ImageView ivImageTextButton;
    TextView tvImageTextButton;

    public ImageTextButtonView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ImageTextButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ImageTextButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ImageTextButtonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.image_text_button_view, this);
    }

    private void initInstances() {
        // findViewById here
        relativeImageTextButton = (RelativeLayout)findViewById(R.id.relativeImageTextButton);
        ivImageTextButton = (ImageView)findViewById(R.id.ivImageTextButton);
        tvImageTextButton = (TextView)findViewById(R.id.tvImageTextButton);
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

    public void setRelativeImageTextButton(int resource){
        relativeImageTextButton.setBackgroundResource(resource);
    }

    public void setIvImageTextButton(int resource){
        ivImageTextButton.setImageResource(resource);
    }

    public void setTvImageTextButton(String text){
        tvImageTextButton.setText(text);
    }

    public void setColorTvImageTextButton(int color){
        tvImageTextButton.setTextColor(color);
    }



}
