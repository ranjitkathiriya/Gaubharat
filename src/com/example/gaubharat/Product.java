package com.example.gaubharat;


import com.example.gaubharat.ImageAdapter;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Product extends TabActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product);
		// get intent data
		//Intent i = getIntent();
				
		// Selected image id
		//int position = i.getExtras().getInt("id");
		ImageAdapter imageAdapter = new ImageAdapter(this);
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Vegetable tab
		Intent intentVeg = new Intent().setClass(this, Vegetables.class);
		TabSpec tabSpecVeg = tabHost
			.newTabSpec("Vegetables")
			.setIndicator("Vegetables")
			.setContent(intentVeg);

		// Fruit tab
		Intent intentFruit = new Intent().setClass(this, Fruits.class);
		TabSpec tabSpecFruit = tabHost
			.newTabSpec("Fruits")
			.setIndicator("Fruits")
			.setContent(intentFruit);
		
		//Pulses tab
		Intent intentPulse = new Intent().setClass(this, Pulses.class);
		TabSpec tabSpecPulse = tabHost
			.newTabSpec("Pulses")
			.setIndicator("Pulses")
			.setContent(intentPulse);
	
		//Cereals tab
		Intent intentCereal = new Intent().setClass(this, Pulses.class);
		TabSpec tabSpecCereal = tabHost
			.newTabSpec("Cereals")
			.setIndicator("Cereals")
			.setContent(intentCereal);
		
		// add all tabs 
		tabHost.addTab(tabSpecVeg);
		tabHost.addTab(tabSpecFruit);
		tabHost.addTab(tabSpecPulse);
		tabHost.addTab(tabSpecCereal);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

}
