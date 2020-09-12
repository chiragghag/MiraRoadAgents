/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.Manifest;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.Operation;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.krish512.k_exchange.Utils.Util;

import static com.krish512.k_exchange.Utils.AppState.REQUEST_READ_PHONE_STATE;

public class Splashscreen extends Activity {

	//private static final int REQUEST_READ_PHONE_STATE = ;

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case REQUEST_READ_PHONE_STATE:
				if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
					//TODO
					finish();
					startActivity(getIntent());
				}
				break;
			default:
				break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splashscreen);
		int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
		} else {

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Splashscreen.TELEPHONY_SERVICE);
		//Workarroud here
		AppState.DeviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

		Log.i("splash screen: ", AppState.DeviceID);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				new MyAsyncTask().execute();
			}
		}, 1500);
		}
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			SharedPreferences settings = getSharedPreferences("UserInfo", 0);
			return Operation.getCityTown(settings.getString("UID", "")
					.toString());
		}

		protected void onPostExecute(Boolean result) {
			SharedPreferences settings = getSharedPreferences("UserInfo", 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("Paid", AppState.Paid);
			editor.putString("PaidStartDate", AppState.PaidStartDate);
			editor.putString("PaidEndDate", AppState.PaidEndDate);
			editor.commit();

			if (result) {
				if (AppState.AppVersion != AppState.ServerAppVersion) {
					Toast toast = Toast
							.makeText(getBaseContext(),
									"Please update your application",
									Toast.LENGTH_LONG);
					toast.show();
					finish();
					try {
						Intent viewIntent = new Intent(
								"android.intent.action.VIEW",
								Uri.parse("market://details?id=com.krish512.k_exchange"));
						startActivity(viewIntent);
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(),
								"Unable to Open Play Store", Toast.LENGTH_SHORT)
								.show();
						e.printStackTrace();
					}
				} else {
					settings = getSharedPreferences("UserInfo", 0);
					String City = settings.getString("currentCity", "")
							.toString();
					String Town = settings.getString("currentTown", "")
							.toString();
					if ((City == "") || (Town == "")) {
						Intent intent = new Intent(Splashscreen.this,
								CityTownActivity.class);
						startActivity(intent);
					} else {
						AppState.currentCity = City;
						AppState.currentTown = Town;
						String UID = settings.getString("UID", "").toString();
						if (UID == "") {
							AppState.loginState = AppState.enumLogin.LoggedOut;
							Intent intent = new Intent(Splashscreen.this,
									LoginActivity.class);
							startActivity(intent);
						} else {
							AppState.currentCity = City;
							AppState.currentTown = Town;
							AppState.UID = UID;
							AppState.BName = settings.getString("BusinessName",
									"").toString();
							AppState.AName = settings
									.getString("AgentName", "").toString();
							AppState.Paid = settings.getString("Paid", "")
									.toString();
							AppState.PaidStartDate = settings.getString(
									"PaidStartDate", "").toString();
							AppState.PaidEndDate = settings.getString(
									"PaidEndDate", "").toString();
							AppState.loginState = AppState.enumLogin.LoggedIn;
							App42API.initialize(Splashscreen.this,
									AppState.API_KEY, AppState.SECRET_KEY);
							App42API.setLoggedInUser(AppState.UID);
							Util.registerWithApp42("1095534075921");
							Intent pageIntent = new Intent(Splashscreen.this,
									HomeActivity.class);
							startActivity(pageIntent);
						}
					}
				}
			} else {
				Toast toast = Toast.makeText(getBaseContext(),
						"Unable to connect network", Toast.LENGTH_LONG);
				toast.show();
			}
			finish();
			// System.exit(0);
		}
	}

	@Override
	public void onBackPressed() {
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splashscreen, menu);
		return true;
	}

}