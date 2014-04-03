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
public class Hinayagan extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hinayagan);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		Button btnmap4 = (Button) findViewById(R.id.btnmap4);
		Button btntri = (Button) findViewById(R.id.btntricyle2);
		Button btnjeep = (Button) findViewById(R.id.btnjeep3);
		TextView textdes = (TextView) findViewById(R.id.textViewHinayagan);
		TextView texttrans = (TextView) findViewById(R.id.transpohinayagan);

		textdes.setText("Previously called “Suhoton Cave” from the local term “suhot” ,which means “to pass through inside”. Bidok a hunter who in that time was chasing a boar discovered this cave.  Since then, villagers considered it a place where their Gods dwelt. Its name was changed because there are other caves in the country that Bear the name Suhoton.  For identity, the local folks renamed it “Hinayagan” which means, “lighted.”  This was so-called Hinayagan because of the opening in the mid-ceiling of the cave.");
		texttrans
				.setText("To get to Hinayagan Cave, its highly recommended to ride a Habal – habal. Yet, cars with big wheels can do also.");

		// map
		btnmap4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Hinayagan.this, MapHinayaganCave.class));
				finish();

			}
		});

		// habalhabal
		btntri.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Hinayagan.this, Habalhabal.class));

			}
		});

		// Jeepney
		btnjeep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Hinayagan.this, Jeep.class));

			}
		});

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// Enable the map button
			btnmap4.setEnabled(true);
		} else {
			// Internet connection is not present
			// Disable the map button
			btnmap4.setEnabled(false);
			btnmap4.setText("No Internet Connection");
		}

	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { R.drawable.hinayagan1,
				R.drawable.hinayagan2, R.drawable.hinayagan3 };

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
			Context context = Hinayagan.this;
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
