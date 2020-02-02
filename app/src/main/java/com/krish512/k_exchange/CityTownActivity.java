/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.R;

public class CityTownActivity extends AppCompatActivity {

	private enum dd {
		CITY, TOWN, LOCALITY, TYPE, FLOOR
	};

	dd ddSection;

	TextView ddCity;
	TextView ddTown;
	Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_town);

		ddCity = (TextView) findViewById(R.id.ddCity);
		ddTown = (TextView) findViewById(R.id.ddTown);
		btnSave = (Button) findViewById(R.id.btnSave);

		SharedPreferences settings = getSharedPreferences("UserInfo", 0);
		ddCity.setText(settings.getString("currentCity", "").toString());
		ddTown.setText(settings.getString("currentTown", "").toString());
		if (ddCity.getText() != "") {
			int index = -1;
			String City = ddCity.getText().toString();
			String Town = ddTown.getText().toString();

			for (int i = 0; (i < LoadData.CityTown.cities.length)
					&& (index == -1); i++) {
				if (LoadData.CityTown.cities[i].city.contentEquals(City)) {
					index = i;
				}
			}
			AppState.currentCityIndex = index;

			index = -1;

			for (int i = 0; (i < LoadData.CityTown.cities[AppState.currentCityIndex].towns.length)
					&& (index == -1); i++) {
				if (LoadData.CityTown.cities[AppState.currentCityIndex].towns[i].town
						.contentEquals(Town)) {
					index = i;
				}
			}
			AppState.currentTownIndex = index;
		}

		ddCity.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				ddSection = dd.CITY;
				//Log.i("getCurrentFocus: ", findViewById(android.R.id.content).toString());
				registerForContextMenu(findViewById(android.R.id.content));
				openContextMenu(findViewById(android.R.id.content));
			}
		});

		ddTown.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				ddSection = dd.TOWN;
				registerForContextMenu(findViewById(android.R.id.content));
				openContextMenu(findViewById(android.R.id.content));
			}
		});

		btnSave.setOnClickListener(new OnClickListener() {
			@SuppressLint("CommitPrefEdits")
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity

				LoadData.Properties.properties = null;
				LoadData.AgentProperties.properties = null;
				LoadData.Agents.agents = null;
				LoadData.Notices.notices = null;

				SharedPreferences settings = getSharedPreferences("UserInfo", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(
						"currentCity",
						LoadData.CityTown.cities[AppState.currentCityIndex].city);
				editor.putString(
						"currentTown",
						LoadData.CityTown.cities[AppState.currentCityIndex].towns[AppState.currentTownIndex].town);
				editor.commit();
				AppState.currentCity = LoadData.CityTown.cities[AppState.currentCityIndex].city;
				AppState.currentTown = LoadData.CityTown.cities[AppState.currentCityIndex].towns[AppState.currentTownIndex].town;
				if (AppState.UID.equalsIgnoreCase("")) {
					Intent intent = new Intent(view.getContext(),
							LoginActivity.class);
					startActivity(intent);
				}
				finish();
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.removeItem(android.R.id.switchInputMethod);
		if (ddSection == dd.CITY) {
			menu.setHeaderTitle("City");
			// getMenuInflater().inflate(R.menu.city, menu);
			for (int i = 0; i < LoadData.CityTown.cities.length; i++) {
				menu.add(0, i, 0, LoadData.CityTown.cities[i].city);
			}
		} else if (ddSection == dd.TOWN) {
			menu.setHeaderTitle("Town");
			// getMenuInflater().inflate(R.menu.town, menu);
			if (AppState.currentCityIndex != -1) {
				for (int i = 0; i < LoadData.CityTown.cities[AppState.currentCityIndex].towns.length; i++) {
					menu.add(
							0,
							i,
							0,
							LoadData.CityTown.cities[AppState.currentCityIndex].towns[i].town);
				}
			}
		} else {
			menu.setHeaderTitle("Sort");
			getMenuInflater().inflate(R.menu.sort, menu);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (ddSection == dd.CITY) {
			AppState.currentCityIndex = item.getItemId();
			AppState.currentCity = LoadData.CityTown.cities[AppState.currentCityIndex].city;
			ddCity.setText(LoadData.CityTown.cities[AppState.currentCityIndex].city);
			ddTown.setText("");
			AppState.currentTownIndex = -1;
			return true;
		} else if (ddSection == dd.TOWN) {
			AppState.currentTownIndex = item.getItemId();
			AppState.currentTown = LoadData.CityTown.cities[AppState.currentCityIndex].towns[AppState.currentTownIndex].town;
			ddTown.setText(LoadData.CityTown.cities[AppState.currentCityIndex].towns[AppState.currentTownIndex].town);
			return true;
		} else {
			return super.onContextItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.city_town, menu);
		return true;
	}

}
