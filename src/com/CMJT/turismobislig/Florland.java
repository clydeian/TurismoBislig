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
public class Florland extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.florland);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap6 = (Button) findViewById(R.id.btnmap6);
		Button btnjeep = (Button) findViewById(R.id.btnjeep4);
		Button btnhabal = (Button) findViewById(R.id.btnhabal2);
		TextView textdes = (TextView) findViewById(R.id.textViewFlorland);
		TextView texttrans = (TextView) findViewById(R.id.florlandtranspo);
		TextView textnum1 = (TextView) findViewById(R.id.florlandacc3);
		TextView textnum2 = (TextView) findViewById(R.id.florlandacc4);

		textdes.setText("See yourself at Floorland Paradise Resort in its relaxing spots for its visitors and a cool swimming pool to bath in. Loose yourself and relax with nature at Floorland Paradise Resort.");
		texttrans
				.setText("Florland Resort is far from the heart of the city, so will ride either a jeepney or habal-habal to go there.");

		// map
		btnmap6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Florland.this, MapFlorland.class));
				finish();

			}
		});

		// jeep
		btnjeep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Florland.this, Jeep.class));

			}
		});

		// habal-habal
		btnhabal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Florland.this, Habalhabal.class));

			}
		});

		// dial first number
		textnum1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:63866160056"));
				startActivity(intent);

			}
		});

		// dial second number
		textnum2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:+63869284338879"));
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
			btnmap6.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap6.setEnabled(false);
			btnmap6.setText("No Internet Connection");
		}

	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.florlnd1,
				R.drawable.florlnd2, R.drawable.florlnd3 };

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
			Context context = Florland.this;
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
