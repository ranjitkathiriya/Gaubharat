package com.example.gaubharat;

import com.example.gaubharat.ImageAdapter;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Inorganic extends TabActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product);
		ImageAdapter imageAdapter = new ImageAdapter(this);
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Vegetable tab
		Intent intentVeg = new Intent().setClass(this, InorganicVeg.class);
		TabSpec tabSpecVeg = tabHost
			.newTabSpec("Vegetables")
			.setIndicator("Vegetables")
			.setContent(intentVeg);

		// Fruit tab
		Intent intentFruit = new Intent().setClass(this, InorganicFruit.class);
		TabSpec tabSpecFruit = tabHost
			.newTabSpec("Fruits")
			.setIndicator("Fruits")
			.setContent(intentFruit);
			
		// add all tabs 
		tabHost.addTab(tabSpecVeg);
		tabHost.addTab(tabSpecFruit);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

}

