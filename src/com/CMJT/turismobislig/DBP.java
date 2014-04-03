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
public class DBP extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbp);

		TextView textdes = (TextView) findViewById(R.id.textViewdbp);
		TextView textdes1 = (TextView) findViewById(R.id.textViewdbp1);
		Button btnmap = (Button) findViewById(R.id.btnmapdbp);

		textdes.setText("The Development Bank of the Philippines commonly known by its initials, DBP, is a state-owned development bank with headquarters in Makati, Philippines. It is the seventh-largest bank in the Philippines in terms of assets, and is the second-largest government-owned bank, next only to Landbank.");
		textdes1.setText("Its Bislig branch is located at Espiritu Street, Mangagoy Bislig City, In front of Rolly’s Bakeshop Building.");

		// For Map
		btnmap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(DBP.this, MapDBP.class));

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
