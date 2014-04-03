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

public class Tyffany extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tyffany);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap7 = (Button) findViewById(R.id.btnmap7);
		TextView textdes = (TextView) findViewById(R.id.textViewtyffany);
		TextView textres = (TextView) findViewById(R.id.textreservations);
		TextView textnum = (TextView) findViewById(R.id.tyffanynumber);

		textdes.setText("Taste the one of the Filipinos Delicacy. The Tfannys Chopsuey is one of thier delicious menu, consist of different variants beef, chicken and pork in affordable price. Try and visit Tfannys Restaurant. ");
		textres.setText("Thier Building is spacious great for parties and other celebrations. They offer reservations in affordable price.");

		btnmap7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tyffany.this, MapTyfanny.class));

			}
		});

		// dial number
		textnum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869303223083"));
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
			btnmap7.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap7.setEnabled(false);
			btnmap7.setText("No Internet Connection");

		}

	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.tfanny,
				R.drawable.tfanny1 };

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
			Context context = Tyffany.this;
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
