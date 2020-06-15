package com.example.gaubharat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Gaubharat extends Activity {
	
	ProgressDialog pDialog;
	ListView mListView;
	SimpleAdapter adapter;
	public int idx;	
	AQuery aq;
	
	private JSONArray mComments = null;
	
	private ArrayList<HashMap<String, Object>> mCommentList;
 
	ArrayList<HashMap<String, Object>> searchResults;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.vegetables);   
        aq=new AQuery(this);
        
  
        // URL to the JSON data         
        String strUrl = "http://gaubharat.in/gaubharat/Second.php";
        
        // Creating a new non-ui thread task to download json data 
        DownloadTask downloadTask = new DownloadTask();

        // Starting the download process
        downloadTask.execute(strUrl);
        
        // Getting a reference to ListView of activity_main
        mListView = (ListView) findViewById(R.id.lv_vegetable);       
    }
 

	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        
        InputStream iStream = null;
        try{
                URL url = new URL(strUrl);
                
                // Creating an http connection to communicate with url 
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url 
                urlConnection.connect();

                // Reading data from url 
                iStream = urlConnection.getInputStream();
                
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
                
                StringBuffer sb  = new StringBuffer();
                
                String line = "";
                while( ( line = br.readLine())  != null){
                	sb.append(line);
                }
                
                data = sb.toString();
                
                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
        }

        return data;
    }

    
    
    /** AsyncTask to download json data */
    private class DownloadTask extends AsyncTask<String, Integer, String>{
        String data = null;
                @Override
                protected String doInBackground(String... url) {
                        try{
                            data = downloadUrl(url[0]);
                                
                        }catch(Exception e){
                        	Log.d("Background Task",e.toString());
                        }
                        return data;
                }

                @Override
                protected void onPostExecute(String result) {
                	
                        // The parsing of the xml data is done in a non-ui thread 
                        ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();
                        
                        // Start parsing xml data
                        listViewLoaderTask.execute(result);                        
                        
                }
    }
    
    /** AsyncTask to parse json data and load ListView */
    private class ListViewLoaderTask extends AsyncTask<String, Void, SimpleAdapter>{
    	/*LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.vegetables, null, true);*/
        
    	JSONObject jObject;
    	// Doing the parsing of xml data in a non-ui thread 
		@Override
		protected  SimpleAdapter doInBackground(String... strJson) {
			try{
				/*Gson gson = new GsonBuilder().create();
				JSONArray jsonResponse = jObject.getJSONArray("posts");*/
	        	jObject = new JSONObject(strJson[0]);
	        	CountryJSONParser countryJsonParser = new CountryJSONParser();
	        	countryJsonParser.parse(jObject);
	        	
	        }
			catch(Exception e){
	        	Log.d("JSON Exception1",e.toString());
	        }		
			
			// Instantiating json parser class
			CountryJSONParser countryJsonParser = new CountryJSONParser();
			
			// A list object to store the parsed countries list
	        List<HashMap<String, Object>> countries = null;
	        
	        try{
	        	// Getting the parsed data as a List construct
	        	countries = countryJsonParser.parse(jObject);
	        }catch(Exception e){
	        	Log.d("Exception",e.toString());
	        }	       

	        // Keys used in Hashmap 
	       // String[] from = { "TAG_PRODUCT_NAME","flag","TAG_QUANTITY"};
	        
	        String[] from = {"product_name","product_memberprice","product_minquantity","product_image"};

	        // Ids of views in listview_layout
	        int[] to = { R.id.product_name,R.id.unitprice,R.id.minqty,R.id.img};

	        // Instantiating an adapter to store each items
	        // R.layout.listview_layout defines the layout of each item	        
	    SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), countries, R.layout.productlayout, from, to);  
	    return adapter;
	    
}
		

		/** Invoked by the Android on "doInBackground" is executed */
		@Override
		protected void onPostExecute(SimpleAdapter adapter) {
			
			// Setting adapter for the listview
			mListView.setAdapter(adapter);
			
			
	        for(int i=0;i<adapter.getCount();i++){
	        	HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem(i);
	        	String imgUrl = (String) hm.get("flag_path");
	        	ImageLoaderTask imageLoaderTask = new ImageLoaderTask();
	        	
	        	HashMap<String, Object> hmDownload = new HashMap<String, Object>();
	        	hm.put("flag_path",imgUrl);
	        	hm.put("position", i);
	        	
	        	// Starting ImageLoaderTask to download and populate image in the listview 
	        	imageLoaderTask.execute(hm); 
	        	
	        		        	
	        	//addtocart(mListView,i);
	        	
	        	 idx = i;
	        	
	        }
	        
	        
	      
	       /* mListView.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					Intent intent = new Intent();
					 Bundle b = new Bundle();
				
					String name= ((TextView) view.findViewById(R.id.product_name)).getText().toString();
					String price = ((TextView)view.findViewById(R.id.unitprice)).getText().toString();
					String qty= ((TextView) view.findViewById(R.id.minqty)).getText().toString();
					String image= ((TextView) view.findViewById(R.id.img)).getText().toString();
					
					 
	                 b.putString("name",name);
	                 b.putString("price",price);
	                 b.putString("qty", qty);
	                 b.putString("image",image);
					intent.setClass(getBaseContext(), Cart.class);
					intent.putExtras(b);
					getBaseContext().startActivity(intent);
				     }
			    });*/
	       
		}		
    }
    	
	public void addtocart(View v){
		
		//final int index = i;
		 mListView = (ListView) findViewById(R.id.lv_vegetable);
    	
		LinearLayout vwParentRow = (LinearLayout)v.getParent();
				
    	TextView child = (TextView)vwParentRow.getChildAt(0);
    	ImageView img = (ImageView)vwParentRow.getChildAt(1);
    	TextView qty = (TextView)vwParentRow.getChildAt(2);
    	TextView pr = (TextView)vwParentRow.getChildAt(3);
    	Button btnChild = (Button)vwParentRow.getChildAt(4);
    	   	
    	/*btnChild.setText(child.getText());
    	btnChild.setText("Index is:" + i );*/
    	btnChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
		Toast.makeText(Gaubharat.this,"Index" + idx ,Toast.LENGTH_LONG ).show();
			}
		});
    				  
		vwParentRow.refreshDrawableState();
    }
    
	/** AsyncTask to download and load an image in ListView */
   private class ImageLoaderTask extends AsyncTask<HashMap<String, Object>, Void, HashMap<String, Object>>{
		
		protected HashMap<String, Object> doInBackground(HashMap<String, Object>... hm) {
			
			InputStream iStream=null;
			
			String imgUrl = (String) hm[0].get("flag_path");
			int position = (Integer) hm[0].get("position");
			
			URL url;
			
			try {
				url = new URL(imgUrl);
				
	            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            
	            urlConnection.connect();
 
	            iStream = urlConnection.getInputStream();
	            
	            File cacheDirectory = getBaseContext().getCacheDir();
	             
	            File tmpFile = new File(cacheDirectory.getPath() + "/wpta_"+position+".png");	            
	            
	            FileOutputStream fOutStream = new FileOutputStream(tmpFile);
	           
		        Bitmap b = BitmapFactory.decodeStream(iStream);	
		            
		        b.compress(Bitmap.CompressFormat.PNG,100, fOutStream);         	            
	            
	           /* fOutStream.flush();
	            
	            fOutStream.close();	  */          
	            
	            HashMap<String, Object> hmBitmap = new HashMap<String, Object>();
	            
	            hmBitmap.put("product_image",tmpFile.getPath());
	           
	            hmBitmap.put("position",position);	            
	           
	            return hmBitmap;	            

			}
			catch (Exception e) {				
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(HashMap<String, Object> result) {
			
	      String path = (String) result.get("product_image");			
			
			int position = (Integer) result.get("position");
			
			SimpleAdapter adapter = (SimpleAdapter ) mListView.getAdapter();
			
			HashMap<String, Object> hm = (HashMap<String, Object>)adapter.getItem(position);	
			
			hm.put("product_image",path);
			
			adapter.notifyDataSetChanged();	
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
 
       }
        /*Button btn = (Button)findViewById(R.id.add_cart);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Vegetables.this,"Index" , Toast.LENGTH_LONG ).show();
			}
		});*/
        return super.onOptionsItemSelected(item);
    }
    
}
