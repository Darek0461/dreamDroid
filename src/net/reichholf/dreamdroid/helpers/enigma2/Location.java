/* © 2010 Stephan Reichholf <stephan at reichholf dot net>
 * 
 * Licensed under the Create-Commons Attribution-Noncommercial-Share Alike 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package net.reichholf.dreamdroid.helpers.enigma2;

import java.util.ArrayList;

import net.reichholf.dreamdroid.dataProviders.SaxDataProvider;
import net.reichholf.dreamdroid.helpers.SimpleHttpClient;
import net.reichholf.dreamdroid.parsers.GenericSaxParser;
import net.reichholf.dreamdroid.parsers.enigma2.saxhandler.E2LocationHandler;

import org.apache.http.NameValuePair;

public class Location {
	/**
	 * @param shc
	 * @param params
	 * @return
	 */
	public static String getList(SimpleHttpClient shc) {
		if (shc.fetchPageContent(URIStore.LOCATIONS,
				new ArrayList<NameValuePair>())) {
			return shc.getPageContentString();
		}

		return null;
	}

	/**
	 * @param xml
	 * @param list
	 * @return
	 */
	public static boolean parseList(String xml, ArrayList<String> list) {
		SaxDataProvider sdp = new SaxDataProvider(new GenericSaxParser());

		E2LocationHandler handler = new E2LocationHandler(list);
		sdp.getParser().setHandler(handler);

		if (sdp.parse(xml)) {
			return true;
		}

		return false;
	}

}
