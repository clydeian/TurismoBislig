/**
 * 
 */
package com.CMJT.turismobislig;

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

/**
 * @author CMJT this class shows Current Location of the user
 * 
 */
public class MapLocation extends FragmentActivity implements
		OnMyLocationChangeListener, LocationListener {

	private GoogleMap mMap;
	private Marker myMarker;
	private String provider;
	private LocationManager locationManager;
	private LatLng latLng;
	private Map<Marker, Class> allMarkersMap = new HashMap<Marker, Class>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
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
			final Location myLocation = locationManager
					.getLastKnownLocation(provider);
			if (myLocation != null) {
				onMyLocationChange(myLocation);
			}

			// set map type
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			// Setting event handler for location change
			mMap.setOnMyLocationChangeListener(this);

			// RESTAURANTS -----------------------------------------

			// Zoom in the Tfanny's Map
			Marker Marker2 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18706, 126.35402))
					.title("Tfanny's Restaurant")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker2, Tyffany.class);

			// Zoom in the Highland Map
			Marker Marker3 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18107, 126.35754))
					.title("Highland Function Farm")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker3, Highland.class);

			// Zoom in the MyDung Map
			Marker Marker4 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18814, 126.35558))
					.title("My Dung Restaurant")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker4, MyDung.class);

			// Zoom in the Gails Restaurant Map
			Marker Marker5 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18842, 126.35542))
					.title("Gail's Resto and Coffee Shoppe")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker5, Gails.class);

			// Zoom in the Casa de Babano Restaurant Map
			Marker Marker6 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18839, 126.35602))
					.title("Casa de Babano Restaurant")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker6, Babano.class);

			// Zoom in the Biaños Map
			Marker Marker7 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18851, 126.35574))
					.title("Biaños Pizzaderia")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker7, Bianos.class);

			// Zoom in the JM Star Map
			Marker Marker8 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18812, 126.35599))
					.title("Jm Star Food Hauz")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker8, Jmstar.class);

			// Zoom in the Dans Grill Map
			Marker Marker9 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18748, 126.35545))
					.title("Dans Grill Restaurant")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker9, DansGrill.class);

			// Zoom in the Chicken Inato Map
			Marker Marker10 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18548, 126.35772))
					.title("Chicken Inato")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker10, Inato.class);

			// Zoom in the The Rock Cafe Map
			Marker Marker29 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18602, 126.35662))
					.title("The Rock Cafe")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker29, TheRock.class);

			// Zoom in the The Nay Titay's Kamayan Map
			Marker Marker42 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18756, 126.35432))
					.title("Nay Titay's Kamayan")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.restaurant)));
			allMarkersMap.put(Marker42, NayTitays.class);

			// Necessities----------------------------------
			// Bank
			// Zoom in the PNB Map
			Marker Marker31 = mMap
					.addMarker(new MarkerOptions()
							.position(new LatLng(8.18782, 126.35522))
							.title("Philippine National Bank")
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.bank)));
			allMarkersMap.put(Marker31, PNB.class);

			// Zoom in the DBP Map
			Marker Marker32 = mMap
					.addMarker(new MarkerOptions()
							.position(new LatLng(8.18753, 126.35553))
							.title("Development Bank of the Philippines")
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.bank)));
			allMarkersMap.put(Marker32, DBP.class);

			// Zoom in the LandBank Map
			Marker Marker33 = mMap
					.addMarker(new MarkerOptions()
							.position(new LatLng(8.18676, 126.35513))
							.title("Landbank of the Philippines")
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.bank)));
			allMarkersMap.put(Marker33, Landbank.class);

			// Zoom in the East West Bank Map
			Marker Marker34 = mMap
					.addMarker(new MarkerOptions()
							.position(new LatLng(8.18617, 126.35637))
							.title("East West Bank")
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.bank)));
			allMarkersMap.put(Marker34, GreenBank.class);

			// Pharmacy/Stores

			// Zoom in Mercury Drug Map
			Marker Marker35 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18771, 126.35506))
					.title("Mercury Drugstore")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.drugstore)));
			allMarkersMap.put(Marker35, MercuryDrug.class);

			// Zoom in The Generics Pharmacy Map
			Marker Marker36 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18765, 126.35497))
					.title("The Generics Pharmacy")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.drugstore)));
			allMarkersMap.put(Marker36, GenericsPharmacy.class);

			// Zoom in The Tonys Store Map
			Marker Marker43 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18734, 126.35460))
					.title("Tonys Superstore")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.store)));
			allMarkersMap.put(Marker43, TonysStore.class);

			// Zoom in Gmars Commercial Map
			Marker Marker44 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18940, 126.35539))
					.title("Gmars Commercial")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.store)));
			allMarkersMap.put(Marker44, GmarsCommercial.class);

			// Terminal-------------
			// Zoom in The Jeepney Terminal Map
			Marker Marker37 = mMap
					.addMarker(new MarkerOptions()
							.position(new LatLng(8.18915, 126.35417))
							.title("Jeepney Integrated Terminal")
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.van)));
			allMarkersMap.put(Marker37, Jeep.class);

			// Zoom in The Habal - Habal Terminal Map
			Marker Marker38 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18746, 126.35250))
					.title("Habal-Habal Lounge")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.motor)));
			allMarkersMap.put(Marker38, Habalhabal.class);

			// Zoom in The Tricycle Terminal Map
			Marker Marker39 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18739, 126.35253))
					.title("Tricycle Terminal")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.tricycle)));
			allMarkersMap.put(Marker39, Tricycle.class);

			// Zoom in The Bus Terminal Map
			Marker Marker40 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18533, 126.35118))
					.title("Phoenix Bus Terminal")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.busstop)));
			allMarkersMap.put(Marker40, BusTerminal.class);

			// Zoom in The Local Sea Port Map
			Marker Marker41 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18876, 126.35273))
					.title("Mangagoy Local Sea Port")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.harbor)));
			allMarkersMap.put(Marker41, SeaPort.class);

			// Zoom in The Van For Hire Map
			Marker Marker47 = mMap
					.addMarker(new MarkerOptions()
							.position(new LatLng(8.18549, 126.35147))
							.title("Van For Hire Terminal")
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.van)));
			allMarkersMap.put(Marker47, VanForHire.class);

			// ATTRACTIONS------------------------------------------------------------

			// Zoom in the Boulevard Map
			Marker Marker14 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.20930, 126.32237))
					.title("Bislig Baywalk")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.attractions)));
			allMarkersMap.put(Marker14, Baywalk.class);

			// Zoom in the Tinuy-an Map
			Marker Marker15 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.17141, 126.22948))
					.title("Tinuy-an Falls")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.attractions)));
			allMarkersMap.put(Marker15, Tinuyan.class);

			// Zoom in the Florland Resort Map
			Marker Marker16 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.29237, 126.31583))
					.title("Florland Resort")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.attractions)));
			allMarkersMap.put(Marker16, Florland.class);

			// Zoom in the Hinayagan Cave Map
			Marker Marker17 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.29672, 126.37119))
					.title("Hinayagan Cave")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.attractions)));
			allMarkersMap.put(Marker17, Hinayagan.class);

			// Zoom in the hagonoy Island Map
			Marker Marker27 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.25576, 126.36974))
					.title("Hagonoy Island")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.attractions)));
			allMarkersMap.put(Marker27, Hagonoy.class);

			// Zoom in the OVP Map
			Marker Marker11 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18335, 126.34250))
					.title("Ocean View Park and Restaurant")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.attractions)));
			allMarkersMap.put(Marker11, Oceanview.class);

			// Zoom in the Kawa-Kawa Map
			Marker Marker48 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.17430, 126.28423))
					.title("Kawa - Kawa")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.attractions)));
			allMarkersMap.put(Marker48, Kawakawa.class);

			// HOTELS-------------------------------------------------

			// Zoom in the PaperCountry Map
			Marker Marker18 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18535, 126.35956))
					.title("Paper Country Inn")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker18, PaperCountryInn.class);

			// Zoom in the Sheilas Pension House Map
			Marker Marker19 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18585, 126.35667))
					.title("Sheilas Pension House")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker19, ShielasPensionHouse.class);

			// Zoom in the Pension La Salle Map
			Marker Marker20 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18132, 126.35590))
					.title("Pensione La Salle")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker20, PensioneLaSalle.class);

			// Zoom in the Pension Casa de Babano Map
			Marker Marker21 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18849, 126.35619))
					.title("Casa de Babano")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker21, CasadeBabano.class);

			// Zoom in the Tffany's Inn Map
			Marker Marker22 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18693, 126.35399))
					.title("Tffany's Inn")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker22, TyffanysInn.class);

			// Zoom in the Sleep Inn Map
			Marker Marker23 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18743, 126.35393))
					.title("Sleep Inn")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker23, SleepInn.class);

			// Zoom in the Yolanda Inn Map
			Marker Marker24 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18147, 126.36100))
					.title("Yolanda Inn")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker24, YolandaInn.class);

			// Zoom in the Violy's Pension House Map
			Marker Marker25 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.17600, 126.35871))
					.title("Violy's Pension House")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker25, ViolysPensionHouse.class);

			// Zoom in the St.Francis Pension House Map
			Marker Marker26 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18812, 126.35519))
					.title("St.Francis Pension House")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker26, StFrancisPensionHouse.class);

			// Zoom in the Bonsay Riverview Inn Map
			Marker Marker45 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.22126, 126.31555))
					.title("Bonsay Riverview Inn")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker45, BonsayRiverviewInn.class);

			// Zoom in the White Palace Inn Map
			Marker Marker46 = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(8.18326, 126.33768))
					.title("White Palace Inn")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.hotels)));
			allMarkersMap.put(Marker46, WhitePalaceInn.class);

			mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick(Marker marker) {
					Class cls = allMarkersMap.get(marker);
					Intent intent = new Intent(MapLocation.this, cls);
					startActivity(intent);

				}

			});

			// Creating a LatLng object for the current location
			LatLng mapper = new LatLng(8.24237, 126.33038);

			// zoom in the center to include all markers in map
			mMap.moveCamera(CameraUpdateFactory.newLatLng(mapper));
			mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

			// Enabling MyLocation Layer of Google Map
			mMap.setMyLocationEnabled(true);

			// Zoom in to the User's Location when my location button is tapped
			mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {

				@Override
				public boolean onMyLocationButtonClick() {

					// zoom in to the user location
					mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
					mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

					return true;
				}
			});

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
		allMarkersMap.put(myMarker, MainActivity.class);

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

}