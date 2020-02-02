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

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RegisterActivity extends Activity {

	private enum dd {
		CITY, TOWN, LOCALITY, TYPE, FLOOR
	};

	dd ddSection;
	int currentCity = -1;
	int currentTown = -1;
	int currentLocality = -1;

	ProgressBar pbProgress;
	EditText editBusinessName;
	EditText editAgentName;
	EditText editEmail;
	EditText editMobile;
	EditText editPassword;
	EditText editConfirm;
	TextView ddCity;
	TextView ddTown;
	TextView ddLocality;
	TextView txtDisclaimer;
	RadioGroup radioUserType;
	RadioButton radioBroker;
	RadioButton radioBuilder;
	RadioButton radioType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		Button btnRegister = (Button) findViewById(R.id.btnRegister);
		TextView btnExisting = (TextView) findViewById(R.id.txtExisting);
		ddCity = (TextView) findViewById(R.id.ddCity);
		ddTown = (TextView) findViewById(R.id.ddTown);
		ddLocality = (TextView) findViewById(R.id.ddLocality);
		txtDisclaimer = (TextView) findViewById(R.id.txtDisclaimer);
		txtDisclaimer.setMovementMethod(LinkMovementMethod.getInstance());
		txtDisclaimer.setText(Html.fromHtml(getResources().getString(
				R.string.disclaimer)));
		editBusinessName = (EditText) findViewById(R.id.editBusinessName);
		editAgentName = (EditText) findViewById(R.id.editAgentName);
		editEmail = (EditText) findViewById(R.id.editEmail);
		editMobile = (EditText) findViewById(R.id.editMobile);
		editPassword = (EditText) findViewById(R.id.editPassword);
		editConfirm = (EditText) findViewById(R.id.editConfirmPassword);
		radioUserType = (RadioGroup) findViewById(R.id.radioUserType);
		radioBroker = (RadioButton) findViewById(R.id.UserBroker);
		radioBuilder = (RadioButton) findViewById(R.id.UserBuilder);
		pbProgress = (ProgressBar) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.GONE);

		btnRegister.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (editPassword.getText().toString()
						.contentEquals(editConfirm.getText().toString())) {
					if (validate()) {
						new MyAsyncTask().execute();
					} else {
						pbProgress.setVisibility(View.GONE);
					}
				} else {
					Toast toast = Toast.makeText(getBaseContext(),
							"Passwords do not match", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		btnExisting.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				finish();
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

		radioUserType.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.UserBroker) {
					radioType = (RadioButton) findViewById(R.id.UserBroker);
				} else if (checkedId == R.id.UserBuilder) {
					radioType = (RadioButton) findViewById(R.id.UserBuilder);
				}
			}
		});
	}

	private boolean validate() {

		if ((radioBroker.isChecked() == false)
				&& (radioBuilder.isChecked() == false)) {
			radioBroker.setTextColor(getResources().getColor(R.color.themeRED));
			radioBuilder
					.setTextColor(getResources().getColor(R.color.themeRED));
			Toast toast = Toast.makeText(getBaseContext(),
					"Error: Select User Type", Toast.LENGTH_LONG);
			toast.show();
			return false;
		} else {
			radioBroker.setTextColor(getResources()
					.getColor(R.color.themeBLACK));
			radioBuilder.setTextColor(getResources().getColor(
					R.color.themeBLACK));
		}

		if (ddCity.getText().toString().contentEquals("")) {
			ddCity.setHint("Required");
			ddCity.setHintTextColor(getResources().getColor(R.color.themeRED));
			Toast toast = Toast.makeText(getBaseContext(),
					"Error: Select city", Toast.LENGTH_LONG);
			toast.show();
			return false;
		}

		if (ddTown.getText().toString().contentEquals("")) {
			ddTown.setHint("Required");
			ddTown.setHintTextColor(getResources().getColor(R.color.themeRED));
			Toast toast = Toast.makeText(getBaseContext(),
					"Error: Select town", Toast.LENGTH_LONG);
			toast.show();
			return false;
		}

		if (editBusinessName.getText().toString().contentEquals("")) {
			editBusinessName.setHint("Required");
			editBusinessName.setHintTextColor(getResources().getColor(
					R.color.themeRED));
			Toast toast = Toast.makeText(getBaseContext(),
					"Error: Fill up details", Toast.LENGTH_LONG);
			toast.show();
			return false;
		}

		if (editAgentName.getText().toString().contentEquals("")) {
			editAgentName.setHint("Required");
			editAgentName.setHintTextColor(getResources().getColor(
					R.color.themeRED));
			Toast toast = Toast.makeText(getBaseContext(),
					"Error: Fill up details", Toast.LENGTH_LONG);
			toast.show();
			return false;
		}

		if (editMobile.getText().toString().contentEquals("")) {
			editMobile.setHintTextColor(getResources().getColor(
					R.color.themeRED));
			return false;
		}

		if (editEmail.getText().toString().contentEquals("")) {
			editEmail.setHint("Required");
			editEmail.setHintTextColor(getResources()
					.getColor(R.color.themeRED));
			Toast toast = Toast.makeText(getBaseContext(),
					"Error: Fill up details", Toast.LENGTH_LONG);
			toast.show();
			return false;
		}

		if ((editPassword.getText().toString().length() < 6)
				|| ((editPassword.getText().toString().length() > 16))) {
			Toast toast = Toast.makeText(getBaseContext(),
					"Error: Password length should be atleast 6 and atmost 16",
					Toast.LENGTH_LONG);
			toast.show();
			return false;
		}

		if ((editConfirm.getText().toString().length() < 6)
				|| ((editConfirm.getText().toString().length() > 16))) {
			return false;
		}

		return true;
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... param) {
			String response = null;
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
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
			params.add(new BasicNameValuePair("phoneno", editMobile.getText()
					.toString()));
			params.add(new BasicNameValuePair("password", editPassword
					.getText().toString()));
			params.add(new BasicNameValuePair("email", editEmail.getText()
					.toString()));
			try {
				response = Operation.postHttpResponse(new URI(
						AppState.absoluteUri + "register.php"), params);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] reply = response.split(":");
			if (reply[0].equalsIgnoreCase("Error") == true) {
				return response.toString();
			} else {
				return null;
			}
		}

		protected void onPostExecute(String result) {
			if (result != null) {
				String[] reply = result.split(":");
				Toast toast = Toast.makeText(getBaseContext(),
						"Register Failed: " + reply[1], Toast.LENGTH_LONG);
				toast.show();
			} else {
				Toast toast = Toast.makeText(getBaseContext(),
						"Registered Successfully", Toast.LENGTH_LONG);
				toast.show();
				Intent intent = new Intent(getBaseContext(),
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

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
			if (currentCity != -1) {
				for (int i = 0; i < LoadData.CityTown.cities[currentCity].towns.length; i++) {
					menu.add(0, i, 0,
							LoadData.CityTown.cities[currentCity].towns[i].town);
				}
			}
		} else if (ddSection == dd.LOCALITY) {
			menu.setHeaderTitle("Locality");
			// getMenuInflater().inflate(R.menu.town, menu);
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
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
