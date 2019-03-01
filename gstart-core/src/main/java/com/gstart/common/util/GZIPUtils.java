package com.gstart.common.util;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-04-02 16:58
 */
public class GZIPUtils {
    public static byte[] toGZIPByteArray(Object object) throws IOException
    {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(baos);
         oos.writeObject(object);
         byte[] zbytes = baos.toByteArray();
         oos.close();
         baos.close();

         ByteArrayOutputStream zbo = new ByteArrayOutputStream();
         GZIPOutputStream gzipout = new GZIPOutputStream(zbo);

         ByteArrayInputStream bis = new ByteArrayInputStream(zbytes);

         int len = 0;
         byte[] bu = new byte[1024];
         while ((len = bis.read(bu)) != -1) {
             gzipout.write(bu, 0, len);
         }
         gzipout.flush();
         gzipout.close();

         return zbo.toByteArray();
         }


     public static <T> T toJava(byte[] bytes, Class<T> clazz)throws IOException, ClassNotFoundException
     {
         ByteArrayInputStream bin = new ByteArrayInputStream(bytes);

         GZIPInputStream zin = new GZIPInputStream(bin);
         ObjectInputStream oin = new ObjectInputStream(zin);
         return (T) oin.readObject();
         }
     }
