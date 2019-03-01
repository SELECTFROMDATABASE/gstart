package com.gstart.common.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-04-02 16:47
 */
public class ExcelUtil {
    /**
     * 创建Workbook
     * @param file
     * @return
     */
    public static Workbook readExcel(File file){
        Workbook  book = null;
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return book;
    }

}
