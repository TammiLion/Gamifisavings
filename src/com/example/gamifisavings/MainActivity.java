package com.example.gamifisavings;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity {

	private Intent intent;
	private ArrayList<SaveGoal> saveGoals = new ArrayList<SaveGoal>();
	private LinearLayout linearLayout;
	private ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionbar = getActionBar();
		actionbar.show();		
		intent = getIntent();
		saveGoals = Storage.load(this);
		showSaveGoals();
	}

	// Shows the saveGoals on screen.
	public void showSaveGoals() {
		scrollView = new ScrollView(this);
		linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		for(SaveGoal goal: saveGoals) {
			SaveGoalView newView = new SaveGoalView(this, goal);
			newView.setPadding(10, 5, 10, 5);
			linearLayout.addView(newView);
		}
		scrollView.addView(linearLayout);
		setContentView(scrollView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.savings:
			// Add savings goal in action bar clicked; go to GoalActivity
			Intent intent = new Intent(this, SavingsGoalActivity.class);
			startActivity(intent);
			return true;
		default:
			return true;
		}
	}
}
