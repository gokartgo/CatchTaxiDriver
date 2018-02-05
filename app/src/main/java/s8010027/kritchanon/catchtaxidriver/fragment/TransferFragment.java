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
import android.widget.EditText;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.view.TransferTopUpView;


@SuppressWarnings("unused")
public class TransferFragment extends Fragment {

    TransferTopUpView customViewTopUp;
    EditText edMoney;
    Button btnOk;
    float moneyFrom;
    int moneySent;
    Dialog dialogFinish;
    Bundle bundle;
    int sumMoney;
    // dialog confirm less than 25 bath
    Dialog dialogConfirm;
    Button btnDialogConfirm, btnDialogCancel;


    public TransferFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TransferFragment newInstance(Bundle bundle) {
        TransferFragment fragment = new TransferFragment();
        Bundle args = new Bundle();
        args.putBundle("bundle", bundle);
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
        View rootView = inflater.inflate(R.layout.fragment_transfer, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        customViewTopUp = new TransferTopUpView(getContext());
        customViewTopUp = rootView.findViewById(R.id.customViewTopUp);

        //set customeViewTopUp value
        customViewTopUp.setImageIvFrom(R.drawable.money);
        customViewTopUp.setImageIvTo(R.drawable.bank);
        customViewTopUp.setTextTvFrom("E-wallet");
        customViewTopUp.setTextTvTo("Banking");
        customViewTopUp.setTextTvMoneyFrom(sumMoney + ".00");
        customViewTopUp.setTextTvMoneyTo("123-456XXX-X");

        moneyFrom = Float.parseFloat(customViewTopUp.getTextTvMoneyFrom());
        edMoney = (EditText) rootView.findViewById(R.id.edMoney);
        btnOk = (Button) rootView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(btnClick);
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

    /**********
     * listener zone
     */

    final View.OnClickListener btnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (view == btnOk) {
                if (edMoney.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please Input Money", Toast.LENGTH_SHORT).show();
                } else {
                    moneySent = Integer.parseInt(edMoney.getText().toString());
                    moneyFrom = moneyFrom - moneySent;
                    if (moneyFrom < 0) {
                        moneyFrom = moneyFrom + moneySent;
                        Toast.makeText(getContext(), "Money you not enough", Toast.LENGTH_SHORT).show();
                    } else if (moneyFrom < 25) {
                        moneyFrom = moneyFrom + moneySent;
                        dialogConfirm = new Dialog(getContext(), R.style.Theme_Dialog);
                        dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogConfirm.setContentView(R.layout.dialog_transfer_less_25);
                        dialogConfirm.setCancelable(true);
                        dialogConfirm.setCanceledOnTouchOutside(true);
                        btnDialogConfirm = (Button) dialogConfirm.findViewById(R.id.btnDialogConfirm);
                        btnDialogConfirm.setOnClickListener(btnDialogClick);
                        btnDialogCancel = (Button) dialogConfirm.findViewById(R.id.btnDialogCancel);
                        btnDialogCancel.setOnClickListener(btnDialogClick);
                        dialogConfirm.show();
                    } else {
                        customViewTopUp.setTextTvMoneyFrom(moneyFrom + "0");
                        edMoney.setText("");
                        showDialogFinish();
                    }
                }
            }
        }
    };

    private void showDialogFinish() {
        dialogFinish = new Dialog(getContext());
        dialogFinish.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogFinish.setContentView(R.layout.dialog_arrived_finish);
        dialogFinish.setCancelable(true);
        dialogFinish.setCanceledOnTouchOutside(true);
        dialogFinish.show();
    }

    /*********
     * dialog on click
     */

    final View.OnClickListener btnDialogClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnDialogConfirm) {
                moneyFrom = moneyFrom - moneySent;
                customViewTopUp.setTextTvMoneyFrom(moneyFrom + "0");
                edMoney.setText("");
                dialogConfirm.cancel();
                showDialogFinish();
            }
            if (view == btnDialogCancel) {
                dialogConfirm.cancel();
            }
        }
    };
}
