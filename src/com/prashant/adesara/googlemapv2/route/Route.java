package com.prashant.adesara.googlemapv2.route;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class Route
{
    private String startAddress;
    private String endAddress;
    private final List<LatLng> points;
    private final List<Segment> segments;
    private String copyright;
    private String warning;
    private String country;
    private int length;
    private String textLength;
    private String duration;
    private String polyline;
    private String status;

    public Route() {
	points = new ArrayList<LatLng>();
	segments = new ArrayList<Segment>();
    }

    public void addPoint(final LatLng point) {
	points.add(point);
    }

    public void addPoints(final List<LatLng> points) {
	this.points.addAll(points);
    }

    public List<LatLng> getPoints() {
	return points;
    }

    public void addSegment(final Segment segment) {
	segments.add(segment);
    }

    public List<Segment> getSegments() {
	return segments;
    }

    /**
     * @param startAddress the name to set
     */
    public void setStartAddress(final String startAddress) {
	this.startAddress = startAddress;
    }

    /**
     * @return the startAddress
     */
    public String getStartAddress() {
	return startAddress;
    }

    /**
     * @param copyright the copyright to set
     */
    public void setCopyright(final String copyright) {
	this.copyright = copyright;
    }

    /**
     * @return the copyright
     */
    public String getCopyright() {
	return copyright;
    }

    /**
     * @param warning the warning to set
     */
    public void setWarning(final String warning) {
	this.warning = warning;
    }

    /**
     * @return the warning
     */
    public String getWarning() {
	return warning;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(final String country) {
	this.country = country;
    }

    /**
     * @return the country
     */
    public String getCountry() {
	return country;
    }

    /**
     * @param length the length to set
     */
    public void setLength(final int length) {
	this.length = length;
    }

    /**
     * @return the length
     */
    public int getLength() {
	return length;
    }

    /**
     * @param textLength the length to set
     */
    public void setTextLength(final String textLength) {
	this.textLength = textLength;
    }

    /**
     * @return the length
     */
    public String getTextLength() {
	return textLength;
    }

    /**
     * @param polyline the polyline to set
     */
    public void setPolyline(final String polyline) {
	this.polyline = polyline;
    }

    /**
     * @return the polyline
     */
    public String getPolyline() {
	return polyline;
    }

    public String getDuration() {
	return duration;
    }

    public void setDuration(final String duration) {
	this.duration = duration;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(final String status) {
	this.status = status;
    }

    public String getEndAddress() {
	return endAddress;
    }

    public void setEndAddress(final String endAddress) {
	this.endAddress = endAddress;
    }
}
