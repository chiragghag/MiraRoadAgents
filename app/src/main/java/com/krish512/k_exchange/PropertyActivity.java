/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.krish512.k_exchange.ListAdapter.Type;
import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.AppState.enumLayout;
import com.krish512.k_exchange.Utils.AppState.enumLogin;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
public class PropertyActivity extends AppCompatActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	FrameLayout pbProgress;
	ListView listProperties;
	TextView btnArea;
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
	TextView txtArea;
	TextView txtCount;
	AutoCompleteTextView editSearch;

	ArrayList<Item> items;
	ArrayAdapter<String> adapterSearch;
	ListAdapter adapter;
	String[] searchLocation = {};

	// TextView txtNoticeboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_property);

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
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (AppState.MaxProperties <= AppState.PropertiesCount) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							PropertyActivity.this);

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
											PropertyActivity.this,
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
					Intent intent = new Intent(PropertyActivity.this,
							AddPropertyActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});

		txtProperties = (TextView) findViewById(R.id.txtProperties);
		txtProperties.setText(txtProperties.getText() + "*");
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

		editSearch = (AutoCompleteTextView) findViewById(R.id.editSearch);
		adapterSearch = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		editSearch.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				items.clear();
				String searchString = s.toString();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				if (LoadData.Properties.properties != null) {
					for (int i = 0; i < LoadData.Properties.properties.length; i++) {

						if (searchString.length() <= LoadData.Properties.properties[i].locality
								.length()) {
							if (AppState.currentSellRent
									.equalsIgnoreCase(LoadData.Properties.properties[i].sellrent)) {
								if (LoadData.Properties.properties[i].locality
										.contains(searchString
												.toUpperCase(Locale
														.getDefault()))) {
									long days = ((((new Date().getTime() - LoadData.Properties.properties[i].lastupdate
											.getTime()) / 1000) / 60) / 60) / 24;
									Boolean isRed = (days > AppState.OldPropertyDays) ? true
											: false;
									items.add(new Item(
											AppState.currentSellRent
													.equalsIgnoreCase("SELL") ? ((LoadData.Properties.properties[i].cost >= AppState.RateThreshold) ? ""
													: "@")
													+ new DecimalFormat(
															"##,##,###")
															.format(LoadData.Properties.properties[i].cost)
													: new DecimalFormat(
															"##,##,###")
															.format(LoadData.Properties.properties[i].rent),
											LoadData.Properties.properties[i].type,
											LoadData.Properties.properties[i].locality,
											LoadData.Properties.properties[i].area
													+ " SqFt",
											LoadData.Properties.properties[i].floor,
											LoadData.Properties.properties[i].address,
											LoadData.Properties.properties[i].rescom,
											LoadData.Properties.properties[i].directside,
											LoadData.Properties.properties[i].sellrent
													.substring(0, 1)
													.toUpperCase(),
											LoadData.Properties.properties[i].pid,
											formatter
													.format(LoadData.Properties.properties[i].lastupdate),
											"View", isRed));
								}
							}
						}
					}

					Collections.sort(items, new Comparator<Object>() {

						public int compare(Object o1, Object o2) {
							Item p1 = (Item) o1;
							Item p2 = (Item) o2;
							return p1
									.getRowLocation()
									.toString()
									.compareToIgnoreCase(
											p2.getRowLocation().toString());
						}

					});

					listProperties.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					txtCount.setText(Integer.toString(listProperties.getCount())
							+ " Properties");
				} else {
					Toast toast = Toast.makeText(getBaseContext(),
							"No Properties Found", Toast.LENGTH_LONG);
					toast.show();
				}
				pbProgress.setVisibility(View.GONE);
			}
		});

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		AppState.currentLayout = enumLayout.Properties;

		final TextView txtSell = (TextView) findViewById(R.id.txtSell);
		final TextView txtRent = (TextView) findViewById(R.id.txtRent);
		btnArea = (TextView) findViewById(R.id.Area);
		TextView txtSort = (TextView) findViewById(R.id.txtSort);
		TextView txtFilter = (TextView) findViewById(R.id.txtFilter);
		listProperties = (ListView) findViewById(R.id.listProperties);
		pbProgress = (FrameLayout) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.VISIBLE);
		txtCount = (TextView) findViewById(R.id.Count);
		btnArea.setText(AppState.currentCity.substring(0, 3) + "-"
				+ AppState.currentTown);
		btnArea.setPaintFlags(btnArea.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);

		new MyAsyncTask().execute();

		listProperties.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// RelativeLayout item = (RelativeLayout) view.getParent();
				// Item item = (Item)
				// listProperties.getItemAtPosition(position);
				TextView rowPID = (TextView) view.findViewById(R.id.rowPID);
				String PID = rowPID.getText().toString();
				int index = -1;

				for (int i = 0; (i < LoadData.Properties.properties.length)
						&& (index == -1); i++) {
					if (LoadData.Properties.properties[i].pid
							.contentEquals(PID)) {
						index = i;
					}
				}
				AppState.currentPID = PID;
				AppState.currentPIDIndex = index;
				Intent intent = new Intent(view.getContext(),
						InfoPropertyActivity.class);
				startActivity(intent);
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
				editSearch.setText("");
				txtRent.setBackgroundResource(R.drawable.clicklink);
				txtSell.setTextAppearance(getApplicationContext(),
						R.style.ClickLinkSmallSelected);
				txtSell.setBackgroundResource(R.drawable.clicklinkpressed);
				AppState.currentSellRent = "SELL";
				// items = new ArrayList<Item>();
				items.clear();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				if (LoadData.Properties.properties != null) {
					for (int i = 0; i < LoadData.Properties.properties.length; i++) {
						if (AppState.currentSellRent
								.equalsIgnoreCase(LoadData.Properties.properties[i].sellrent)) {
							long days = ((((new Date().getTime() - LoadData.Properties.properties[i].lastupdate
									.getTime()) / 1000) / 60) / 60) / 24;
							Boolean isRed = (days > AppState.OldPropertyDays) ? true
									: false;
							items.add(new Item(
									((LoadData.Properties.properties[i].cost >= AppState.RateThreshold) ? ""
											: "@")
											+ new DecimalFormat("##,##,###")
													.format(LoadData.Properties.properties[i].cost),
									LoadData.Properties.properties[i].type,
									LoadData.Properties.properties[i].locality,
									LoadData.Properties.properties[i].area
											+ " SqFt",
									LoadData.Properties.properties[i].floor,
									LoadData.Properties.properties[i].address,
									LoadData.Properties.properties[i].rescom,
									LoadData.Properties.properties[i].directside,
									LoadData.Properties.properties[i].sellrent
											.substring(0, 1).toUpperCase(),
									LoadData.Properties.properties[i].pid,
									formatter
											.format(LoadData.Properties.properties[i].lastupdate),
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
					adapter = new ListAdapter(PropertyActivity.this, items,
							Type.PROPERTY);
					listProperties.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					txtCount.setText(Integer.toString(listProperties.getCount())
							+ " Properties");
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
				editSearch.setText("");
				txtSell.setBackgroundResource(R.drawable.clicklink);
				txtRent.setTextAppearance(getApplicationContext(),
						R.style.ClickLinkSmallSelected);
				txtRent.setBackgroundResource(R.drawable.clicklinkpressed);
				AppState.currentSellRent = "RENT";
				// items = new ArrayList<Item>();
				items.clear();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				if (LoadData.Properties.properties != null) {
					for (int i = 0; i < LoadData.Properties.properties.length; i++) {
						if (AppState.currentSellRent
								.equalsIgnoreCase(LoadData.Properties.properties[i].sellrent)) {
							long days = ((((new Date().getTime() - LoadData.Properties.properties[i].lastupdate
									.getTime()) / 1000) / 60) / 60) / 24;
							Boolean isRed = (days > AppState.OldPropertyDays) ? true
									: false;
							items.add(new Item(
									new DecimalFormat("##,##,###")
											.format(LoadData.Properties.properties[i].rent),
									LoadData.Properties.properties[i].type,
									LoadData.Properties.properties[i].locality,
									LoadData.Properties.properties[i].area
											+ " SqFt",
									LoadData.Properties.properties[i].floor,
									LoadData.Properties.properties[i].address,
									LoadData.Properties.properties[i].rescom,
									LoadData.Properties.properties[i].directside,
									LoadData.Properties.properties[i].sellrent
											.substring(0, 1).toUpperCase(),
									LoadData.Properties.properties[i].pid,
									formatter
											.format(LoadData.Properties.properties[i].lastupdate),
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
					adapter = new ListAdapter(PropertyActivity.this, items,
							Type.PROPERTY);
					listProperties.setAdapter(adapter);
					adapter.notifyDataSetChanged();

					txtCount.setText(Integer.toString(listProperties.getCount())
							+ " Properties");
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

		txtFilter.setOnClickListener(new OnClickListener() {
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

		txtSort.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				registerForContextMenu(findViewById(android.R.id.content));
				openContextMenu(findViewById(android.R.id.content));
			}
		});

		btnArea.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						CityTownActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			if (LoadData.Properties.properties == null) {
				return Operation.getProperties();
			} else {
				return true;
			}
		}

		protected void onPostExecute(Boolean result) {
			items = new ArrayList<Item>();
			if (result) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				for (int i = 0; i < LoadData.Properties.properties.length; i++) {
					if (AppState.currentSellRent
							.equalsIgnoreCase(LoadData.Properties.properties[i].sellrent)) {
						long days = ((((new Date().getTime() - LoadData.Properties.properties[i].lastupdate
								.getTime()) / 1000) / 60) / 60) / 24;
						Boolean isRed = (days > AppState.OldPropertyDays) ? true
								: false;
						items.add(new Item(
								AppState.currentSellRent
										.equalsIgnoreCase("SELL") ? ((LoadData.Properties.properties[i].cost >= AppState.RateThreshold) ? ""
										: "@")
										+ new DecimalFormat("##,##,###")
												.format(LoadData.Properties.properties[i].cost)
										: new DecimalFormat("##,##,###")
												.format(LoadData.Properties.properties[i].rent),
								LoadData.Properties.properties[i].type,
								LoadData.Properties.properties[i].locality,
								LoadData.Properties.properties[i].area
										+ " SqFt",
								LoadData.Properties.properties[i].floor,
								LoadData.Properties.properties[i].address,
								LoadData.Properties.properties[i].rescom,
								LoadData.Properties.properties[i].directside,
								LoadData.Properties.properties[i].sellrent
										.substring(0, 1).toUpperCase(),
								LoadData.Properties.properties[i].pid,
								formatter
										.format(LoadData.Properties.properties[i].lastupdate),
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
							date1 = formatter.parse(p1.getRowDate().toString());
							date2 = formatter.parse(p2.getRowDate().toString());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return date2.compareTo(date1);
					}

				});

				adapter = new ListAdapter(PropertyActivity.this, items,
						Type.PROPERTY);
				listProperties.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				txtCount.setText(Integer.toString(listProperties.getCount())
						+ " Properties");
				for (LoadData.Property p : LoadData.Properties.properties) {
					if (adapterSearch.getPosition(p.locality) == -1)
						adapterSearch.add(p.locality);
				}
				editSearch.setAdapter(adapterSearch);
				editSearch.setThreshold(1);
			} else {
				Toast toast = Toast.makeText(getBaseContext(),
						"No Properties Found", Toast.LENGTH_LONG);
				toast.show();
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.sort, menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.property, menu);
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

		Intent intent;
		switch (item.getItemId()) {
		case R.id.action_add:
			if (AppState.loginState == enumLogin.LoggedIn) {
				if (AppState.MaxProperties <= AppState.PropertiesCount) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							PropertyActivity.this);

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
											PropertyActivity.this,
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
					intent = new Intent(PropertyActivity.this,
							AddPropertyActivity.class);
					startActivity(intent);
					finish();
				}
			} else {
				finish();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		// Handle your other action bar items...

		// return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_date:
			Collections.sort(items, new Comparator<Object>() {
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
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
					return date2.compareTo(date1);
				}

			});
			adapter = new ListAdapter(getBaseContext(), items, Type.PROPERTY);
			listProperties.setAdapter(adapter);
			txtCount.setText(Integer.toString(listProperties.getCount())
					+ " Properties");
			return true;

		case R.id.action_rate:
			Collections.sort(items, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					Item p1 = (Item) o1;
					Item p2 = (Item) o2;

					return Integer.valueOf(
							Integer.parseInt(p1.getRowCost().toString()
									.replaceAll("[\\D]", ""))).compareTo(
							Integer.valueOf(Integer.parseInt(p2.getRowCost()
									.toString().replaceAll("[\\D]", ""))));
				}

			});
			adapter = new ListAdapter(getBaseContext(), items, Type.PROPERTY);
			listProperties.setAdapter(adapter);
			txtCount.setText(Integer.toString(listProperties.getCount())
					+ " Properties");
			return true;

		case R.id.action_locality:
			Collections.sort(items, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					Item p1 = (Item) o1;
					Item p2 = (Item) o2;

					return p1
							.getRowLocation()
							.toString()
							.compareToIgnoreCase(p2.getRowLocation().toString());
				}

			});
			adapter = new ListAdapter(getBaseContext(), items, Type.PROPERTY);
			listProperties.setAdapter(adapter);
			txtCount.setText(Integer.toString(listProperties.getCount())
					+ " Properties");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
