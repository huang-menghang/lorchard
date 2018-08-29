package com.ysdevelop.lochard.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lochard.common.utils
 * 
 * @Description Http 请求工具类
 * 
 * @Date 2018年8月29日
 * 
 * @Version
 * 
 */

public class HttpClientRequestUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientRequestUtil.class);

	/**
	 * 
	 * @param requestUrl
	 *            请求Url
	 * 
	 * @param method
	 *            请求方法
	 * 
	 * @param params
	 *            请求参数
	 * 
	 * @return
	 */
	public static String httpRequest(String requestUrl, Constant.HttpMethod method, Map<String, String> params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// POST方法
			conn.setRequestMethod(method.getValue());
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			// 设置输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			if (params != null) {
				StringBuilder param = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if (param.length() > 0) {
						param.append("&");
					}
					param.append(entry.getKey());
					param.append("=");
					param.append(entry.getValue());
				}
				logger.info("param" + param.toString());
				out.write(param.toString());
			}
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result.toString();
	}

	
	/**
	 * 通过请求获取输出流获取二维码
	 * 
	 * @param requestUrl
	 * 
	 * @param method
	 * 
	 * @param params
	 */
	public static void httpRequestCreateMerchantQr(String requestUrl, String jsonRequest, String inviteFileCodePath){
		InputStream inputStream = null;
		FileOutputStream fileOutPutStream = null;
   
		try {
			TrustManager[] tm = { new RequestX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);

			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setRequestProperty("Content-Type", " application/json");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(Constant.HttpMethod.POST.getValue());
			if (null != jsonRequest) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(jsonRequest.getBytes("UTF-8"));
				outputStream.flush();
				outputStream.close();
			}
			inputStream = conn.getInputStream();
			File orImgFile = new File(inviteFileCodePath);
			if (!orImgFile.exists()) {
				if(!orImgFile.getParentFile().exists()){
					orImgFile.getParentFile().mkdirs();
				}
				orImgFile.createNewFile();
			}
			if (inputStream != null) {
				fileOutPutStream = new FileOutputStream(orImgFile);

				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = inputStream.read(b)) != -1) {
					fileOutPutStream.write(b, 0, nRead);
				}
				fileOutPutStream.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileOutPutStream != null) {
				try {
					fileOutPutStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}

class RequestX509TrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}