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
public class Jeep extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jeep);

		TextView textjeep = (TextView) findViewById(R.id.textViewJeep);
		Button btnjeep = (Button) findViewById(R.id.btnmapjeep);

		textjeep.setText("Jeepney is the common long distance transportation of Bislig City. If want to see Natural beauty of Bislig with your family and friends the Jeepney is hihgly recomended for your trip. They are not usually roaming around the city in order to get ride with you go to their terminal.");

		// For Map
		btnjeep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Jeep.this, MapJeep.class));
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
			btnjeep.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnjeep.setEnabled(false);
			btnjeep.setText("No Internet Connection");
		}

	}

}
