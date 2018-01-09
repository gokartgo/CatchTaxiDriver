package s8010027.kritchanon.catchtaxidriver.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.manager.UserData;


@SuppressWarnings("unused")
public class SignUpFragment extends Fragment {

    public interface FragmentListener {
        void onButtonSignUpClick();
    }

    Toolbar toolbar;
    EditText edMobile;
    EditText edName;
    EditText edEmail;
    EditText edTaxiId;
    TextView tvMobile;
    TextView tvName;
    TextView tvEmail;
    TextView tvTaxiId;
    Button btnNext;
    FragmentListener listener;
    int check = 0;


    public SignUpFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // set tool bar
        toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        // set up button
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        edMobile = (EditText)rootView.findViewById(R.id.edMobile);
        edName = (EditText)rootView.findViewById(R.id.edName);
        edEmail = (EditText)rootView.findViewById(R.id.edEmail);
        edTaxiId = (EditText)rootView.findViewById(R.id.edTextId);
        tvMobile = (TextView)rootView.findViewById(R.id.tvMobile);
        tvName = (TextView)rootView.findViewById(R.id.tvName);
        tvEmail = (TextView)rootView.findViewById(R.id.tvEmail);
        tvTaxiId = (TextView)rootView.findViewById(R.id.tvTaxiId);
        btnNext = (Button)rootView.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(btnClick);

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
     * up button onclick
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            listener = (FragmentListener) getActivity();
            listener.onButtonSignUpClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /***************
     * Listener Zone
     ***************/

    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnNext){
                if(!edMobile.getText().toString().trim().equals("")){
                    tvMobile.setVisibility(View.INVISIBLE);
                    check++;
                }
                if(edMobile.getText().toString().trim().equals("")){
                    tvMobile.setVisibility(View.VISIBLE);
                }
                if(!edName.getText().toString().trim().equals("")){
                    tvName.setVisibility(View.INVISIBLE);
                    check++;
                }
                if(edName.getText().toString().trim().equals("")){
                    tvName.setVisibility(View.VISIBLE);
                }
                if(!edEmail.getText().toString().trim().equals("")){
                    tvEmail.setVisibility(View.INVISIBLE);
                    check++;
                }
                if(edEmail.getText().toString().trim().equals("")){
                    tvEmail.setVisibility(View.VISIBLE);
                }
                if(!edTaxiId.getText().toString().trim().equals("")){
                    tvTaxiId.setVisibility(View.INVISIBLE);
                    check++;
                }
                if(edTaxiId.getText().toString().trim().equals("")){
                    tvTaxiId.setVisibility(View.VISIBLE);
                }
                if(check == 4){
                    setDataUser();getFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer,NumberDigitFragment.newInstance(),"NumberDigitFragment")
                            .addToBackStack(null)
                            .commit();
                }
                if(check != 4){
                    check = 0;
                }
            }
        }
    };

    private void setDataUser() {
        UserData.getInstance().setEmail(edEmail.getText().toString());
        UserData.getInstance().setMobile(edMobile.getText().toString());
        UserData.getInstance().setName(edName.getText().toString());
        UserData.getInstance().setTaxiId(edTaxiId.getText().toString());
    }
}
