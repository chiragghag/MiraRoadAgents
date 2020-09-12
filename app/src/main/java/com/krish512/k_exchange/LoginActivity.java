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
import 	androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

public class LoginActivity extends AppCompatActivity {

	TextView txtEmail;
	TextView txtPassword;
	TextView btnForgot;
	TextView btnRegister;
	TextView btnProperties;
	TextView btnAgents;
	TextView btnMyCityTown;
	TextView btnContactUs;
	Button btnLogin;
	ProgressBar pbProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		LoadData.Properties.properties = null;
		LoadData.MyProperties.properties = null;
		LoadData.Agents.agents = null;
		AppState.loginState = AppState.enumLogin.LoggedOut;

		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnForgot = (TextView) findViewById(R.id.txtForgot);
		btnRegister = (TextView) findViewById(R.id.txtNewUser);
		btnProperties = (TextView) findViewById(R.id.txtProperties);
		btnAgents = (TextView) findViewById(R.id.txtAgents);
		btnMyCityTown = (TextView) findViewById(R.id.txtMyCityTown);
		btnContactUs = (TextView) findViewById(R.id.txtContactUs);
		txtEmail = (TextView) findViewById(R.id.editEmail);
		txtPassword = (TextView) findViewById(R.id.editPassword);
		pbProgress = (ProgressBar) findViewById(R.id.pbProgess);

		btnLogin.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			// On click function
			public void onClick(View view) {
				// String devID = Secure.getString(getBaseContext()
				// .getContentResolver(), Secure.ANDROID_ID);
				List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("email", txtEmail.getText()
						.toString()));
				params.add(new BasicNameValuePair("password", Operation
						.MD5(txtPassword.getText().toString())));
				params.add(new BasicNameValuePair("deviceid", AppState.DeviceID));
				Log.d(null, "Device id: " + AppState.DeviceID);
				pbProgress.setVisibility(View.VISIBLE);
				new MyAsyncTask().execute(params);
			}
		});

		btnForgot.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				// AlertDialog.Builder builder = new AlertDialog.Builder(
				// LoginActivity.this);
				//
				// builder.setTitle("Call");
				// builder.setMessage("Do you want to call Customer Care to reset your password?");
				//
				// builder.setPositiveButton("Yes",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int which) {
				// Intent callIntent = new Intent(
				// Intent.ACTION_DIAL, Uri
				// .parse("tel:9833452109"));
				// startActivity(callIntent);
				// }
				// });
				//
				// builder.setNegativeButton("NO",
				// new DialogInterface.OnClickListener() {
				//
				// @Override
				// public void onClick(DialogInterface dialog,
				// int which) {
				// // I do not need any action here you might
				// dialog.dismiss();
				// }
				// });
				//
				// AlertDialog alert = builder.create();
				// alert.show();

				Intent intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(AppState.absoluteUri + "ForgotPassword.html"));
				startActivity(intent);
			}
		});

		btnRegister.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						RegisterActivity.class);
				startActivity(intent);
			}
		});

		btnProperties.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						FilterActivity.class);
				startActivity(intent);
			}
		});

		btnAgents.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						AgentActivity.class);
				startActivity(intent);
			}
		});

		btnMyCityTown.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						CityTownActivity.class);
				startActivity(intent);
			}
		});

		btnContactUs.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(LoginActivity.this,
						HelpActivity.class);
				startActivity(intent);
			}
		});

	}

	private class MyAsyncTask extends
			AsyncTask<List<BasicNameValuePair>, String, String> {

		@Override
		protected String doInBackground(List<BasicNameValuePair>... params) {
			// TODO Auto-generated method stub
			try {
				String[] reply = Operation.postHttpResponse(
						new URI(AppState.absoluteUri + "login.php"), params[0])
						.split(":");
				if (reply.length > 1) {
					if (reply[0].equalsIgnoreCase("Error") == true) {
						return "Login Failed: " + reply[1];
					} else {
						AppState.UID = reply[1].equalsIgnoreCase(null) ? ""
								: reply[1];
						if (Operation.getMyProfile()) {
							SharedPreferences settings = getSharedPreferences(
									"UserInfo", 0);
							SharedPreferences.Editor editor = settings.edit();
							editor.putString("UID", AppState.UID);
							editor.putString("Paid", AppState.Paid);
							editor.putString("PaidStartDate",
									AppState.PaidStartDate);
							editor.putString("PaidEndDate",
									AppState.PaidEndDate);
							editor.putString("UserType", AppState.UserType);
							editor.putString("BusinessName", AppState.BName);
							editor.putString("AgentName", AppState.AName);
							editor.putString("City", AppState.City);
							editor.putString("Town", AppState.Town);
							editor.putString("Locality", AppState.Locality);
							editor.putString("Address", AppState.Address);
							editor.putString("Email", AppState.Email);
							editor.putString("Mobile", AppState.Phoneno);
							editor.putString("Alt", AppState.Altno);
							editor.putString("Website", AppState.Website);
							editor.commit();
							return null;
						} else {
							return "Profile Fetch Failed: " + reply[1];
						}
					}
				} else {
					return "HTTP Request unsuccessful, try again";
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String result) {
			if (result != null) {
				Toast toast = Toast.makeText(getBaseContext(), result,
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				Intent intent = new Intent(getBaseContext(), HomeActivity.class);
				startActivity(intent);
				finish();
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_about:
			Intent intent = new Intent(this.getBaseContext(),
					AboutActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
