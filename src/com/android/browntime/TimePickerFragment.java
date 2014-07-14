package com.android.browntime;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment
	implements TimePickerDialog.OnTimeSetListener {
	
	public static final String EXTRA_HOUR = "com.android.browntime.hour";
	public static final String EXTRA_MINUTE = "com.android.browntime.minute";
	
	private int mHour;
	private int mMinute;
	
//	static TimePickerFragment newInstance(int id, int hour, int minute) 
//	{
//	    Bundle args = new Bundle();
//	    args.putInt("picker_id", id);
//	    args.putInt("hour", hour);
//	    args.putInt("minute", minute);
//	    TimePickerFragment fragment = new TimePickerFragment();
//	    fragment.setArguments(args);
//	    return fragment;
//	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
		TimePicker timePicker = (TimePicker)v.findViewById(R.id.dialog_timePicker);
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
		timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				getArguments().putSerializable(EXTRA_HOUR, hourOfDay);
				getArguments().putSerializable(EXTRA_MINUTE, hourOfDay);
			}
		});
		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setTitle("order time")
		.setPositiveButton("OK", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				})
		.create();
	}
	
	private void sendResult(int resultCode) {
		if (getTargetFragment() == null) return;
		
		Intent i = new Intent();
		i.putExtra(EXTRA_HOUR, mHour);
		i.putExtra(EXTRA_MINUTE, mMinute);
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	// Do something with the time chosen by the user
	}
}
