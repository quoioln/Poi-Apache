package com.quoioln.sample.copycsv.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TimeZone;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.quoioln.sample.copycsv.utils.Utils;

/**
 * The Class DataHandler.
 */
public class DataHandler {
    private String spcFileName = "SPC.xls";
    private String ctoFileName = "CTO.xls";
    private String resultFileName = "result.xls";

    /**
     * The constructor
     * 
     * @param spcFileName
     * @param ctoFileName
     * @param resultFileName
     */
    public DataHandler(String spcFileName, String ctoFileName, String resultFileName) {
        this.spcFileName = spcFileName;
        this.ctoFileName = ctoFileName;
        this.resultFileName = resultFileName;
    }

    /**
     * Insert record.
     */
    public void insertRecord() {
        try {
            // Load SPC.xls and CTO.xls
            FileInputStream spcFile = new FileInputStream(spcFileName);
            FileInputStream ctoFile = new FileInputStream(ctoFileName);

            HSSFWorkbook spcWorkbook = new HSSFWorkbook(spcFile);
            HSSFWorkbook ctoWorkbook = new HSSFWorkbook(ctoFile);

            HSSFSheet resultSheet = spcWorkbook.getSheetAt(0);
            HSSFSheet ctoSheet = ctoWorkbook.getSheetAt(0);

//            CellStyle cellStyleNormal = spcWorkbook.createCellStyle();//resultSheet.getColumnStyle(0);
//            Font font = spcWorkbook.createFont();
//            font.setFontName("Times New Roman");
//            font.setFontHeight((short)280);
//            cellStyleNormal.setFont(font);

//            CellStyle cellStyleAlert = spcWorkbook.createCellStyle();
//            cellStyleAlert.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

            HSSFSheet spcSheet = spcWorkbook.cloneSheet(0);
            
            Iterator<Row> ctoRowsList = ctoSheet.iterator();
            DataFormatter formatter = new DataFormatter();
            if (!ctoRowsList.hasNext()) {
                spcWorkbook.close();
                ctoWorkbook.close();
                spcFile.close();
                ctoFile.close();
                return;
            }
            // pass header
            ctoRowsList.next();
            // row start copy
            // int rowStartCopy = spcRowsList.
            int lastSPCRow = spcSheet.getLastRowNum();
            
            // Create cell style alert from SPC
            CellStyle cellStyleNormal = spcSheet.getColumnStyle(0);
            CellStyle cellStyleSPCAlert = spcWorkbook.createCellStyle();
            cellStyleSPCAlert.cloneStyleFrom(cellStyleNormal);
            cellStyleSPCAlert.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
            cellStyleSPCAlert.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//            cellStyleSPCAlert.
//            cellStyleSPCAlert.getF
            
            Row ctoRow = null;
            Cell ctoCell = null;
            while (ctoRowsList.hasNext()) {
                Iterator<Row> spcRowsList = spcSheet.iterator();
                int columnNumber = 0;
                ctoRow = ctoRowsList.next();

                // Create new row spc
                Row newSPCRow = resultSheet.createRow(++lastSPCRow);

                int newNumberColumn = 0;
                CheckRecord checkRecord = Utils.checkRecord(ctoRow, spcRowsList);

                Iterator<Cell> ctoCellList = ctoRow.iterator();
                while (ctoCellList.hasNext()) {
                    ctoCell = ctoCellList.next();
//                    cellStyleNormal.cloneStyleFrom(ctoCell.getCellStyle());
                    columnNumber++;
                    if (columnNumber == 1) {
                        continue;
                    }
                    if (columnNumber > 4) {
                        break;
                    }

                    Cell newSPCCell = newSPCRow.createCell(newNumberColumn++);
                    String value = formatter.formatCellValue(ctoCell);
                    newSPCCell.setCellValue(value);
                }
                if (!checkRecord.isSuccess()) {
                    Cell newSPCCell = newSPCRow.createCell(newNumberColumn++);
                    newSPCCell.setCellValue(checkRecord.getMessage());
                    newSPCCell.setCellStyle(cellStyleSPCAlert);
                }

            }
            
            FileOutputStream fileResult = new FileOutputStream(resultFileName);
            spcWorkbook.write(fileResult);

            spcWorkbook.close();
            ctoWorkbook.close();
            fileResult.close();
            spcFile.close();
            ctoFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
