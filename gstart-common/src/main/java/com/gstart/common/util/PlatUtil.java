package com.gstart.common.util;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 地图工具类
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-03-24 19:27
 */
public class PlatUtil {
    /**
     * 获取地理信息
     * @return
     */
    private static String url_translate = "http://api.map.baidu.com/geocoder/v2/";
    private static String ak = "yShVVei6EM2x2fkfo9OQKoSt5OucBRXz";
    private static String sk = "bfXRm9OrW6ZZsvcEheg4wautFwEs5Nlj";/*
    private static String ak = "PljzDQ4gSCOOj8pGnGkC8C8ATxWkfOFP";
    private static String sk = "jGFSmaomb4DkvzCSF5Gk5ZcCSh5jdlNS";*/
    public static String getPosition(int type,String lat,String lng) throws IOException {
        Map<String, String> param  = new LinkedHashMap<>();
        param.put("location",lat+","+lng);
        if(type == 1){
            param.put("coordtype","wgs84ll");
        }else{
        }

        param.put("output","json");
        param.put("ak",ak);
        String url = StringUtil.getUrlWithQueryString("/geocoder/v2/?",param) + sk;
        url = StringUtil.encodeURI2UTF8(url);
        String sn = SignUtil.md5(url);

        JSONObject jo = ConnectUtil.sendString(StringUtil.getUrlWithQueryString(url_translate,param)+"&sn="+sn
                ,"GET",null,ConnectUtil.CONNECT_URL_TYPE_HTTP);
        return jo.toString();
    }

    public static String getProvinceFromAddress(String address) throws IOException {
        Map<String, String> param  = new LinkedHashMap<>();
        param.put("address",address);
        param.put("output","json");
        param.put("ak",ak);
        String url = StringUtil.getUrlWithQueryString("/geocoder/v2/?",param) + sk;
        url = StringUtil.encodeURI2UTF8(url);
        String sn = SignUtil.md5(url);

        JSONObject jo = ConnectUtil.sendString(StringUtil.getUrlWithQueryString(url_translate,param)+"&sn="+sn
                ,"GET",null,ConnectUtil.CONNECT_URL_TYPE_HTTP);
        return jo.toString();
    }
    public static void main(String[] args) throws IOException {
        Workbook b =
        ExcelUtil.readExcel(new File("D:\\Jerry\\Documents\\WeChat Files\\WeChat Files\\god__1996\\Files\\全国医院名录excel版(17341).xls"));
        Sheet s = b.getSheet("Sheet1");
        Iterator<Row> it = s.rowIterator();
        int i = 0;
        try{
            while (it.hasNext()){
                if(i==0){
                    Row row = it.next();
                    i = 1;
                    continue;
                }else{
                    Row row = it.next();
                    if(!row.getCell(2).getStringCellValue().isEmpty()){
                        JSONObject jo = JSONObject.fromBean(PlatUtil.getProvinceFromAddress(row.getCell(2).getStringCellValue()));
                        System.out.println(jo.toString());
                        if(jo.has("result")){
                            String location = jo.getJSONObject("result").get("location").toString();
                            JSONObject locatio = JSONObject.fromBean(location);
                            System.out.println(locatio.toString());
                            try{
                                JSONObject joo = JSONObject.fromBean(getPosition(2,locatio.get("lat").toString(),locatio.getString("lng")));
                                System.out.println(joo.toString());
                                if (joo.has("result")){
                                    JSONObject resu = JSONObject.fromBean(joo.get("result"));
                                    System.out.println(resu);
                                    row.createCell(9).setCellValue(resu.getJSONObject("addressComponent").getString("city"));
                                }
                            }catch (Exception e){
                                continue;
                            }
                        }
                    }
                }


            }
        }catch (Exception e){
            File f = new File("H:\\test.xls");
            f.deleteOnExit();
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            b.write(fos);
        }
        File f = new File("H:\\test.xls");
        f.deleteOnExit();
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(f);
        b.write(fos);
    }
}
