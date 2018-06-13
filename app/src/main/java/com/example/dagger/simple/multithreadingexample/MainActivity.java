package com.example.dagger.simple.multithreadingexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	//Start First Async Task:
	private class FirstAsyncTask extends AsyncTask<String, String, Void> {

		@Override
		protected Void doInBackground(String... params) {
			for (int index = 0; index < 50; index++) {
				Log.i("AsyncTask", "FirstAsyncTask is running on "
						+ Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority());
				try {
					Thread.sleep(100);
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.d("AsyncTask", "FirstonPostExecute()");
		}

		@Override
		protected void onPreExecute() {
			Log.i("AsyncTask", "FirstOnPreExecute()");
		}
	}

	//Start Second Async Task:
	private class SecondAsyncTask extends AsyncTask<String, String, Void> {

		@Override
		protected Void doInBackground(String... params) {
			for (int index = 0; index < 50; index++) {
				Log.d("AsyncTask", "SecondAsyncTask is running on "
						+ Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority());
				try {
					Thread.sleep(100);
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.d("AsyncTask", "SecondOnPostExecute()");
		}

		@Override
		protected void onPreExecute() {
			Log.i("AsyncTask", "SecondOnPreExecute()");
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				runMultipleAsyncTask();
			}
		});
	}

	private void runMultipleAsyncTask() // Run Multiple Async Task
	{

		AsyncTask<String, String, Void> asyncTask = new FirstAsyncTask(); // First
		SampleThreadPool.DEFAULT.post(asyncTask);
		AsyncTask<String, String, Void> asyncTask2 = new SecondAsyncTask(); // Second
		SampleThreadPool.DEFAULT.post(asyncTask2);
	}

}
