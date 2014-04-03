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
public class Restaurants extends Activity {

	// Setting Constants
	public static final int TYFANNYS = 0;
	public static final int HIGHLAND = 1;
	public static final int MYDUNG = 2;
	public static final int GAILS = 3;
	public static final int BABANO = 4;
	public static final int BIANOS = 5;
	public static final int JMSTAR = 6;
	public static final int DANSGRILL = 7;
	public static final int CHICKENINATO = 8;
	public static final int ROCK = 9;
	public static final int NAYTITAYS = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant);

		ListView list2 = (ListView) findViewById(R.id.restaurantlist);

		final String[] items2 = { "Tfanny's Cuisine", "Highland Function Farm",
				"My Dung ", "Gail's Resto and Coffee Shoppe",
				"Casa de Babano Resto", "Biaños Pizzaderia",
				"Jm Star Food Hauz", "Dans Grill", "Chicken Inato",
				"The Rock Coffee Shop", "Nay Titays Kamayan" };

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items2);
		list2.setAdapter(adapter2);

		// ----------------Item ClickListener-------------------
		list2 = (ListView) findViewById(R.id.restaurantlist);
		list2.setAdapter(adapter2);
		list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case TYFANNYS:
					intent = new Intent(Restaurants.this, Tyffany.class);
					startActivity(intent);
					break;

				case HIGHLAND:
					intent = new Intent(Restaurants.this, Highland.class);
					startActivity(intent);
					break;

				case MYDUNG:
					intent = new Intent(Restaurants.this, MyDung.class);
					startActivity(intent);
					break;

				case GAILS:
					intent = new Intent(Restaurants.this, Gails.class);
					startActivity(intent);
					break;

				case BABANO:
					intent = new Intent(Restaurants.this, Babano.class);
					startActivity(intent);
					break;

				case BIANOS:
					intent = new Intent(Restaurants.this, Bianos.class);
					startActivity(intent);
					break;

				case JMSTAR:
					intent = new Intent(Restaurants.this, Jmstar.class);
					startActivity(intent);
					break;

				case DANSGRILL:
					intent = new Intent(Restaurants.this, DansGrill.class);
					startActivity(intent);
					break;

				case CHICKENINATO:
					intent = new Intent(Restaurants.this, Inato.class);
					startActivity(intent);
					break;

				case ROCK:
					intent = new Intent(Restaurants.this, TheRock.class);
					startActivity(intent);
					break;

				case NAYTITAYS:
					intent = new Intent(Restaurants.this, NayTitays.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

		setListViewHeightBasedOnChildren(list2);
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
