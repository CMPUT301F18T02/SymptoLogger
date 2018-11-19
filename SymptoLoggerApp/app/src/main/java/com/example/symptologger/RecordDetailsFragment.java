package com.example.symptologger;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * <p>
 *     Fragment to display record details. App user should be able to see
 *     1. assigned care provider if there is any
 *     2. Date and time of the record
 *     3. Geo location if recorded
 *     4. Pictures if there are any
 *     5. Body parts indicating where pictures are taken if there is any
 * </p>
 */
public class RecordDetailsFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    public RecordDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_record_details, container, false);

        Location location;

        // TODO: replace p, cp, record
        CareProvider careProvider = new CareProvider("002", "CPFirst", "CPLast", "test@test.com", "123456790");
        Record record = new Record();
        Patient patient = new Patient("001", "PFirst", "PLast", "test@test.com", "123456790");

        String careProviderName = careProvider.getFullName();
        String datetime = record.getDate().toString();

        TextView careProviderView = view.findViewById(R.id.careProviderContent);
        careProviderView.setText(careProviderName);

        TextView datetimeView = view.findViewById(R.id.dateTimeContent);
        datetimeView.setText(datetime);

        // TODO: maybe map fragment?
//        mapView = findViewById(R.id.recordMapView);
//        mapView.onCreate(Bundle.EMPTY);
//        MapsInitializer.initialize(this);

        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
