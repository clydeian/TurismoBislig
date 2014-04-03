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
public class BusTerminal extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busterminal);

		TextView textbus = (TextView) findViewById(R.id.textViewBus);
		Button btnbus = (Button) findViewById(R.id.btnmapbus);

		textbus.setText("The only bus terminal in the city. It is operated by Bachelor Express Inc. These buses transport people in other nearby cities such as Davao City, Butuan City, Surigao City and Tagum City.");

		// For Map
		btnbus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(BusTerminal.this, MapBus.class));
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
			btnbus.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnbus.setEnabled(false);
			btnbus.setText("No Internet Connection");
		}

	}

}
