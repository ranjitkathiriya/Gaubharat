package com.example.gaubharat;

import com.example.gaubharat.ImageAdapter;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Cooking extends TabActivity {
	
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
		Intent intentVeg = new Intent().setClass(this, Flour.class);
		TabSpec tabSpecVeg = tabHost
			.newTabSpec("Flour")
			.setIndicator("Flour")
			.setContent(intentVeg);

		// Fruit tab
		Intent intentFruit = new Intent().setClass(this, DryFruit.class);
		TabSpec tabSpecFruit = tabHost
			.newTabSpec("Dry Fruit")
			.setIndicator("Dry Fruit")
			.setContent(intentFruit);
		
		//Pulses tab
		Intent intentPulse = new Intent().setClass(this, Oil.class);
		TabSpec tabSpecPulse = tabHost
			.newTabSpec("Oil")
			.setIndicator("Oil")
			.setContent(intentPulse);
	
			
		//Cereals tab
		Intent intentSpice = new Intent().setClass(this, Spices.class);
		TabSpec tabSpecSpice = tabHost
			.newTabSpec("Spices")
			.setIndicator("Spices")
			.setContent(intentSpice);
		
		// add all tabs 
		tabHost.addTab(tabSpecVeg);
		tabHost.addTab(tabSpecFruit);
		tabHost.addTab(tabSpecPulse);
		tabHost.addTab(tabSpecSpice);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

}
