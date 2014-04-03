/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author CMJT
 * 
 */
public class Tricycle extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tricycle);

		TextView texttri = (TextView) findViewById(R.id.textViewTricycle);
		Button btntricycle = (Button) findViewById(R.id.btnmaptricycle);

		texttri.setText("This is the common transportation in Bislig City. Also known as short distance transportation since they can only take the passengers within the city. The tricycle can transport you only within the Mangagoy and Bislig main roads and they cannot go to other places such as remote areas. They are commonly roaming around the city and picking up passengers. But if want to have a trip for a certain number of persons you can go to their terminal to transport you directly.");

		// For Map
		btntricycle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tricycle.this, MapTricycle.class));
				finish();

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
