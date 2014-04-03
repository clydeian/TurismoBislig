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
public class Landbank extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landbank);

		TextView textdes = (TextView) findViewById(R.id.textViewlandbank);
		TextView textdes1 = (TextView) findViewById(R.id.textViewlandbank1);
		Button btnmap = (Button) findViewById(R.id.btnmaplandbank);

		textdes.setText("Land Bank of the Philippines (Filipino: Bangko sa Lupa ng Pilipinas), (Spanish: Banco Hipotecario de Filipinas') also known as LANDBANK or by its initials, LBP, is a universal bank in the Philippines owned by the Philippine government with a special focus on serving the needs of farmers and fishermen. ");
		textdes1.setText("Its Bislig branch is located at San Vicente Street, Mangagoy Bislig City, providing services to Filipino farmers, fishermen and indigents.");

		// For Map
		btnmap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Landbank.this, MapLandbank.class));

			}
		});

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// Enable the map button
			btnmap.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap.setEnabled(false);
			btnmap.setText("No Internet Connection");
		}

	}

}
