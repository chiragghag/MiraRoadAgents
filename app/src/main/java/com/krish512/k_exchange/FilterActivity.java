/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.Utils.LoadData;
import com.krish512.k_exchange.R;

public class FilterActivity extends AppCompatActivity {

	RadioGroup radioResCom;
	RadioButton radioRes;
	RadioButton radioCom;
	TextView ddType;
	Button btnShowProperties;
	EditText txtFrom;
	EditText txtTo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);

		radioResCom = (RadioGroup) findViewById(R.id.radioResCom);
		radioRes = (RadioButton) findViewById(R.id.radioRes);
		radioRes.setChecked(AppState.filterResCom
				.equalsIgnoreCase("residential") ? true : false);
		radioCom = (RadioButton) findViewById(R.id.radioCom);
		radioCom.setChecked(AppState.filterResCom
				.equalsIgnoreCase("commercial") ? true : false);
		ddType = (TextView) findViewById(R.id.ddType);
		ddType.setText((AppState.filterType.equalsIgnoreCase("")) ? ""
				: AppState.filterType);
		txtFrom = (EditText) findViewById(R.id.txtFrom);
		txtFrom.setText(new DecimalFormat("##,##,###")
				.format(AppState.filterCostFrom));
		txtTo = (EditText) findViewById(R.id.txtTo);
		txtTo.setText(new DecimalFormat("##,##,###")
				.format(AppState.filterCostTo));
		registerForContextMenu(ddType);
		btnShowProperties = (Button) findViewById(R.id.btnShowProperties);

		ddType.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				// openContextMenu(getCurrentFocus());
				openContextMenu(ddType);
			}
		});

		radioResCom.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// checkedId is the RadioButton selected
				// String x = ;
				if (checkedId == R.id.radioRes) {
					AppState.filterResCom = "residential";
					AppState.filterType = "";
					ddType.setText((AppState.filterType.equalsIgnoreCase("")) ? ""
							: AppState.filterType);
					LoadData.Properties.properties = null;
				} else if (checkedId == R.id.radioCom) {
					AppState.filterResCom = "commercial";
					AppState.filterType = "";
					LoadData.Properties.properties = null;
					ddType.setText((AppState.filterType.equalsIgnoreCase("")) ? ""
							: AppState.filterType);
				}
			}
		});

		txtFrom.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (txtFrom.getText().length() > 0) {
						int tmp = Integer.parseInt(txtFrom.getText().toString()
								.replaceAll("[^0-9]", ""));
						txtFrom.setText(new DecimalFormat("##,##,###")
								.format(tmp));
					}
				}
			}
		});

		txtFrom.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (txtFrom.getText().length() > 0) {
					AppState.filterCostFrom = Integer.parseInt(txtFrom
							.getText().toString().replaceAll("[^0-9]", ""));
					LoadData.Properties.properties = null;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		txtTo.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (txtTo.getText().length() > 0) {
						int tmp = Integer.parseInt(txtTo.getText().toString()
								.replaceAll("[^0-9]", ""));
						txtTo.setText(new DecimalFormat("##,##,###")
								.format(tmp));
					}
				}
			}
		});

		txtTo.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (txtTo.getText().length() > 0) {
					AppState.filterCostTo = Integer.parseInt(txtTo.getText()
							.toString().replaceAll("[^0-9]", ""));
					LoadData.Properties.properties = null;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		btnShowProperties.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				// Create the intent to start another activity
				if (radioRes.isChecked() || radioCom.isChecked()) {
					if (!AppState.filterType.equalsIgnoreCase("")) {
						Intent intent = new Intent(getBaseContext(),
								PropertyActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast toast = Toast.makeText(getBaseContext(),
								"Select Type", Toast.LENGTH_LONG);
						toast.show();
					}
				} else {
					Toast toast = Toast.makeText(getBaseContext(),
							"Select Residential or Commercial",
							Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});

		// TextView txtHeading = (TextView) findViewById(R.id.Heading);
		// final TextView txtCostFrom = (TextView)
		// findViewById(R.id.txtCostFrom);
		// final TextView txtCostTo = (TextView) findViewById(R.id.txtCostTo);
		// final TextView txtRateMin = (TextView) findViewById(R.id.txtRateMin);
		// final TextView txtRateMax = (TextView) findViewById(R.id.txtRateMax);
		// final TextView txtRateFrom = (TextView)
		// findViewById(R.id.txtRateFrom);
		// final TextView txtRateTo = (TextView) findViewById(R.id.txtRateTo);

		// RangeSeekBar<Integer> costSeekBar = new RangeSeekBar<Integer>(0,
		// 100000, FilterActivity.this);
		// costSeekBar
		// .setOnRangeSeekBarChangeListener(new
		// OnRangeSeekBarChangeListener<Integer>() {
		// @Override
		// public void onRangeSeekBarValuesChanged(
		// RangeSeekBar<?> bar, Integer minValue,
		// Integer maxValue) {
		// // handle changed range values
		// String TAG = null;
		// Log.i(TAG, "User selected new range values: MIN="
		// + minValue + ", MAX=" + maxValue);
		//
		// if ((maxValue > 99) && (maxValue < 10000)) {
		// // float max = maxValue / 100;
		// DecimalFormat form = new DecimalFormat("0.00");
		// String max = form.format((float) maxValue / 100);
		// txtCostTo.setText(max + "L");
		// } else if ((maxValue > 9999) && (maxValue < 100001)) {
		// // float max = maxValue / 100;
		// DecimalFormat form = new DecimalFormat("0.00");
		// String max = form.format((float) maxValue / 10000);
		// txtCostTo.setText(max + "Cr");
		// } else {
		// txtCostTo.setText(maxValue.toString() + "K");
		// }
		//
		// if ((minValue > 99) && (minValue < 10000)) {
		// // float max = maxValue / 100;
		// DecimalFormat form = new DecimalFormat("0.00");
		// String min = form.format((float) minValue / 100);
		// txtCostFrom.setText(min + "L");
		// } else if ((minValue > 9999) && (minValue < 100001)) {
		// // float max = maxValue / 100;
		// DecimalFormat form = new DecimalFormat("0.00");
		// String min = form.format((float) minValue / 10000);
		// txtCostFrom.setText(min + "Cr");
		// } else {
		// txtCostFrom.setText(minValue.toString() + "K");
		// }
		// }
		// });
		//
		// // add RangeSeekBar to pre-defined layout
		// ViewGroup costLayout = (ViewGroup) findViewById(R.id.layoutCost);
		// costLayout.addView(costSeekBar);
		//
		// RangeSeekBar<Integer> rateSeekBar = new RangeSeekBar<Integer>(4000,
		// 9000, getBaseContext());
		// rateSeekBar
		// .setOnRangeSeekBarChangeListener(new
		// OnRangeSeekBarChangeListener<Integer>() {
		// @Override
		// public void onRangeSeekBarValuesChanged(
		// RangeSeekBar<?> bar, Integer minValue,
		// Integer maxValue) {
		// // handle changed range values
		// String TAG = null;
		// Log.i(TAG, "User selected new range values: MIN="
		// + minValue + ", MAX=" + maxValue);
		// txtRateFrom.setText("@" + minValue.toString());
		// txtRateTo.setText("@" + maxValue.toString());
		// }
		// });
		//
		// // add RangeSeekBar to pre-defined layout
		// ViewGroup rateLayout = (ViewGroup) findViewById(R.id.layoutRate);
		// rateLayout.addView(rateSeekBar);
		// txtRateMin.setText("@4000");
		// txtRateMax.setText("@9000");
		// txtRateFrom.setText("@4000");
		// txtRateTo.setText("@9000");
	}

	// @Override
	// public boolean onPrepareOptionsMenu(Menu menu) {
	// return false;
	// }

	@Override
	public void onBackPressed() {
		if (radioRes.isChecked() || radioCom.isChecked()) {
			if (!AppState.filterType.equalsIgnoreCase("")) {
				Intent intent = new Intent(getBaseContext(),
						PropertyActivity.class);
				startActivity(intent);
				finish();
			} else {
				finish();
			}
		} else {
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.removeItem(android.R.id.switchInputMethod);
		menu.setHeaderTitle("Type");
		if (radioResCom.getCheckedRadioButtonId() == radioRes.getId()) {
			getMenuInflater().inflate(R.menu.typeres, menu);
		} else {
			getMenuInflater().inflate(R.menu.typecom, menu);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		ddType.setText(item.getTitle());
		AppState.filterType = item.getTitle().toString();
		LoadData.Properties.properties = null;
		return true;
	}

}
