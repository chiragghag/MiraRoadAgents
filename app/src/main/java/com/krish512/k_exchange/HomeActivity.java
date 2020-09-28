/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import 	androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.R;

@SuppressLint("SimpleDateFormat")
public class HomeActivity extends AppCompatActivity {

	TextView btnMyProfile;
	TextView btnMyProperties;
	TextView btnMyNoticeboard;
	TextView btnAddProperty;
	TextView btnMyCityTown;
	TextView btnMyAccount;
	TextView btnProperties;
	TextView btnAgents;
	TextView btnNoticeboard;
	TextView btnContactUs;
	TextView Heading;
	TextView ShareBlock;
	ListView listMyMenu;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		AppState.loginState = AppState.enumLogin.LoggedIn;

		btnMyProfile = (TextView) findViewById(R.id.txtMyProfile);
		btnMyProperties = (TextView) findViewById(R.id.txtMyProperties);
		btnMyNoticeboard = (TextView) findViewById(R.id.txtMyNoticeboard);
		btnAddProperty = (TextView) findViewById(R.id.txtAddProperty);
		btnMyCityTown = (TextView) findViewById(R.id.txtMyCityTown);
		btnMyAccount = (TextView) findViewById(R.id.txtMyAccount);
		btnProperties = (TextView) findViewById(R.id.txtProperties);
		btnAgents = (TextView) findViewById(R.id.txtAgents);
		btnNoticeboard = (TextView) findViewById(R.id.txtNoticeboard);
		btnContactUs = (TextView) findViewById(R.id.txtContactUs);
		Heading = (TextView) findViewById(R.id.Heading);
		Heading.setText(AppState.BName.toString());
		ShareBlock = (TextView) findViewById(R.id.ShareBlock);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy");

		// try {
		// txtDuration
		// .setText(date.format(formatter
		// .parse(AppState.PaidStartDate))
		// + " - "
		// + date.format(formatter
		// .parse(AppState.PaidEndDate)));
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		Date paidEnd = new Date();
		try {
			paidEnd = formatter.parse(AppState.PaidEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(paidEnd);
		cal.add(Calendar.MONTH, -1);
		Date paidEndBefore = cal.getTime();

		if (AppState.Paid.equalsIgnoreCase("UNPAID")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Contact Us");
			builder.setMessage("Hi "
					+ AppState.AName
					+ "! You have limited access to this App. Join Mira Road Agents & get full access to the App.");

			builder.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(HomeActivity.this,
									HelpActivity.class);
							startActivity(intent);
						}
					});

			builder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// I do not need any action here you might
							dialog.dismiss();
						}
					});

			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		} else if (paidEndBefore.compareTo(new Date()) <= 0) {
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Join Mira Road Agents");
			alertDialog.setMessage("Hi " + AppState.AName
					+ "! Your Mira Road Agents membership expires on "
					+ date.format(paidEnd) + ". Renew your membership before "
					+ date.format(paidEnd) + " & get Loyalty Discount.");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			alertDialog.show();
		}

		btnMyProfile.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						ViewProfileActivity.class);
				startActivity(intent);
			}
		});

		btnMyProperties.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						MyPropertyActivity.class);
				startActivity(intent);
			}
		});

		btnMyNoticeboard.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (AppState.Paid.equalsIgnoreCase("UNPAID")) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							HomeActivity.this);

					builder.setTitle("Contact Us");
					builder.setMessage("Hi "
							+ AppState.AName
							+ "! You have limited access to this App. Join Mira Road Agents & get full access to the App.");

					builder.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											HomeActivity.this,
											HelpActivity.class);
									startActivity(intent);
								}
							});

					builder.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// I do not need any action here you might
									dialog.dismiss();
								}
							});

					AlertDialog alertDialog = builder.create();
					alertDialog.show();
				} else {
					Intent intent = new Intent(view.getContext(),
							MyNoticeboardActivity.class);
					startActivity(intent);
				}
			}
		});

		btnAddProperty.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (AppState.MaxProperties <= AppState.PropertiesCount) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							HomeActivity.this);

					builder.setTitle("Contact Us");
					builder.setMessage("Hi "
							+ AppState.AName
							+ "! You have reached you Property Limit of "
							+ AppState.MaxProperties
							+ " prop. You can delete 'old / Sold out' properties & add a new property. If you wish to increase your property listing limit contact Customer Care.");

					builder.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											HomeActivity.this,
											HelpActivity.class);
									startActivity(intent);
								}
							});

					builder.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// I do not need any action here you might
									dialog.dismiss();
								}
							});

					AlertDialog alertLimitDialog = builder.create();
					alertLimitDialog.show();
				} else {
					Intent intent = new Intent(HomeActivity.this,
							AddPropertyActivity.class);
					startActivity(intent);
				}
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

		btnMyAccount.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						MyAccountActivity.class);
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

		btnNoticeboard.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						NoticeboardActivity.class);
				startActivity(intent);
			}
		});

		btnContactUs.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(HomeActivity.this,
						HelpActivity.class);
				startActivity(intent);
			}
		});

		ShareBlock.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent
						.putExtra(
								Intent.EXTRA_TEXT,
								"Hi! I'm now using 'Mira Road Agents App By Brokers For Brokers'. Free download from Play Store. https://play.google.com/store/apps/developer?id=K-SOLUTIONS");
				sendIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendIntent, getResources()
						.getText(R.string.share)));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.action_logout:
			SharedPreferences settings = getSharedPreferences("UserInfo", 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("UID", "");
			editor.remove("UID");
			editor.commit();
			LoadData.Properties.properties = null;
			LoadData.MyProperties.properties = null;
			LoadData.Agents.agents = null;
			AppState.loginState = AppState.enumLogin.LoggedOut;
			AppState.Paid = "UNPAID";
			AppState.MaxProperties = 0;
			intent = new Intent(this.getBaseContext(), LoginActivity.class);
			startActivity(intent);
			finish();
			return true;
		case R.id.action_share:
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent
					.putExtra(
							Intent.EXTRA_TEXT,
							"Hi! I'm now using 'Mira Road Agents App By Brokers For Brokers'. Free download from Play Store. https://play.google.com/store/apps/developer?id=K-SOLUTIONS");
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, getResources()
					.getText(R.string.share)));
			return true;
		case R.id.action_about:
			intent = new Intent(this.getBaseContext(), AboutActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}