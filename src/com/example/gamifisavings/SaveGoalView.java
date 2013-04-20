package com.example.gamifisavings;

import java.util.Calendar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class SaveGoalView extends LinearLayout implements OnClickListener {

	public static final String DAYS_LEFT = "days left";
	public static final String DAYS_DUE = "days due";

	private Context context;
	private TextView name;
	private TextView amount;
	private TextView days;
	private TextView leftOrDue;
	private SaveGoal saveGoal;

	public SaveGoalView(Context context, SaveGoal saveGoal) {
		this(context, null, saveGoal);
	}

	public SaveGoalView(Context context, AttributeSet attrs, SaveGoal saveGoal) {
		super(context, attrs);
		this.saveGoal = saveGoal;
		this.context = context;

		String inflatorService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater layoutInflator = (LayoutInflater) getContext().getSystemService(inflatorService);
		layoutInflator.inflate(R.layout.save_goal_view, this, true);
		setOnClickListener(this);

		findViewsById();
	}

	public SaveGoalView(Context context, AttributeSet attrs, int defStyle, SaveGoal saveGoal) {
		this(context, attrs, saveGoal);
	}

	private void findViewsById() {
		name = (TextView) findViewById(R.id.name);
		name.setTextColor(Color.BLUE);
		amount = (TextView) findViewById(R.id.amount);
		amount.setTextColor(Color.GRAY);
		days = (TextView) findViewById(R.id.days);
		leftOrDue = (TextView) findViewById(R.id.left_or_due);
		setViewsContent();
	}

	private void setViewsContent() {
		setName(saveGoal.getName());
		setAmount(saveGoal.getAmount());
		setDays(saveGoal.getEndDate());
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public String getName() {
		return name.getText().toString();
	}

	public void setAmount(int amount) {
		this.amount.setText(Integer.toString(amount));
	}

	public void setDays(long endDate) {
		if(endDate != 0) {
			Calendar nowCalendar = Calendar.getInstance();

			if(endDate > nowCalendar.getTimeInMillis()) {
				days.setText(Integer.toString((int) (endDate - nowCalendar.getTimeInMillis()) / (1000*60*60*24)));
				leftOrDue.setText(DAYS_LEFT);
				leftOrDue.setTextColor(Color.GREEN);
			} else {
				days.setText(Integer.toString((int) (nowCalendar.getTimeInMillis() - endDate) / (1000*60*60*24)));
				leftOrDue.setText(DAYS_DUE);
				leftOrDue.setTextColor(Color.RED);
			}
			days.setVisibility(View.VISIBLE);
			leftOrDue.setVisibility(View.VISIBLE);	
		}		
	}

	@Override
	public void onClick(View v) {
		//Toast.makeText(context, Long.toString(beginDate), Toast.LENGTH_SHORT).show();
		//Log.v("Select", name.getText().toString());
		Intent intent = new Intent(context, SaveGoalStatus.class);
		intent.putExtra("SaveGoal", saveGoal);
		context.startActivity(intent);
	}
}
