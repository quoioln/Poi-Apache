package com.quoioln.sample;

import com.quoioln.sample.model.DataHandler;


public class Main {

    public static void main(String[] args) {
    	DataHandler dataHandler = new DataHandler("F:/QUOI DATA/Code/SPC.xls", "F:/QUOI DATA/Code/CTO.xls", "F:/QUOI DATA/Code/result.xls");
    	dataHandler.insertRecord();
    }
}
