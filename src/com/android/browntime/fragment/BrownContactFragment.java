package com.android.browntime.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.browntime.R;

/**
 * Created by kimsanghwan on 8/7/2014.
 */
public class BrownContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact,  parent, false);
        return v;
    }

}
