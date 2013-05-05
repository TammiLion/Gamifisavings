package com.example.gamifisavings;

import java.util.ArrayList;
import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class SavingsGoalActivity extends Activity {

	private ArrayList<SaveGoal> saveGoals;
	private EditText name;
	private EditText amount;
	private DatePicker beginDate;
	private DatePicker endDate;
	private boolean hasDates = false;
	private Calendar beginCalendar = Calendar.getInstance();
	private Calendar endCalendar = Calendar.getInstance();
	private OnDateChangedListener beginDateListener = new OnDateChangedListener() {

		@SuppressWarnings("static-access")
		@Override
		public void onDateChanged(DatePicker beginDate, int currentYear, int currentMonth, int currentDay) {
			//when beginDate is changed, the changed date will be the new minDate for endDate
			beginCalendar.set(beginCalendar.DAY_OF_MONTH, beginDate.getDayOfMonth());
			beginCalendar.set(beginCalendar.MONTH, beginDate.getMonth());
			beginCalendar.set(beginCalendar.YEAR, beginDate.getYear());
			endDate.setMinDate(beginCalendar.getTimeInMillis());
		}
	};

	private OnDateChangedListener endDateListener = new OnDateChangedListener() {

		@SuppressWarnings("static-access")
		@Override
		public void onDateChanged(DatePicker endDate, int currentYear, int currentMonth, int currentDay) {
			endCalendar.set(endCalendar.DAY_OF_MONTH, endDate.getDayOfMonth());
			endCalendar.set(endCalendar.MONTH, endDate.getMonth());
			endCalendar.set(endCalendar.YEAR, endDate.getYear());
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_savings_goal);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		saveGoals = Storage.load(this);
		name = (EditText) findViewById(R.id.name_goal);
		amount = (EditText) findViewById(R.id.amount_goal);
		beginDate = (DatePicker) findViewById(R.id.begin_date);
		endDate = (DatePicker) findViewById(R.id.end_date);
		onCreateDatePickers();
	}

	//applies settings to DatePickers, sets a listener for beginDate
	public void onCreateDatePickers() {
		beginDate.setCalendarViewShown(false);
		endDate.setCalendarViewShown(false);
		beginDate.init(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH), beginCalendar.get(Calendar.DAY_OF_MONTH), beginDateListener);
		endDate.init(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH), beginCalendar.get(Calendar.DAY_OF_MONTH), endDateListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_savings_goal, menu);
		return true;
	}

	public void onToggleClicked(View view) {
		hasDates = true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.cancel_goal:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.save_goal:
			//Checks if name has been filled in. True: sent intent to MainActivity. False: show message to user indicating missing field.
			if(name.getText().toString().equals("")) {
				Toast.makeText(this, "Your goal has to have a name", Toast.LENGTH_SHORT).show();
			} else if(!hasUniqueName()) {
				Toast.makeText(this, "The name for your goal has to be unique", Toast.LENGTH_SHORT).show();
			} else {
				saveGoals.add(createSaveGoal());
				Storage.save(saveGoals, this);
				startActivity(createIntent());
				finish();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Checks if the newly entered name is unique.
	private boolean hasUniqueName() {
		for(SaveGoal saveGoal : saveGoals) {
			if(name.getText().toString().equals(saveGoal.getName())) {
				return false;
			}
		}
		return true;
	}

	// Returns a new SaveGoal with the user inputted values.
	private SaveGoal createSaveGoal() {
		SaveGoal saveGoal = new SaveGoal(name.getText().toString());
		//Checks if amount has been filled in.
		if(amount.getText().toString().matches("[0-9]+")) {
			saveGoal.setAmount(Integer.parseInt(amount.getText().toString()));
		}
		// Checks if end and begin date are set.
		if(hasDates) {
			saveGoal.setBeginDate(beginCalendar.getTimeInMillis());
			saveGoal.setEndDate(endCalendar.getTimeInMillis());
		}
		return saveGoal;
	}

	// Creates an Intent to send to MainActivity.
	private Intent createIntent() {
		Intent intent = new Intent(this, MainActivity.class);
		//Makes sure that when you press back at MainActivity you go to home screen instead of through activity stack
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		return intent;
	}

}
