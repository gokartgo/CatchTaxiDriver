package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.view.ChooseCustomerView;


@SuppressWarnings("unused")
public class ChooseCustomerFragment extends Fragment {

    public interface ChooseCustomerFragmentListener{
        void onCustomerItemClick(int value);
    }

    ChooseCustomerView[] chooseCustomerViews;
    int[] resuorce;
    String[] startHead;
    String[] start;
    String[] destinationHead;
    String[] destination;
    String[] customer;
    String phone;
    String[] rate;

    public ChooseCustomerFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ChooseCustomerFragment newInstance() {
        ChooseCustomerFragment fragment = new ChooseCustomerFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_choose_customer, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        // set value of ChooseCustomerView
        resuorce = new int[]{R.id.chooseCustomerView1, R.id.chooseCustomerView2, R.id.chooseCustomerView3, R.id.chooseCustomerView4};
        startHead = getContext().getResources().getStringArray(R.array.CustomerStartHead);
        start = getContext().getResources().getStringArray(R.array.CustomerStart);
        destinationHead = getContext().getResources().getStringArray(R.array.CustomerDestinationHead);
        destination = getContext().getResources().getStringArray(R.array.CustomerDestination);
        customer = new String[]{"Frank White","Tina Martin","Justin Long","Anna Stewart"};
        phone = "+661234XXXX";
        rate = new String[]{"5.0","4.5","4.5","3.5"};
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        // set ChooseCustomerView
        chooseCustomerViews = new ChooseCustomerView[4];
        for(int i=0;i<4;i++) {
            chooseCustomerViews[i] = (ChooseCustomerView) rootView.findViewById(resuorce[i]);
            // set ChooseCustomerView value
            chooseCustomerViews[i].setTextTvStartHead(startHead[i]);
            chooseCustomerViews[i].setTextTvStart(start[i]);
            chooseCustomerViews[i].setTextTvDestinationHead(destinationHead[i]);
            chooseCustomerViews[i].setTextTvDestination(destination[i]);
            chooseCustomerViews[i].setTextTvCustomerName(customer[i]);
            chooseCustomerViews[i].setTextTvPhone(phone);
            chooseCustomerViews[i].setTextTvCustomerRate(rate[i]);
            // set on click ChooseCustomerViews
            chooseCustomerViews[i].setOnClickListener(onClickListener);
        }
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

    /***********
     * Listener
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int value=-1;
            if(view == chooseCustomerViews[0]){
                value=0;
            }
            if(view == chooseCustomerViews[1]){
                value=1;
            }
            if(view == chooseCustomerViews[2]){
                value=2;
            }
            if(view == chooseCustomerViews[3]){
                value=3;
            }
            ChooseCustomerFragmentListener fragmentListener = (ChooseCustomerFragmentListener)getActivity();
            fragmentListener.onCustomerItemClick(value);
        }
    };

}
