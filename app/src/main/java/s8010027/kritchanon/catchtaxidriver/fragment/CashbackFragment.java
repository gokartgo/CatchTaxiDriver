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

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.view.CashbackEWalletView;


@SuppressWarnings("unused")
public class CashbackFragment extends Fragment {

    CashbackEWalletView customViewCashback;
    Button btnAccount;
    Button btnPin;
    int credit = 0;
    int money = 0;
    Bundle bundle;
    // dialog zone
    Dialog dialog;
    Button btnConfirm;
    Button btnCancel;

    public CashbackFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CashbackFragment newInstance(Bundle bundle) {
        CashbackFragment fragment = new CashbackFragment();
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
        credit = bundle.getInt("credit");


        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cash_back, container, false);
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
        customViewCashback.setImageIvWallet(R.drawable.credit);
        customViewCashback.setTextTvWallet("Cashback");
        customViewCashback.setTextTvMoney(credit+".00");
        btnAccount = (Button) rootView.findViewById(R.id.btnAccount);
        btnPin = (Button) rootView.findViewById(R.id.btnPin);
        btnAccount.setOnClickListener(btnClick);
        btnPin.setOnClickListener(btnClick);
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
                        .replace(R.id.contentContainer,TopUpFragment.newInstance(bundle),"TopUpFragment")
                        .addToBackStack(null)
                        .commit();
            }
            if(view == btnPin){
                showDialogFinish();
                btnConfirm = (Button) dialog.findViewById(R.id.btnConfirm);
                btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                btnConfirm.setOnClickListener(btnDialogClick);
                btnCancel.setOnClickListener(btnDialogClick);
            }
        }
    };

    final View.OnClickListener btnDialogClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnConfirm){
                dialog.cancel();
            }
            if(view == btnCancel){
                dialog.cancel();
            }
        }
    };

    private void showDialogFinish() {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_top_up_pin);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
