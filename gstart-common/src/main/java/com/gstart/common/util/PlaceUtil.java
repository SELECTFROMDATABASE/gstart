package com.gstart.common.util;

import com.gstart.common.bean.plat.PlatPostion;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-04-03 11:55
 */
public class PlaceUtil {
    private static final String PY_URL = "F:\\Study\\gstart\\gstart-py\\test.py";

    public static PlatPostion transformPlace(String [] palce) throws FileNotFoundException {

        Process proc = null;
        try {
            String [] args = new String[]{"python",PY_URL};
            List l = new ArrayList(Arrays.asList(args));
            List l1 = new ArrayList(Arrays.asList(palce));
            l.addAll(l1);
            args = (String[]) l.toArray(new String[]{});
            proc = Runtime.getRuntime().exec(args);
            BufferedReader sr = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s ;
            /*File f = new File("H:\\test.txt");
            if (!f.exists()){
                f.createNewFile();
            }
            FileWriter fos = new FileWriter(f,true);
            while((s = sr.readLine())!= null){
                fos.append(s);
                fos.append("\n");
            }
            fos.flush();
            fos.close();
            s = null;*/
            BufferedReader ss = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            while ((s = ss.readLine()) != null){
                System.out.println(s);
            }
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Workbook b =
                ExcelUtil.readExcel(new File("D:\\Jerry\\Documents\\WeChat Files\\WeChat Files\\god__1996\\Files\\全国医院名录excel版(17341).xls"));
        Sheet s = b.getSheet("Sheet1");
        Iterator<Row> it = s.rowIterator();
        int i = 0;
        List<String> list = new ArrayList<>();
        try{
            while (it.hasNext()){
                if(i==0){
                    Row row = it.next();
                    i = 1;
                    continue;
                }else{
                    Row row = it.next();
                    if(!row.getCell(3).getStringCellValue().isEmpty()){
                       list.add(row.getCell(3).getStringCellValue());
                    }
                    if(i % 30 == 0){
                        PlaceUtil.transformPlace(list.toArray(new String[]{}));
                        list = new ArrayList<>();
                    }
                    i++;
                }


            }
        }catch (Exception e){
           /* File f = new File("H:\\test.xls");
            f.deleteOnExit();
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f,true);
            b.write(fos);*/
        }
       /* File f = new File("H:\\test.xls");
        f.deleteOnExit();
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(f);
        b.write(fos);*/

    }
}
