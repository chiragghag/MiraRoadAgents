/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import 	androidx.appcompat.app.AppCompatActivity;
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

@SuppressLint("SimpleDateFormat")
public class NoticeboardActivity extends AppCompatActivity {

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
	TextView PostBlock;
	AutoCompleteTextView editSearch;

	ListAdapter adapter;
	ArrayAdapter<String> adapterSearch;
	ArrayList<Item> items;

	String Phoneno = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticeboard);

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
							NoticeboardActivity.this).create();
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
					Intent intent = new Intent(NoticeboardActivity.this,
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
		txtNoticeboard.setText(txtNoticeboard.getText() + "*");
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
				for (int i = 0; i < LoadData.Notices.notices.length; i++) {
					if (searchString.length() <= LoadData.Notices.notices[i].bname
							.length()) {
						if (LoadData.Notices.notices[i].bname
								.contains(searchString.toUpperCase(Locale
										.getDefault()))) {
							items.add(new Item(
									LoadData.Notices.notices[i].bname,
									LoadData.Notices.notices[i].phoneno,
									(LoadData.Notices.notices[i].altno
											.equalsIgnoreCase("null") ? ""
											: " / "
													+ LoadData.Notices.notices[i].altno),
									formatter
											.format(LoadData.Notices.notices[i].lastupdate),
									LoadData.Notices.notices[i].content));
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

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		AppState.currentLayout = enumLayout.Noticeboard;

		btnArea = (TextView) findViewById(R.id.Area);
		listProperties = (ListView) findViewById(R.id.listProperties);
		pbProgress = (FrameLayout) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.VISIBLE);
		txtCount = (TextView) findViewById(R.id.Count);
		PostBlock = (TextView) findViewById(R.id.ShareBlock);
		btnArea.setText(AppState.currentCity.substring(0, 3) + "-"
				+ AppState.currentTown);
		btnArea.setPaintFlags(btnArea.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);

		new MyAsyncTask().execute();

		listProperties.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView row1 = (TextView) view.findViewById(R.id.row1);
				String bname = row1.getText().toString();
				TextView rowPhoneno = (TextView) view
						.findViewById(R.id.rowPhoneno);
				Phoneno = rowPhoneno.getText().toString();

				AlertDialog.Builder builder = new AlertDialog.Builder(
						NoticeboardActivity.this);

				builder.setTitle("Call");
				builder.setMessage("Do you want to call " + bname + "?");

				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent callIntent = new Intent(
										Intent.ACTION_DIAL, Uri.parse("tel:"
												+ Phoneno));
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

		// TextView txtAbbreviations = (TextView)
		// findViewById(R.id.txtAbbreviations);
		// txtAbbreviations.setOnClickListener(new OnClickListener() {
		// @Override
		// // On click function
		// public void onClick(View view) {
		// // Create the intent to start another activity
		// Intent intent = new Intent(view.getContext(),
		// AbbreviationsActivity.class);
		// startActivity(intent);
		// }
		// });
		//
		// TextView txtHelp = (TextView) findViewById(R.id.txtHelp);
		// txtHelp.setOnClickListener(new OnClickListener() {
		// @Override
		// // On click function
		// public void onClick(View view) {
		// // Create the intent to start another activity
		// Intent intent = new Intent(view.getContext(),
		// HelpActivity.class);
		// startActivity(intent);
		// }
		// });

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

		PostBlock.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				Intent intent = new Intent(NoticeboardActivity.this,
						MyNoticeboardActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			if (LoadData.Notices.notices == null) {
				return Operation.getNotices();
			} else {
				return true;
			}
		}

		protected void onPostExecute(Boolean result) {
			if (result) {
				items = new ArrayList<Item>();
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
				for (int i = 0; i < LoadData.Notices.notices.length; i++) {
					items.add(new Item(
							LoadData.Notices.notices[i].bname,
							LoadData.Notices.notices[i].phoneno,
							(LoadData.Notices.notices[i].altno
									.equalsIgnoreCase("null") ? "" : " / "
									+ LoadData.Notices.notices[i].altno),
							formatter
									.format(LoadData.Notices.notices[i].lastupdate),
							LoadData.Notices.notices[i].content));
				}
				//
				// Collections.sort(items, new Comparator<Object>() {
				//
				// public int compare(Object o1, Object o2) {
				// Item p1 = (Item) o1;
				// Item p2 = (Item) o2;
				// return p1.getRow1().toString()
				// .compareToIgnoreCase(p2.getRow1().toString());
				// }
				//
				// });
				//
				adapter = new ListAdapter(getBaseContext(), items,
						Type.NOTICEBOARD);
				listProperties.setAdapter(adapter);
				txtCount.setText(Integer.toString(listProperties.getCount())
						+ " Notices");
				for (LoadData.Notice a : LoadData.Notices.notices) {
					if (adapterSearch.getPosition(a.bname) == -1) {
						adapterSearch.add(a.bname);
					}
				}
				editSearch.setAdapter(adapterSearch);
				editSearch.setThreshold(1);
			} else {
				Toast toast = Toast.makeText(getBaseContext(), "No Notices",
						Toast.LENGTH_LONG);
				toast.show();
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noticeboard, menu);
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
