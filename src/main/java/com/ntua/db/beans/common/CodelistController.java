package com.ntua.db.beans.common;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("codelistController")
public class CodelistController {

	public List<String> getOperators(){
		List<String> list = new ArrayList<String>();
		list.add("=");
		list.add("<");
		list.add(">");
		return list;
	}
	
	public List<String> getStrOperators(){
		List<String> list = new ArrayList<String>();
		list.add("=");
		list.add("like");
		list.add("not null");
		list.add("null");
		return list;
	}
}
