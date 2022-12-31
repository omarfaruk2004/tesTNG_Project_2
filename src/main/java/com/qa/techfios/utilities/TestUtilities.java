package com.qa.techfios.utilities;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;

public class TestUtilities {
	
	public List<String> getMonthsList(){
		DateFormatSymbols dfs = new DateFormatSymbols();
		String months = Arrays.toString(dfs.getShortMonths());
		months = months.substring(1, months.length()-3);
		return Arrays.asList(months.split(", "));
	}

}
