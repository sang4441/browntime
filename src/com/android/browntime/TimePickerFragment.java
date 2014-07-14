package com.android.browntime;

import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickerFragment extends DialogFragment
                            implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    	((BrownCartListActivity)getActivity()).onTimeSetValue(view, hourOfDay, minute);
    	
//    	Intent intent = new Intent (getActivity(), BrownCartListActivity.class);
//        intent.putExtra("hour", hourOfDay);
//        intent.putExtra("minute", minute);
//        startActivityForResult(intent, 101);
		Toast.makeText(getActivity(), "hours: "+hourOfDay, Toast.LENGTH_SHORT).show();
    }
}