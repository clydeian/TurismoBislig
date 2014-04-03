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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author CMJT
 * 
 */
public class Kawakawa extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kawakawa);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap = (Button) findViewById(R.id.btnmapkawakawa);
		Button btnbangka = (Button) findViewById(R.id.btnbangka);
		Button btnjeep = (Button) findViewById(R.id.btnjeep);
		TextView textdes = (TextView) findViewById(R.id.textViewKawakawa);
		TextView textrans = (TextView) findViewById(R.id.transpokawakawa);

		textdes.setText("'Kawa - Kawa' also known as the vast cauldron of water. There are pot holes in the river that serves as a natural jacuzzi. More than 16 bathing tubs resembling giant woks of different sizes. Many people are facinated to its beautiful structures.");
		textrans.setText("21 kms. From Poblacion, from Bislig wharf ,1 hr. leisurely pump boat ride. You can ride to lake 77 and take a boat to the kawa-kawa.");

		// For Map
		btnmap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Kawakawa.this, MapKawakawa.class));
				finish();

			}
		});

		// For Bangka
		btnbangka.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Kawakawa.this, SeaPort.class));

			}
		});

		// For Jeep
		btnjeep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Kawakawa.this, Jeep.class));

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
		private int[] mImages = new int[] { R.drawable.kawa_kawa,
				R.drawable.kawa_kawa1, R.drawable.kawa_kawa2 };

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
			Context context = Kawakawa.this;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
