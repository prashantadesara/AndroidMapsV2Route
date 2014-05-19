package com.prashant.adesara.googlemapv2.route;

public class Address {
    private int addressId;
    private String address;
    private double latitude;
    private double longitude;

    public int getAddressId() {
	return addressId;
    }

    public void setAddressId(final int addressId) {
	this.addressId = addressId;
    }

    public String getAddress() {
	return address;
    }

    public double getLatitude() {
	return latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setAddress(final String address) {
	this.address = address;
    }

    public void setLatitude(final double latitude) {
	this.latitude = latitude;
    }

    public void setLongitude(final double longitude) {
	this.longitude = longitude;
    }
}
