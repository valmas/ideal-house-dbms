package com.ntua.db.common;

import java.util.Date;

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
	
	public static String getSqlDate(Date date){
		if(date == null)
			return null;
		else {
			return new java.sql.Date(date.getTime()).toString();
		}
			
		
	}
	
}
