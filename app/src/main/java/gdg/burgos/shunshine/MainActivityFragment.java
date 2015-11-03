package gdg.burgos.shunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import gdg.burgos.shunshine.adapter.ForecastListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ForecastListAdapter forecastAdapter;
    private int forecastItemLayoutID;
    private int forecasTextViewID;
    private List<String> forecastData;
    private ListView forecastListView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] forecastArray = getResources().getStringArray(R.array.forecast_list);

        forecastItemLayoutID =R.layout.list_item_forecast;
        forecasTextViewID = R.id.list_item_forecast_textview;
        forecastData = Arrays.asList(forecastArray);

        forecastAdapter= new ForecastListAdapter(getContext(), forecastItemLayoutID,forecasTextViewID,forecastData);

        forecastListView =(ListView) mView.findViewById(R.id.listview_forecast);

        forecastListView.setAdapter(forecastAdapter);

        return mView;
    }
}
