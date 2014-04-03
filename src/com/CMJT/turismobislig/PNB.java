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
public class PNB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pnb);

		TextView textdes = (TextView) findViewById(R.id.textViewpnb);
		TextView textdes1 = (TextView) findViewById(R.id.textViewpnb1);
		Button btnmap = (Button) findViewById(R.id.btnmappnb);

		textdes.setText("The Philippine National Bank (PNB, Filipino: Bangko Nasyonal ng Pilipinas or Pambansang Bangko ng Pilipinas, Castilian Spanish: Banco Nacional de Agricola de Filipinas) is one of the largest banks in the Philippines.");
		textdes1.setText("Its Bislig branch is located at Abarca Street, Mangagoy Bislig City. Providing financial assistance to its clients.");

		// For Map
		btnmap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(PNB.this, MapPNB.class));

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
