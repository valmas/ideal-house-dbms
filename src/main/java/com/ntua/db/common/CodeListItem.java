package com.ntua.db.common;

public class CodeListItem {

	private String value;
	
	private String description;
	
	private String label;
	
	public CodeListItem(String value, String description, boolean detailedLabel) {
		super();
		this.value = value;
		this.description = description;
		if(detailedLabel)
			label = "(" + value + ") " + description;
		else
			label = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
