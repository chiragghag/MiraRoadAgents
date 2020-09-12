/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import android.os.Bundle;
import 	androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import com.krish512.k_exchange.R;

public class AboutActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		// TextView txtDeveloper = (TextView) findViewById(R.id.txtDeveloper);
		//
		// txtDeveloper.setOnClickListener(new OnClickListener() {
		// @Override
		// // On click function
		// public void onClick(View view) {
		// // Create the intent to start another activity
		// String url = "http://www.krish512.com";
		// Intent intent = new Intent(Intent.ACTION_VIEW);
		// intent.setData(Uri.parse(url));
		// startActivity(intent);
		// }
		// });
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

}
