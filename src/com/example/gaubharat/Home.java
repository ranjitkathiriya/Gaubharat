package com.example.gaubharat;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.gaubharat.R;
import com.example.gaubharat.MainActivity;
import com.example.gaubharat.Home.CreateUser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends Activity implements OnClickListener  {

	public Home(){}
	
private	Button skip,login;

private EditText contactnum, pass;
	private ProgressDialog pDialog;
	
		JSONParser jsonParser = new JSONParser();

	private static final String LOGIN_URL = "http://gaubharat.in/gaubharat/registeruser.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private int count;
    private SharedPreferences settings;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home); 
		
		contactnum = (EditText) findViewById(R.id.contactnumber);
		pass = (EditText) findViewById(R.id.password);
		
		skip = (Button) findViewById(R.id.skip);
		login = (Button) findViewById(R.id.login);	
		
		login.setOnClickListener(this);
		skip.setOnClickListener(this);
			
			//@Override
			//public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	new CreateUser().execute();
			//}
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
			new CreateUser().execute();
			break;
			
		case R.id.skip:
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}
	
class CreateUser extends AsyncTask<String, String, String> {
	
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(Home.this);
		pDialog.setMessage("Attempting login...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}

	
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		int success;
		
		String username = contactnum.getText().toString();
		String password = pass.getText().toString();
		try {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));

			Log.d("request!", "starting");
			// getting product details by making HTTP request
			JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
					params);

			// check your log for json response
			Log.d("Login attempt", json.toString());

			// json success tag
			success = json.getInt(TAG_SUCCESS);
			
			if (success == 1) {
				//Log.d("Login Successful!", json.toString());
				// save user data
				Log.d("User Created!", json.toString());              	
            	//finish();
            	
            	Intent intent = new Intent();
				intent.setClass(Home.this, MainActivity.class);
			    startActivity(intent);
				Home.this.finish();
				return json.getString(TAG_MESSAGE);
				// setCount(1);
					// save user data
				//	SharedPreferences sp = PreferenceManager
					//		.getDefaultSharedPreferences(Home.this);
					//Editor edit = sp.edit();
					//edit.putString("user_contactnumber", username);
				//	edit.commit();
		
			} else {
				Log.d("Login Failure!", json.getString(TAG_MESSAGE));
				return json.getString(TAG_MESSAGE);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;

	}
	//private void setCount(int count) {
		//SharedPreferences.Editor e = settings.edit();
       // e.putInt("count",count);
     //   e.commit();
		// TODO Auto-generated method stub
		
	//}
	
	protected void onPostExecute(String file_url) {
		// dismiss the dialog once product deleted
		pDialog.dismiss();
		if (file_url != null) {
			Toast.makeText(Home.this, file_url, Toast.LENGTH_LONG).show();
		}

	}
}
}

