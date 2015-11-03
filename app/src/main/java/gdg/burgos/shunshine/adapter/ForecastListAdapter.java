package gdg.burgos.shunshine.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.List;

/**
 * Created by RobertoMiranda on 3/11/15.
 */
public class ForecastListAdapter extends ArrayAdapter<String> {


    public ForecastListAdapter(Context context, int id_itemLayout, int id_textView,List<String> data) {
        super(context,id_itemLayout,id_textView,data);
    }
}
