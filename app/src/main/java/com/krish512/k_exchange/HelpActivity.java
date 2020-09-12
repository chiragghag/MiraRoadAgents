/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import 	androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.krish512.k_exchange.R;

public class HelpActivity extends AppCompatActivity {

	TextView txtMembershipDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		TextView txtCustomerCare = (TextView) findViewById(R.id.txtCustomerCare);
		txtCustomerCare
				.setText("Customer Care - 9833452109\n10:00 am to 7:00 pm");
		TextView txtAddress = (TextView) findViewById(R.id.txtAddress);
		// txtAddress.setText("Address:\nArunodaya Chs, 104, C-16, Sector-8,\nShantinagar, Mira Road - E,\nThane - 401107");
		txtAddress
				.setText("");
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

		txtMembershipDetails = (TextView) findViewById(R.id.txtMembershipDetails);
		txtMembershipDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(),
						MembershipDetailsActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
