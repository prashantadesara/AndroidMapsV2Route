package com.prashant.adesara.googlemapv2.route;

/**
 * Access the Google API and return the routing data
 */

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.prashant.adesara.googlemapv2.R;

public class Routing extends AsyncTask<LatLng, Void, Route>
{
    private final GoogleMap map;
    private LatLng start;
    private LatLng dest;
    private final LatLngBounds.Builder builder = new LatLngBounds.Builder();;
    private final int[] lineColor = { Color.GREEN };
    private final int pos;

    public Routing(final Activity activity, final GoogleMap map) {
	super();
	this.map = map;
	this.pos = 0;
    }

    private TextView txtDistance;

    public Routing(final Activity activity, final GoogleMap map, final TextView txtDistance) {
	super();
	this.map = map;
	this.pos = 0;
	this.txtDistance = txtDistance;
    }

    @Override
    protected Route doInBackground(final LatLng... points) {
	try {
	    start = points[0];
	    dest = points[1];
	    Parser parser;
	    final String jsonURL = "http://maps.googleapis.com/maps/api/directions/json?";
	    final StringBuffer sBuf = new StringBuffer(jsonURL);
	    sBuf.append("origin=");
	    sBuf.append(start.latitude);
	    sBuf.append(',');
	    sBuf.append(start.longitude);
	    sBuf.append("&destination=");
	    sBuf.append(dest.latitude);
	    sBuf.append(',');
	    sBuf.append(dest.longitude);
	    sBuf.append("&sensor=true&mode=driving");
	    System.out.println("sbuf: " + sBuf.toString());
	    parser = new GoogleParser(sBuf.toString());
	    final Route route = parser.parse();
	    return route;
	} catch (final Exception e) {}
	return null;
    }

    @Override
    protected void onPreExecute() {
	/** Empty Method */
    }// end onPreExecute method

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onPostExecute(final Route result) {
	try {
	    if (result == null) {
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(dest, 15));
		map.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);
	    } else {
		final String text = result.getTextLength();
		final String startAddress = result.getStartAddress().toString().trim().replaceAll(", ", ",\n");
		final String endAddress = result.getEndAddress().toString().trim().replaceAll(", ", ",\n");
		txtDistance.setVisibility(View.GONE);
		if (text != null) {
		    txtDistance.setVisibility(View.VISIBLE);
		    txtDistance.setBackgroundResource(android.R.color.holo_orange_light);
		    txtDistance.setText(" Total Distance : " + text + "\n Total Duration : " + result.getDuration() + "  ");
		}
		final List<LatLng> directionPoint = result.getPoints();
		final PolylineOptions rectLine = new PolylineOptions().width(10).color(lineColor[pos]);
		for (int i = 0; i < directionPoint.size(); i++) {
		    rectLine.add(directionPoint.get(i));
		}
		map.addPolyline(rectLine);

		final Marker startLocation = map.addMarker(new MarkerOptions().position(start).title(startAddress).snippet("Main Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.markera)));
		final Marker endLocation = map.addMarker(new MarkerOptions().position(dest).title(endAddress).snippet("Destination Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.markerb)));
		builder.include(startLocation.getPosition());
		builder.include(endLocation.getPosition());
		final LatLngBounds bounds = builder.build();

		// Pan to see all markers in view.
		final int padding = 100; // offset from edges of the map in pixels
		final CameraUpdate cup = CameraUpdateFactory.newLatLngBounds(bounds, padding);

		map.moveCamera(cup);
		map.animateCamera(cup);

	    }
	} catch (final Exception e) {
	    e.printStackTrace();
	}
    }// end onPostExecute method
}
