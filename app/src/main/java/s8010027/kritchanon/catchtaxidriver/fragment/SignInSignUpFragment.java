package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rd.PageIndicatorView;

import s8010027.kritchanon.catchtaxidriver.R;


@SuppressWarnings("unused")
public class SignInSignUpFragment extends Fragment {

    public SignInSignUpFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SignInSignUpFragment newInstance() {
        SignInSignUpFragment fragment = new SignInSignUpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_sign_in_sign_up, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        // set PageIndicatorView
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return SignInSignUpFristFragment.newInstance();
                    case 1:
                        return SignInSignUpSecondFragment.newInstance();
                    case 2:
                        return SignInSignUpThirdFragment.newInstance();
                    case 3:
                        return SignInSignUpFouthFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        PageIndicatorView pageIndicatorView = (PageIndicatorView) rootView.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(viewPager);
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

}
