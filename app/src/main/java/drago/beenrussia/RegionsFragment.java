package drago.beenrussia;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_REGION_LIST = "region_list";

    // TODO: Rename and change types of parameters
    private ArrayList<Region> regionParam;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param regions global RegionList.
     * @return A new instance of fragment RegionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegionsFragment newInstance(ArrayList<Region> regions) {
        RegionsFragment fragment = new RegionsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_REGION_LIST, regions);
        fragment.setArguments(args);
        return fragment;
    }

    public RegionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            regionParam = (ArrayList<Region>) arguments.getSerializable(ARG_REGION_LIST);
        }

        Log.i("MainActivity", "RegionsFragment.onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("MainActivity", "RegionsFragment.onCreateView");
        View view = inflater.inflate(R.layout.fragment_regions, container, false);
        MainActivity activity = (MainActivity) getActivity();
        ListView listView = (ListView) view.findViewById(R.id.regionList);
        listView.setAdapter(activity.regionAdapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Region country = (Region) parent.getItemAtPosition(position);
                Toast.makeText(getActivity().getBaseContext().getApplicationContext(),
                        "Clicked on Row: " + country.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });*/
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
