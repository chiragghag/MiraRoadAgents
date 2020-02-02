/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.krish512.k_exchange.ListAdapter.Type;
import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.AppState.enumLayout;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
public class MyPropertyActivity extends AppCompatActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	FrameLayout pbProgress;
	TextView navHeading;
	TextView txtMyProfile;
	TextView txtMyProperties;
	TextView txtMyNoticeboard;
	TextView txtMyCityTown;
	TextView txtMyAccount;
	TextView txtProperties;
	TextView btnAddProperty;
	TextView txtAgents;
	TextView txtNoticeboard;
	TextView txtCount;
	ListView listProperties;

	ArrayList<Item> items;
	ArrayList<String> selectedProperties;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myproperty);

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
		txtMyProperties.setText(txtMyProperties.getText() + "*");
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

		btnAddProperty = (TextView) findViewById(R.id.txtAddProperties);
		btnAddProperty.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (AppState.MaxProperties <= AppState.PropertiesCount) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MyPropertyActivity.this);

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
											MyPropertyActivity.this,
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
					Intent intent = new Intent(view.getContext(),
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

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		AppState.currentLayout = enumLayout.MyProperties;

		final TextView txtSell = (TextView) findViewById(R.id.txtSell);
		final TextView txtRent = (TextView) findViewById(R.id.txtRent);
		TextView txtRefresh = (TextView) findViewById(R.id.txtRefresh);
		TextView txtDelete = (TextView) findViewById(R.id.txtDelete);
		TextView txtAddProperty = (TextView) findViewById(R.id.txtAddProperty);
		txtCount = (TextView) findViewById(R.id.Count);
		pbProgress = (FrameLayout) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.VISIBLE);

		new MyAsyncTask().execute();

		listProperties = (ListView) findViewById(R.id.listProperties);

		listProperties.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView rowPID = (TextView) view.findViewById(R.id.rowPID);
				String PID = rowPID.getText().toString();
				int index = -1;

				for (int i = 0; (i < LoadData.MyProperties.properties.length)
						&& (index == -1); i++) {
					if (LoadData.MyProperties.properties[i].pid
							.contentEquals(PID)) {
						index = i;
					}
				}
				AppState.currentPID = PID;
				AppState.currentPIDIndex = index;
				Intent intent = new Intent(view.getContext(),
						InfoPropertyActivity.class);
				startActivity(intent);
				finish();
			}
		});

		TextView txtAbbreviations = (TextView) findViewById(R.id.txtAbbreviations);
		txtAbbreviations.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						AbbreviationsActivity.class);
				startActivity(intent);
			}
		});

		TextView txtHelp = (TextView) findViewById(R.id.txtHelp);
		txtHelp.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						HelpActivity.class);
				startActivity(intent);
			}
		});

		txtSell.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				pbProgress.setVisibility(View.VISIBLE);
				txtRent.setBackgroundResource(R.drawable.clicklink);
				txtSell.setTextAppearance(getApplicationContext(),
						R.style.ClickLinkSmallSelected);
				txtSell.setBackgroundResource(R.drawable.clicklinkpressed);
				AppState.currentSellRent = "SELL";
				items = new ArrayList<Item>();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				if (LoadData.MyProperties.properties != null) {
					for (int i = 0; i < LoadData.MyProperties.properties.length; i++) {
						if (AppState.currentSellRent
								.equalsIgnoreCase(LoadData.MyProperties.properties[i].sellrent)) {
							long days = ((((new Date().getTime() - LoadData.MyProperties.properties[i].lastupdate
									.getTime()) / 1000) / 60) / 60) / 24;
							Boolean isRed = (days > AppState.OldPropertyDays) ? true
									: false;
							items.add(new Item(
									AppState.currentSellRent
											.equalsIgnoreCase("SELL") ? new DecimalFormat(
											"##,##,###")
											.format(LoadData.MyProperties.properties[i].cost)
											: ((LoadData.MyProperties.properties[i].cost >= AppState.RateThreshold) ? ""
													: "@")
													+ new DecimalFormat(
															"##,##,###")
															.format(LoadData.MyProperties.properties[i].rent),
									LoadData.MyProperties.properties[i].type,
									LoadData.MyProperties.properties[i].locality,
									LoadData.MyProperties.properties[i].area
											+ " SqFt",
									LoadData.MyProperties.properties[i].floor,
									LoadData.MyProperties.properties[i].address,
									LoadData.MyProperties.properties[i].rescom,
									LoadData.MyProperties.properties[i].directside,
									LoadData.MyProperties.properties[i].sellrent
											.substring(0, 1).toUpperCase(),
									LoadData.MyProperties.properties[i].pid,
									formatter
											.format(LoadData.MyProperties.properties[i].lastupdate),
									"View", isRed));
						}
					}

					Collections.sort(items, new Comparator<Object>() {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"dd MMM yy");
						Date date1 = new Date();
						Date date2 = new Date();

						public int compare(Object o1, Object o2) {
							Item p1 = (Item) o1;
							Item p2 = (Item) o2;
							try {
								date1 = formatter.parse(p1.getRowDate()
										.toString());
								date2 = formatter.parse(p2.getRowDate()
										.toString());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							return date2.compareTo(date1);
						}

					});

					ListAdapter adapter = new ListAdapter(getBaseContext(),
							items, Type.MYPROPERTY);
					listProperties.setAdapter(adapter);
					String i = Integer.toString(listProperties.getCount());
					txtCount.setText(i + " Properties");
				} else {
					Toast toast = Toast.makeText(getBaseContext(),
							"No Properties Found", Toast.LENGTH_LONG);
					toast.show();
				}
				pbProgress.setVisibility(View.GONE);
			}
		});

		txtRent.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				pbProgress.setVisibility(View.VISIBLE);
				txtSell.setBackgroundResource(R.drawable.clicklink);
				txtRent.setTextAppearance(getApplicationContext(),
						R.style.ClickLinkSmallSelected);
				txtRent.setBackgroundResource(R.drawable.clicklinkpressed);
				AppState.currentSellRent = "RENT";
				items = new ArrayList<Item>();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				if (LoadData.MyProperties.properties != null) {
					for (int i = 0; i < LoadData.MyProperties.properties.length; i++) {
						if (AppState.currentSellRent
								.equalsIgnoreCase(LoadData.MyProperties.properties[i].sellrent)) {
							long days = ((((new Date().getTime() - LoadData.MyProperties.properties[i].lastupdate
									.getTime()) / 1000) / 60) / 60) / 24;
							Boolean isRed = (days > AppState.OldPropertyDays) ? true
									: false;
							items.add(new Item(
									new DecimalFormat("##,##,###")
											.format(LoadData.MyProperties.properties[i].rent),
									LoadData.MyProperties.properties[i].type,
									LoadData.MyProperties.properties[i].locality,
									LoadData.MyProperties.properties[i].area
											+ " SqFt",
									LoadData.MyProperties.properties[i].floor,
									LoadData.MyProperties.properties[i].address,
									LoadData.MyProperties.properties[i].rescom,
									LoadData.MyProperties.properties[i].directside,
									LoadData.MyProperties.properties[i].sellrent
											.substring(0, 1).toUpperCase(),
									LoadData.MyProperties.properties[i].pid,
									formatter
											.format(LoadData.MyProperties.properties[i].lastupdate),
									"View/Edit", isRed));
						}
					}

					Collections.sort(items, new Comparator<Object>() {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"dd MMM yy");
						Date date1 = new Date();
						Date date2 = new Date();

						public int compare(Object o1, Object o2) {
							Item p1 = (Item) o1;
							Item p2 = (Item) o2;
							try {
								date1 = formatter.parse(p1.getRowDate()
										.toString());
								date2 = formatter.parse(p2.getRowDate()
										.toString());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							return date2.compareTo(date1);
						}

					});

					ListAdapter adapter = new ListAdapter(getBaseContext(),
							items, Type.MYPROPERTY);
					listProperties.setAdapter(adapter);
					String i = Integer.toString(listProperties.getCount());
					txtCount.setText(i + " Properties");
				} else {
					Toast toast = Toast.makeText(getBaseContext(),
							"No Properties Found", Toast.LENGTH_LONG);
					toast.show();
				}
				pbProgress.setVisibility(View.GONE);
			}
		});

		if (AppState.currentSellRent.equalsIgnoreCase("SELL")) {
			txtRent.setBackgroundResource(R.drawable.clicklink);
			txtSell.setTextAppearance(getApplicationContext(),
					R.style.ClickLinkSmallSelected);
			txtSell.setBackgroundResource(R.drawable.clicklinkpressed);
		} else {
			txtSell.setBackgroundResource(R.drawable.clicklink);
			txtRent.setTextAppearance(getApplicationContext(),
					R.style.ClickLinkSmallSelected);
			txtRent.setBackgroundResource(R.drawable.clicklinkpressed);
		}

		txtRefresh.setOnClickListener(new OnClickListener() {

			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (!listProperties.getAdapter().isEmpty()) {
					selectedProperties = new ArrayList<String>();
					selectedProperties.clear();
					android.widget.ListAdapter la = listProperties.getAdapter();
					for (int i = 0; i < la.getCount(); i++) {
						Item item = (Item) la.getItem(i);
						if (item.isSelected()) {
							selectedProperties.add(item.getRowPID().toString());
						}
					}
					if (selectedProperties.isEmpty()) {
						Toast toast = Toast.makeText(MyPropertyActivity.this,
								"No Properties Selected", Toast.LENGTH_LONG);
						toast.show();
					} else {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								MyPropertyActivity.this);

						builder.setTitle("Refresh Property");
						builder.setMessage("Do you want to refresh "
								+ selectedProperties.size() + " properties?");

						builder.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									@SuppressWarnings("unchecked")
									public void onClick(DialogInterface dialog,
											int which) {
										pbProgress.setVisibility(View.VISIBLE);
										List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
										params.add(new BasicNameValuePair(
												"PID", AppState.currentPID));
										new RefreshAsyncTask()
												.execute(selectedProperties);
									}
								});

						builder.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// I do not need any action here you
										// might
										dialog.dismiss();
									}
								});

						AlertDialog alert = builder.create();
						alert.show();
					}
				}
			}
		});

		txtDelete.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (!listProperties.getAdapter().isEmpty()) {
					selectedProperties = new ArrayList<String>();
					selectedProperties.clear();
					android.widget.ListAdapter la = listProperties.getAdapter();
					for (int i = 0; i < la.getCount(); i++) {
						Item item = (Item) la.getItem(i);
						if (item.isSelected()) {
							selectedProperties.add(item.getRowPID().toString());
						}
					}
					if (selectedProperties.isEmpty()) {
						Toast toast = Toast.makeText(MyPropertyActivity.this,
								"No Properties Selected", Toast.LENGTH_LONG);
						toast.show();
					} else {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								MyPropertyActivity.this);

						builder.setTitle("Delete Property");
						builder.setMessage("Do you want to delete "
								+ selectedProperties.size() + " properties?");

						builder.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									@SuppressWarnings("unchecked")
									public void onClick(DialogInterface dialog,
											int which) {
										pbProgress.setVisibility(View.VISIBLE);
										List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
										params.add(new BasicNameValuePair(
												"PID", AppState.currentPID));
										new DeleteAsyncTask()
												.execute(selectedProperties);
									}
								});

						builder.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// I do not need any action here you
										// might
										dialog.dismiss();
									}
								});

						AlertDialog alert = builder.create();
						alert.show();
					}
				}
			}
		});

		txtAddProperty.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (AppState.MaxProperties <= AppState.PropertiesCount) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MyPropertyActivity.this);

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
											MyPropertyActivity.this,
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
					Intent intent = new Intent(MyPropertyActivity.this,
							AddPropertyActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			if (LoadData.MyProperties.properties == null) {
				return Operation.getMyProperties();
			} else {
				return true;
			}
		}

		@SuppressWarnings("deprecation")
		protected void onPostExecute(Boolean result) {
			if (result) {
				Boolean showAlert = false;
				items = new ArrayList<Item>();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				for (int i = 0; i < LoadData.MyProperties.properties.length; i++) {
					if (AppState.currentSellRent
							.equalsIgnoreCase(LoadData.MyProperties.properties[i].sellrent)) {

						long days = ((((new Date().getTime() - LoadData.MyProperties.properties[i].lastupdate
								.getTime()) / 1000) / 60) / 60) / 24;
						Boolean isRed = (days > AppState.OldPropertyDays) ? true
								: false;
						showAlert = (isRed ? true : showAlert);

						items.add(new Item(
								AppState.currentSellRent
										.equalsIgnoreCase("SELL") ? ((LoadData.MyProperties.properties[i].cost >= AppState.RateThreshold) ? ""
										: "@")
										+ new DecimalFormat("##,##,###")
												.format(LoadData.MyProperties.properties[i].cost)
										: new DecimalFormat("##,##,###")
												.format(LoadData.MyProperties.properties[i].rent),
								LoadData.MyProperties.properties[i].type,
								LoadData.MyProperties.properties[i].locality,
								LoadData.MyProperties.properties[i].area
										+ " SqFt",
								LoadData.MyProperties.properties[i].floor,
								LoadData.MyProperties.properties[i].address,
								LoadData.MyProperties.properties[i].rescom,
								LoadData.MyProperties.properties[i].directside,
								LoadData.MyProperties.properties[i].sellrent
										.substring(0, 1).toUpperCase(),
								LoadData.MyProperties.properties[i].pid,
								formatter
										.format(LoadData.MyProperties.properties[i].lastupdate),
								"View/Edit", isRed, false));
					}
				}

				Collections.sort(items, new Comparator<Object>() {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd MMM yy");
					Date date1 = new Date();
					Date date2 = new Date();

					public int compare(Object o1, Object o2) {
						Item p1 = (Item) o1;
						Item p2 = (Item) o2;
						try {
							date1 = formatter.parse(p1.getRowDate().toString());
							date2 = formatter.parse(p2.getRowDate().toString());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return date1.compareTo(date2);
					}
				});

				ListAdapter adapter = new ListAdapter(getBaseContext(), items,
						Type.MYPROPERTY);
				listProperties.setAdapter(adapter);
				String i = Integer.toString(listProperties.getCount());
				txtCount.setText(i + " Properties");
				if (showAlert) {
					AlertDialog alertDialog = new AlertDialog.Builder(
							MyPropertyActivity.this).create();
					alertDialog.setTitle("Join Mira Road Agents");
					alertDialog
							.setMessage("Hi "
									+ AppState.AName
									+ "!  Refresh you properties from time to time. Properties older than 15 Days will not be included in K-Exchange Print Issue.");
					alertDialog.setButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							});
					alertDialog.show();
				}
			} else {
				Toast toast = Toast.makeText(getBaseContext(),
						"No Properties found", Toast.LENGTH_LONG);
				toast.show();
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_property, menu);
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
		Intent intent;
		switch (item.getItemId()) {
		case R.id.action_addproperty:
			if (AppState.MaxProperties <= AppState.PropertiesCount) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MyPropertyActivity.this);

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
										MyPropertyActivity.this,
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
				intent = new Intent(MyPropertyActivity.this,
						AddPropertyActivity.class);
				startActivity(intent);
				finish();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class RefreshAsyncTask extends
			AsyncTask<ArrayList<String>, String, String> {

		@Override
		protected String doInBackground(ArrayList<String>... selectedProperties) {
			// TODO Auto-generated method stub
			String reply = null, PIDs = "";
			String APP_TAG = null;
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			for (String PID : selectedProperties[0]) {
				PIDs += PID + ",";
			}
			PIDs = PIDs.substring(0, PIDs.length() - 1);
			Log.d(APP_TAG, "PID: " + PIDs);
			params.add(new BasicNameValuePair("PID", PIDs));
			try {
				reply = Operation.postHttpResponse(new URI(AppState.absoluteUri
						+ "refreshmyproperty.php"), params);
				Log.d(APP_TAG, reply);
				String[] test = reply.split(":");
				if (test[0].equalsIgnoreCase("Error") == true) {
					return "Error: " + test[1];
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return reply;
		}

		protected void onPostExecute(String result) {
			String[] reply = result.split(":");
			Toast toast = Toast.makeText(getBaseContext(), reply[1],
					Toast.LENGTH_LONG);
			toast.show();
			pbProgress.setVisibility(View.GONE);
			if (reply[0].equalsIgnoreCase("Error") == false) {
				LoadData.MyProperties.properties = null;
				Intent intent = new Intent(getBaseContext(),
						MyPropertyActivity.class);
				startActivity(intent);
				finish();
			}
		}
	}

	private class DeleteAsyncTask extends
			AsyncTask<ArrayList<String>, String, String> {

		@Override
		protected String doInBackground(ArrayList<String>... selectedProperties) {
			// TODO Auto-generated method stub
			String reply = null, PIDs = "";
			String APP_TAG = null;
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			for (String PID : selectedProperties[0]) {
				PIDs += PID + ",";
			}
			PIDs = PIDs.substring(0, PIDs.length() - 1);
			Log.d(APP_TAG, "PID: " + PIDs);
			params.add(new BasicNameValuePair("PID", PIDs));
			try {
				reply = Operation.postHttpResponse(new URI(AppState.absoluteUri
						+ "deletemyproperty.php"), params);
				Log.d(APP_TAG, reply);
				String[] test = reply.split(":");
				if (test[0].equalsIgnoreCase("Error") == true) {
					return "Error: " + test[1];
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return reply;
		}

		protected void onPostExecute(String result) {
			String[] reply = result.split(":");
			Toast toast = Toast.makeText(getBaseContext(), reply[1],
					Toast.LENGTH_LONG);
			toast.show();
			pbProgress.setVisibility(View.GONE);
			if (reply[0].equalsIgnoreCase("Error") == false) {
				LoadData.MyProperties.properties = null;
				Intent intent = new Intent(getBaseContext(),
						MyPropertyActivity.class);
				startActivity(intent);
				finish();
			}
		}
	}

}
