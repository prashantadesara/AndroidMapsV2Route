package com.prashant.adesara.googlemapv2.route;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class RouteOverlay extends Overlay
{
    /** GeoPoints representing this routePoints. **/
    private final List<LatLng> routePoints;
    /** Color to paint routePoints. **/
    private int colour;
    /** Alpha setting for route overlay. **/
    private static final int ALPHA = 150;
    /** Stroke width. **/
    private static final float STROKE = 8f;
    /** Route path. **/
    private final Path path;
    /** Point to draw with. **/
    // private final Point point;
    /** Paint for path. **/
    private final Paint paint;

    /**
     * Public constructor.
     * 
     * @param route Route object representing the route.
     * @param defaultColour default colour to draw route in.
     */
    public RouteOverlay(final Route route, final int defaultColour) {
	super();
	routePoints = route.getPoints();
	colour = defaultColour;
	path = new Path();
	paint = new Paint();
    }

    @Override
    public final void draw(final Canvas canvas, final MapView mapView, final boolean shadow) {
	super.draw(canvas, mapView, shadow);
	paint.setColor(colour);
	paint.setAlpha(ALPHA);
	paint.setAntiAlias(true);
	paint.setStrokeWidth(STROKE);
	paint.setDither(true);
	paint.setStyle(Paint.Style.STROKE);

	redrawPath(mapView);
	canvas.drawPath(path, paint);
    }

    /**
     * Set the colour to draw this route's overlay with.
     * 
     * @param color Int representing colour.
     */
    public final void setColour(final int color) {
	colour = color;
    }

    /**
     * Clear the route overlay.
     */
    public final void clear() {
	routePoints.clear();
    }

    /**
     * Recalculate the path accounting for changes to the projection and routePoints.
     * 
     * @param mapView MapView the path is drawn to.
     */
    private void redrawPath(final MapView mapView) {
	try {
	    path.rewind();
	} catch (final Exception e) {
	    e.printStackTrace();
	}
    }

}
