package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.manager.CustomerRewardData;
import s8010027.kritchanon.catchtaxidriver.view.MyRewardPointView;
import s8010027.kritchanon.catchtaxidriver.view.RewardView;


@SuppressWarnings("unused")
public class RewardAllFragment extends Fragment{

    MyRewardPointView myRewardPointView;
    RewardView customViewPromotionAll1;
    RewardView customViewPromotionAll2;
    RewardView customViewPromotionAll3;
    int i=0;

    public RewardAllFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static RewardAllFragment newInstance() {
        RewardAllFragment fragment = new RewardAllFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_reward_all, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        myRewardPointView = (MyRewardPointView)rootView.findViewById(R.id.customViewMyRewardPointView);
        myRewardPointView.setTextTvPoint(CustomerRewardData.getInstance().getRewardPoint()+"");
        customViewPromotionAll1 = (RewardView) rootView.findViewById(R.id.customViewPromotionAll1);
        customViewPromotionAll2 = (RewardView) rootView.findViewById(R.id.customViewPromotionAll2);
        customViewPromotionAll3 = (RewardView) rootView.findViewById(R.id.customViewPromotionAll3);
        customViewPromotionAll1.setImageIvPromotion(R.drawable.reward_taxi);
        customViewPromotionAll1.setTextTvPromotionName(R.string.promotion_name_1);
        customViewPromotionAll1.setTextTvPromotionPoint("900");
        customViewPromotionAll2.setImageIvPromotion(R.drawable.reward_movie);
        customViewPromotionAll2.setTextTvPromotionName(R.string.promotion_name_2);
        customViewPromotionAll2.setTextTvPromotionPoint("1500");
        customViewPromotionAll3.setImageIvPromotion(R.drawable.reward_mk);
        customViewPromotionAll3.setTextTvPromotionName(R.string.promotion_name_3);
        customViewPromotionAll3.setTextTvPromotionPoint("1000");
    }

    @Override
    public void onResume() {
        super.onResume();
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

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
       if (menuVisible) {
           if(getView()!=null) {
               myRewardPointView.setTextTvPoint(CustomerRewardData.getInstance().getRewardPoint() + "");
           }
        }
    }


}
