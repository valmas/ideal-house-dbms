package com.ntua.db.jpa;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

public class Advertisment {

	private Date publishDate;
	
	private String propertyRegNo;
	
	private String newspaperId;
	
	private BigDecimal cost;
	
	private Integer duration;
	
	private String publishDateOp;
	
	private String propertyRegNoOp;
	
	private String newspaperIdOp;
	
	private String costOp;
	
	private String durationOp;
	
	public Advertisment(){
		
	}
	
	public Advertisment(Advertisment ad){
		publishDate = ad.getPublishDate();
		propertyRegNo = ad.getPropertyRegNo();
		newspaperId = ad.getNewspaperId();
		cost = ad.getCost();
		duration = ad.getDuration();
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPropertyRegNo() {
		return propertyRegNo;
	}

	public void setPropertyRegNo(String propertyRegNo) {
		this.propertyRegNo = propertyRegNo;
	}

	public String getNewspaperId() {
		return newspaperId;
	}

	public void setNewspaperId(String newspaperId) {
		this.newspaperId = newspaperId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPublishDateOp() {
		return publishDateOp;
	}

	public void setPublishDateOp(String publishDateOp) {
		this.publishDateOp = publishDateOp;
	}

	public String getPropertyRegNoOp() {
		return propertyRegNoOp;
	}

	public void setPropertyRegNoOp(String propertyRegNoOp) {
		this.propertyRegNoOp = propertyRegNoOp;
	}

	public String getNewspaperIdOp() {
		return newspaperIdOp;
	}

	public void setNewspaperIdOp(String newspaperIdOp) {
		this.newspaperIdOp = newspaperIdOp;
	}

	public String getCostOp() {
		return costOp;
	}

	public void setCostOp(String costOp) {
		this.costOp = costOp;
	}

	public String getDurationOp() {
		return durationOp;
	}

	public void setDurationOp(String durationOp) {
		this.durationOp = durationOp;
	}
	
	public String insertQuery(){
		String query = "INSERT INTO Advertisements VALUES(";
		query = query + (publishDate !=null ? "'" + Utils.getSqlDate(publishDate) + "'," : "null,");
		query = query + (StringUtils.hasText(propertyRegNo) ? propertyRegNo + "," : "null,");
		query = query + (StringUtils.hasText(newspaperId) ? "'" + newspaperId + "'," : "null,");
		query = query + cost + ",";
		query = query + duration + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateNewsId the update news id
	 * @param updatePropRegNo the update prop reg no
	 * @param updateDate the update date
	 * @return the string
	 */
	public String updateQuery(String updateNewsId, String updatePropRegNo, Date updateDate){
		String query = "UPDATE Advertisements SET ";
		query = query + (publishDate !=null ? "DateOfPublish=" + "'" +  Utils.getSqlDate(publishDate) + "'," : "DateOfPublish=null,");
		query = query + (StringUtils.hasText(newspaperId) ? "NewspaperID='" + newspaperId + "'," : "NewspaperID=null,");
		query = query + (StringUtils.hasText(propertyRegNo) ? "PropertyRegistrationNo=" + propertyRegNo + "," : "PropertyRegistrationNo=null,");
		query = query + "Cost=" + cost + ",";
		query = query + "Duration=" + duration + ",";
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE NewspaperID='"+updateNewsId+"' and PropertyRegistrationNo='" + updatePropRegNo 
				+"' and DateOfPublish='" + Utils.getSqlDate(updateDate) + "'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM Advertisements WHERE";
		query = query + (StringUtils.hasText(propertyRegNo) ? " PropertyRegistrationNo"+ propertyRegNoOp+"" + propertyRegNo + " and" : "");
		query = query + (publishDate != null ? " DateOfPublish"+ publishDateOp+"'" + Utils.getSqlDate(publishDate) + "' and" : "");
		query = query + Utils.constructSearchQuery(newspaperIdOp, newspaperId, " NewspaperID");
		query = query + (cost != null ? " Cost"+ costOp+"" + cost + " and" : "");
		query = query + (duration != null ? " Duration"+ durationOp+"" + duration + " and" : "");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-6);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
}
