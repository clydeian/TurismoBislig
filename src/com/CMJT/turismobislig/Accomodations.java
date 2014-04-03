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
public class Accomodations extends Activity {

	// Setting Constants
	public static final int PCI = 0;
	public static final int SHIELAS = 1;
	public static final int PLS = 2;
	public static final int CASABABANO = 3;
	public static final int TYFANNYINN = 4;
	public static final int SLEEPINN = 5;
	public static final int YOLANDA = 6;
	public static final int VIOLYS = 7;
	public static final int STFRANCIS = 8;
	public static final int BONSAY = 9;
	public static final int WHITEPALACE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accomodations);
		ListView list3 = (ListView) findViewById(R.id.accommodationlist);

		final String[] items3 = { "Paper Country Inn", "Sheilas Pension House",
				"Pensione La Salle", "Casa de Babano", "Tffany's Inn",
				"Sleep Inn", "Yolanda Inn", "Violy's Pension House",
				"St.Francis Pension House", "Bonsay Riverview Inn",
				"White Palace Inn" };

		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items3);
		list3.setAdapter(adapter3);

		// ----------------Item ClickListener-------------------
		list3 = (ListView) findViewById(R.id.accommodationlist);
		list3.setAdapter(adapter3);
		list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case PCI:
					intent = new Intent(Accomodations.this,
							PaperCountryInn.class);
					startActivity(intent);
					break;

				case SHIELAS:
					intent = new Intent(Accomodations.this,
							ShielasPensionHouse.class);
					startActivity(intent);
					break;

				case PLS:
					intent = new Intent(Accomodations.this,
							PensioneLaSalle.class);
					startActivity(intent);
					break;

				case CASABABANO:
					intent = new Intent(Accomodations.this, CasadeBabano.class);
					startActivity(intent);
					break;

				case TYFANNYINN:
					intent = new Intent(Accomodations.this, TyffanysInn.class);
					startActivity(intent);
					break;

				case SLEEPINN:
					intent = new Intent(Accomodations.this, SleepInn.class);
					startActivity(intent);
					break;

				case YOLANDA:
					intent = new Intent(Accomodations.this, YolandaInn.class);
					startActivity(intent);
					break;

				case VIOLYS:
					intent = new Intent(Accomodations.this,
							ViolysPensionHouse.class);
					startActivity(intent);
					break;

				case STFRANCIS:
					intent = new Intent(Accomodations.this,
							StFrancisPensionHouse.class);
					startActivity(intent);
					break;

				case BONSAY:
					intent = new Intent(Accomodations.this,
							BonsayRiverviewInn.class);
					startActivity(intent);
					break;

				case WHITEPALACE:
					intent = new Intent(Accomodations.this,
							WhitePalaceInn.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

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
