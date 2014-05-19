package com.prashant.adesara.googlemapv2.route;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class XMLParser
{
    // names of the XML tags
    protected static final String MARKERS = "markers";
    protected static final String MARKER = "marker";

    protected URL feedUrl;

    protected XMLParser(final String feedUrl) {
	try {
	    this.feedUrl = new URL(feedUrl);
	} catch (final MalformedURLException e) {}
    }

    protected InputStream getInputStream() {
	try {
	    return feedUrl.openConnection().getInputStream();
	} catch (final IOException e) {
	    return null;
	}
    }
}
