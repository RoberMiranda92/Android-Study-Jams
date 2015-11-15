package gdg.burgos.shunshine.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import gdg.burgos.shunshine.R;
import gdg.burgos.shunshine.activity.DetailActivity;
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
    FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);

        fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swiperefresh);

        setHasOptionsMenu(true);
        /**
         String[] forecastArray = getResources().getStringArray(R.array.forecast_list);
         forecastData = new ArrayList(Arrays.asList(forecastArray));*/

        forecastItemLayoutID = R.layout.list_item_forecast;
        forecasTextViewID = R.id.list_item_forecast_textview;
        forecastData = new ArrayList<>();

        forecastAdapter = new ForecastListAdapter(getContext(), forecastItemLayoutID, forecasTextViewID, forecastData);

        forecastListView = (ListView) mView.findViewById(R.id.listview_forecast);

        forecastListView.setAdapter(forecastAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeather();
            }
        });


        forecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String foreCastItem = forecastAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, foreCastItem);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWeather();
                    }
                }, 700);

            }
        });

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
            getWeather();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onStart() {
        super.onStart();
        getWeather();
    }

    private void getWeather() {

        FetchWeatherTask task = new FetchWeatherTask(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = preferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        task.execute(location);
    }

    public void addData(String[] result) {

        swipeRefreshLayout.setRefreshing(false);
        if (result != null) {
            forecastAdapter.clear();
            for (String dayForecast : result)

                forecastAdapter.add(dayForecast);
        } else
            Toast.makeText(getContext(), "Error Obteniendo Datos", Toast.LENGTH_SHORT).show();
    }
}
