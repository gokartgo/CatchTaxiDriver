package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.activity.SettingsActivity;
import s8010027.kritchanon.catchtaxidriver.manager.UserData;


@SuppressWarnings("unused")
public class ProfileFragment extends Fragment {

    public interface SaveFragmentListener{
        void onSaveItemClick();
    }

    Button btnSave;
    EditText edMobile;
    EditText edName;
    EditText edEmail;
    EditText edTaxiId;
    TextView tvMobile;
    TextView tvName;
    TextView tvEmail;
    TextView tvTaxiId;


    public ProfileFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        // call activity to set title action bar
        ((SettingsActivity) getActivity()).setActionBarTitle(R.string.profile_actionbar);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnSave = (Button) rootView.findViewById(R.id.btnSave);
        edMobile = (EditText)rootView.findViewById(R.id.edMobile);
        edName = (EditText)rootView.findViewById(R.id.edName);
        edEmail = (EditText)rootView.findViewById(R.id.edEmail);
        edTaxiId = (EditText)rootView.findViewById(R.id.edTextId);
        tvMobile = (TextView)rootView.findViewById(R.id.tvMobile);
        tvName = (TextView)rootView.findViewById(R.id.tvName);
        tvEmail = (TextView)rootView.findViewById(R.id.tvEmail);
        tvTaxiId = (TextView)rootView.findViewById(R.id.tvTaxiId);
        // set text from user data
        edMobile.setText(UserData.getInstance().getMobile());
        edName.setText(UserData.getInstance().getMobile());
        edEmail.setText(UserData.getInstance().getEmail());
        edTaxiId.setText(UserData.getInstance().getTaxiId());
        btnSave.setOnClickListener(btnClick);
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

    /*************
     * Listener zone
     */

    View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnSave){
                checkEditProfile();
                if(!edMobile.getText().toString().trim().equals("") && !edName.getText().toString().trim().equals("") &&
                        !edEmail.getText().toString().trim().equals("") && !edTaxiId.getText().toString().trim().equals("")) {
                    saveDataUser();
                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                    SaveFragmentListener saveFragmentListener = (SaveFragmentListener) getActivity();
                    saveFragmentListener.onSaveItemClick();
                }
                else{
                    Toast.makeText(getContext(), "Please fill up this form", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void checkEditProfile() {
        if(!edMobile.getText().toString().trim().equals("")){
            tvMobile.setVisibility(View.INVISIBLE);
        }
        if(edMobile.getText().toString().trim().equals("")){
            tvMobile.setVisibility(View.VISIBLE);
        }
        if(!edName.getText().toString().trim().equals("")){
            tvName.setVisibility(View.INVISIBLE);
        }
        if(edName.getText().toString().trim().equals("")){
            tvName.setVisibility(View.VISIBLE);
        }
        if(!edEmail.getText().toString().trim().equals("")){
            tvEmail.setVisibility(View.INVISIBLE);
        }
        if(edEmail.getText().toString().trim().equals("")){
            tvEmail.setVisibility(View.VISIBLE);
        }
        if(!edTaxiId.getText().toString().trim().equals("")){
            tvTaxiId.setVisibility(View.INVISIBLE);
        }
        if(edTaxiId.getText().toString().trim().equals("")){
            tvTaxiId.setVisibility(View.VISIBLE);
        }
    }

    private void saveDataUser() {
        UserData.getInstance().setEmail(edEmail.getText().toString());
        UserData.getInstance().setMobile(edMobile.getText().toString());
        UserData.getInstance().setName(edName.getText().toString());
        UserData.getInstance().setTaxiId(edTaxiId.getText().toString());
    }
}
