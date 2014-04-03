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
public class Tinuyan extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tinuyan);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap2 = (Button) findViewById(R.id.btnmap2);

		btnmap2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tinuyan.this, MapTinuyanFalls.class));
				finish();

			}
		});

		Button btnhabal = (Button) findViewById(R.id.btnhabal);
		Button btnjeep = (Button) findViewById(R.id.btnjeep);
		TextView textdes = (TextView) findViewById(R.id.textViewTinuyan);
		TextView textrans = (TextView) findViewById(R.id.transpo1);

		textdes.setText("The falls are 95 m wide and 55 metres (180 ft) high, touted as the Niagara Falls of the Philippines. Tinuy-an is a white water curtain that flows in three levels and is said to be the widest waterfall in the Philippines. Every morning, the area shows a rainbow between 9 a.m. to 11 a.m.Beautiful place, easy access. can swim at the base of the falls, take a raft to the mist, or climb to the top. Very nice to visit.");
		textrans.setText("To go to the falls tourist should ride any for hire vehicles one of the common transportation is the Habal-Habal. It is the only ride that can take you directly to the site. Another alternative ride is to get in the jeep but it only stop in Brgy. Borboanan where you should take a second ride to reach the falls.");

		btnhabal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tinuyan.this, Habalhabal.class));

			}
		});

		btnjeep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tinuyan.this, Jeep.class));

			}
		});

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// Enable the map button
			btnmap2.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap2.setEnabled(false);
			btnmap2.setText("No Internet Connection");
		}

	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.tinuyan1,
				R.drawable.tinuyan2, R.drawable.tinuyan3 };

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
			Context context = Tinuyan.this;
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
