package com.ambro.app;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends Activity {
    private TextView testo2;
    private ProgressBar progbar;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.update);
        super.onCreate(savedInstanceState);
        testo2= (TextView) findViewById(R.id.textView2);
        progbar= (ProgressBar) findViewById(R.id.progressBar1);

    }
    public boolean CheckInternet() {
        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        
        if (wifi.isConnected()) {
            return true;
        } else if (mobile.isConnected()) {
            return true;
        }
        return false;
    }
    public void goToUpdate (View view) {
    	if (CheckInternet()) {
    		new connection().execute();
    	} else {
    		Toast.makeText(Update.this,"You Do not have Internet Connection",Toast.LENGTH_LONG).show();
    	     Update.this.startActivity(new Intent(Settings.ACTION_SETTINGS));
    	}
    }
    
    public class connection extends AsyncTask<Void, Integer, Boolean> {
    	protected void onPreExecute(){
    		testo2.setText("");
    		progbar.setProgress(0);
    		progbar.setVisibility(0);
    	}
        protected Boolean doInBackground(Void... params) {
             boolean updated=false;
             String lastversion=null;
             Element e=null;
             final String URL = "http://www.lookedpath.tk/apps/firstapp/version.xml";
             final String VERSION = "version";
             final String APPLICATION = "application";
             XMLParser parser = new XMLParser();
             String xml = parser.getXmlFromUrl(URL);
             Document doc = parser.getDomElement(xml);
             publishProgress(50);
             NodeList nl = doc.getElementsByTagName(APPLICATION);
             for (int i=0;i<nl.getLength();i++) {
                e = (Element) nl.item(0);
                lastversion = parser.getValue(e, VERSION);
             }
             publishProgress(100);
             String actver = getString(R.string.version);
             if(actver.equals(lastversion)) updated=true;
             return updated;
        }
        protected void onProgressUpdate(Integer... progress) {
            progbar.setProgress(progress[0]);
        }
        protected void onPostExecute(Boolean result) {
        	progbar.setVisibility(4);
            if(result==false){
                testo2.setText(R.string.newversion);
            } else {
                testo2.setText(R.string.nonewversion);
            }

        }

    };

}
                               	    		
     	    

        
       


 
	
        
	    
	    
	    
		
	    



