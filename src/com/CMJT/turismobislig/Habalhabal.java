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
public class Habalhabal extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.habalhabal);

		TextView texthabal = (TextView) findViewById(R.id.textViewHabal);
		Button btnmaphabal = (Button) findViewById(R.id.btnmaphabal);

		texthabal
				.setText("It is the another alternative transportation of the jeepney. In case your the only one to explore the beauty of Bislig the habal- habal is highly recommended for your trip. You can also less from fees since they are only motorcycles (with wings). Enjoy the adventurous ride of Habal-Habal an alternative motorcycle transport vehicle for remote areas in the city to explore.");

		// For Map
		btnmaphabal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Habalhabal.this, MapHabal.class));
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
			btnmaphabal.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmaphabal.setEnabled(false);
			btnmaphabal.setText("No Internet Connection");
		}

	}

}
