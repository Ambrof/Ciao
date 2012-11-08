package com.ambro.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Ambro_app extends Activity {
	private TextView testo;
	public int i=1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        testo=(TextView) findViewById(R.id.textView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }
    public void goToNext (View view) {
    	Resources res =getResources();
		String[] frasi = res.getStringArray(R.array.frasi);
    	int max=frasi.length;
    	if(i==max) i=0;
    	testo.setText(frasi[i]);
    	i++;
    }
    public void goToInfo (View view) {
    	setContentView(R.layout.info);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_settings:
    		startActivity(new Intent(this, Info.class));
    		break;
    	case R.id.update:
    		startActivity(new Intent(this, Update.class));
    		
    	}
    	return true;
    }

}

