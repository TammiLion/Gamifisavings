package com.example.gamifisavings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

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
		load();
		if (intent.getExtras() != null) {
			addGoal();
			save();
		}
		showCustom();
	}

	public void showCustom() {
		scrollView = new ScrollView(this);
		linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		for(SaveGoal goal: saveGoals) {
			SaveGoalView newView = new SaveGoalView(this);
			newView.setName(goal.getName());
			newView.setAmount(goal.getAmount());
			newView.setDays(goal.getEndDate());
			newView.setPadding(10, 5, 10, 5);
			//newView.setOnClickListener(this);
			linearLayout.addView(newView);
		}

		scrollView.addView(linearLayout);
		setContentView(scrollView);
	}

	public void addGoal() {

		SaveGoal newGoal = new SaveGoal(intent.getStringExtra("GoalName"));
		if(intent.hasExtra("GoalAmount")) {
			newGoal.setAmount(intent.getIntExtra("GoalAmount", -1));
		}
		if(intent.hasExtra("BeginDate") | intent.hasExtra("EndDate")) {
			newGoal.setBeginDate(intent.getLongExtra("BeginDate", -1));
			newGoal.setEndDate(intent.getLongExtra("EndDate", -1));
		}
		saveGoals.add(newGoal);
	}

	public void save() {
		try {
			@SuppressWarnings("unused")
			File file = new File(this.getFilesDir(), "save");
			FileOutputStream fos = openFileOutput("save", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveGoals);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void load() {
		try{
			FileInputStream fin = openFileInput("save");
			ObjectInputStream ois = new ObjectInputStream(fin);
			saveGoals = (ArrayList<SaveGoal>) ois.readObject();
			ois.close();
			fin.close();
		} catch(Exception ex){
			ex.printStackTrace();
		} 
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
