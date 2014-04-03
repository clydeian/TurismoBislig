/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
public class Bianos extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bianos);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap = (Button) findViewById(R.id.btnmap12);
		TextView textdes = (TextView) findViewById(R.id.textViewbianos);
		TextView textres = (TextView) findViewById(R.id.textreservationsbianos);
		TextView textnum = (TextView) findViewById(R.id.bianosnumber);

		textdes.setText("Bianos Pizzaderia serves and delivers variety of flavored pizza to your homes and hotels at affordable price.Popular to the town folks in Mangagoy because of their tasty Pizza. They are the only pizzaderia that has many customers overtime. For affordable and reasonable price you can taste their tasty pizza.");
		textres.setText("They deliver pizza in your homes and hotels just call them and they'll be there.");

		// Map
		btnmap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Bianos.this, MapBianos.class));

			}
		});

		// dial number
		textnum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:63868532554"));
				startActivity(intent);

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

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.bianos,
				R.drawable.bianos1 };

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
			Context context = Bianos.this;
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
