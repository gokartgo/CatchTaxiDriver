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
        String[] name = {"Rose ****","Andres ****","Felix ****","Jesse ****"};
        String[] date = {"13 Sep 2017","26 Sep 2017","05 Dec 2017","07 Jan 2018"};
        String[] time = {"08 : 27","12 : 35","07 : 45","19 : 33"};
        String[] from = getContext().getResources().getStringArray(R.array.From);
        String[] to = getContext().getResources().getStringArray(R.array.To);
        String[] pay = {"310","292","276","334"};
        int[] rateStar = {R.drawable.star_4,R.drawable.star_4_half,R.drawable.star_5,R.drawable.star_3_half};
        for(int i=0;i<4;i++) {
            historyData.add(new HistoryData(name[i],date[i],time[i], from[i], to[i], pay[i],rateStar[i]));
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
