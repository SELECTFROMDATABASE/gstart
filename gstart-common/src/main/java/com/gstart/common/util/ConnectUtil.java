package com.gstart.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 
 * http��https���ӵĹ�����
 * 
 * @author Jerry
 * 
 */
public class ConnectUtil {
	private static Logger logger = LoggerFactory.getLogger(ConnectUtil.class);
	public static final String CONNECT_URL_TYPE_HTTPS = "1";
	public static final String CONNECT_URL_TYPE_HTTP = "2";

	/**
	 * 
	 * create HTTPS Connection
	 * 
	 * @author Jerry
	 * 
	 * @param url connectUrl
	 *
	 * @param requestMethod actionMethod
	 *
	 * @return
	 */
	public static HttpsURLConnection getHTTPSRequestConnection(String url,
			String requestMethod, TrustManager tr) {
		if (StringUtil.isEmpty(url) && StringUtil.isEmpty(requestMethod)) {
			logger.debug("getHTTPSRequestConnection:url is empty");
			return null;
		}
		if (null == tr) {
			logger.debug("Method : getHTTPSRequestConnection:use default TrustManger");
			tr = new DefaultTrustManager();
		}
		logger.debug("Method : getHTTPSRequestConnection -> url:{},requestMethod:{}",url,requestMethod);
		HttpsURLConnection con = (HttpsURLConnection) getURLConnection(url,
				requestMethod);
		SSLSocketFactory sc = initSSLSocketFactory(tr);
		con.setSSLSocketFactory(sc);
		return con;
	}

	/**
	 * 
	 * Init SSLSocket
	 * 
	 * @param tr TrustManager
	 * @return  SSLSocketFactory
	 */

	private static SSLSocketFactory initSSLSocketFactory(TrustManager tr) {

		if (null == tr) {
			logger.debug("initSSLSocketFactory:TrustManager is empty");
			return null;
		}
		TrustManager[] tm = { tr };
		SSLSocketFactory sc = null;
		SSLContext slc;
		try {
			slc = SSLContext.getInstance("SSL", "SunJSSE");
			slc.init(null, tm, new java.security.SecureRandom());
			sc = slc.getSocketFactory();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return sc;

	}

	/**
	 * get HTTPURL Connection
	 * 
	 * @author Jerry
	 * @param url url
	 * @param requestMethod requestMethod
	 * @return
	 */
	public static HttpURLConnection getHTTPRequestConnection(String url,
			String requestMethod) {
		if (StringUtil.isEmpty(url) && StringUtil.isEmpty(requestMethod)) {
			logger.debug("getHTTPRequestConnection:url or requestmethod is empty");
			return null;
		}
		HttpURLConnection con = (HttpURLConnection) getURLConnection(url,
				requestMethod);
		return con;
	}

	/**
	 *
	 * get URLConnection
	 * 
	 * @param url
	 * @param method
	 * @return URLConnection
	 */
	private static URLConnection getURLConnection(String url, String method) {
		if (StringUtil.isEmpty(url) && StringUtil.isEmpty(method)) {
			logger.debug("getURLConnection:url or requestmethod is empty");
			return null;
		}
		URL u;
		try {
			u = new URL(url);
			URLConnection con = u.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			((HttpURLConnection) con).setRequestMethod(method);
			return con;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String sendString(String url, String requestMethod,
			String outputStr, String type, TrustManager trust) {
		HttpURLConnection con = null;
		if (type.equals(ConnectUtil.CONNECT_URL_TYPE_HTTP))
			con = getHTTPRequestConnection(url, requestMethod);
		if (type.equals(ConnectUtil.CONNECT_URL_TYPE_HTTPS))
			con = getHTTPSRequestConnection(url, requestMethod, trust);
		if (outputStr != null) {
			OutputStream ow = null;
			BufferedWriter bw = null;
			OutputStreamWriter out = null;
			try {
				ow = con.getOutputStream();
				out = new OutputStreamWriter(ow, "utf-8");
				bw = new BufferedWriter(out);
				bw.write(outputStr);
				bw.flush();
			} catch (IOException e) {
				try {
					bw.close();
					out.close();
					ow.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		}
		BufferedReader s = null;
		try {
			InputStream in = con.getInputStream();
			String i;
			s = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			StringBuffer sb = new StringBuffer();
			while ((i = s.readLine()) != null) {
				sb.append(i);
			}
			return sb.toString();
		} catch (IOException e) {
			try {
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return null;
	}

}
