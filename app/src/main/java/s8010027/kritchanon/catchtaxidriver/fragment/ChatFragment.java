package s8010027.kritchanon.catchtaxidriver.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import s8010027.kritchanon.catchtaxidriver.R;


@SuppressWarnings("unused")
public class ChatFragment extends Fragment {

    public interface FragmentListener {
        void onButtonHomeClick();
        void onCallClick();
    }

    Toolbar toolbar;
    ImageButton btnCall;

    public ChatFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Chat");

        btnCall = (ImageButton)rootView.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(ImageButtonClick);
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
            FragmentListener listener = (FragmentListener) getActivity();
            listener.onButtonHomeClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /***********
     * listener zone
     */

    final View.OnClickListener ImageButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnCall){
                FragmentListener listener = (FragmentListener) getActivity();
                listener.onCallClick();
            }
        }
    };
}
