package com.gstart.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {
	public static void InputStreamToOutPutStream(InputStream is,OutputStream os,int size){
		if(null==is){
			System.out.println(StreamUtil.class.getName()+":InputStreamΪ��");
		}
		if(null==os){
			System.out.println(StreamUtil.class.getName()+":OutputStreamΪ��");
		}
		byte [] b = new byte[size];
		try {
			int flag;
			while((flag=is.read(b))!=-1){
				os.write(b,0,flag);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void InputStreamToOutPutStream(InputStream is,OutputStream os){
		InputStreamToOutPutStream(is, os, 1024);
	}
	
}
