package com.prashant.adesara.googlemapv2.utilities;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Prashant Adesara
 */
public class Utils
{
    /**
     * @Method used to checks if device having network connection or not.
     * @param context the context
     * @return <code>true</code> if the phone is connected
     */
    public static boolean isConnected(final Context context) {
	try {
	    final ConnectivityManager connMngr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

	    final NetworkInfo wifiNetwork = connMngr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    if (wifiNetwork != null && wifiNetwork.isConnectedOrConnecting()) { return true; }

	    final NetworkInfo mobileNetwork = connMngr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    if (mobileNetwork != null && mobileNetwork.isConnectedOrConnecting()) { return true; }

	    final NetworkInfo activeNetwork = connMngr.getActiveNetworkInfo();
	    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) { return true; }
	} catch (final Exception e) {
	    e.printStackTrace();
	}
	return false;
    }

    public static boolean isGPSTurnOn(final Context context) {
	final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
