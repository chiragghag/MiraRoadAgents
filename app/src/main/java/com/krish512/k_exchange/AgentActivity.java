/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

public class AgentActivity extends AppCompatActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	FrameLayout pbProgress;
	String Phoneno;
	String phone;
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
	TextView txtCount;
	AutoCompleteTextView editSearch;

	ListAdapter adapter;
	ArrayAdapter<String> adapterSearch;
	ArrayList<Item> items;
	AlertDialog alertDialog;

	// TextView txtNoticeboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		//R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
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

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Contact Us");
		builder.setMessage("Hi "
				+ AppState.AName
				+ "! You have limited access to this App. Join Mira Road Agents & get full access to the App.");

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(AgentActivity.this,
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

		alertDialog = builder.create();

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
							AgentActivity.this);

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
											AgentActivity.this,
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
					Intent intent = new Intent(AgentActivity.this,
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
		txtAgents.setText(txtAgents.getText() + "*");
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
				for (int i = 0; i < LoadData.Agents.agents.length; i++) {

					if (searchString.length() <= LoadData.Agents.agents[i].bname
							.length()) {
						// if (searchString
						// .equalsIgnoreCase((String)
						// LoadData.Agents.agents[i].bname
						// .subSequence(0, searchString.length()))) {
						if ((LoadData.Agents.agents[i].bname
								.contains(searchString.toUpperCase(Locale
										.getDefault())))
								|| (LoadData.Agents.agents[i].aname
										.contains(searchString
												.toUpperCase(Locale
														.getDefault())))
								|| (LoadData.Agents.agents[i].locality
										.contains(searchString
												.toUpperCase(Locale
														.getDefault())))) {
							if (LoadData.Agents.agents[i].altno.length() == 10) {
								Phoneno = LoadData.Agents.agents[i].phoneno
										+ " / "
										+ LoadData.Agents.agents[i].altno;
							} else {
								Phoneno = LoadData.Agents.agents[i].phoneno;
							}
							items.add(new Item(LoadData.Agents.agents[i].bname,
									LoadData.Agents.agents[i].aname, Phoneno,
									LoadData.Agents.agents[i].locality));
						}

					}
				}

				Collections.sort(items, new Comparator<Object>() {

					public int compare(Object o1, Object o2) {
						Item p1 = (Item) o1;
						Item p2 = (Item) o2;
						return p1.getRow1().toString()
								.compareToIgnoreCase(p2.getRow1().toString());
					}

				});
				listProperties.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				txtCount.setText(Integer.toString(listProperties.getCount())
						.toString() + " Agents");
			}
		});

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		AppState.currentLayout = enumLayout.Agents;

		pbProgress = (FrameLayout) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.VISIBLE);
		btnArea = (TextView) findViewById(R.id.Area);
		txtCount = (TextView) findViewById(R.id.Count);
		btnArea.setText(AppState.currentCity.substring(0, 3) + "-"
				+ AppState.currentTown);
		btnArea.setPaintFlags(btnArea.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);
		Phoneno = null;

		new MyAsyncTask().execute();

		listProperties = (ListView) findViewById(R.id.listProperties);

		listProperties.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView row1 = (TextView) view.findViewById(R.id.row1);
				// TextView row3 = (TextView) view.findViewById(R.id.row3);
				if (AppState.Paid.equalsIgnoreCase("UNPAID")) {
					alertDialog.show();
				} else {
					int i = 0;

					while ((i < LoadData.Agents.agents.length)
							&& (!row1
									.getText()
									.toString()
									.equalsIgnoreCase(
											LoadData.Agents.agents[i].bname))) {
						i++;
					}
					if ((i < LoadData.Agents.agents.length)
							&& (row1.getText().toString()
									.equalsIgnoreCase(LoadData.Agents.agents[i].bname))) {
						LoadData.AgentProperties.properties = (AppState.currentAID == LoadData.Agents.agents[i].aid ? LoadData.AgentProperties.properties
								: null);
						AppState.currentAID = LoadData.Agents.agents[i].aid;
						AppState.currentAIDIndex = i;
						Intent intent = new Intent(view.getContext(),
								AgentPropertyActivity.class);
						startActivity(intent);
					} else {
						Toast toast = Toast.makeText(getBaseContext(),
								"Agent not found", Toast.LENGTH_LONG);
						toast.show();
					}
				}
				// phone = row3.getText().toString();
				// phone = phone.split(" ")[0];
				// if (phone.length() == 10) {
				// AlertDialog.Builder builder = new AlertDialog.Builder(
				// AgentActivity.this);
				//
				// builder.setTitle("Call");
				// builder.setMessage("Do you want to call " + row1.getText()
				// + "?");
				//
				// builder.setPositiveButton("Yes",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int which) {
				// Intent callIntent = new Intent(
				// Intent.ACTION_DIAL, Uri
				// .parse("tel:" + phone));
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
				// }
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
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			if (LoadData.Agents.agents == null) {
				return Operation.getAgents();
			} else {
				return true;
			}
		}

		protected void onPostExecute(Boolean result) {
			if (result) {
				items = new ArrayList<Item>();
				for (int i = 0; i < LoadData.Agents.agents.length; i++) {
					if (LoadData.Agents.agents[i].altno.length() == 10) {
						Phoneno = LoadData.Agents.agents[i].phoneno + " / "
								+ LoadData.Agents.agents[i].altno;
					} else {
						Phoneno = LoadData.Agents.agents[i].phoneno;
					}
					items.add(new Item(LoadData.Agents.agents[i].bname,
							LoadData.Agents.agents[i].aname, Phoneno,
							LoadData.Agents.agents[i].locality));
				}

				Collections.sort(items, new Comparator<Object>() {

					public int compare(Object o1, Object o2) {
						Item p1 = (Item) o1;
						Item p2 = (Item) o2;
						return p1.getRow1().toString()
								.compareToIgnoreCase(p2.getRow1().toString());
					}

				});

				adapter = new ListAdapter(getBaseContext(), items, Type.AGENT);
				listProperties.setAdapter(adapter);
				txtCount.setText(Integer.toString(listProperties.getCount())
						+ " Agents");
				for (LoadData.Agent a : LoadData.Agents.agents) {
					if (adapterSearch.getPosition(a.bname) == -1) {
						adapterSearch.add(a.bname);
						adapterSearch.add(a.aname);
					}
					if (adapterSearch.getPosition(a.locality) == -1) {
						adapterSearch.add(a.locality);
					}
				}
				editSearch.setAdapter(adapterSearch);
				editSearch.setThreshold(1);
			} else {
				Toast toast = Toast.makeText(getBaseContext(), "No Agents",
						Toast.LENGTH_LONG);
				toast.show();
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agent, menu);
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
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}
}
