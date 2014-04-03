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
public class Oceanview extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ovp);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap3 = (Button) findViewById(R.id.btnmap3);
		Button btntri = (Button) findViewById(R.id.btntricyle1);
		Button btnjeep = (Button) findViewById(R.id.btnjeep2);
		TextView textdes = (TextView) findViewById(R.id.textViewOvp);
		TextView texttrans = (TextView) findViewById(R.id.transpoovp);

		textdes.setText("It offers very affordable food, cozy yet airy ambiance with breathtaking ocean view of Bislig City. It is where family enjoys time together, friends gather and socialize, and is a place where we can kick back our heals and relax at the end of a long day. Ocean View Park has a special attraction to the guests an 'International Doll House' collections of dolls from all over the world.");
		texttrans
				.setText("You can go to the park via Tricycle or by Jeepney. You can easily locate the place since it is near in the highway and easy to recognize.");

		btnmap3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Oceanview.this, MapOVP.class));
				finish();

			}
		});

		btntri.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Oceanview.this, Tricycle.class));

			}
		});

		btnjeep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Oceanview.this, Jeep.class));

			}
		});

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// Enable the map button
			btnmap3.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap3.setEnabled(false);
			btnmap3.setText("No Internet Connection");
		}

	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.ovp1, R.drawable.ovp2,
				R.drawable.ovp3 };

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
			Context context = Oceanview.this;
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
