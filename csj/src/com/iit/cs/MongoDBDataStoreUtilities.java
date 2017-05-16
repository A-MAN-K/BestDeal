package com.iit.cs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import java.io.*;

import com.mongodb.AggregationOutput;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBDataStoreUtilities {

	MongoClient mongoClient 	= new MongoClient("localhost", 27017);
	DBCollection dbCollection 	= null;

	public void createConnectionMongoDB()
	{	
		DB db       = 	mongoClient.getDB("BestDealMongoDB");
		dbCollection = db.getCollection("bestDealReviews");		
	}

	
	
	public int submitReview(HashMap<String,ReviewBean> hrb)
	{
		int result = 0;
		WriteResult resultSet;

		Set set 	= hrb.entrySet();
		Iterator iterator = set.iterator();
		ReviewBean reviewBean = new ReviewBean();
		
		while(iterator.hasNext())
		{
			Map.Entry<String, ReviewBean> map = (Map.Entry)iterator.next();
			reviewBean = (ReviewBean)map.getValue();
		}
		
        String productCategory  = reviewBean.getProductCategory();
		String productPrice 	= reviewBean.getProductPrice();
		String retailerName 	= reviewBean.getRetailerName();
		String retailerCity 	= reviewBean.getRetailerCity();
		String retailerState 	= reviewBean.getRetailerState();
		String retailerZip 		= reviewBean.getRetailerZip();
		String productOnSale 	= reviewBean.getProductOnSale();
		String manufacturerName	= reviewBean.getManufacturerName();
		String rebateAvailable	= reviewBean.getRebateAvailable();
		String productName 		= reviewBean.getProductName();
		String userName 		= reviewBean.getUserName();
		String userAge 			= reviewBean.getUserAge();
		String userGender 		= reviewBean.getUserGender();
		String userOccupation 	= reviewBean.getUserOccupation();
		String reviewRating 	= reviewBean.getReviewRating();	
		String reviewDate 		= reviewBean.getReviewDate();
		String reviewText 		= reviewBean.getReviewText();

		
		createConnectionMongoDB();
		BasicDBObject basicDBObject = new BasicDBObject("title", "bestDealReviews").
		
		append("productCategoryMongo", productCategory).
		append("productPriceMongo", productPrice).
		append("retailerNameMongo", retailerName).
		append("retailerCityMongo", retailerCity).
		append("retailerStateMongo", retailerState).
		append("retailerZipMongo", retailerZip).
		append("productOnSaleMongo", productOnSale).
		append("manufacturerNameMongo",manufacturerName).
		append("rebateAvailableMongo", rebateAvailable).
		append("productNameMongo", productName).
		append("userNameMongo", userName).
		append("userAgeMongo", userAge).
		append("userGenderMongo", userGender).
		append("userOccupationMongo", userOccupation).
		append("reviewRatingMongo", reviewRating).
		append("reviewDateMongo", reviewDate).
		append("reviewTextMongo", reviewText);
		
	    resultSet = dbCollection.insert(basicDBObject);
	    
	    result = resultSet.getN();
	    
		return result;
	}
	
	
	public HashMap<String, ArrayList<ReviewBean>> viewReviews(String productName)
	{
		HashMap<String, ArrayList<ReviewBean>> hrv = new HashMap<String, ArrayList<ReviewBean>>();
		ArrayList<ReviewBean> arrayList = new ArrayList<ReviewBean>();
		
		createConnectionMongoDB();
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("productNameMongo", productName);
		DBCursor dbCursor = dbCollection.find(basicDBObject);
		
		while(dbCursor.hasNext())
		{
			ReviewBean reviewBean = new ReviewBean();
			BasicDBObject basicdbobject = (BasicDBObject)dbCursor.next();
			reviewBean.setProductName(basicdbobject.getString("productNameMongo"));
			reviewBean.setProductCategory(basicdbobject.getString("productCategoryMongo"));
			reviewBean.setProductPrice(basicdbobject.getString("productPriceMongo"));
			reviewBean.setRetailerName(basicdbobject.getString("retailerNameMongo"));
			reviewBean.setRetailerCity(basicdbobject.getString("retailerCityMongo"));
			reviewBean.setRetailerState(basicdbobject.getString("retailerStateMongo"));
			reviewBean.setRetailerZip(basicdbobject.getString("retailerZipMongo"));
			reviewBean.setProductOnSale(basicdbobject.getString("productOnSaleMongo"));
			reviewBean.setManufacturerName(basicdbobject.getString("manufacturerNameMongo"));
			reviewBean.setRebateAvailable(basicdbobject.getString("rebateAvailableMongo"));
			reviewBean.setUserName(basicdbobject.getString("userNameMongo"));
			reviewBean.setUserAge(basicdbobject.getString("userAgeMongo"));
			reviewBean.setUserGender(basicdbobject.getString("userGenderMongo"));
			reviewBean.setUserOccupation(basicdbobject.getString("userOccupationMongo"));
			reviewBean.setReviewRating(basicdbobject.getString("reviewRatingMongo"));
			reviewBean.setReviewDate(basicdbobject.getString("reviewDateMongo"));
			reviewBean.setReviewText(basicdbobject.getString("reviewTextMongo"));
			arrayList.add(reviewBean);
		}
		hrv.put("ReviewsHmap",arrayList);
		
		
		return hrv;
	}
	
	
	public AggregationOutput trendingSearch()
	{
		AggregationOutput aggregationOutput = null;
		
		createConnectionMongoDB();
		DBObject match = null;
		DBObject groupFields = null;
		DBObject group = null;
		DBObject projectFields = null;
		DBObject project = null;
		DBObject sort = null;
		
				groupFields = new BasicDBObject("_id", 0);
				groupFields.put("_id", "$retailerCityMongo");
				groupFields.put("count", new BasicDBObject("$sum", 1));
				groupFields.put("productName", new BasicDBObject("$push", "$productNameMongo"));
				groupFields.put("productPrice", new BasicDBObject("$push", "$productPriceMongo"));
				groupFields.put("review", new BasicDBObject("$push", "$reviewTextMongo"));
				groupFields.put("rating", new BasicDBObject("$push", "$reviewRatingMongo"));
				groupFields.put("maxPrice", new BasicDBObject("$max", "$productPriceMongo"));
				group = new BasicDBObject("$group", groupFields);
				projectFields = new BasicDBObject("_id", 0);
				projectFields.put("City", "$_id");
				projectFields.put("Review Count", "$count");
				projectFields.put("maxPrice", "$maxPrice");
				projectFields.put("productPrice", "$productPrice");
				projectFields.put("Product", "$productName");
				projectFields.put("Reviews", "$review");
				projectFields.put("Rating", "$rating");
				project = new BasicDBObject("$project", projectFields);
				aggregationOutput = dbCollection.aggregate(group, project);		
				return aggregationOutput;
		
	}
	
	
	
	
	
}
