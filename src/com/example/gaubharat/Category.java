package com.example.gaubharat;

import com.example.gaubharat.Vegetables;
import android.support.v7.app.ActionBarActivity;
import com.example.gaubharat.ImageAdapter;
import com.example.gaubharat.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class Category extends ActionBarActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);

		GridView gridView = (GridView) findViewById(R.id.grid_view);
		
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this));

		/**
		 * On Click event for Single Gridview Item
		 * */
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				int i = position;
				// Sending image id to FullScreenActivity
				//Intent i = new Intent(getApplicationContext(), Product.class);
				//Toast.makeText(Category.this,"Hello" + i + "Welcome",Toast.LENGTH_LONG ).show();
				// passing array index
				/*i.putExtra("id", position);
				startActivity(i);*/
				switch (i) {
				case 0:
					Intent i1 = new Intent(getApplicationContext(),Product.class);
					startActivity(i1);
					break;
					
				case 1:
					Intent i2 = new Intent(getApplicationContext(),Inorganic.class);
					startActivity(i2);
					break;
					
				case 2:
					Intent i3 = new Intent(getApplicationContext(),Cooking.class);
					startActivity(i3);
					break;

				case 3:
					Intent i4 = new Intent(getApplicationContext(),Dairy.class);
					startActivity(i4);
					break;
					
				case 4:
					Intent i5 = new Intent(getApplicationContext(),Beauty.class);
					startActivity(i5);
					break;
					
				case 5:
					Intent i6 = new Intent(getApplicationContext(),Ayurved.class);
					startActivity(i6);
					break;
				
				case 6:
					Intent i7 = new Intent(getApplicationContext(),Books.class);
					startActivity(i7);
					break;
					
				case 7:
					Intent i8 = new Intent(getApplicationContext(),Medicine.class);
					startActivity(i8);
					break;
			  
				case 8:
					Intent i9 = new Intent(getApplicationContext(),Baby.class);
					startActivity(i9);
					break;
				
				case 9:
					Intent i10 = new Intent(getApplicationContext(),Rajiv.class);
					startActivity(i10);
					break;
				
				case 10:
					Intent i11 = new Intent(getApplicationContext(),Sweet.class);
					startActivity(i11);
					break;
				
				case 11:
					Intent i12 = new Intent(getApplicationContext(),Snacks.class);
					startActivity(i12);
					break;
					
				case 12:
					Intent i13 = new Intent(getApplicationContext(),Grocery.class);
					startActivity(i13);
					break;
					
				case 13:
					Intent i14 = new Intent(getApplicationContext(),Gaubharat.class);
					startActivity(i14);
					break;
				default:
					break;
				}
			}
		});
	}
	
}