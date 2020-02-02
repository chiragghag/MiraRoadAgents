/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.AppState.enumLayout;
import com.krish512.k_exchange.R;

@SuppressLint("SimpleDateFormat")
public class MyAccountActivity extends AppCompatActivity {

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
	TextView txtMembershipDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_account);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_close /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				// getSupportActionBar().setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				// getSupportActionBar().setTitle(mTitle);
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

		// txtMyProfile = (TextView) findViewById(R.id.txtMyProfile);
		// txtMyProfile.setOnClickListener(new OnClickListener() {
		// @Override
		// // On click function
		// public void onClick(View view) {
		// // Create the intent to start another activity
		// Intent intent = new Intent(view.getContext(),
		// ViewProfileActivity.class);
		// startActivity(intent);
		// finish();
		// }
		// });

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
		txtMyAccount.setText(txtMyAccount.getText() + "*");
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
							MyAccountActivity.this).create();
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
					Intent intent = new Intent(MyAccountActivity.this,
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

		txtMembershipDetails = (TextView) findViewById(R.id.txtMembershipDetails);
		txtMembershipDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(),
						MembershipDetailsActivity.class);
				startActivity(intent);
			}
		});

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		AppState.currentLayout = enumLayout.MyAccount;

		final TextView txtNo1 = (TextView) findViewById(R.id.No1);
		final TextView txtNo2 = (TextView) findViewById(R.id.No2);

		TextView btnCall1 = (TextView) findViewById(R.id.btnCall1);
		btnCall1.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:" + txtNo1.getText().toString()));
				startActivity(callIntent);
			}
		});

		TextView btnCall2 = (TextView) findViewById(R.id.btnCall2);
		btnCall2.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:" + txtNo2.getText().toString()));
				startActivity(callIntent);
			}
		});

		TextView valAccountType = (TextView) findViewById(R.id.valAccountType);
		TextView valMembershipPeriod = (TextView) findViewById(R.id.valMembershipPeriod);
		TextView txtDuration = (TextView) findViewById(R.id.txtDuration);
		TextView valStatus = (TextView) findViewById(R.id.valStatus);
		if (AppState.Paid.equalsIgnoreCase("PAID")) {
			valAccountType.setText("K-Exchange Member");
			valAccountType.setTextColor(getResources().getColor(
					R.color.themeGREEN));
			valStatus.setText("Active");
			valMembershipPeriod.setTextColor(getResources().getColor(
					R.color.themeGREEN));
			if ((!AppState.PaidStartDate.equalsIgnoreCase("null"))
					&& (!AppState.PaidEndDate.equalsIgnoreCase("null"))) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy");
				try {
					txtDuration
							.setText(date.format(formatter
									.parse(AppState.PaidStartDate))
									+ " - "
									+ date.format(formatter
											.parse(AppState.PaidEndDate)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtDuration.setVisibility(View.VISIBLE);
				txtDuration.setTextColor(getResources().getColor(
						R.color.themeGREEN));
			}
		} else {
			valAccountType.setText("Non member / Free");
			valAccountType.setTextColor(getResources().getColor(
					R.color.themeRED));
			valStatus.setText("Inactive");
			valMembershipPeriod.setText("NA");
			valMembershipPeriod.setTextColor(getResources().getColor(
					R.color.themeRED));
			if ((!AppState.PaidStartDate.equalsIgnoreCase("null"))
					&& (!AppState.PaidEndDate.equalsIgnoreCase("null"))) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat date = new SimpleDateFormat("dd MMMMM yyyy");
				try {
					txtDuration
							.setText(date.format(formatter
									.parse(AppState.PaidStartDate))
									+ " - "
									+ date.format(formatter
											.parse(AppState.PaidEndDate)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtDuration.setVisibility(View.VISIBLE);
				txtDuration.setTextColor(getResources().getColor(
						R.color.themeRED));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account, menu);
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
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

}
