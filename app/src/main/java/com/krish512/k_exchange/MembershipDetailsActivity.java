/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.krish512.k_exchange.R;

public class MembershipDetailsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_membership_details);

		final TextView txtNo1 = (TextView) findViewById(R.id.No1);
		final TextView txtNo2 = (TextView) findViewById(R.id.No2);

		TextView btnCall1 = (TextView) findViewById(R.id.btnCall1);
		btnCall1.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:" + txtNo1.getText().toString()));
				startActivity(callIntent);
			}
		});

		TextView btnCall2 = (TextView) findViewById(R.id.btnCall2);
		btnCall2.setOnClickListener(new OnClickListener() {
			@Override
			// On click function
			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:" + txtNo2.getText().toString()));
				startActivity(callIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.membership_details, menu);
		return true;
	}

}
