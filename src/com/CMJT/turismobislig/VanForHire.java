/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author CMJT
 * 
 */
public class VanForHire extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.van);

		TextView texttri = (TextView) findViewById(R.id.textViewvan);
		TextView textres = (TextView) findViewById(R.id.textreservationsvan);
		TextView textnum = (TextView) findViewById(R.id.vannumber);
		Button btntricycle = (Button) findViewById(R.id.mapvanbtn);

		texttri.setText("Another Type of transportation in Bislig City. This van for hire can make you tour around the spots in Bislig City.");
		textres.setText("For inquiries you can contact them. The fee will depend upon your agreement to the van staff.");

		// For Map
		btntricycle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(VanForHire.this, MapVanForHire.class));
				finish();

			}
		});

		// dial number
		textnum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869398426956"));
				startActivity(intent);

			}
		});

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// Enable the map button
			btntricycle.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btntricycle.setEnabled(false);
			btntricycle.setText("No Internet Connection");
		}

	}

}
