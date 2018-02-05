package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.view.WalletView;


@SuppressWarnings("unused")
public class WalletFragment extends Fragment {

    // define value for walletViewCredit
    WalletView walletViewCredit;
    int credit = 250;

    // define value for walletViewCash
    WalletView walletViewCash;
    int money = 0;
    int sumMoney = 1900;

    // sent value by bundle
    Bundle bundle;

    public WalletFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static WalletFragment newInstance(int money) {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        args.putInt("money",money);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        money = getArguments().getInt("money");
        sumMoney = sumMoney+money;

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wallet, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        walletViewCredit = (WalletView) rootView.findViewById(R.id.walletViewCredit);
        walletViewCredit.setImageIvCatchPay(R.drawable.credit);
        walletViewCredit.setTextTvCash("Cashback");
        walletViewCredit.setTextTvMoney(credit+".00");
        walletViewCash = (WalletView) rootView.findViewById(R.id.walletViewCash);
        walletViewCash.setImageIvCatchPay(R.drawable.money);
        walletViewCash.setTextTvCash("E-Wallet");
        walletViewCash.setTextTvMoney(sumMoney+".00");
        walletViewCredit.setOnClickListener(btnClick);
        walletViewCash.setOnClickListener(btnClick);
    }

    @Override
    public void onStart() { super.onStart(); }

    @Override
    public void onStop() { super.onStop(); }

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

    /**********
     * listener zone
     */
    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            putValueToBundle();
            if(view == walletViewCredit){
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer,CashbackFragment.newInstance(bundle),"CashbackFragment")
                        .addToBackStack(null)
                        .commit();
            }
            if(view == walletViewCash){
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer,EWalletFragment.newInstance(bundle),"EWalletFragment")
                        .addToBackStack(null)
                        .commit();
            }
        }
    };

    private void putValueToBundle() {
        bundle = new Bundle();
        bundle.putInt("money",money);
        bundle.putInt("credit",credit);
        bundle.putInt("sumMoney",sumMoney);
    }
}
