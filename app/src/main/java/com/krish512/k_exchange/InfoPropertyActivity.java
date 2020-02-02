/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.Utils.AppState.enumLayout;
import com.krish512.k_exchange.Utils.Operation;
import com.krish512.k_exchange.R;

@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
public class InfoPropertyActivity extends AppCompatActivity {

	TextView Heading;
	TextView txtTitle;
	TextView txtType;
	TextView txtLocation;
	TextView txtArea;
	TextView txtFloor;
	TextView txtAddress;
	TextView txtExtraInfo;
	TextView txtBrokerage;
	TextView txtContact;
	TextView txtNumber;
	TextView txtAltNumber;
	TextView txtMembershipDetails;
	ProgressBar pbProgress;
	LinearLayout editMenu;
	LinearLayout callMenu;
	TextView btnCall;
	TextView btnAltCall;
	TextView btnSMS;
	TextView btnEdit;
	TextView btnDelete;
	TextView btnRefresh;
	RelativeLayout layoutContact;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_property);

		Heading = (TextView) findViewById(R.id.Heading);
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtType = (TextView) findViewById(R.id.valType);
		txtLocation = (TextView) findViewById(R.id.valLocation);
		txtArea = (TextView) findViewById(R.id.valArea);
		txtFloor = (TextView) findViewById(R.id.valFloor);
		txtAddress = (TextView) findViewById(R.id.valAddress);
		txtExtraInfo = (TextView) findViewById(R.id.valExtraInfo);
		txtBrokerage = (TextView) findViewById(R.id.valBrokerage);
		txtContact = (TextView) findViewById(R.id.valContact);
		txtNumber = (TextView) findViewById(R.id.valNumber);
		txtAltNumber = (TextView) findViewById(R.id.valNumber2);
		txtMembershipDetails = (TextView) findViewById(R.id.txtMembershipDetails);
		editMenu = (LinearLayout) findViewById(R.id.editMenu);
		callMenu = (LinearLayout) findViewById(R.id.callMenu);
		btnCall = (TextView) findViewById(R.id.btnCall);
		btnAltCall = (TextView) findViewById(R.id.btnCall2);
		btnSMS = (TextView) findViewById(R.id.btnSMS);
		btnEdit = (TextView) findViewById(R.id.btnEdit);
		btnDelete = (TextView) findViewById(R.id.btnDelete);
		btnRefresh = (TextView) findViewById(R.id.btnRefresh);
		layoutContact = (RelativeLayout) findViewById(R.id.layoutContact);

		pbProgress = (ProgressBar) findViewById(R.id.pbProgess);
		pbProgress.setVisibility(View.VISIBLE);

		if (AppState.currentLayout == enumLayout.MyProperties) {
			editMenu.setVisibility(View.VISIBLE);
			callMenu.setVisibility(View.GONE);
			layoutContact.setVisibility(View.GONE);
		}

		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("PID", AppState.currentPID));
		params.add(new BasicNameValuePair("paid", AppState.Paid));
		new LoadAsyncTask().execute(params);

		btnCall.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:" + txtNumber.getText().toString()));
				startActivity(callIntent);
			}
		});

		btnAltCall.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:" + txtAltNumber.getText().toString()));
				startActivity(callIntent);
			}
		});

		btnSMS.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
				smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
				smsIntent.setType("vnd.android-dir/mms-sms");
				smsIntent.setData(Uri.parse("sms:"
						+ txtNumber.getText().toString()));
				smsIntent.putExtra("sms_body",
						"Hey, I want to be a member of Mira Road Agents. Please contact me. - "
								+ AppState.AName);
				smsIntent.putExtra("compose_mode", true);
				startActivity(smsIntent);
			}
		});

		btnEdit.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent intent = new Intent(getBaseContext(),
						EditPropertyActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnDelete.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						InfoPropertyActivity.this);

				builder.setTitle("Delete Property");
				builder.setMessage("Do you want to delete the property?");

				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								pbProgress.setVisibility(View.VISIBLE);
								List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
								params.add(new BasicNameValuePair("PID",
										AppState.currentPID));
								new DeleteAsyncTask().execute(params);
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

		btnRefresh.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						InfoPropertyActivity.this);

				builder.setTitle("Refresh Property");
				builder.setMessage("Do you want to refresh the property?");

				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								pbProgress.setVisibility(View.VISIBLE);
								List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
								params.add(new BasicNameValuePair("PID",
										AppState.currentPID));
								new RefreshAsyncTask().execute(params);
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

		txtMembershipDetails.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(),
						MembershipDetailsActivity.class);
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
	}

	private class LoadAsyncTask extends
			AsyncTask<List<BasicNameValuePair>, String, String> {

		@Override
		protected String doInBackground(List<BasicNameValuePair>... params) {
			// TODO Auto-generated method stub
			String reply = null;
			try {
				reply = Operation.postHttpResponse(new URI(AppState.absoluteUri
						+ "getpropertyinfo.php"), params[0]);
				String APP_TAG = null;
				Log.d(APP_TAG, reply);
				String[] test = reply.split(":");
				if (test[0].equalsIgnoreCase("Error") == true) {
					return "Failed: " + test[1];
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return reply;
		}

		protected void onPostExecute(String result) {
			String APP_TAG = null;
			JSONObject infoJSON = null;
			try {
				infoJSON = new JSONObject(result);

				Heading.setText("K"
						+ infoJSON.getString("sellrent").substring(0, 1)
						+ AppState.currentPID + " - "
						+ infoJSON.getString("lastupdate"));
				txtType.setText(infoJSON.getString("buildtype") + " ("
						+ infoJSON.getString("rescom") + ")");
				txtLocation.setText(infoJSON.getString("locality"));
				txtArea.setText(infoJSON.getString("area") + " SqFt");
				txtFloor.setText(infoJSON.getString("floor"));
				txtAddress.setText(infoJSON.getString("propaddress") + "\n"
						+ infoJSON.getString("town") + " | "
						+ infoJSON.getString("city"));
				txtExtraInfo.setText(infoJSON.getString("optionalinfo"));
				txtBrokerage.setText(infoJSON.getString("directside"));

				if (infoJSON.getString("sellrent").equalsIgnoreCase("SELL")) {

					txtTitle.setText("Rs. "
							+ ((Integer.parseInt(infoJSON.getString("cost")) >= AppState.RateThreshold) ? ""
									: "@")
							+ new DecimalFormat("##,##,###").format(Integer
									.parseInt(infoJSON.getString("cost"))));
				} else {
					txtTitle.setText("Rs. "
							+ new DecimalFormat("##,##,###").format(Integer
									.parseInt(infoJSON.getString("rent")))
							+ " / "
							+ "Rs. "
							+ new DecimalFormat("##,##,###").format(Integer
									.parseInt(infoJSON.getString("deposit"))));
				}

				// txtAddress.setText(infoJSON.getString("propaddress") + "\n"
				// + infoJSON.getString("optionalinfo") + "\n"
				// + infoJSON.getString("area") + "SF / "
				// + infoJSON.getString("floor") + " / "
				// + infoJSON.getString("rescom").substring(0, 4) + ". / "
				// + infoJSON.getString("directside"));

				if (AppState.Paid.equalsIgnoreCase("PAID")) {
					txtContact.setText(Html
							.fromHtml("<b><font color='#393285'>"
									+ infoJSON.getString("bname")
									+ "</font></b>" + "<br/>"
									+ infoJSON.getString("aname")));
					txtNumber.setText(infoJSON.getString("phoneno"));
					if (!infoJSON.getString("altno").equalsIgnoreCase("NULL")) {
						TextView slash = (TextView) findViewById(R.id.slash);
						slash.setVisibility(View.VISIBLE);
						txtAltNumber.setText(infoJSON.getString("altno"));
						btnAltCall.setVisibility(View.VISIBLE);
					} else {
						TextView slash = (TextView) findViewById(R.id.slash);
						slash.setVisibility(View.GONE);
						txtAltNumber.setVisibility(View.GONE);
						btnAltCall.setVisibility(View.GONE);
					}
					btnSMS.setVisibility(View.GONE);
					txtMembershipDetails.setVisibility(View.GONE);
				} else {
					txtContact
							.setText("Business Name - **********\nAgent Name - **********\nPhone - # **********\n\nTo get contact info of this property & all the properties in your town Join K-Exchange\n");
					txtNumber.setText("9833452109");
					txtAltNumber.setText("9920964745");
					btnSMS.setVisibility(View.VISIBLE);
					txtMembershipDetails.setVisibility(View.VISIBLE);
				}

				Log.d(APP_TAG, infoJSON.getString("city"));
			} catch (JSONException e1) {
				e1.printStackTrace();
				Log.d(APP_TAG, "Error:" + e1.getMessage());
			}
			pbProgress.setVisibility(View.GONE);
		}
	}

	private class DeleteAsyncTask extends
			AsyncTask<List<BasicNameValuePair>, String, String> {

		@Override
		protected String doInBackground(List<BasicNameValuePair>... params) {
			// TODO Auto-generated method stub
			String reply = null;
			try {
				reply = Operation.postHttpResponse(new URI(AppState.absoluteUri
						+ "deleteproperty.php"), params[0]);
				String APP_TAG = null;
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
				AppState.PropertiesCount -= 1;
				Intent intent = new Intent(getBaseContext(),
						MyPropertyActivity.class);
				startActivity(intent);
				finish();
			}
		}
	}

	private class RefreshAsyncTask extends
			AsyncTask<List<BasicNameValuePair>, String, String> {

		@Override
		protected String doInBackground(List<BasicNameValuePair>... params) {
			// TODO Auto-generated method stub
			String reply = null;
			try {
				reply = Operation.postHttpResponse(new URI(AppState.absoluteUri
						+ "refreshproperty.php"), params[0]);
				String APP_TAG = null;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_property, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		if (AppState.currentLayout == enumLayout.MyProperties) {
			Intent intent = new Intent(getBaseContext(),
					MyPropertyActivity.class);
			startActivity(intent);
		}
		finish();
	}

}
