package com.iit.cs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser4BestDealRetail extends DefaultHandler {
	
	//Chunk for the Laptops
	Laptop laptop;
	List<Laptop> laptops;
	String laptopXMLFilename;
	String elementValueRead;
	
	//Chunk for the Mobiles
	Mobile mobile;
	List<Mobile> mobiles;
	
	
	
    public SaxParser4BestDealRetail(String laptopXMLFilename) {
    this.laptopXMLFilename = laptopXMLFilename;
	System.out.println(laptopXMLFilename);
    laptops = new ArrayList<Laptop>();
    mobiles = new ArrayList<Mobile>();
    parseDocument();
  
    }    
    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(laptopXMLFilename, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

  
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("laptop")) {
        	laptop = new Laptop();
        	laptop.setId(attributes.getValue("id"));
        	laptop.setRetailer(attributes.getValue("retailer"));
        }
        if (elementName.equalsIgnoreCase("mobile")){
        	mobile = new Mobile();
        	mobile.setId(attributes.getValue("id"));
        	mobile.setRetailer(attributes.getValue("retailer"));
        }
    }

    
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("laptop")) {
            laptops.add(laptop);
            
	    return;
        }
        if (element.equals("mobile")) {
            mobiles.add(mobile);
            
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            laptop.setImage(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("mobimage")) {
        	mobile.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            laptop.setName(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("mobname")) {           
           mobile.setName(elementValueRead);
	    return;
        }

        if(element.equalsIgnoreCase("accessory")){
        	laptop.getAccessories().add(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("mobaccessory")){
        	mobile.getAccessories().add(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("condition")) {
            laptop.setCondition(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("mobcondition")) {
        	mobile.setCondition(elementValueRead);
	    return;
        }

        if(element.equalsIgnoreCase("price")){
        	laptop.setPrice(Integer.parseInt(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("mobprice")){
        	mobile.setPrice(Integer.parseInt(elementValueRead));
	    return;
        }


    }

   
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


}
