package com.example.gamifisavings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;

// Class for saving and loading saveGoals.
public class Storage {

	public Storage() {
	}

	// Saves the ArrayList containing saveGoals sent by the caller to the internal storage.
	public static void save(ArrayList<SaveGoal> saveGoals, Context context) {
		try {
			@SuppressWarnings("unused")
			File file = new File(context.getFilesDir(), "save");
			FileOutputStream fos = context.openFileOutput("save", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveGoals);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Loads the ArrayList containing saveGoals from the internal storage and returns it to the caller.
	@SuppressWarnings("unchecked")
	public static ArrayList<SaveGoal> load(Context context) {
		try{
			FileInputStream fin = context.openFileInput("save");
			ObjectInputStream ois = new ObjectInputStream(fin);
			ArrayList<SaveGoal> saveGoals = (ArrayList<SaveGoal>) ois.readObject();
			ois.close();
			fin.close();
			return saveGoals;
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return new ArrayList<SaveGoal>();
	}
}
