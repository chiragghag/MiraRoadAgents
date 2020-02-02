/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;
import com.krish512.k_exchange.R;

public class AbbreviationsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abbreviations);
		
		TextView txtAbbreviations = (TextView) findViewById(R.id.txtAbbreviations);
		String Abbreviations = "";
		Abbreviations += "OC - Occupancy Certificate\n";
		Abbreviations += "Ter - Terrace\n";
		Abbreviations += "OP - Open parking\n";
		Abbreviations += "SP - Stilt Parking\n";
		Abbreviations += "GF - Garden Facing\n";
		Abbreviations += "BF - Back facing\n";
		Abbreviations += "RF - Road Facing\n";
		Abbreviations += "FW - Full white\n";
		Abbreviations += "AV - Agrement Value\n";
		Abbreviations += "CM - Club Membership\n";
		Abbreviations += "Bal - Balcony\n";
		Abbreviations += "FF - Fully Furnished\n";
		Abbreviations += "SF - Semi Furnished\n";
		Abbreviations += "UC - Under Construction\n";
		Abbreviations += "HD - Heavy Deposit\n";
		Abbreviations += "MB - Master Bed\n";
		txtAbbreviations.setText(Abbreviations);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.abbreviations, menu);
		return true;
	}

}
