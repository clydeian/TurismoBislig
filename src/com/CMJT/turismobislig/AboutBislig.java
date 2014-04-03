/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author CMJT
 * 
 */

public class AboutBislig extends Activity {

	static Point size;
	static float density;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutbislig);

		TextView txtview1 = (TextView) findViewById(R.id.txtview1);
		TextView txtview2 = (TextView) findViewById(R.id.textView2);
		TextView textnum1 = (TextView) findViewById(R.id.textView24);
		TextView textnum2 = (TextView) findViewById(R.id.textView25);
		TextView textnum3 = (TextView) findViewById(R.id.textView26);
		TextView textnum4 = (TextView) findViewById(R.id.textView27);
		Button btn1 = (Button) findViewById(R.id.visitwebbtn);
		Button btn2 = (Button) findViewById(R.id.visithallbtn);

		txtview1.setText("\t Bislig City is a city on the eastern coast of Mindanao facing the Pacific Ocean which is surrounded by lush natural forest and industrial tree plantations.");
		txtview2.setText("\t According to oral tradition, the city was named after the forest vine of the rattan family which grew in abundance along the river banks. This vine was noted for its strength and was credited known for saving a royal couple from drowning when they once crossed the river.");

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://bislig.gov.ph/");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AboutBislig.this,
						MaptoCityHall.class);
				startActivity(intent);

			}
		});

		// dial number
		textnum1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63868536089110"));
				startActivity(intent);

			}
		});

		textnum2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63868535466"));
				startActivity(intent);

			}
		});

		textnum3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869184538358"));
				startActivity(intent);

			}
		});

		textnum4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869236040285"));
				startActivity(intent);

			}
		});

		// Enable / Disable External Buttons - for Internet Connection
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// Enable the map button
			btn1.setEnabled(true);
			btn2.setEnabled(true);

		} else {
			// Internet connection is not present
			// Disable the map button
			btn1.setEnabled(false);
			btn1.setText("No Internet Connection");
			btn1.setTextColor(Color.GRAY);
			btn2.setEnabled(false);
			btn2.setText("Map To City Hall");
			btn2.setTextColor(Color.GRAY);
		}

	}

}
