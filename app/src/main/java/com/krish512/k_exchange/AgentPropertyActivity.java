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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import 	androidx.appcompat.app.AppCompatActivity;
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
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.Operation;

@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
public class AgentPropertyActivity extends AppCompatActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	FrameLayout pbProgress;
	ListView listProperties;
	TextView btnCall;
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

	// TextView txtNoticeboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_property);

		this.setTitle(LoadData.Agents.agents[AppState.currentAIDIndex].bname);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		//R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
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
			@SuppressWarnings("deprecation")
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (AppState.MaxProperties <= AppState.PropertiesCount) {
					AlertDialog alertDialog = new AlertDialog.Builder(
							AgentPropertyActivity.this).create();
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
					Intent intent = new Intent(AgentPropertyActivity.this,
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

				if (!items.isEmpty()) {
					items.clear();
				}
				String searchString = s.toString();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				if (LoadData.AgentProperties.properties != null) {
					for (int i = 0; i < LoadData.AgentProperties.properties.length; i++) {

						if (searchString.length() <= LoadData.AgentProperties.properties[i].locality
								.length()) {
							if (AppState.currentSellRent
									.equalsIgnoreCase(LoadData.AgentProperties.properties[i].sellrent)) {
								if (LoadData.AgentProperties.properties[i].locality
										.contains(searchString
												.toUpperCase(Locale
														.getDefault()))) {
									long days = ((((new Date().getTime() - LoadData.AgentProperties.properties[i].lastupdate
											.getTime()) / 1000) / 60) / 60) / 24;
									Boolean isRed = (days > AppState.OldPropertyDays) ? true
											: false;
									items.add(new Item(
											AppState.currentSellRent
													.equalsIgnoreCase("SELL") ? ((LoadData.AgentProperties.properties[i].cost >= AppState.RateThreshold) ? ""
													: "@")
													+ new DecimalFormat(
															"##,##,###")
															.format(LoadData.AgentProperties.properties[i].cost)
													: new DecimalFormat(
															"##,##,###")
															.format(LoadData.AgentProperties.properties[i].rent),
											LoadData.AgentProperties.properties[i].type,
											LoadData.AgentProperties.properties[i].locality,
											LoadData.AgentProperties.properties[i].area
													+ " SqFt",
											LoadData.AgentProperties.properties[i].floor,
											LoadData.AgentProperties.properties[i].address,
											LoadData.AgentProperties.properties[i].rescom,
											LoadData.AgentProperties.properties[i].directside,
											LoadData.AgentProperties.properties[i].sellrent
													.substring(0, 1)
													.toUpperCase(),
											LoadData.AgentProperties.properties[i].pid,
											formatter
													.format(LoadData.AgentProperties.properties[i].lastupdate),
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
					ListAdapter adapter = new ListAdapter(getBaseContext(),
							items, Type.PROPERTY);
					listProperties.setAdapter(adapter);
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

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		AppState.currentLayout = enumLayout.AgentProperties;

		final TextView txtSell = (TextView) findViewById(R.id.txtSell);
		final TextView txtRent = (TextView) findViewById(R.id.txtRent);
		btnCall = (TextView) findViewById(R.id.Call);
		btnCall.setText(LoadData.Agents.agents[AppState.currentAIDIndex].phoneno
				+ " Call?");
		btnCall.setVisibility(View.GONE);
		TextView txtSort = (TextView) findViewById(R.id.txtSort);
		TextView txtFilter = (TextView) findViewById(R.id.txtFilter);
		listProperties = (ListView) findViewById(R.id.listProperties);
		pbProgress = (FrameLayout) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.VISIBLE);
		txtCount = (TextView) findViewById(R.id.Count);
		// btnCall.setText(AppState.currentCity.substring(0, 3) + "-"
		// + AppState.currentTown);
		// btnCall.setPaintFlags(btnCall.getPaintFlags()
		// | Paint.UNDERLINE_TEXT_FLAG);

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

				for (int i = 0; (i < LoadData.AgentProperties.properties.length)
						&& (index == -1); i++) {
					if (LoadData.AgentProperties.properties[i].pid
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
				txtRent.setBackgroundResource(R.drawable.clicklink);
				txtSell.setTextAppearance(getApplicationContext(),
						R.style.ClickLinkSmallSelected);
				txtSell.setBackgroundResource(R.drawable.clicklinkpressed);
				AppState.currentSellRent = "SELL";
				items = new ArrayList<Item>();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				if (LoadData.AgentProperties.properties != null) {
					for (int i = 0; i < LoadData.AgentProperties.properties.length; i++) {
						if (AppState.currentSellRent
								.equalsIgnoreCase(LoadData.AgentProperties.properties[i].sellrent)) {
							long days = ((((new Date().getTime() - LoadData.AgentProperties.properties[i].lastupdate
									.getTime()) / 1000) / 60) / 60) / 24;
							Boolean isRed = (days > AppState.OldPropertyDays) ? true
									: false;
							items.add(new Item(
									((LoadData.AgentProperties.properties[i].cost >= AppState.RateThreshold) ? ""
											: "@")
											+ new DecimalFormat("##,##,###")
													.format(LoadData.AgentProperties.properties[i].cost),
									LoadData.AgentProperties.properties[i].type,
									LoadData.AgentProperties.properties[i].locality,
									LoadData.AgentProperties.properties[i].area
											+ " SqFt",
									LoadData.AgentProperties.properties[i].floor,
									LoadData.AgentProperties.properties[i].address,
									LoadData.AgentProperties.properties[i].rescom,
									LoadData.AgentProperties.properties[i].directside,
									LoadData.AgentProperties.properties[i].sellrent
											.substring(0, 1).toUpperCase(),
									LoadData.AgentProperties.properties[i].pid,
									formatter
											.format(LoadData.AgentProperties.properties[i].lastupdate),
									"View", isRed));
						}
					}

					ListAdapter adapter = new ListAdapter(getBaseContext(),
							items, Type.PROPERTY);

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

					listProperties.setAdapter(adapter);
					txtCount.setText(Integer.toString(listProperties.getCount())
							+ " Properties");
				} else {
					Toast toast = Toast.makeText(getBaseContext(),
							"No Properties Found", Toast.LENGTH_LONG);
					toast.show();
				}
				pbProgress.setVisibility(View.GONE);
				editSearch.setText("");
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
				if (LoadData.AgentProperties.properties != null) {
					for (int i = 0; i < LoadData.AgentProperties.properties.length; i++) {
						if (AppState.currentSellRent
								.equalsIgnoreCase(LoadData.AgentProperties.properties[i].sellrent)) {
							long days = ((((new Date().getTime() - LoadData.AgentProperties.properties[i].lastupdate
									.getTime()) / 1000) / 60) / 60) / 24;
							Boolean isRed = (days > AppState.OldPropertyDays) ? true
									: false;
							items.add(new Item(
									new DecimalFormat("##,##,###")
											.format(LoadData.AgentProperties.properties[i].rent),
									LoadData.AgentProperties.properties[i].type,
									LoadData.AgentProperties.properties[i].locality,
									LoadData.AgentProperties.properties[i].area
											+ " SqFt",
									LoadData.AgentProperties.properties[i].floor,
									LoadData.AgentProperties.properties[i].address,
									LoadData.AgentProperties.properties[i].rescom,
									LoadData.AgentProperties.properties[i].directside,
									LoadData.AgentProperties.properties[i].sellrent
											.substring(0, 1).toUpperCase(),
									LoadData.AgentProperties.properties[i].pid,
									formatter
											.format(LoadData.AgentProperties.properties[i].lastupdate),
									"View", isRed));
						}
					}

					ListAdapter adapter = new ListAdapter(getBaseContext(),
							items, Type.PROPERTY);

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

					listProperties.setAdapter(adapter);
					txtCount.setText(Integer.toString(listProperties.getCount())
							+ " Properties");
				} else {
					Toast toast = Toast.makeText(getBaseContext(),
							"No Properties Found", Toast.LENGTH_LONG);
					toast.show();
				}
				pbProgress.setVisibility(View.GONE);
				editSearch.setText("");
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

		txtFilter.setVisibility(View.GONE);
		txtFilter.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(view.getContext(),
						FilterActivity.class);
				startActivity(intent);
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

		btnCall.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AgentPropertyActivity.this);

				builder.setTitle("Call");
				builder.setMessage("Do you want to call "
						+ LoadData.Agents.agents[AppState.currentAIDIndex].bname
						+ "?");

				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent callIntent = new Intent(
										Intent.ACTION_DIAL,
										Uri.parse("tel:"
												+ LoadData.Agents.agents[AppState.currentAIDIndex].phoneno));
								startActivity(callIntent);
							}
						});

				builder.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// I do not need any action here you might
								dialog.dismiss();
							}
						});

				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			return Operation.getAgentProperties();
		}

		protected void onPostExecute(Boolean result) {
			items = new ArrayList<Item>();
			if (result) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				for (int i = 0; i < LoadData.AgentProperties.properties.length; i++) {
					if (AppState.currentSellRent
							.equalsIgnoreCase(LoadData.AgentProperties.properties[i].sellrent)) {
						long days = ((((new Date().getTime() - LoadData.AgentProperties.properties[i].lastupdate
								.getTime()) / 1000) / 60) / 60) / 24;
						Boolean isRed = (days > AppState.OldPropertyDays) ? true
								: false;
						items.add(new Item(
								AppState.currentSellRent
										.equalsIgnoreCase("SELL") ? ((LoadData.AgentProperties.properties[i].cost >= AppState.RateThreshold) ? ""
										: "@")
										+ new DecimalFormat("##,##,###")
												.format(LoadData.AgentProperties.properties[i].cost)
										: new DecimalFormat("##,##,###")
												.format(LoadData.AgentProperties.properties[i].rent),
								LoadData.AgentProperties.properties[i].type,
								LoadData.AgentProperties.properties[i].locality,
								LoadData.AgentProperties.properties[i].area
										+ " SqFt",
								LoadData.AgentProperties.properties[i].floor,
								LoadData.AgentProperties.properties[i].address,
								LoadData.AgentProperties.properties[i].rescom,
								LoadData.AgentProperties.properties[i].directside,
								LoadData.AgentProperties.properties[i].sellrent
										.substring(0, 1).toUpperCase(),
								LoadData.AgentProperties.properties[i].pid,
								formatter
										.format(LoadData.AgentProperties.properties[i].lastupdate),
								"View", isRed));
					}
				}

				ListAdapter adapter = new ListAdapter(getBaseContext(), items,
						Type.PROPERTY);

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

				listProperties.setAdapter(adapter);
				txtCount.setText(Integer.toString(listProperties.getCount())
						+ " Properties");
				for (LoadData.Property p : LoadData.AgentProperties.properties) {
					if (adapterSearch.getPosition(p.locality) == -1)
						adapterSearch.add(p.locality);
				}
				editSearch.setAdapter(adapterSearch);
				editSearch.setThreshold(1);
			} else {
				Toast toast = Toast.makeText(getBaseContext(),
						"No Properties Found", Toast.LENGTH_LONG);
				toast.show();
				LoadData.AgentProperties.properties = null;
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
		getMenuInflater().inflate(R.menu.agent_property, menu);
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
		switch (item.getItemId()) {
		case R.id.action_call:
			AlertDialog.Builder builder = new AlertDialog.Builder(
					AgentPropertyActivity.this);

			builder.setTitle("Call");
			builder.setMessage("Do you want to call "
					+ LoadData.Agents.agents[AppState.currentAIDIndex].bname
					+ "?");

			builder.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent callIntent = new Intent(
									Intent.ACTION_DIAL,
									Uri.parse("tel:"
											+ LoadData.Agents.agents[AppState.currentAIDIndex].phoneno));
							startActivity(callIntent);
						}
					});

			builder.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// I do not need any action here you might
							dialog.dismiss();
						}
					});

			AlertDialog alert = builder.create();
			alert.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		ListAdapter adapter;
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

					// try {
					// return timestamper
					// .format(formatter.parse((String) p1
					// .getRowDate()))
					// .toString()
					// .compareToIgnoreCase(
					// timestamper.format(formatter
					// .parse((String) p2.getRowDate())));
					// } catch (ParseException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					// return 0;
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
