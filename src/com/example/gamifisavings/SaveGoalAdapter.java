package com.example.gamifisavings;

import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SaveGoalAdapter extends ArrayAdapter {

	private int resource;
	private LayoutInflater inflater;
	private Context context;

	@SuppressWarnings("unchecked")
	public SaveGoalAdapter(Context context, int resourceId, List objects) {
		super(context, resourceId, objects);
		this.resource = resourceId;
		inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/* create a new view of my layout and inflate it in the row */
		convertView = (RelativeLayout) inflater.inflate(resource, null);

		/* Extract the city's object to show */
		SaveGoal goal = (SaveGoal) getItem(position);

		/* Take the TextView from layout and set the city's name */
		TextView txtName = (TextView) convertView.findViewById(R.id.name);
		txtName.setText(goal.getName());

		/* Take the TextView from layout and set the city's wiki link */
		TextView txtAmount = (TextView) convertView.findViewById(R.id.amount);
		txtAmount.setText(goal.getAmount());

		return convertView;
	}
}
