/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author CMJT
 * 
 */
public class EmergencyNumber extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emergencynumber);

		// Dial Mgy Police Station
		TextView txtviewpolice1 = (TextView) findViewById(R.id.txtviewc);

		txtviewpolice1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:166"));
				startActivity(intent);

			}
		});

		// Dial Poblacion Police Station
		TextView txtviewpolice2 = (TextView) findViewById(R.id.txtviewpolice2);

		txtviewpolice2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:167"));
				startActivity(intent);

			}
		});

		// Dial Bureau of Fire Station
		TextView txtviewfire1 = (TextView) findViewById(R.id.txtviewfire1);

		txtviewfire1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:160"));
				startActivity(intent);

			}
		});

		// Dial Hospital
		TextView txtviewhospital1 = (TextView) findViewById(R.id.txtviewhospital1);
		TextView txtviewhospital2 = (TextView) findViewById(R.id.txtviewhospital2);
		TextView txtviewhospital3 = (TextView) findViewById(R.id.txtviewhospital3);
		TextView txtviewhospital4 = (TextView) findViewById(R.id.txtviewhospital4);
		TextView txtviewhospital5 = (TextView) findViewById(R.id.txtviewhospital5);

		txtviewhospital1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:169"));
				startActivity(intent);

			}
		});

		txtviewhospital2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:0868533124"));
				startActivity(intent);

			}
		});

		txtviewhospital3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:0868535338"));
				startActivity(intent);

			}
		});

		txtviewhospital4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:0868534398"));
				startActivity(intent);

			}
		});

		txtviewhospital5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:0868531365"));
				startActivity(intent);

			}
		});

	}
}
