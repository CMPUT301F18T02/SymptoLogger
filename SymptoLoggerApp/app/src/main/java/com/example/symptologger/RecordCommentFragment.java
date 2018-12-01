package com.example.symptologger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
 *     Fragment to display list of Care Provider comments.
 * </p>
 */

public class RecordCommentFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    private ArrayList<CareProviderComment> careProviderCommentList = new ArrayList<>();
    private ArrayAdapter<CareProviderComment> adapter;

    int RECORD_POS;
    int CONCERN_POS;
    String USERNAME;

    Record record;

    public RecordCommentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        RECORD_POS = bundle.getInt("RECORD_POS");
        CONCERN_POS = bundle.getInt("CONCERN_POS");
        USERNAME = bundle.getString("USERNAME");

        Collection<Concern> concerns = ConcernListController.getConcernList(USERNAME).getConcernsList();
        ArrayList<Concern> concernList = new ArrayList<Concern>(concerns);
        Concern concernToView = concernList.get(CONCERN_POS);
        ArrayList<Record> recordList = new ArrayList<Record>(concernToView.getRecords());

        record = recordList.get(RECORD_POS);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_comment, container, false);

        record.addCareProviderComment();
        careProviderCommentList = record.getCareProviderComment();

        adapter = new ArrayAdapter<CareProviderComment>(
                getContext(),
                R.layout.list_layout,
                careProviderCommentList);
        ListView careProviderCommentsListView = view.findViewById(R.id.recordCommentList);
        careProviderCommentsListView.setAdapter(adapter);

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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
