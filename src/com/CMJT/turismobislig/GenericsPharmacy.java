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
public class GenericsPharmacy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.generics);

		TextView textdes = (TextView) findViewById(R.id.textViewgenerics);
		TextView textdes1 = (TextView) findViewById(R.id.textViewgenerics1);
		Button btnmap = (Button) findViewById(R.id.btnmapgenerics);

		textdes.setText("THE GENERICS PHARMACY (TGP) is a pharmaceutical company, acknowledging the dire need for quality medicines but at affordable prices, the company focused on generic medicines to provide the Filipino with a more affordable alternative.");
		textdes1.setText(" Its Bislig branch is located at Abarca Street, Mangagoy Bislig City, next to the Mercury Drug building.");

		// For Map
		btnmap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(GenericsPharmacy.this,
						MapGenerics.class));

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
