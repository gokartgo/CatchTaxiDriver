package s8010027.kritchanon.catchtaxidriver.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;


@SuppressWarnings("unused")
public class SignUpFragment extends Fragment {

    public interface FragmentListener{
        void onButtonSignUpClick();
    }

    EditText edName, edSurname, edEmail, edStudentId, edMobile, edUsername, edPassword, edRePassword;
    RadioGroup rgSex;
    Button btnSignUpOk;
    SQLiteDatabase database;
    Cursor cursor;

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
        edName = (EditText) rootView.findViewById(R.id.edName);
        edSurname = (EditText) rootView.findViewById(R.id.edSurname);
        edEmail = (EditText) rootView.findViewById(R.id.edEmail);
        edStudentId = (EditText) rootView.findViewById(R.id.edStudentId);
        edMobile = (EditText) rootView.findViewById(R.id.edMobile);
        edUsername = (EditText) rootView.findViewById(R.id.edUsername);
        edPassword = (EditText) rootView.findViewById(R.id.edPassword);
        edRePassword = (EditText) rootView.findViewById(R.id.edRePassword);
        rgSex = (RadioGroup) rootView.findViewById(R.id.rgSex);
        btnSignUpOk = (Button) rootView.findViewById(R.id.btnSignUpOk);
        btnSignUpOk.setOnClickListener(buttonClickSignUp);
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

    /***************
     * Listener Zone
     ***************/

    final View.OnClickListener buttonClickSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnSignUpOk) {
                // button to SignUp
                if (edName.length() > 0 && edSurname.length() > 0
                        && edEmail.length() > 0 && edStudentId.length() > 0
                        && edMobile.length() > 0 && edUsername.length() > 0
                        && edPassword.length() > 0 && edRePassword.length() > 0) {
                    if (edPassword.getText().toString().equals(edRePassword.getText().toString())) {
                        Toast.makeText(getContext(), "SignUp Complete", Toast.LENGTH_SHORT).show();
                        //sent value to activity
                        FragmentListener listener = (FragmentListener) getActivity();
                        listener.onButtonSignUpClick();
                    } else {
                        Toast.makeText(getContext(), "Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please Input Data", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
