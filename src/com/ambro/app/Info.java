package com.ambro.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Info extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
    }
	 public void goToNext (View view) {
		 startActivity(new Intent(this, Ambro_app.class));
	 }
}
