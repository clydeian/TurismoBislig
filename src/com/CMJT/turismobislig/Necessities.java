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

/**
 * @author CMJT
 * 
 */
public class Necessities extends Activity {

	// Case Constants
	public static final int PNB = 0;
	public static final int DBP = 1;
	public static final int LBP = 2;
	public static final int GRB = 3;
	public static final int MERCURY = 0;
	public static final int GENERICS = 1;
	public static final int TONYS = 2;
	public static final int GMARS = 3;
	public static final int JEEP = 0;
	public static final int HABALHABAL = 1;
	public static final int TRICYCLE = 2;
	public static final int BUS = 3;
	public static final int SEAPORT = 4;
	public static final int VAN = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.necessities);

		ListView list1 = (ListView) findViewById(R.id.neslist1);
		ListView list2 = (ListView) findViewById(R.id.neslist2);
		ListView list3 = (ListView) findViewById(R.id.neslist3);

		final String[] items1 = { "Philippine National Bank",
				"Development Bank of the Philippines",
				"Landbank of the Philippines", "East West Bank" };
		final String[] items2 = { "Mercury Drugstore", "The Generics Pharmacy",
				"Tony's Superstore", "G-mars Commercial" };
		final String[] items3 = { "Jeepney Integrated Terminal",
				"Habal-Habal Lounge", "Tricycle Terminal",
				"Phoenix Bus Terminal", "Mangagoy Local Sea Port",
				"MAVODCO Van For Hire Terminal" };

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items1);
		list1.setAdapter(adapter1);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items2);
		list2.setAdapter(adapter2);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items3);
		list2.setAdapter(adapter3);

		// -----------------1st Item ClickListener-------------------
		list1 = (ListView) findViewById(R.id.neslist1);
		list1.setAdapter(adapter1);
		list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case PNB:
					intent = new Intent(Necessities.this, PNB.class);
					startActivity(intent);
					break;
				case DBP:
					intent = new Intent(Necessities.this, DBP.class);
					startActivity(intent);
					break;
				case LBP:
					intent = new Intent(Necessities.this, Landbank.class);
					startActivity(intent);
					break;
				case GRB:
					intent = new Intent(Necessities.this, GreenBank.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

		// -----------------2nd Item ClickListener-------------------
		list2 = (ListView) findViewById(R.id.neslist2);
		list2.setAdapter(adapter2);
		list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case MERCURY:
					intent = new Intent(Necessities.this, MercuryDrug.class);
					startActivity(intent);
					break;
				case GENERICS:
					intent = new Intent(Necessities.this,
							GenericsPharmacy.class);
					startActivity(intent);
					break;
				case TONYS:
					intent = new Intent(Necessities.this, TonysStore.class);
					startActivity(intent);
					break;
				case GMARS:
					intent = new Intent(Necessities.this, GmarsCommercial.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

		// -----------------3rd Item ClickListener-------------------
		list3 = (ListView) findViewById(R.id.neslist3);
		list3.setAdapter(adapter3);
		list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case JEEP:
					intent = new Intent(Necessities.this, Jeep.class);
					startActivity(intent);
					break;
				case HABALHABAL:
					intent = new Intent(Necessities.this, Habalhabal.class);
					startActivity(intent);
					break;
				case TRICYCLE:
					intent = new Intent(Necessities.this, Tricycle.class);
					startActivity(intent);
					break;
				case BUS:
					intent = new Intent(Necessities.this, BusTerminal.class);
					startActivity(intent);
					break;
				case SEAPORT:
					intent = new Intent(Necessities.this, SeaPort.class);
					startActivity(intent);
					break;
				case VAN:
					intent = new Intent(Necessities.this, VanForHire.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

		setListViewHeightBasedOnChildren(list1);
		setListViewHeightBasedOnChildren(list2);
		setListViewHeightBasedOnChildren(list3);

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
