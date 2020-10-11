/**
 * Author: Krishna Modi
 * Contact: krish512@hotmail.com
 */
package com.krish512.k_exchange;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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

	EditText otpEditText;
	Button sendOtpTextView;
	String generatedOTP = "!@#$%^&";

	Handler handler = new Handler();

	String mobileLoginType = "otp";

	TextView otpText, passwordText;

	String checkType = "";

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
		otpText = findViewById(R.id.otpText);
		passwordText = findViewById(R.id.passwordText);

		otpEditText = findViewById(R.id.et_otp);
		sendOtpTextView = findViewById(R.id.sendOTP);

		sendOtpTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (txtEmail.getText().toString().isEmpty()) {
					txtEmail.setError("Please enter mail/number");
					return;
				}


				checkUserExists();
			}
		});

		txtEmail.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {

				if (s.toString().isEmpty()) {
					findViewById(R.id.passwordLayout).setVisibility(GONE);
					findViewById(R.id.otpLayout).setVisibility(View.GONE);
					findViewById(R.id.chooseLayout).setVisibility(View.GONE);
					return;
				}

				if (onlyDigits(s.toString())) {
					findViewById(R.id.passwordLayout).setVisibility(GONE);
					findViewById(R.id.otpLayout).setVisibility(View.VISIBLE);
					findViewById(R.id.chooseLayout).setVisibility(View.VISIBLE);

				} else {
					findViewById(R.id.passwordLayout).setVisibility(View.VISIBLE);
					findViewById(R.id.otpLayout).setVisibility(GONE);
					findViewById(R.id.chooseLayout).setVisibility(View.GONE);
				}
			}
		});

		otpText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mobileLoginType.equals("password")) {
					otpText.setTypeface(null, Typeface.BOLD);
					passwordText.setTypeface(null, Typeface.NORMAL);
					mobileLoginType = "otp";
					findViewById(R.id.passwordLayout).setVisibility(GONE);
					findViewById(R.id.otpLayout).setVisibility(View.VISIBLE);
				}
			}
		});

		passwordText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mobileLoginType.equals("otp")) {
					otpText.setTypeface(null, Typeface.NORMAL);
					passwordText.setTypeface(null, Typeface.BOLD);
					mobileLoginType = "password";
					findViewById(R.id.passwordLayout).setVisibility(View.VISIBLE);
					findViewById(R.id.otpLayout).setVisibility(GONE);
				}
			}
		});

		btnLogin.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			// On click function
			public void onClick(View view) {

				checkType = "userLogin";

				if (mobileLoginType.equals("otp")
						&& onlyDigits(txtEmail.getText().toString())
						&& otpEditText.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "Please enter otp for mobile login", Toast.LENGTH_LONG).show();
					return;
				}

				if (mobileLoginType.equals("otp")
						&& onlyDigits(txtEmail.getText().toString())
						&& !otpEditText.getText().toString().equals(generatedOTP)) {
					Toast.makeText(LoginActivity.this, "Incorrect OTP for mobile login", Toast.LENGTH_LONG).show();
					return;
				}

				// String devID = Secure.getString(getBaseContext()
				// .getContentResolver(), Secure.ANDROID_ID);
				List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("email", txtEmail.getText()
						.toString()));
				params.add(new BasicNameValuePair("password", Operation
						.MD5(txtPassword.getText().toString())));
				params.add(new BasicNameValuePair("deviceid", AppState.DeviceID));
				if (findViewById(R.id.otpLayout).getVisibility() == VISIBLE) {
					params.add(new BasicNameValuePair("otp", "!@#$%^&"));
				}

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

	public void checkUserExists() {

		checkType = "userExist";

		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("email", txtEmail.getText()
				.toString()));

		pbProgress.setVisibility(View.VISIBLE);
		new MyAsyncTask().execute(params);

	}


	public boolean onlyDigits(String str) {
		// Regex to check string
		// contains only digits
		String regex = "[0-9]+";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the string is empty
		// return false
		if (str == null) {
			return false;
		}

		// Find match between given string
		// and regular expression
		// using Pattern.matcher()
		Matcher m = p.matcher(str);

		// Return if the string
		// matched the ReGex
		return m.matches();
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
				if (checkType.equals("userExist")
						&& !result.toLowerCase().contains("user does not exist")
						&& !result.toLowerCase().contains("unappropriate")) {
					sendOTP();
					pbProgress.setVisibility(GONE);
					return;
				}
				Toast toast = Toast.makeText(getBaseContext(), result,
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				Intent intent = new Intent(getBaseContext(), HomeActivity.class);
				startActivity(intent);
				finish();
			}
			pbProgress.setVisibility(GONE);
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

	public void sendOTP() {

		sendOtpTextView.setEnabled(false);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				sendOtpTextView.setEnabled(true);
			}
		}, 60000);

		new CountDownTimer(60000, 1000) { // adjust the milli seconds here

			public void onTick(long millisUntilFinished) {
				sendOtpTextView.setText("" + String.format("%02d:%02d",
						TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
						TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
								TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
			}

			public void onFinish() {
				sendOtpTextView.setText("Re-send otp");
			}

		}.start();

		generatedOTP = String.format(Locale.ENGLISH, "%04d", new Random().nextInt(9999));

		final Map<String, String> param = new HashMap<>();
		param.put("sender_id", "FSTSMS");
		param.put("language", "english");
		param.put("route", "qt");
		param.put("numbers", txtEmail.getText().toString());
		param.put("message", "37698");
		param.put("variables", "{AA}");
		param.put("variables_values", generatedOTP);

		StringRequest postRequest = new StringRequest(Request.Method.POST, "https://www.fast2sms.com/dev/bulk",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Toast.makeText(LoginActivity.this, "OTP sent successfully", Toast.LENGTH_LONG).show();
						txtEmail.setEnabled(false);
					}

				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(LoginActivity.this, "Failure!!!", Toast.LENGTH_LONG).show();
					}
				}
		) {
			@Override
			protected Map<String, String> getParams() {
				return param;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("authorization", getResources().getString(R.string.otp_api_key));
				headers.put("cache-control", "no-cache");
				headers.put("device", "application/x-www-form-urlencoded");

				return headers;
			}
		};

		postRequest.setRetryPolicy(new DefaultRetryPolicy(0,
				-1,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

		Volley.newRequestQueue(this).add(postRequest);
	}


}