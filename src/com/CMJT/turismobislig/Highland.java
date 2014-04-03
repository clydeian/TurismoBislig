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
public class Highland extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highland);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap8 = (Button) findViewById(R.id.btnmap8);
		TextView textdes = (TextView) findViewById(R.id.textViewhighland);
		TextView textres = (TextView) findViewById(R.id.textreservationshighland);
		TextView textnum1 = (TextView) findViewById(R.id.highlandnumber1);
		TextView textnum2 = (TextView) findViewById(R.id.highlandnumber2);

		textdes.setText("Watch Barangay Mangagoy over hill as you dine in in the one of the best restaurants the Highland Function Farm. The place is perfect for family outing and occasion. Enjoy and feel the breeze of nature. They also serve snacks all different variants.");
		textres.setText("They offer catering services for all occasions and provide reservations for your loved ones.");

		btnmap8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Highland.this, MapHighland.class));

			}
		});

		// dial number1
		textnum1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:63868533355"));
				startActivity(intent);

			}
		});

		// dial number2
		textnum2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869399043572"));
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
			btnmap8.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap8.setEnabled(false);
			btnmap8.setText("No Internet Connection");
		}

	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.highland,
				R.drawable.highland1 };

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
			Context context = Highland.this;
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
