package drago.beenrussia;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by drago on 04.04.15.
 */
public class RegionsViewAdapter extends ArrayAdapter<Region> {
    private ArrayList<Region> regionList;

    public RegionsViewAdapter (Context context, int textViewResourceId,
                              ArrayList<Region> regionList) {
        super(context, textViewResourceId, regionList);
        this.regionList = regionList;
    }

    private class ViewHolder {
        TextView regionName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.region_text, null);

            holder = new ViewHolder();
            holder.regionName = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);

            convertView.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    ViewHolder tv = (ViewHolder) v.getTag();
                    //Region region = (Region) tv.regionName.getTag();
                    Toast.makeText(getContext().getApplicationContext(),
                            "Clicked on Region: " + tv.regionName.getText(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (!regionList.isEmpty()) {
            Region region = regionList.get(position);
            holder.regionName.setText(region.getName());
            holder.regionName.setTag(region);
        }
        return convertView;
    }
}
