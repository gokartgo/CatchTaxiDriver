package s8010027.kritchanon.catchtaxidriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.adapter.HistoryAdapter;
import s8010027.kritchanon.catchtaxidriver.manager.HistoryData;


@SuppressWarnings("unused")
public class HistoryFragment extends Fragment {

    ListView listView;
    HistoryAdapter historyAdapter;
    ArrayList<HistoryData> historyData = new ArrayList<HistoryData>();

    public HistoryFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        String[] name = {"Rose Cruz","Andres Horton","Felix Pierce","Jesse Banks"};
        String[] from = getContext().getResources().getStringArray(R.array.From);
        String[] to = getContext().getResources().getStringArray(R.array.To);
        String[] pay = {"310","292","276","334"};
        String[] rate = {"4.0","4.5","5.0","3.5"};
        for(int i=0;i<4;i++) {
            historyData.add(new HistoryData(name[i], from[i], to[i], pay[i], rate[i]));
        }
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listView);
        historyAdapter = new HistoryAdapter(historyData);
        listView.setAdapter(historyAdapter);
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
