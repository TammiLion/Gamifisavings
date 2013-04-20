package com.example.gamifisavings;

import java.util.Calendar;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SaveGoalView extends LinearLayout {

	public static final String DAYS_LEFT = "days left";
	public static final String DAYS_DUE = "days due";

	private Context context;
	private TextView name;
	private TextView amount;
	private TextView days;
	private TextView leftOrDue;

	public SaveGoalView(Context context) {
		this(context, null);
	}

	public SaveGoalView(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;
		String inflatorService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater layoutInflator = (LayoutInflater) getContext().getSystemService(inflatorService);
		layoutInflator.inflate(R.layout.save_goal_view, this, true);

		loadViews();
	}

	public SaveGoalView(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs);
	}

	private void loadViews() {
		name = (TextView) findViewById(R.id.name);
		name.setTextColor(Color.BLUE);
		amount = (TextView) findViewById(R.id.amount);
		amount.setTextColor(Color.GRAY);
		days = (TextView) findViewById(R.id.days);
		leftOrDue = (TextView) findViewById(R.id.left_or_due);
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
}
