package com.example.gaubharat;


import com.example.gaubharat.ImageAdapter;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Beauty extends TabActivity {
	
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
		Intent intentVeg = new Intent().setClass(this, Shampoo.class);
		TabSpec tabSpecVeg = tabHost
			.newTabSpec("Shampoo")
			.setIndicator("Shampoo")
			.setContent(intentVeg);

		// Fruit tab
		Intent intentFruit = new Intent().setClass(this, Cream.class);
		TabSpec tabSpecFruit = tabHost
			.newTabSpec("Cream")
			.setIndicator("Cream")
			.setContent(intentFruit);
		
		//Pulses tab
		Intent intentPulse = new Intent().setClass(this, Facewash.class);
		TabSpec tabSpecPulse = tabHost
			.newTabSpec("Facewash")
			.setIndicator("Facewash")
			.setContent(intentPulse);
	
		//Cereals tab
		Intent intentCereal = new Intent().setClass(this, Cosmetics.class);
		TabSpec tabSpecCereal = tabHost
			.newTabSpec("Cosmetics")
			.setIndicator("Cosmetics")
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
