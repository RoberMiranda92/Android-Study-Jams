package gdg.burgos.shunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import gdg.burgos.shunshine.adapter.ForecastListAdapter;
import gdg.burgos.shunshine.tasks.FetchWeatherTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    private ForecastListAdapter forecastAdapter;
    private int forecastItemLayoutID;
    private int forecasTextViewID;
    private ArrayList<String> forecastData;
    private ListView forecastListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);


        setHasOptionsMenu(true);
        /**
         String[] forecastArray = getResources().getStringArray(R.array.forecast_list);
         forecastData = new ArrayList(Arrays.asList(forecastArray));*/

        forecastItemLayoutID = R.layout.list_item_forecast;
        forecasTextViewID = R.id.list_item_forecast_textview;
        forecastData = new ArrayList<String>();

        forecastAdapter = new ForecastListAdapter(getContext(), forecastItemLayoutID, forecasTextViewID, forecastData);

        forecastListView = (ListView) mView.findViewById(R.id.listview_forecast);

        forecastListView.setAdapter(forecastAdapter);


        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            FetchWeatherTask task = new FetchWeatherTask(this);
            task.execute("09001");
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void addData(String[] result) {
        if (result != null) {
            forecastAdapter.clear();
            for (String dayForecast : result)

                forecastAdapter.add(dayForecast);
        } else
            Toast.makeText(getContext(), "Error Obteniendo Datos", Toast.LENGTH_SHORT).show();
    }
}
