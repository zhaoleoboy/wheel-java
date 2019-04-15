package com.leo.wheel;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.druid.support.json.JSONUtils;

public class RestTest {

	private static final String TOKEN = "XbttGSKfUBpvauwczCeqIQ==";
	private static final String SOURCE_URL = "https://119.163.126.182:8089";
	private static final String ADDRESS = "https://119.163.126.182:8087/gateway/portalhome/horizon/workflow/rest/sms/sendSms.wf";

	@Test
	public void testHttps() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			ClientProtocolException, IOException {

		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy() {

			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
				return true;
			}
		}).build();

		HostnameVerifier verifier = new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, verifier);

		CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		HttpPost post = new HttpPost(ADDRESS);
		post.setHeader("token", TOKEN);
		post.setHeader("sourceUrl", SOURCE_URL);
//		post.setHeader("Content-type", "application/json; charset=utf-8");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deptid", "");
		params.put("senduserid", "");
		params.put("ids", "");
		params.put("content", "测试返回体");
		params.put("phone", "13061564226");
		StringEntity body = new StringEntity(JSONUtils.toJSONString(params), Charset.forName("UTF-8"));
		body.setContentEncoding("UTF-8");
		// 发送Json格式的数据请求
		body.setContentType("application/json");
		post.setEntity(body);

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity));

	}

}
