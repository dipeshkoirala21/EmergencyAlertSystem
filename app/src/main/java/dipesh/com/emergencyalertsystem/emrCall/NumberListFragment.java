package dipesh.com.emergencyalertsystem.emrCall;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dipesh.com.emergencyalertsystem.R;
import dipesh.com.emergencyalertsystem.emrCall.model.CallLog;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NumberListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumberListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumberListFragment extends Fragment implements AdapterCallback{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RecyclerView recyclerView;
    View view;
    List<Number> fmList=new ArrayList<>();
    List<CallLog> apiData=new ArrayList<>();

    List<CallLog>fms;
    int currentPosition;
    RecyclerViewAdapter adapter;
   // RadioManager radioManager;
    private ViewPager viewPager;
    ProgressDialog progressDialog;

    private OnFragmentInteractionListener mListener;

    public NumberListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumberListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumberListFragment newInstance(String param1, String param2) {
        NumberListFragment fragment = new NumberListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //radioManager = RadioManager.with(getActivity());

    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_radio_list, container, false);
        recyclerView= view.findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
//        recyclerView.setAdapter(new RecyclerViewAdapter(new ArrayList<Fm>()));
//        loadFMLIST();
        fmList = (List<Number>) getArguments().getSerializable("numbers");
        adapter = new RecyclerViewAdapter(getActivity(),fmList,apiData,this);
        recyclerView.setAdapter(adapter);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    //override method will auto called on item clicked in adapter call
    @Override
    public void itemClicked(Number fmRadio) {

//        newFragment fragment = new newFragment();
//        Bundle args = new Bundle();
//        args.putSerializable("fm", fmRadio);
//
//        fragment.setArguments(args);
//        FragmentManager fm = ((EmergencyCall) getActivity()).getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.flFrame, fragment).addToBackStack(null)
//                .commit();

//        Fragment newFragment = new OneFragment();
//        // consider using Java coding conventions (upper first char class names!!!)
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        // Replace whatever is in the fragment_container view with this fragment,
//        // and add the transaction to the back stack
//        transaction.replace(R.id.child_fragment, newFragment);
//        transaction.addToBackStack(null);
//        // Commit the transaction
//        transaction.commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
