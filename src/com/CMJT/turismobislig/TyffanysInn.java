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
public class TyffanysInn extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tyffanysinn);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap = (Button) findViewById(R.id.btnmap22);
		TextView textdes = (TextView) findViewById(R.id.textViewtfannysinn);
		TextView textres = (TextView) findViewById(R.id.textreservationstfannysinn);
		TextView textnum1 = (TextView) findViewById(R.id.tfannysinnnumber1);
		TextView textnum2 = (TextView) findViewById(R.id.tfannysinnnumber2);
		TextView textnum3 = (TextView) findViewById(R.id.tfannysinnnumber3);

		textdes.setText("A new hotel in Bislig City, A relaxing and one of the well known accomodations around. With Air-condition, with wifi , with bathroom in Cr., HOT & COLD Shower. Free breakfast from 6:30 am to 10:00 am.");
		textres.setText("The hotel caters room reservations at affordable and reasonable prices. A great place to sleep on.");

		// Map
		btnmap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(TyffanysInn.this, MapTyfannysInn.class));

			}
		});

		// dial number
		textnum1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:63868536304"));
				startActivity(intent);

			}
		});

		// dial number
		textnum2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869194708998"));
				startActivity(intent);

			}
		});

		// dial number
		textnum3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869107776465"));
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
		private int[] mImages = new int[] { R.drawable.tfannyinn2,
				R.drawable.tfannyinn1 };

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
			Context context = TyffanysInn.this;
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
