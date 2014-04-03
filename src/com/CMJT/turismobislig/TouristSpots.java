/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author CMJT
 * 
 */
public class TouristSpots extends Activity {

	// Setting Constants
	public static final int BISLIGBAYWALK = 0;
	public static final int TINUYANFALLS = 1;
	public static final int OVP = 2;
	public static final int HINAYAGANCAVE = 3;
	public static final int HAGONOY = 4;
	public static final int FLORLAND = 5;
	public static final int KAWAKAWA = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touristspots);

		ListView list1 = (ListView) findViewById(R.id.touristspotslist);

		final String[] items1 = { "Bislig Baywalk", "Tinuy-an Falls",
				"Ocean View Park and International Doll House",
				"Hinayagan Cave", "Hagonoy Island", "FlorLand Resort",
				"Kawa-Kawa" };

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items1);
		list1.setAdapter(adapter1);

		// ----------------Item ClickListener-------------------
		list1 = (ListView) findViewById(R.id.touristspotslist);
		list1.setAdapter(adapter1);
		list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case BISLIGBAYWALK:
					intent = new Intent(TouristSpots.this, Baywalk.class);
					startActivity(intent);
					break;
				case TINUYANFALLS:
					intent = new Intent(TouristSpots.this, Tinuyan.class);
					startActivity(intent);
					break;
				case OVP:
					intent = new Intent(TouristSpots.this, Oceanview.class);
					startActivity(intent);
					break;
				case HINAYAGANCAVE:
					intent = new Intent(TouristSpots.this, Hinayagan.class);
					startActivity(intent);
					break;
				case HAGONOY:
					intent = new Intent(TouristSpots.this, Hagonoy.class);
					startActivity(intent);
					break;
				case FLORLAND:
					intent = new Intent(TouristSpots.this, Florland.class);
					startActivity(intent);
					break;
				case KAWAKAWA:
					intent = new Intent(TouristSpots.this, Kawakawa.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});

		setListViewHeightBasedOnChildren(list1);
	}

	public void setListViewHeightBasedOnChildren(ListView listView) {
		ArrayAdapter listAdapter = (ArrayAdapter) listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}
}
