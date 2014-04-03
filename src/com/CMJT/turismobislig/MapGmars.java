/**
 * 
 */
package com.CMJT.turismobislig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * @author CMJT
 * 
 */
public class MapGmars extends FragmentActivity implements
		OnMyLocationChangeListener, LocationListener {

	private GoogleMap mMap;
	private Marker myMarker;
	private String provider;
	private LocationManager locationManager;
	private LatLng latLng;
	private LatLng latLng1;
	RadioButton rbDriving;
	RadioButton rbWalking;
	RadioGroup rgModes;
	ArrayList<LatLng> markerPoints;
	int mMode = 0;
	final int MODE_DRIVING = 0;
	final int MODE_WALKING = 2;
	TextView tvDistanceDuration;
	private Polyline poly;
	private static final LatLng Gmars = new LatLng(8.18940, 126.35539);
	private Map<Marker, Class> allMarkersMap = new HashMap<Marker, Class>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maplocation);
		setUpMapIfNeeded();

		// Getting Google Play availability status
		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());

		// Showing status
		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
													// not available
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();
		}

		else { // if google is available

			// Getting reference to rb_driving
			rbDriving = (RadioButton) findViewById(R.id.rb_driving);

			// Getting reference to rb_walking
			rbWalking = (RadioButton) findViewById(R.id.rb_walking);

			// Getting Reference to rg_modes
			rgModes = (RadioGroup) findViewById(R.id.rg_modes);

			this.tvDistanceDuration = (TextView) this
					.findViewById(R.id.tv_distance_time);
			// Initializing
			this.markerPoints = new ArrayList<LatLng>();

			// Get LocationManager object from System Service LOCATION_SERVICE
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			// Create a criteria object to retrieve provider
			Criteria criteria = new Criteria();
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			criteria.setAccuracy(Criteria.ACCURACY_COARSE);
			criteria.setPowerRequirement(Criteria.POWER_MEDIUM);

			// Get the name of the best provider
			provider = locationManager.getBestProvider(criteria, true);

			// Get Current Location
			locationManager.requestLocationUpdates(provider, 1000, 0, this);
			Location myLocation = locationManager
					.getLastKnownLocation(provider);
			if (myLocation != null) {
				onMyLocationChange(myLocation);
			}

			// set map type
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			// Enabling MyLocation Layer of Google Map
			mMap.setMyLocationEnabled(true);

			// Setting event handler for location change
			mMap.setOnMyLocationChangeListener(this);

			mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick(Marker marker) {
					Class cls = allMarkersMap.get(marker);
					Intent intent = new Intent(MapGmars.this, cls);
					startActivity(intent);

				}

			});

			// Creating a LatLng object for the Gmars Commercial location
			latLng1 = new LatLng(8.18940, 126.35539);

			// Showing the current location in Google Map
			mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));

			// Zoom in the Google Map
			mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

			// Zoom in Gmars Commercial Map
			Marker Marker44 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18940, 126.35539))
					.title("Gmars Commercial")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.store)));
			allMarkersMap.put(Marker44, GmarsCommercial.class);

			rgModes.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {

					// mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

					LatLng origin = latLng;
					LatLng dest = Gmars;

					// Getting URL to the Google Directions API
					String url = MapGmars.this.getDirectionsUrl(origin, dest);

					DownloadTask downloadTask = new DownloadTask();

					// Start downloading json data from Google Directions API
					downloadTask.execute(url);
				}

			});

			LatLng origin = latLng;
			LatLng dest = Gmars;

			// Getting URL to the Google Directions API
			String url = MapGmars.this.getDirectionsUrl(origin, dest);

			DownloadTask downloadTask = new DownloadTask();

			// Start downloading json data from Google Directions API
			downloadTask.execute(url);

		}

	}

	private String getDirectionsUrl(LatLng origin, LatLng dest) {
		// Origin of route
		String str_origin = "origin=" + origin.latitude + ","
				+ origin.longitude;

		// Destination of route
		String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

		// Sensor enabled
		String sensor = "sensor=false";

		// Travelling Mode
		String mode = "mode=driving";

		if (rbDriving.isChecked()) {
			mode = "mode=driving";
			mMode = 0;
		} else if (rbWalking.isChecked()) {
			mode = "mode=walking";
			mMode = 2;
		}

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&" + sensor + "&"
				+ mode;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;

		return url;
	}

	/** A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String> {
		private ProgressDialog progressDialog;

		// Downloading data in non-ui thread

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(MapGmars.this);
			progressDialog.setMessage("Fetching route, Please wait...");
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = MapGmars.this.downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.hide();
			ParserTask parserTask = new ParserTask();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);

		}

	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {
			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();

				// Starts parsing data
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {

			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();
			String distance = "";
			String duration = "";

			if (result.size() < 1) {
				Toast.makeText(MapGmars.this.getBaseContext(),
						"You are out of navigation bounds",
						Toast.LENGTH_SHORT).show();
				return;
			}

			// Traversing through all the routes
			for (int i = 0; i < result.size(); i++) {
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);

				// Fetching all the points in i-th route
				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					if (j == 0) { // Get distance from the list
						distance = point.get("distance");
						continue;
					} else if (j == 1) { // Get duration from the list
						duration = point.get("duration");
						continue;
					}
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);
					points.add(position);
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(2);

				// Changing the color polyline according to the mode
				if (mMode == MODE_DRIVING)
					lineOptions.color(Color.RED);

				else if (mMode == MODE_WALKING)
					lineOptions.color(Color.BLUE);

			}
			if (mMode == MODE_DRIVING)
				MapGmars.this.tvDistanceDuration.setText("Distance:" + distance
						+ ", Duration:" + duration);
			else if (mMode == MODE_WALKING)
				MapGmars.this.tvDistanceDuration.setText("Distance:" + distance
						+ ", Duration:" + duration);

			// Drawing polyline in the Google Map for the i-th route
			if (poly != null) {

				poly.remove();
			}
			poly = MapGmars.this.mMap.addPolyline(lineOptions);
		}
	}

	@Override
	public void onPause() {
		if (locationManager != null) {
			locationManager.removeUpdates(this);
		}

		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
		locationManager.requestLocationUpdates(provider, 1000, 0, this);
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onMyLocationChange(Location location) {

		// Remove Recent Marker
		if (myMarker != null) {

			myMarker.remove();
		}

		// Getting latitude of the current location
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();

		// Creating a LatLng object for the current location
		latLng = new LatLng(latitude, longitude);

		// Update the marker
		myMarker = mMap.addMarker(new MarkerOptions()
				.position(new LatLng(latitude, longitude))
				.title("You are here!")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		allMarkersMap.put(myMarker, Restaurants.class);

	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
