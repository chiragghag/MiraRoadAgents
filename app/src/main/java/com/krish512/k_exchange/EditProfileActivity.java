/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

public class EditProfileActivity extends AppCompatActivity {

	private enum dd {
		CITY, TOWN, LOCALITY, TYPE, FLOOR
	};

	dd ddSection;
	int currentCity = -1;
	int currentTown = -1;
	int currentLocality = -1;
	int selected = -1;

	ProgressBar pbProgress;
	EditText editBusinessName;
	EditText editAgentName;
	EditText editAddress;
	EditText editEmail;
	EditText editMobile;
	EditText editAlt;
	EditText editWebsite;
	TextView btnPasswordLink;
	TextView ddCity;
	TextView ddTown;
	TextView ddLocality;
	RadioGroup radioUserType;
	RadioButton radioType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editprofile);

		Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
		TextView btnCancel = (TextView) findViewById(R.id.txtCancel);
		ddCity = (TextView) findViewById(R.id.ddCity);
		ddTown = (TextView) findViewById(R.id.ddTown);
		ddLocality = (TextView) findViewById(R.id.ddLocality);
		editBusinessName = (EditText) findViewById(R.id.editBusinessName);
		editAgentName = (EditText) findViewById(R.id.editAgentName);
		editAddress = (EditText) findViewById(R.id.editAddress);
		editEmail = (EditText) findViewById(R.id.editEmail);
		editMobile = (EditText) findViewById(R.id.editMobile);
		editAlt = (EditText) findViewById(R.id.editAlt);
		editWebsite = (EditText) findViewById(R.id.editWebsite);
		btnPasswordLink = (TextView) findViewById(R.id.txtPasswordLink);
		radioUserType = (RadioGroup) findViewById(R.id.radioUserType);
		pbProgress = (ProgressBar) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.GONE);
		// int selected = radioUserType.getCheckedRadioButtonId();
		// radioType = (RadioButton) findViewById(selected);
		// radioType.getText();

		SharedPreferences settings = getSharedPreferences("UserInfo", 0);
		if (settings.getString("UserType", "").toString()
				.equalsIgnoreCase("Broker")) {
			radioType = (RadioButton) findViewById(R.id.UserBroker);
			radioType.setChecked(true);
		} else {
			radioType = (RadioButton) findViewById(R.id.UserBuilder);
			radioType.setChecked(true);
		}
		editBusinessName.setText(settings.getString("BusinessName", "")
				.toString());
		editAgentName.setText(settings.getString("AgentName", "").toString());
		ddCity.setText(settings.getString("City", "").toString());
		ddTown.setText(settings.getString("Town", "").toString());
		ddLocality.setText(settings.getString("Locality", "").toString());
		editAddress.setText(settings.getString("Address", "").toString());
		editEmail.setText(settings.getString("Email", "").toString());
		editMobile.setText(settings.getString("Mobile", "").toString());
		editAlt.setText(settings.getString("Alt", "").toString());
		editWebsite.setText(settings.getString("Website", "").toString());
		if (ddCity.getText() != "") {
			int index = -1;
			String City = ddCity.getText().toString();

			for (int i = 0; (i < LoadData.CityTown.cities.length)
					&& (index == -1); i++) {
				if (LoadData.CityTown.cities[i].city.contentEquals(City)) {
					index = i;
				}
			}

			currentCity = index;

			if (ddTown.getText() != "") {
				index = -1;
				String Town = ddTown.getText().toString();

				for (int i = 0; (i < LoadData.CityTown.cities[currentCity].towns.length)
						&& (index == -1); i++) {
					if (LoadData.CityTown.cities[currentCity].towns[i].town
							.contentEquals(Town)) {
						index = i;
					}
				}

				currentTown = index;

				if (ddLocality.getText() != "") {
					index = -1;
					String Locality = ddLocality.getText().toString();

					for (int i = 0; (i < LoadData.CityTown.cities[currentCity].towns[currentTown].localities.length)
							&& (index == -1); i++) {
						if (LoadData.CityTown.cities[currentCity].towns[currentTown].localities[i].locality
								.contentEquals(Locality)) {
							index = i;
						}
					}

					currentLocality = index;
				}
			}
		}

		btnUpdate.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				selected = radioUserType.getCheckedRadioButtonId();
				radioType = (RadioButton) findViewById(selected);
				pbProgress.setVisibility(View.VISIBLE);
				new MyAsyncTask().execute();
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						ViewProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnPasswordLink.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(AppState.absoluteUri + "ChangePassword.html"));
				startActivity(intent);
			}
		});

		radioUserType.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.UserBroker) {
					radioType = (RadioButton) findViewById(R.id.UserBroker);
				} else if (checkedId == R.id.UserBuilder) {
					radioType = (RadioButton) findViewById(R.id.UserBuilder);
				}
			}
		});

		ddCity.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				ddSection = dd.CITY;
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

		ddLocality.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				ddSection = dd.LOCALITY;
				registerForContextMenu(findViewById(android.R.id.content));
				openContextMenu(findViewById(android.R.id.content));
			}
		});
	}

	private boolean validate() {

		if (ddCity.getText().toString().contentEquals("")) {
			ddCity.setHint("Required");
			ddCity.setHintTextColor(getResources().getColor(R.color.themeRED));
			return false;
		}
		if (ddTown.getText().toString().contentEquals("")) {
			ddTown.setHint("Required");
			ddTown.setHintTextColor(getResources().getColor(R.color.themeRED));
			return false;
		}
		if (ddLocality.getText().toString().contentEquals("")) {
			ddLocality.setHint("Required");
			ddLocality.setHintTextColor(getResources().getColor(
					R.color.themeRED));
			return false;
		}
		if (editBusinessName.getText().toString().contentEquals("")) {
			editBusinessName.setHint("Required");
			editBusinessName.setHintTextColor(getResources().getColor(
					R.color.themeRED));
			return false;
		}
		if (editAgentName.getText().toString().contentEquals("")) {
			editAgentName.setHint("Required");
			editAgentName.setHintTextColor(getResources().getColor(
					R.color.themeRED));
			return false;
		}

		if (editAddress.getText().toString().contentEquals("")) {
			editAddress.setHint("Required");
			editAddress.setHintTextColor(getResources().getColor(
					R.color.themeRED));
			return false;
		}
		if (!(editAlt.getText().toString().contentEquals(""))) {
			if (editAlt.getText().length() != 10) {
				// editAlt.setHint("10 digits number");
				// editAlt.setTextColor(getResources().getColor(R.color.themeRED));
				return false;
			}
		}
		return true;
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... param) {
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("uid", AppState.UID));
			params.add(new BasicNameValuePair("type", radioType.getText()
					.toString()));
			params.add(new BasicNameValuePair("bname", editBusinessName
					.getText().toString()));
			params.add(new BasicNameValuePair("aname", editAgentName.getText()
					.toString()));
			params.add(new BasicNameValuePair("city", ddCity.getText()
					.toString()));
			params.add(new BasicNameValuePair("town", ddTown.getText()
					.toString()));
			params.add(new BasicNameValuePair("locality", ddLocality.getText()
					.toString()));
			params.add(new BasicNameValuePair("address", editAddress.getText()
					.toString()));
			params.add(new BasicNameValuePair("altno", editAlt.getText()
					.toString()));
			params.add(new BasicNameValuePair("website", editWebsite.getText()
					.toString()));
			if (!validate()) {
				return null;
			} else {
				String response = null;

				try {
					response = Operation
							.postHttpResponse(new URI(AppState.absoluteUri
									+ "updateprofile.php"), params);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return response;
			}
		}

		protected void onPostExecute(String response) {
			if (response == null) {
				Toast toast = Toast.makeText(getBaseContext(),
						"Please fill up the details", Toast.LENGTH_LONG);
				toast.show();
			} else {
				String[] reply = response.split(":");
				String msg = "";
				try {
					if (reply[1] != null) {
						msg = reply[1];
					}
				} catch (ArrayIndexOutOfBoundsException aiobe) {
					String APP_TAG = null;
					Log.d(APP_TAG, "Error:ArrayIndexOutOfBoundsException "
							+ aiobe);
				}
				if (reply[0].equalsIgnoreCase("Error") == true) {
					if (reply[1] != null) {
						msg = reply[1];
					}

					Toast toast = Toast.makeText(getBaseContext(),
							"Update Failed: " + msg, Toast.LENGTH_LONG);
					toast.show();
				} else {
					Toast toast = Toast.makeText(getBaseContext(), msg,
							Toast.LENGTH_LONG);
					toast.show();
					SharedPreferences settings = getSharedPreferences(
							"UserInfo", 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("UserType", radioType.getText().toString());
					editor.putString("BusinessName", editBusinessName.getText()
							.toString());
					editor.putString("AgentName", editAgentName.getText()
							.toString());
					editor.putString("City", ddCity.getText().toString());
					editor.putString("Town", ddTown.getText().toString());
					editor.putString("Locality", ddLocality.getText()
							.toString());
					editor.putString("Address", editAddress.getText()
							.toString());
					editor.putString("Email", editEmail.getText().toString());
					editor.putString("Mobile", editMobile.getText().toString());
					editor.putString("Alt", editAlt.getText().toString());
					editor.putString("Website", editWebsite.getText()
							.toString());
					editor.commit();

					Intent intent = new Intent(getBaseContext(),
							ViewProfileActivity.class);
					startActivity(intent);
					finish();
				}
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this.getBaseContext(),
				ViewProfileActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.removeItem(android.R.id.switchInputMethod);
		if (ddSection == dd.CITY) {
			menu.setHeaderTitle("City");
			for (int i = 0; i < LoadData.CityTown.cities.length; i++) {
				menu.add(0, i, 0, LoadData.CityTown.cities[i].city);
			}
		} else if (ddSection == dd.TOWN) {
			menu.setHeaderTitle("Town");
			if (currentCity != -1) {
				for (int i = 0; i < LoadData.CityTown.cities[currentCity].towns.length; i++) {
					menu.add(0, i, 0,
							LoadData.CityTown.cities[currentCity].towns[i].town);
				}
			}
		} else if (ddSection == dd.LOCALITY) {
			menu.setHeaderTitle("Locality");
			if ((currentCity != -1) && (currentTown != -1)) {
				for (int i = 0; i < LoadData.CityTown.cities[currentCity].towns[currentTown].localities.length; i++) {
					menu.add(
							0,
							i,
							0,
							LoadData.CityTown.cities[currentCity].towns[currentTown].localities[i].locality);
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
			currentCity = item.getItemId();
			ddCity.setText(LoadData.CityTown.cities[currentCity].city);
			ddTown.setText("");
			ddLocality.setText("");
			currentTown = -1;
			currentLocality = -1;
			return true;
		} else if (ddSection == dd.TOWN) {
			currentTown = item.getItemId();
			ddTown.setText(LoadData.CityTown.cities[currentCity].towns[currentTown].town);
			ddLocality.setText("");
			currentLocality = -1;
			return true;
		} else if (ddSection == dd.LOCALITY) {
			currentLocality = item.getItemId();
			ddLocality
					.setText(LoadData.CityTown.cities[currentCity].towns[currentTown].localities[currentLocality].locality);
			return true;
		} else {
			return super.onContextItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
