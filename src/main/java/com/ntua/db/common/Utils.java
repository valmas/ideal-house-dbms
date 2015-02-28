package com.ntua.db.common;

import org.springframework.util.StringUtils;

public class Utils {

	public static String constructSearchQuery(String operator, String operand, String columnName){
		if(operator.equals("null"))
			return columnName+" is null and";
		else if(operator.equals("not null"))
			return columnName+" is not null and";
		else if(StringUtils.hasText(operand) && operator.equals("=")) 	
			return columnName+"='"+operand+"' and";
		else if(StringUtils.hasText(operand) && operator.equals("like")) 	
			return columnName+" like '%"+operand+"%' and";
		return "";
	}
	
}
