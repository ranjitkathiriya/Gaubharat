package com.example.gaubharat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class CountryJSONParser {
	
	private static final String TAG_PRODUCT_NAME = "product_name";
	private static final String TAG_PRICE = "product_memberprice";
	private static final String TAG_QUANTITY = "product_minquantity";
	private static final String TAG_IMAGE = "product_image";
	private static final String TAG_POSTS = "posts";
	
	// Receives a JSONObject and returns a list
		public List<HashMap<String,Object>> parse(JSONObject jObject){		
			
			JSONArray jCountries = null;
			try {		
				// Retrieves all the elements in the 'countries' array 
				jCountries = jObject.getJSONArray("posts");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			 // Invoking getCountries with the array of json object
			 // where each json object represent a country
			return getCountries(jCountries);
		}
		
		
		private List<HashMap<String, Object>> getCountries(JSONArray jCountries){
			int countryCount = jCountries.length();
			List<HashMap<String, Object>> countryList = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> country = null;	

			// Taking each country, parses and adds to list object 
			for(int i=0; i<countryCount;i++){
				try {
					// Call getCountry with country JSON object to parse the country 
					country = getCountry((JSONObject)jCountries.get(i));
					countryList.add(country);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			return countryList;
		}
		
		// Parsing the Country JSON object 
		private HashMap<String, Object> getCountry(JSONObject jCountry){

			HashMap<String, Object> country = new HashMap<String, Object>();
			/*String productname = "";
			String image="";
			String qty = "";
			String price = "";*/
			
			String productName = "";
			String price="";
			String quantity = "";
			String image = "";
			
					
			
			try {
				
/*				countryName = jCountry.getString(TAG_PRODUCT_NAME);
				flag = jCountry.getString("flag");
				language = jCountry.getString("language");
				capital = jCountry.getString("capital");*/
				productName = jCountry.getString(TAG_PRODUCT_NAME);
				price = jCountry.getString(TAG_PRICE);
				quantity = jCountry.getString(TAG_QUANTITY);
				image = jCountry.getString(TAG_IMAGE);
			
				
				/*String details = language + "\n" + capital ;*/
				
				country.put("product_name", productName);
				country.put("product_memberprice", price);
				country.put("product_minquantity", quantity);
				country.put("product_image", R.drawable.blank);
				country.put("flag_path", image);
				
				/*productname = jCountry.getString("TAG_PRODUCT_NAME");
				image = jCountry.getString("TAG_IMAGE");
				qty = jCountry.getString("TAG_QUANTITY");
				price = jCountry.getString("TAG_PRICE");
				
				String details =  qty + "\n" + price + "\n" ;
				
				country.put("TAG_PRODUCT_NAME", productname);
				country.put("flag", R.drawable.blank);
				country.put("TAG_IMAGE", image);
				country.put("TAG_QUANTITY", qty);*/
				
			} catch (JSONException e) {			
				e.printStackTrace();
			}		
			return country;
		}

}
