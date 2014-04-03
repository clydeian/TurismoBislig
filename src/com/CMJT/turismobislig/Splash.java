/**
 * 
 */
package com.CMJT.turismobislig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

/**
 * @author xhan
 * 
 */
public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// Thread for displaying the Splash Screen
		Thread splash = new Thread() {
			public void run() {
				try {
					sleep(5000);

				} catch (Exception e) {
					e.printStackTrace();

				} finally {

					startActivity(new Intent(Splash.this, MainActivity.class));
					finish();
				}
			}
		};
		splash.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}
}
