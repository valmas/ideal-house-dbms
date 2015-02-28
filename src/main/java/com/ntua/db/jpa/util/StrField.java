package com.ntua.db.jpa.util;

import org.springframework.util.StringUtils;

/**
 * The Class StrField.
 */
public class StrField {
	
	/** The operand. */
	private String operand;
	
	/** The operator. */
	private String operator;
	
	/** The column name. */
	private String columnName;
	
	/**
	 * Instantiates a new str field.
	 *
	 * @param columnName the column name
	 */
	public StrField(String columnName){
		this.columnName = columnName;
	}

	/**
	 * Gets the operand.
	 *
	 * @return the operand
	 */
	public String getOperand() {
		return operand;
	}

	/**
	 * Sets the operand.
	 *
	 * @param operand the new operand
	 */
	public void setOperand(String operand) {
		this.operand = operand;
	}

	/**
	 * Gets the operator.
	 *
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * Sets the operator.
	 *
	 * @param operator the new operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		if(operator.equals("null"))
			return columnName+" is null";
		else if(operator.equals("not null"))
			return columnName+" is not null";
		else if(StringUtils.hasText(operator) && operator.equals("=")) 	
			return columnName+"='"+operand+"'";
		else if(StringUtils.hasText(operator) && operator.equals("like")) 	
			return columnName+"='%"+operand+"%'";
		return "";
	}
	
}
