package com.example.swiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataLink;

    private static final int threadDuration = 2500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
		//final TextView rndNum = (TextView) findViewById(R.id.lb1);

        listView = (ListView) findViewById(R.id.listViewSwipe);
        dataLink = new ArrayList<String>();

        //https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataLink);

        listView.setAdapter(adapter);

        this.adapter.notifyDataSetChanged();

        /**
         * Load initial data
         */
        initalData();

		swipeView.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_dark);
		swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			
			@Override
			public void onRefresh() {

				// TODO Auto-generated method stub
				swipeView.setRefreshing(true);


				Log.d("Swipe", "Refreshing Number");

                /**
                 * Waits duration of thread before performing action...
                 */
				( new Handler()).postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						swipeView.setRefreshing(false);
						double f = Math.random();
						//rndNum.setText(String.valueOf(f));

                        executeOperation();

					}
				}, threadDuration);
			}
		});
	}

    private void initalData() {
        dataLink.add("Recordsss");
        dataLink.add("Recordsss");
        dataLink.add("Recordsss");
    }

    private void executeOperation() {
        Toast.makeText(getApplicationContext(), "Refreshing..", Toast.LENGTH_SHORT).show();

        //add a value to the listView
        dataLink.add("Pullout game strong??");

        /**
         * Notify adapter to notify ListView dataset has changed
         */
        this.adapter.notifyDataSetChanged();


    }

}
