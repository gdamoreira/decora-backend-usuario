package br.com.damoreira.util;

import java.util.Iterator;

import com.google.common.base.Splitter;

public class SortParserUtils {

	private static final String ASC_VALUE = "asc";
	private static final String DESC_VALUE = "desc";
	private static final int DESC = -1;
	private static final int ASC = 1;

	public static String getField(String sort) {
		if (sort == null) {
			return "_id";
		}
		return Splitter.on(" ").split(sort).iterator().next();
	}

	public static Integer getDirection(String sort) {
		if (sort == null) {
			return ASC;
		}
		Iterator<String> iterator = Splitter.on(" ").split(sort).iterator();
		iterator.next(); // field value
		String direction = iterator.next();
		return ASC_VALUE.equalsIgnoreCase(direction) ? ASC : DESC_VALUE.equalsIgnoreCase(direction) ? DESC : ASC;
	}

	private SortParserUtils() {
	}

}
