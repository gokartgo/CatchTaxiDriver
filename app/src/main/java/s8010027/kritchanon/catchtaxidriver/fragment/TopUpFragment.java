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
public class TopUpFragment extends Fragment {

    TransferTopUpView customViewTopUp;
    EditText edMoney;
    Button btnOk;
    float moneyFrom;
    int moneySent;
    float moneyTo;
    Dialog dialogFinish;
    Bundle bundle;
    int money;
    int credit;
    int sumMoney;

    public TopUpFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TopUpFragment newInstance(Bundle bundle) {
        TopUpFragment fragment = new TopUpFragment();
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
        money = bundle.getInt("money");
        credit = bundle.getInt("credit");
        sumMoney = bundle.getInt("sumMoney");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_up, container, false);
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
        customViewTopUp.setTextTvMoneyFrom(credit+".00");
        customViewTopUp.setTextTvMoneyTo(sumMoney+".00");
        moneyFrom = Float.parseFloat(customViewTopUp.getTextTvMoneyFrom());
        moneyTo = Float.parseFloat(customViewTopUp.getTextTvMoneyTo());
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

    final View.OnClickListener btnClick = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(view == btnOk){
                if(edMoney.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Input Money",Toast.LENGTH_SHORT).show();
                }
                else {
                    moneySent = Integer.parseInt(edMoney.getText().toString());
                    moneyFrom = moneyFrom - moneySent;
                    moneyTo = moneyTo + moneySent;
                    if(moneyFrom<0){
                        moneyFrom = moneyFrom+moneySent;
                        moneyTo = moneyTo-moneySent;
                        Toast.makeText(getContext(),"Money you not enough",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        customViewTopUp.setTextTvMoneyFrom(moneyFrom+"0");
                        customViewTopUp.setTextTvMoneyTo(moneyTo+"0");
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
}
