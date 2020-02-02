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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.AppState.enumLayout;
import com.krish512.k_exchange.Utils.AppState.enumLogin;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

public class MyNoticeboardActivity extends AppCompatActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	TextView navHeading;
	TextView txtMyProfile;
	TextView txtMyProperties;
	TextView txtMyNoticeboard;
	TextView txtMyCityTown;
	TextView txtMyAccount;
	TextView txtAddProperty;
	TextView txtProperties;
	TextView txtAgents;
	TextView txtNoticeboard;
	TextView txtMessage;
	TextView txtCount;
	FrameLayout pbProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_noticeboard);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_close /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
			}
		};

		navHeading = (TextView) findViewById(R.id.navHeading);
		navHeading.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						HomeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});

		txtMyProperties = (TextView) findViewById(R.id.txtMyProperties);
		txtMyProperties.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						MyPropertyActivity.class);
				startActivity(intent);
				finish();
			}
		});

		txtMyCityTown = (TextView) findViewById(R.id.txtMyCityTown);
		txtMyCityTown.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						CityTownActivity.class);
				startActivity(intent);
			}
		});

		txtMyNoticeboard = (TextView) findViewById(R.id.txtMyNoticeboard);
		txtMyNoticeboard.setText(txtMyNoticeboard.getText() + "*");
		txtMyNoticeboard.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						MyNoticeboardActivity.class);
				startActivity(intent);
				finish();
			}
		});

		txtMyAccount = (TextView) findViewById(R.id.txtMyAccount);
		txtMyAccount.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						MyAccountActivity.class);
				startActivity(intent);
			}
		});

		txtAddProperty = (TextView) findViewById(R.id.txtAddProperties);
		txtAddProperty.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (AppState.MaxProperties <= AppState.PropertiesCount) {
					AlertDialog alertDialog = new AlertDialog.Builder(
							MyNoticeboardActivity.this).create();
					alertDialog.setTitle("Join Mira Road Agents");
					alertDialog
							.setMessage("Hi "
									+ AppState.AName
									+ "! You have reached you Property Limit of "
									+ AppState.MaxProperties
									+ " prop. You can delete 'old / Sold out' properties & add a new property. If you wish to increase your property listing limit contact Customer Care.");
					alertDialog.setButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							});
					alertDialog.show();
				} else {
					Intent intent = new Intent(MyNoticeboardActivity.this,
							AddPropertyActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});

		txtProperties = (TextView) findViewById(R.id.txtProperties);
		txtProperties.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						FilterActivity.class);
				startActivity(intent);
				finish();
			}
		});

		txtAgents = (TextView) findViewById(R.id.txtAgents);
		txtAgents.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						AgentActivity.class);
				startActivity(intent);
				finish();
			}
		});

		txtNoticeboard = (TextView) findViewById(R.id.txtNoticeboard);
		txtNoticeboard.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						NoticeboardActivity.class);
				startActivity(intent);
				finish();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		AppState.currentLayout = enumLayout.MyNoticeboard;

		txtMessage = (TextView) findViewById(R.id.valMessage);
		txtMessage.setText(AppState.Notice);
		txtMessage.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				300) });
		txtCount = (TextView) findViewById(R.id.Count);
		txtCount.setText(txtMessage.getLineCount() + " Lines");
		Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
		TextView btnCancel = (TextView) findViewById(R.id.txtCancel);
		pbProgress = (FrameLayout) findViewById(R.id.pbProgess);

		if (AppState.Paid.equalsIgnoreCase("UNPAID")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MyNoticeboardActivity.this);

			builder.setTitle("Contact Us");
			builder.setMessage("Hi "
					+ AppState.AName
					+ "! You have limited access to this App. Join K-Exchange & get full access to the App.");

			builder.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									MyNoticeboardActivity.this,
									HelpActivity.class);
							startActivity(intent);
							finish();
						}
					});

			builder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// I do not need any action here you might
							dialog.dismiss();
							finish();
						}
					});

			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}

		txtMessage.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				txtCount.setText(txtMessage.getLineCount() + " Lines");
			}
		});

		btnUpdate.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				pbProgress.setVisibility(View.VISIBLE);
				new MyAsyncTask().execute();
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				finish();
			}
		});
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... param) {
			String response = null;
			String APP_TAG = null;
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("uid", AppState.UID));
			Log.d(APP_TAG, AppState.UID);
			params.add(new BasicNameValuePair("notice", txtMessage.getText()
					.toString()));
			Log.d(APP_TAG, txtMessage.getText().toString());
			try {
				response = Operation.postHttpResponse(new URI(
						AppState.absoluteUri + "addnotice.php"), params);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return response.toString();
		}

		protected void onPostExecute(String result) {
			String[] reply = result.split(":");

			Log.d(null, reply[0]);
			if (reply[0].equalsIgnoreCase("Success") == false) {
				Toast toast = Toast.makeText(MyNoticeboardActivity.this,
						"Failed: " + reply[1], Toast.LENGTH_LONG);
				toast.show();
			} else {
				LoadData.Notices.notices = null;
				AppState.Notice = txtMessage.getText().toString();
				Toast toast = Toast.makeText(MyNoticeboardActivity.this,
						result, Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_noticeboard, menu);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (AppState.loginState == enumLogin.LoggedIn) {
			if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

}
