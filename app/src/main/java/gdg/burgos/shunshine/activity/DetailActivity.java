package gdg.burgos.shunshine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gdg.burgos.shunshine.R;

public class DetailActivity extends AppCompatActivity {

    private static String weatherInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_detail, new PlaceholderFragment())
                    .commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(DetailActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.menu_item_share) {
            share();
        }

        return super.onOptionsItemSelected(item);
    }

    public void share() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, weatherInformation);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Intent intent = getActivity().getIntent();

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                TextView detail_text = (TextView) rootView.findViewById(R.id.detail_text);
                detail_text.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
                weatherInformation = intent.getStringExtra(Intent.EXTRA_TEXT);
            }
            return rootView;
        }


    }

}