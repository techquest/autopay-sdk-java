package com.interswitchng.techquest.autopay.sdk.lib.http;

import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.interswitchng.techquest.autopay.sdk.lib.dto.Errors;
import com.interswitchng.techquest.autopay.sdk.lib.dto.RestResponse;
import com.interswitchng.techquest.autopay.sdk.lib.utils.ConstantUtils;




public class AutoPAYHttpClient<Req> {

	private static String truststore;
	private static String truststorePassword;
	private static String keystore;
	private static String keystorePassword;
	
	public AutoPAYHttpClient()
	{
		truststore = "keystore.jks";
		truststorePassword = "password";
		keystore = "keystore.jks";
		keystorePassword = "password";
	}
	
	public RestResponse post(String url, Req request, Map<String, List<String>> headers)
	{
		RestResponse restResponse;
		try
		{
//			System.setProperty("javax.net.debug", "all");
			System.setProperty("javax.net.ssl.trustStore", truststore);
			System.setProperty("javax.net.ssl.trustStorePassword", truststorePassword);
			System.setProperty("javax.net.ssl.keyStore", keystore);
			System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword);	
			HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
			AutoPAYRestTemplate restTemplate = new AutoPAYRestTemplate(httpComponentsClientHttpRequestFactory);
			HttpHeaders requestHttpHeaders = new HttpHeaders();
			requestHttpHeaders.putAll(headers);	
			HttpEntity<Req> entity = new HttpEntity<Req>(request, requestHttpHeaders);
			restResponse = restTemplate.postForObject(url, entity, RestResponse.class);
			
			if(restResponse != null)
				restResponse = new RestResponse();
			
			HttpHeaders responseHttpHeaders = restTemplate.getResponse().getHeaders();
			restResponse.setSignature(responseHttpHeaders.containsKey(ConstantUtils.SIGNATURE_HEADER) ? responseHttpHeaders.get(ConstantUtils.SIGNATURE_HEADER).get(0) : null);
			restResponse.setSignatureMethod(responseHttpHeaders.containsKey(ConstantUtils.SIGNATURE_METHOD_HEADER) ? responseHttpHeaders.get(ConstantUtils.SIGNATURE_METHOD_HEADER).get(0) : null);
			restResponse.setTimestamp(responseHttpHeaders.containsKey(ConstantUtils.TIMESTAMP_HEADER) ? responseHttpHeaders.get(ConstantUtils.TIMESTAMP_HEADER).get(0) : null);
			restResponse.setNonce(responseHttpHeaders.containsKey(ConstantUtils.NONCE_HEADER) ? responseHttpHeaders.get(ConstantUtils.NONCE_HEADER).get(0) : null);
			restResponse.setSuccessful(true);
			return restResponse;
		}
		catch (HttpClientErrorException hceex)
		{
			hceex.printStackTrace();
			Errors errors = new Errors();			
			try {
				String errorResponse = hceex.getResponseBodyAsString();
				ObjectMapper mapper = new ObjectMapper();
				errors = mapper.readValue(errorResponse, Errors.class);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return errors;
		}
		catch (HttpServerErrorException hseex)
		{
			hseex.printStackTrace();
			Errors errors = new Errors();			
			try {
				String errorResponse = hseex.getResponseBodyAsString();
				ObjectMapper mapper = new ObjectMapper();
				errors = mapper.readValue(errorResponse, Errors.class);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return errors;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			Errors errors = new Errors();	
			return errors;
		}
	}
	
	
	public RestResponse get(String url, RestResponse response, Map<String, List<String>> headers)
	{
		RestResponse quickTellerResponse;
		try
		{
//			System.setProperty("javax.net.debug", "all");
			System.setProperty("javax.net.ssl.trustStore", truststore);
			System.setProperty("javax.net.ssl.trustStorePassword", truststorePassword);
			System.setProperty("javax.net.ssl.keyStore", keystore);
			System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword);	
			HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
			AutoPAYRestTemplate restTemplate = new AutoPAYRestTemplate(httpComponentsClientHttpRequestFactory);	
			HttpHeaders requestHttpHeaders = new HttpHeaders();
			requestHttpHeaders.putAll(headers);
			HttpEntity entity = new HttpEntity(requestHttpHeaders);			
			quickTellerResponse = restTemplate.exchange(url, HttpMethod.GET, entity, RestResponse.class).getBody();
			
			if(quickTellerResponse != null)
				quickTellerResponse = new RestResponse();
			
			HttpHeaders responseHttpHeaders = restTemplate.getResponse().getHeaders();
			quickTellerResponse.setSignature(responseHttpHeaders.containsKey(ConstantUtils.SIGNATURE_HEADER) ? responseHttpHeaders.get(ConstantUtils.SIGNATURE_HEADER).get(0) : null);
			quickTellerResponse.setSignatureMethod(responseHttpHeaders.containsKey(ConstantUtils.SIGNATURE_METHOD_HEADER) ? responseHttpHeaders.get(ConstantUtils.SIGNATURE_METHOD_HEADER).get(0) : null);
			quickTellerResponse.setTimestamp(responseHttpHeaders.containsKey(ConstantUtils.TIMESTAMP_HEADER) ? responseHttpHeaders.get(ConstantUtils.TIMESTAMP_HEADER).get(0) : null);
			quickTellerResponse.setNonce(responseHttpHeaders.containsKey(ConstantUtils.NONCE_HEADER) ? responseHttpHeaders.get(ConstantUtils.NONCE_HEADER).get(0) : null);
			quickTellerResponse.setSuccessful(true);
			return quickTellerResponse;
		}
		catch (HttpClientErrorException hceex)
		{
			hceex.printStackTrace();
			Errors errors = new Errors();			
			try {
				String errorResponse = hceex.getResponseBodyAsString();
				ObjectMapper mapper = new ObjectMapper();
				errors = mapper.readValue(errorResponse, Errors.class);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return errors;
		}
		catch (HttpServerErrorException hseex)
		{
			hseex.printStackTrace();
			Errors errors = new Errors();			
			try {
				String errorResponse = hseex.getResponseBodyAsString();
				ObjectMapper mapper = new ObjectMapper();
				errors = mapper.readValue(errorResponse, Errors.class);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return errors;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			Errors errors = new Errors();	
			return errors;
		}
	}
	
	
	public HttpClient httpClient()
	{
		PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
		HttpClient defaultHttpClient = new DefaultHttpClient(connectionManager);

		connectionManager.setMaxTotal(100);
		connectionManager
		    .setDefaultMaxPerRoute(100);

		defaultHttpClient.getParams().setIntParameter( CoreConnectionPNames.CONNECTION_TIMEOUT,
		    60000);
		  return defaultHttpClient;
	}
	
//	private static class HTTP401Extractor implements ResponseExtractor<RestResponse> {
//
//	    public <RestResponse> extractData(ClientHttpResponse response) throws   
//	    {
//	        return doSomthingWithHeader(response.getHeaders());
//	    }
//	}
}
