package drago.beenrussia;

import android.Manifest;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends Activity {
    // Состояние пограммы, добавляем или просматриваем регионы
    public int FragmentState;

    public static final byte STATE_MAIN = 0;
    public static final byte STATE_ADD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
            FragmentState = STATE_MAIN;
        }
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
                switchFragment(item);
                return true;
            case R.id.action_add:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchFragment(MenuItem item){
        if (FragmentState == STATE_MAIN){
            item.setIcon(R.drawable.ic_action_accept);
        } else {
            item.setIcon(R.drawable.ic_action_new);
        }


    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.cbUpItem:
                TextView textView = (TextView) findViewById(R.id.txtUpItem);
                textView.setTextColor(checked ? Color.BLUE : Color.WHITE);
                break;
            case R.id.cbDownItem:
                TextView textViewDown = (TextView) findViewById(R.id.txtDown);
                textViewDown.setTextColor(checked ? Color.BLUE : Color.WHITE);
                break;
        }

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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
