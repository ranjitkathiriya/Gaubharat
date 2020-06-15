package com.example.gaubharat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Cart extends Activity {

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart);	

	//	mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		//mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		String price = getIntent().getStringExtra("price");
		//Double lat1=Double.parseDouble(lat);
		String image = getIntent().getStringExtra("image");
		//Double log1=Double.parseDouble(log);
		String name = getIntent().getStringExtra("name");
		
	}

	//@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
