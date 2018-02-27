package com.gstart.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	public static File saveFile(InputStream in, String path) {

		if (null == in) {
			log.debug("Method : saveFile -> InputStream is null ");
			return null;
		}
		if (StringUtil.isEmpty(path)) {
			log.debug("Method : saveFile -> path is null ");
			return null;
		}
		File f = new File(path);
		if (f.exists() != true) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				log.error("Method : saveFile -> createFile catch error ");
				e.printStackTrace();
				return null;
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(f);
			StreamUtil.InputStreamToOutPutStream(in, fos);
		} catch (FileNotFoundException e) {
			log.error("Method : saveFile -> file not found ");
			e.printStackTrace();
			return null;
		}
		return f;
	}
}
