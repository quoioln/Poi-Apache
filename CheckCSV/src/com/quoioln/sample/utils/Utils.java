/**
 * 
 */
package com.quoioln.sample.utils;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import com.quoioln.sample.model.CheckRecord;

/**
 * @author Quoi Vo
 *
 */
public class Utils {

    private final static int MA_VT_SPC = 0;

    private final static int TEN_SPC = 1;

    private static final DataFormatter dataFormatter = new DataFormatter();

    public static CheckRecord checkRecord(Row row, Iterator<Row> spcRowList) {
        CheckRecord checkRecord = new CheckRecord("success", true);
        if (!spcRowList.hasNext()) {
            return checkRecord;
        }
//        spcRowList.next();
        Row spcRow = null;

        Cell maVtSPCCell = null;
        Cell tenSPCCell = null;

        String mvVtSPCCheck = dataFormatter.formatCellValue(row.getCell(MA_VT_SPC + 1));
        String tenSPCCheck = dataFormatter.formatCellValue(row.getCell(TEN_SPC + 1));
        int line = 0;
        
        while (spcRowList.hasNext()) {
            line++;
            spcRow = spcRowList.next();
            maVtSPCCell = spcRow.getCell(MA_VT_SPC);
            tenSPCCell = spcRow.getCell(TEN_SPC);
            // Get mvVtSPC, tenSPC from SPC row
            String mvVtSPC = dataFormatter.formatCellValue(maVtSPCCell);
            String tenSPC = dataFormatter.formatCellValue(tenSPCCell);
            if (mvVtSPCCheck.equals(mvVtSPC)) {
                if (tenSPCCheck.equals(tenSPC)) {
                    checkRecord.setMessage(createMeassage(mvVtSPCCheck, true, line));
                } else {
                    checkRecord.setMessage(createMeassage(mvVtSPCCheck, false, line));
                }
//                System.out.println("Error: " + checkRecord.getMessage());
                checkRecord.setSuccess(false);
            }
        }
        return checkRecord;
    }

    private static String createMeassage(String mvVtSPCCheck, boolean isDuplicate, int line) {
        StringBuffer meassage = new StringBuffer();
        if (isDuplicate) {
            meassage.append("MAVTSPC ").append(mvVtSPCCheck).append(" đã tồn tại ở dòng ").append(line);
        } else {
            meassage.append("TENSPC ").append(" khác so với dòng ").append(line);
        }

        return meassage.toString();
    }
}

