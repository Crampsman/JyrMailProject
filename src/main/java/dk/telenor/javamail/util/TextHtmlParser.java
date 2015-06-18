package dk.telenor.javamail.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

public class TextHtmlParser {

	public static String getHtmlText(String htmlText) {
 
		    return Jsoup.parse(htmlText).text();
	}
}
