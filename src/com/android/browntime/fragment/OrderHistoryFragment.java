package com.android.browntime.fragment;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by kimsanghwan on 8/2/2014.
 */
public class OrderHistoryFragment extends Fragment {

    public static final String ARG_DRAWER_NUMBER = "drawer";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int drawerNum = getArguments().getInt(ARG_DRAWER_NUMBER);


//        Fragment fragment = new CollectionDemoActivity();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.content_frame, fragment)
//                .commit();
//
//        if (drawerNum == 1) {
//            Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
//            startActivity(intent);
//        }
    }
}
