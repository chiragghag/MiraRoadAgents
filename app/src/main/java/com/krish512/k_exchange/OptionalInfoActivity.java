/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import 	androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.widget.CheckBox;

import com.krish512.k_exchange.Utils.AppState;
import com.krish512.k_exchange.R;

public class OptionalInfoActivity extends AppCompatActivity {

	// Button btnSave;
	CheckBox cb_OC;
	CheckBox cb_TER;
	CheckBox cb_OP;
	CheckBox cb_SP;
	CheckBox cb_GF;
	CheckBox cb_BF;
	CheckBox cb_RF;
	CheckBox cb_FW;
	CheckBox cb_CM;
	CheckBox cb_BAL;
	CheckBox cb_FF;
	CheckBox cb_SF;
	CheckBox cb_UC;
	CheckBox cb_HD;
	CheckBox cb_MB;
	String[] OptionalInfo = null;
	Set<String> VALUES;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_optional_info);

		// btnSave = (Button) findViewById(R.id.btnSave);
		cb_OC = (CheckBox) findViewById(R.id.cb_OC);
		cb_TER = (CheckBox) findViewById(R.id.cb_TER);
		cb_OP = (CheckBox) findViewById(R.id.cb_OP);
		cb_SP = (CheckBox) findViewById(R.id.cb_SP);
		cb_GF = (CheckBox) findViewById(R.id.cb_GF);
		cb_BF = (CheckBox) findViewById(R.id.cb_BF);
		cb_RF = (CheckBox) findViewById(R.id.cb_RF);
		cb_FW = (CheckBox) findViewById(R.id.cb_FW);
		cb_CM = (CheckBox) findViewById(R.id.cb_CM);
		cb_BAL = (CheckBox) findViewById(R.id.cb_BAL);
		cb_FF = (CheckBox) findViewById(R.id.cb_FF);
		cb_SF = (CheckBox) findViewById(R.id.cb_SF);
		cb_UC = (CheckBox) findViewById(R.id.cb_UC);
		cb_HD = (CheckBox) findViewById(R.id.cb_HD);
		cb_MB = (CheckBox) findViewById(R.id.cb_MB);

		CheckBox[] cb_List = { cb_OC, cb_TER, cb_OP, cb_SP, cb_GF, cb_BF,
				cb_RF, cb_FW, cb_CM, cb_BAL, cb_FF, cb_SF, cb_UC, cb_HD, cb_MB };

		String[] options = { "OC", "TER", "OP", "SP", "GF", "BF", "RF", "FW",
				"CM", "BAL", "FF", "SF", "UC", "HD", "MB" };

		if (AppState.temp != null) {
			OptionalInfo = AppState.temp.split(",");
		}

		if (OptionalInfo != null) {
			VALUES = new HashSet<String>(Arrays.asList(OptionalInfo));
		}

		if (OptionalInfo != null) {
			if (OptionalInfo.length > 0) {
				for (int i = 0; i < options.length; i++) {
					if (VALUES.contains(options[i])) {
						cb_List[i].setChecked(true);
					}
				}
			}
		}

		// btnSave.setOnClickListener(new OnClickListener() {
		// @Override
		// // On click function
		// public void onClick(View view) {
		// // Create the intent to start another activity
		// String selected = "";
		// selected += (cb_OC.isChecked() ? "OC," : "");
		// selected += (cb_TER.isChecked() ? "TER," : "");
		// selected += (cb_OP.isChecked() ? "OP," : "");
		// selected += (cb_SP.isChecked() ? "SP," : "");
		// selected += (cb_GF.isChecked() ? "GF," : "");
		// selected += (cb_BF.isChecked() ? "BF," : "");
		// selected += (cb_RF.isChecked() ? "RF," : "");
		// selected += (cb_FW.isChecked() ? "FW," : "");
		// selected += (cb_CM.isChecked() ? "CM," : "");
		// selected += (cb_BAL.isChecked() ? "BAL," : "");
		// selected += (cb_FF.isChecked() ? "FF," : "");
		// selected += (cb_SF.isChecked() ? "SF," : "");
		// selected += (cb_UC.isChecked() ? "UC," : "");
		// selected += (cb_HD.isChecked() ? "HD," : "");
		// selected += (cb_MB.isChecked() ? "MB," : "");
		// AppState.temp = (selected.length() > 0) ? selected.substring(0,
		// selected.length() - 1) : selected;
		// Intent intent = new Intent();
		// // intent.putExtra(KEY_RESPONSE, responseData);
		// setResult(RESULT_OK, intent);
		// finish();
		// }
		// });
	}

	@Override
	public void onBackPressed() {
		String selected = "";
		selected += (cb_OC.isChecked() ? "OC," : "");
		selected += (cb_TER.isChecked() ? "TER," : "");
		selected += (cb_OP.isChecked() ? "OP," : "");
		selected += (cb_SP.isChecked() ? "SP," : "");
		selected += (cb_GF.isChecked() ? "GF," : "");
		selected += (cb_BF.isChecked() ? "BF," : "");
		selected += (cb_RF.isChecked() ? "RF," : "");
		selected += (cb_FW.isChecked() ? "FW," : "");
		selected += (cb_CM.isChecked() ? "CM," : "");
		selected += (cb_BAL.isChecked() ? "BAL," : "");
		selected += (cb_FF.isChecked() ? "FF," : "");
		selected += (cb_SF.isChecked() ? "SF," : "");
		selected += (cb_UC.isChecked() ? "UC," : "");
		selected += (cb_HD.isChecked() ? "HD," : "");
		selected += (cb_MB.isChecked() ? "MB," : "");
		AppState.temp = (selected.length() > 0) ? selected.substring(0,
				selected.length() - 1) : selected;
		Intent intent = new Intent();
		// intent.putExtra(KEY_RESPONSE, responseData);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optional_info, menu);
		return true;
	}

}
