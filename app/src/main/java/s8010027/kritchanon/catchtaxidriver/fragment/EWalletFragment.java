package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.view.CashbackEWalletView;


@SuppressWarnings("unused")
public class EWalletFragment extends Fragment {

    CashbackEWalletView customViewCashback;
    Button btnAccount;
    Button btnHistory;
    int sumMoney = 0;
    Bundle bundle;

    public EWalletFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static EWalletFragment newInstance(Bundle bundle) {
        EWalletFragment fragment = new EWalletFragment();
        Bundle args = new Bundle();
        args.putBundle("bundle",bundle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        bundle = new Bundle();
        bundle = getArguments().getBundle("bundle");
        sumMoney = bundle.getInt("sumMoney");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_e_wallet, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        customViewCashback = new CashbackEWalletView(getContext());
        customViewCashback = (CashbackEWalletView) rootView.findViewById(R.id.customViewCashback);
        customViewCashback.setImageIvWallet(R.drawable.money);
        customViewCashback.setTextTvWallet("E-wallet");
        customViewCashback.setTextTvMoney(sumMoney+".00");
        btnAccount = (Button) rootView.findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(btnClick);
        btnHistory = (Button) rootView.findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(btnClick);
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
     * listener zone
     */
    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnAccount){
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer,TransferFragment.newInstance(bundle),"TransferFragment")
                        .addToBackStack(null)
                        .commit();
            }
            if(view == btnHistory){
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer,TransactionHistoryFragment.newInstance(),"TransactionHistoryFragment")
                        .addToBackStack(null)
                        .commit();
            }
        }
    };
}
