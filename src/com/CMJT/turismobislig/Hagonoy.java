/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author CMJT
 * 
 */
public class Hagonoy extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hagonoy);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap5 = (Button) findViewById(R.id.btnmap5);
		Button btnbangka = (Button) findViewById(R.id.btnbangka);
		TextView textdes = (TextView) findViewById(R.id.textViewHagonoy);
		TextView texttrans = (TextView) findViewById(R.id.transhagonoy);

		textdes.setText("A pearl shaped island with clear blue water; 45 minutes pump-boat ride within the Bislig Bay. Suitable for a tropical getaway. Though it is also a towering ocean surrounded by powdery white sand beach, sparkling turquoise waters and coconut trees that shed against searing heat and punishing winds of the Pacific.");
		texttrans
				.setText("To Hagonoy Island adventure you must ride a Bangka a big boat used in transporting people across the island of Bislig City. This app routes you to the nearest sea ports in order for you to ride a boat. Hagonoy Island can be reached via pump boat from the Mangagoy sea port, travel time: approximately 5- 30 minutes depending on the wind, sea currents and sea waves.");

		// Map
		btnmap5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Hagonoy.this, MapHagonoy.class));
				finish();

			}
		});

		// Bangka
		btnbangka.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Hagonoy.this, SeaPort.class));

			}
		});

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// Enable the map button
			btnmap5.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap5.setEnabled(false);
			btnmap5.setText("No Internet Connection");
		}

	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.hagonoy1,
				R.drawable.hagonoy2, R.drawable.hagonoy3 };

		@Override
		public int getCount() {
			return mImages.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((ImageView) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Context context = Hagonoy.this;
			ImageView imageView = new ImageView(context);
			int padding = context.getResources().getDimensionPixelSize(
					R.dimen.activity_horizontal_margin);
			imageView.setPadding(0, 0, 0, 0);
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setImageResource(mImages[position]);
			((ViewPager) container).addView(imageView, 0);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((ImageView) object);
		}
	}

}
