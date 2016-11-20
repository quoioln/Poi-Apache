package com.quoioln.sample.copycsv;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.quoioln.sample.copycsv.model.DataHandler;


public class Main {

    public static void main(String[] args) {
//    	DataHandler dataHandler = new DataHandler("F:/QUOI DATA/Code/SPC.xls", "F:/QUOI DATA/Code/CTO.xls", "F:/QUOI DATA/Code/result.xls");
//    	dataHandler.insertRecord();
    	
    	try {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2016-10-05T12:00"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
