package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.view.ImageTextButtonView;


@SuppressWarnings("unused")
public class SignInFragment extends Fragment {

    public interface FragmentListener{
        void onButtonSignInClick();
    }

    ImageTextButtonView btnSignInWithDevice;
    ImageTextButtonView btnSignInWithGoogle;
    Button btnSignInDevice;
    Button btnSignInGoogle;

    public SignInFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        btnSignInWithDevice = (ImageTextButtonView) rootView.findViewById(R.id.btnSignInWithDevice);
        btnSignInWithGoogle = (ImageTextButtonView) rootView.findViewById(R.id.btnSignInWithGoogle);
        setButtonSignIn();

    }

    private void setButtonSignIn() {
        // set value btnSignWithDevice
        btnSignInWithDevice.setRelativeImageTextButton(R.drawable.sign_in_button);
        btnSignInWithDevice.setColorTvImageTextButton(0xffffffff);
        btnSignInWithDevice.setIvImageTextButton(R.drawable.icon_mobile);
        btnSignInWithDevice.setTvImageTextButton("Sign in with Device");
        // set value btnSignInWithGoogle
        btnSignInWithGoogle.setRelativeImageTextButton(R.drawable.sign_up_button);
        btnSignInWithGoogle.setIvImageTextButton(R.drawable.icon_google);
        btnSignInWithGoogle.setTvImageTextButton("Sign in with Google");

        btnSignInWithDevice.setOnClickListener(btnClick);
        btnSignInWithGoogle.setOnClickListener(btnClick);
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
     * Listener zone
     */

    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(btnSignInWithDevice == view){
                FragmentListener listener = (FragmentListener) getActivity();
                listener.onButtonSignInClick();
            }
            if(btnSignInWithGoogle == view){
                FragmentListener listener = (FragmentListener) getActivity();
                listener.onButtonSignInClick();
            }
        }
    };

}
