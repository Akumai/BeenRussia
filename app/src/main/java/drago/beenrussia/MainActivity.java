package drago.beenrussia;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.TypedArrayUtils;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends FragmentActivity implements RegionsFragment.OnFragmentInteractionListener , RegionsViewFragment.OnFragmentInteractionListener  {
    // Состояние программы, добавляем или просматриваем регионы
    public int FragmentState;

    public static final byte STATE_MAIN = 0;
    public static final byte STATE_ADD = 1;
    public static final String DELIMITER = ",";

    public static ArrayList<Region> regions;
    public static ArrayList<Region> regionsChecked;
    public RegionArrayAdapter regionAdapter = null;
    public RegionsViewAdapter regionsViewAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new RegionsViewFragment())
                    .commit();
            FragmentState = STATE_MAIN;
        }
        regions = getRegionsList();
        regionsChecked = new ArrayList<>();
        updateRegionsCheckedList();
        regionAdapter = new RegionArrayAdapter(this, R.layout.regions_row, regions);
        regionsViewAdapter = new RegionsViewAdapter(this, R.layout.region_text, regionsChecked);
    }

    public void updateRegionsCheckedList() {
        regionsChecked.clear();
        for (int i =0; i< regions.size();i++){
            Region cur = regions.get(i);
            if (cur.isSelected()){
                regionsChecked.add(cur);
            }
        }
    }

    // Checked regions to string with separator
    public String getCheckedRegionsString() {
        int regionsCount = regionsChecked.size();
        String[] codes = new String[regionsCount];
        for (int i =0; i< regionsCount;i++){
            codes[i] = regionsChecked.get(i).getCode();
        }
        return TextUtils.join(DELIMITER, codes);
    }

    private ArrayList<Region> getRegionsList() {
        ArrayList<Region> regionsList = new ArrayList<>();
        Resources res = getResources();
        String[] regionNames = res.getStringArray(R.array.region_name_titles);
        String[] regionValues = res.getStringArray(R.array.region_name_values);

        // Load data from DB
        List<String> savedRegions = Arrays.asList(TextUtils.split(getRegionsFromDB(), DELIMITER));


        for (int i = 0; i < regionNames.length; i++){
            regionsList.add(new Region(regionNames[i], regionValues[i], savedRegions.indexOf(regionValues[i]) >= 0));
        }
        return regionsList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_share:
                Toast.makeText(getApplicationContext(), "Share to FB", Toast.LENGTH_SHORT);
                return true;
            case R.id.action_add:
                switchFragment(item);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchFragment(MenuItem item){
        if (FragmentState == STATE_MAIN){
            DrawNewFragment(item);
            FragmentState = STATE_ADD;
        } else {
            updateRegionsCheckedList();
            DrawMainFragment(item);
            FragmentState = STATE_MAIN;
        }
    }

    // Главный экран, на котором изображены области
    private void DrawMainFragment(MenuItem item) {
        item.setIcon(R.drawable.ic_action_new);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new RegionsViewFragment())
                .addToBackStack(null)
                .commit();
    }

    // Экран со списком регионов
    private void DrawNewFragment(MenuItem item) {
        item.setIcon(R.drawable.ic_action_accept);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new RegionsFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first

        saveRegionsToDB();
    }

    // Save regions list to shared preferences
    private void saveRegionsToDB() {
        getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putString(getString(R.string.preferences_saved_regions), getCheckedRegionsString())
                .apply();
    }

    // Save regions list to shared preferences
    private String getRegionsFromDB() {
        return getPreferences(Context.MODE_PRIVATE)
                .getString(getString(R.string.preferences_saved_regions), "");
    }

}
