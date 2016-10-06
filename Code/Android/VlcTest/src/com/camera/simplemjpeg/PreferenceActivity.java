package com.camera.simplemjpeg;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PreferenceActivity extends Activity {
	public static final String KEY_HOSTNAME = "hostname";
	public static final String KEY_PORTNUM = "portnum";
	public static final String KEY_WIDTH = "width";
	public static final String KEY_HEIGHT = "height";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView( R.layout.preference);
		
		// load stored data
		SharedPreferences sp = this.getPreferences( MODE_PRIVATE);
		String hostname = sp.getString( KEY_HOSTNAME, this.getString( R.string.defaultHostName));
		String portnum = sp.getString( KEY_PORTNUM, this.getString( R.string.defaultPortNum));
		String width = sp.getString( KEY_WIDTH, this.getString( R.string.defaultWidth));
		String height = sp.getString( KEY_HEIGHT, this.getString( R.string.defaultHeight));
		
		// set stored parameters to the contents
		EditText et = (EditText)findViewById( R.id.editText_hostname);
		et.setText( hostname);
		et = (EditText)findViewById( R.id.editText_portnum);
		et.setText( portnum);
		et = (EditText)findViewById( R.id.editText_width);
		et.setText( width);
		et = (EditText)findViewById( R.id.editText_height);
		et.setText( height);
	}

	/*
	 * This function is called when user clicks start button.
	 */
	public void onClick( View view){
		// get data from EditText components
		EditText etHost = (EditText)findViewById( R.id.editText_hostname);
		EditText etPort = (EditText)findViewById( R.id.editText_portnum);
		EditText etWidth = (EditText)findViewById( R.id.editText_width);
		EditText etHeight = (EditText)findViewById( R.id.editText_height);
		String hostname = etHost.getText().toString();
		String portnum = etPort.getText().toString();
		String width = etWidth.getText().toString();
		String height = etHeight.getText().toString();
		
		// store the input data
		SharedPreferences sp = this.getPreferences( MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString( KEY_HOSTNAME, hostname);
		editor.putString( KEY_PORTNUM, portnum);
		editor.putString( KEY_WIDTH, width);
		editor.putString( KEY_HEIGHT, height);
		editor.commit();
		
		// set the image size
		MjpegView.setImageSize( Integer.parseInt( width), Integer.parseInt(height));
		
		// launch MjpegActivity
		Intent intent = new Intent( this, MjpegActivity.class);
		intent.putExtra( KEY_HOSTNAME, hostname);
		intent.putExtra( KEY_PORTNUM, portnum);
		this.startActivity( intent);
	}
}
