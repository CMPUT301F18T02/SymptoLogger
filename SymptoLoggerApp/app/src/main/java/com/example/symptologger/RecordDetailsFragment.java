package com.example.symptologger;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
    private Record record;

    int RECORD_POS;
    int CONCERN_POS;
    String USERNAME;

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
        Bundle bundle = getArguments();

        try {
            RECORD_POS = bundle.getInt("RECORD_POS");
            CONCERN_POS = bundle.getInt("CONCERN_POS");
            USERNAME = bundle.getString("USERNAME");
        } catch (Exception e) {
            // TODO: offline mode
        }

        View view = inflater.inflate(R.layout.fragment_record_details, container, false);

        Collection<Concern> concerns = ConcernListController.getConcernList(USERNAME).getConcernsList();
        ArrayList<Concern> concernList = new ArrayList<Concern>(concerns);
        Concern concernToView = concernList.get(CONCERN_POS);
        ArrayList<Record> recordList = new ArrayList<Record>(concernToView.getRecords());

        record = recordList.get(RECORD_POS);

        // CareProvider careProvider = new CareProvider("002", "test@test.com", "123456790", "care_provider");
        // Patient patient = new Patient("001", "test@test.com", "123456790", "patient");

        // String careProviderName = careProvider.getFullName();

        // TextView careProviderView = view.findViewById(R.id.careProviderContent);
        // careProviderView.setText(careProviderName);

        String datetime = record.getDate();
        TextView datetimeView = view.findViewById(R.id.dateTimeContent);
        datetimeView.setText(datetime);

        final Location location;
        location = record.getGeoLocation();

        if (location != null) {
            final MapView mapView = view.findViewById(R.id.recordMapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(new OnMapReadyCallback() {

                @Override
                public void onMapReady(GoogleMap googleMap) {
                    LatLng coordinates = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(coordinates).title("testing"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15));
                    mapView.onResume();
                }
            });
        } else {
            TextView t = view.findViewById(R.id.locationViewText);
            t.setText("No location record.");
        }

        ArrayList<Photograph> photographs = record.getPhoto();
        if (photographs.size() != 0) {
            // TODO: load the pictures
        } else {
            TextView t = view.findViewById(R.id.recordPictureText);
            t.setText("No pictures record.");
        }

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
