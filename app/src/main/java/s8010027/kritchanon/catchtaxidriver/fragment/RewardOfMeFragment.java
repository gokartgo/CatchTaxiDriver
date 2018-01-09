package s8010027.kritchanon.catchtaxidriver.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.manager.CustomerRewardData;
import s8010027.kritchanon.catchtaxidriver.view.MyRewardPointView;
import s8010027.kritchanon.catchtaxidriver.view.RewardView;


@SuppressWarnings("unused")
public class RewardOfMeFragment extends Fragment {

    interface SentDataFragment {

    }

    MyRewardPointView myRewardPointView;
    RewardView customViewPromotionMe1;
    Dialog dialog;
    Button btnConfirm;
    Button btnCancel;
    Dialog dialogThank;

    public RewardOfMeFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static RewardOfMeFragment newInstance() {
        RewardOfMeFragment fragment = new RewardOfMeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_of_me, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        myRewardPointView = (MyRewardPointView) rootView.findViewById(R.id.customViewMyRewardPointView);
        myRewardPointView.setTextTvPoint(CustomerRewardData.getInstance().getRewardPoint() + "");
        customViewPromotionMe1 = (RewardView) rootView.findViewById(R.id.customViewPromotionMe1);
        customViewPromotionMe1.setImageIvPromotion(R.drawable.reward_taxi);
        customViewPromotionMe1.setTextTvPromotionName(R.string.promotion_me_name_1);
        customViewPromotionMe1.setTextTvPromotionPoint("500");
        customViewPromotionMe1.getBtnPromotionPurchase().setOnClickListener(btnClick);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    /*********
     * listener zone
     */


    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == customViewPromotionMe1.getBtnPromotionPurchase()) {
                dialog = new Dialog(getContext(), R.style.Theme_Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_change_reward);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                btnConfirm = (Button) dialog.findViewById(R.id.btnConfirm);
                btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                btnConfirm.setOnClickListener(btnClick);
                btnCancel.setOnClickListener(btnClick);
                dialog.show();
            }
            if (view == btnConfirm) {
                dialog.cancel();
                dialogThank = new Dialog(getContext());
                dialogThank.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogThank.setContentView(R.layout.dialog_can_change_reward);
                dialogThank.setCancelable(true);
                dialogThank.setCanceledOnTouchOutside(true);
                dialogThank.show();
                customViewPromotionMe1.setVisibility(View.GONE);
                CustomerRewardData.getInstance()
                        .setRewardPoint(Integer.parseInt(customViewPromotionMe1.getTextTvPromotionPoint()));
                myRewardPointView.setTextTvPoint(CustomerRewardData.getInstance().getRewardPoint() + "");

            }
            if (view == btnCancel) {
                dialog.cancel();
            }
        }
    };
}
