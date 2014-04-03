package com.CMJT.turismobislig;

import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// Case Constants
	public static final int ABOUTBISLIG = 0;
	public static final int MYLOCATION = 1;
	public static final int TOURISTSPOTS = 0;
	public static final int RESTAURANTS = 1;
	public static final int ACCOMODATIONS = 2;
	public static final int EMERGENCYNUMBERS = 3;
	public static final int NECESSITIES = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ----------------------Detect Network Connection--------------

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false

		final TextView txtOut = (TextView) findViewById(R.id.txtView2);
		// check for Internet status
		if (isInternetPresent) {
			// Internet Connection is Present
			// make HTTP requests
			txtOut.setText("ONLINE MODE");
			txtOut.setTextColor(Color.BLUE);
		} else {
			// Internet connection is not present
			// Ask user to connect to Internet
			txtOut.setText("OFFLINE MODE");
			txtOut.setTextColor(Color.RED);
			showAlertDialog(
					MainActivity.this,
					"WARNING!!\nNO Internet",
					"Map will not Function Normally without internet. Please Turn ON your Wifi in order to use the services to its full functionality. ",
					false);
		}

		// --- GPS DETECTOR

		final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			buildAlertMessageNoGps();
		}

		// --Banner Click
		TextView tv1 = (TextView) findViewById(R.id.txtviewc);

		tv1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showAlertDialog(
						MainActivity.this,
						"G.ANDROID DEV.\n" + "|CMJT-2014|",
						"Clyde Ian Acaso\n - Developer -\n\n"
								+ "Marigold Partosa\n - UI/UX Designer -\n"
								+ "\nJoie F. Cosmiano\n"
								+ "Theresa Mae T. Suan\n"
								+ "- Data Researchers -"
								+ "\n\n- NOTE -\n This app serves as the THESIS PROJECT of DLSJBC IT Students.",
						true);

			}
		});

		// ----------------------List Activity---------------------------

		ListView list1 = (ListView) findViewById(R.id.list1);
		ListView list2 = (ListView) findViewById(R.id.list2);

		final String[] items1 = { "About Bislig", "Your Location" };
		final String[] items2 = { "Tourist Spots", "Restaurants",
				"Accomodations", "Directory", "Utilities" };

		ArrayAdapter<String> adapter1 = new ArrayAdapter1(this,
				android.R.layout.simple_list_item_1, R.id.txtView1, items1);
		list1.setAdapter(adapter1);
		ArrayAdapter<String> adapter2 = new ArrayAdapter2(this,
				android.R.layout.simple_list_item_1, R.id.txtView1, items2);
		list2.setAdapter(adapter2);

		// -----------------1st Item ClickListener-------------------
		list1 = (ListView) findViewById(R.id.list1);
		list1.setAdapter(adapter1);
		list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case ABOUTBISLIG:
					intent = new Intent(MainActivity.this, AboutBislig.class);
					startActivity(intent);
					break;
				case MYLOCATION:
					intent = new Intent(MainActivity.this, MapLocation.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

		// -----------------2nd Item ClickListener-------------------
		list2 = (ListView) findViewById(R.id.list2);
		list2.setAdapter(adapter2);
		list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case TOURISTSPOTS:
					intent = new Intent(MainActivity.this, TouristSpots.class);
					startActivity(intent);
					break;
				case RESTAURANTS:
					intent = new Intent(MainActivity.this, Restaurants.class);
					startActivity(intent);
					break;
				case ACCOMODATIONS:
					intent = new Intent(MainActivity.this, Accomodations.class);
					startActivity(intent);
					break;
				case EMERGENCYNUMBERS:
					intent = new Intent(MainActivity.this,
							EmergencyNumber.class);
					startActivity(intent);
					break;
				case NECESSITIES:
					intent = new Intent(MainActivity.this, Necessities.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

		setListViewHeightBasedOnChildren(list1);
		setListViewHeightBasedOnChildren(list2);

	}

	// --Redirect to GPS Settings
	private void buildAlertMessageNoGps() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"Your GPS seems to be disabled, do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									final int id) {
								startActivity(new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * Function to display simple Alert Dialog for no Internet connection
	 * */
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog
				.setIcon((status) ? R.drawable.dlsjbc_logo : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();

		// Setting to Center
		TextView messageView = (TextView) alertDialog
				.findViewById(android.R.id.message);
		messageView.setGravity(Gravity.CENTER);
	}

	// 1st List Icon Placing
	private class ArrayAdapter1 extends ArrayAdapter<String> {

		public ArrayAdapter1(Context context, int resource,
				int textViewResourceId, String[] strings) {
			super(context, resource, textViewResourceId, strings);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.menulist, parent, false);
			final String[] items1 = { "About Bislig", "Your Location" };

			ImageView iv = (ImageView) row.findViewById(R.id.imgView1);
			TextView tv = (TextView) row.findViewById(R.id.txtView1);

			tv.setText(items1[position]);

			if (items1[position].equals("About Bislig")) {
				iv.setImageResource(R.drawable.abouticon);
			} else if (items1[position].equals("Your Location")) {
				iv.setImageResource(R.drawable.mylocation);
			}

			return row;
		}
	}

	// 2nd List Icon Placing
	private class ArrayAdapter2 extends ArrayAdapter<String> {

		public ArrayAdapter2(Context context, int resource,
				int textViewResourceId, String[] strings) {
			super(context, resource, textViewResourceId, strings);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.menulist, parent, false);
			String[] items2 = { "Tourist Spots", "Restaurants",
					"Accomodations", "Directory", "Utilities" };

			ImageView iv = (ImageView) row.findViewById(R.id.imgView1);
			TextView tv = (TextView) row.findViewById(R.id.txtView1);

			tv.setText(items2[position]);

			if (items2[position].equals("Tourist Spots")) {
				iv.setImageResource(R.drawable.attractionsicon);
			} else if (items2[position].equals("Restaurants")) {
				iv.setImageResource(R.drawable.restauranticon);
			} else if (items2[position].equals("Accomodations")) {
				iv.setImageResource(R.drawable.accomodationicon);
			} else if (items2[position].equals("Directory")) {
				iv.setImageResource(R.drawable.emergencyicon);
			} else if (items2[position].equals("Utilities")) {
				iv.setImageResource(R.drawable.market);
			}

			return row;
		}
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

	// Hard Back Button Override
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								MainActivity.this.finish();
							}
						}).setNegativeButton("No", null).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
