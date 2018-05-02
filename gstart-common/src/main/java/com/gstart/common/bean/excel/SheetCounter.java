package com.gstart.common.bean.excel;

import com.gstart.common.util.ExcelUtil;
import com.gstart.common.util.GZIPUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-04-02 17:24
 */
public class SheetCounter {
    private int sheetNum = 0;
    private int rowNum = 0;
    private int cellNum = 0;
    private Cell cell ;
    public void createCell(String [] values,Row row) throws IOException, ClassNotFoundException{
        for(String value : values){
            byte [] b ;
            String bye ;
            SheetCounter.this.cell = row.createCell(SheetCounter.this.cellNum);
            if(value == null || value.length()< 32767){
                SheetCounter.this.cell.setCellValue(value);
            }else{
                b = GZIPUtils.toGZIPByteArray(value);
                bye = new String(b);
                SheetCounter.this.cell.setCellValue(bye);
            }
            SheetCounter.this.cellNum = SheetCounter.this.cellNum + 1;
        }
        this.cellNum = 0;
    }

    public org.apache.poi.ss.usermodel.Row createRow(Sheet sheet){
        org.apache.poi.ss.usermodel.Row s = sheet.createRow(SheetCounter.this.rowNum);
        SheetCounter.this.rowNum = SheetCounter.this.rowNum + 1;
        return s;
    }
    public void reset(){
        SheetCounter.this.rowNum = 0;
        SheetCounter.this.cellNum = 0;
    }
}
