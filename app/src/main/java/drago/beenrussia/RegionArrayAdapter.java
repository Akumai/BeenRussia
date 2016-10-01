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
public class RegionArrayAdapter extends ArrayAdapter<Region> {
    private ArrayList<Region> regionList;

    public RegionArrayAdapter(Context context, int textViewResourceId,
                           ArrayList<Region> regionList) {
        super(context, textViewResourceId, regionList);
        this.regionList = new ArrayList<>();
        this.regionList.addAll(regionList);
    }

    private class ViewHolder {
        TextView regionName;
        CheckBox checkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (BuildConfig.DEBUG){
            Log.v("ConvertView", String.valueOf(position));
        }

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            //TODO: попробовать parent
            convertView = vi.inflate(R.layout.regions_row, null);

            holder = new ViewHolder();
            holder.regionName = (TextView) convertView.findViewById(R.id.regionName);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

            convertView.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    ViewHolder tv = (ViewHolder) v.getTag();
                    tv.checkBox.performClick();
                    Region region = (Region) tv.regionName.getTag();
                    Toast.makeText(getContext().getApplicationContext(),
                            "Clicked on Checkbox: " + tv.regionName.getText() +
                                    " is " + tv.checkBox.isChecked(),
                            Toast.LENGTH_LONG).show();
                    region.setSelected(tv.checkBox.isChecked());
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Region region = regionList.get(position);
        holder.regionName.setText(region.getName());
        holder.checkBox.setChecked(region.isSelected());
        holder.regionName.setTag(region);
        holder.checkBox.setTag(region);

        return convertView;
    }
}
