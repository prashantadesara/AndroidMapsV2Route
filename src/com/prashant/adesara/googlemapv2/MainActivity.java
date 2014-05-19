package com.prashant.adesara.googlemapv2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.prashant.adesara.googlemapv2.route.Routing;
import com.prashant.adesara.googlemapv2.utilities.Utils;

/**
 * @author Prashant Adesara
 */
public class MainActivity extends Activity
{
    private GoogleMap mMap = null;
    private final double endLatitude = 23.022505;
    private final double endLongitude = 72.571362;
    private final LatLng end = new LatLng(endLatitude, endLongitude);

    private final double startLatitude = 22.303894;
    private final double startLongitude = 70.802160;
    private final LatLng start = new LatLng(startLatitude, startLongitude);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	if (!Utils.isConnected(getApplicationContext())) {
	    Toast.makeText(getApplicationContext(), "Internet not available. Cross check your internet connectivity and try again", Toast.LENGTH_LONG).show();
	    return;
	}
	if (!Utils.isGPSTurnOn(getApplicationContext())) {
	    showGPSDialog();
	    return;
	}

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
	super.onResume();
	if (Utils.isConnected(getApplicationContext())) {
	    mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    mMap.setMyLocationEnabled(true);
	    final TextView txtDistance = (TextView) findViewById(R.id.txtSpeed);
	    new Routing(getParent(), mMap, txtDistance).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, start, end);
	}
    }

    private void showGPSDialog() {
	new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppBaseTheme)) // Theme
	.setTitle(R.string.gps_lable_gps) // setTitle
	.setMessage(R.string.gps_lable_warning_message) // setMessage
	.setInverseBackgroundForced(false).setCancelable(false) //
	.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(final DialogInterface dialog, final int which) {
		startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
	    }
	}).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(final DialogInterface dialog, final int which) {
		dialog.dismiss();
	    }
	}).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (Utils.isGPSTurnOn(getApplicationContext())) {
	    onResume();
	}
    }
}
