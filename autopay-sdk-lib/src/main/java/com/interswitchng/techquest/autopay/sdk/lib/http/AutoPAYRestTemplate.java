package com.interswitchng.techquest.autopay.sdk.lib.http;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class AutoPAYRestTemplate extends RestTemplate{

	ClientHttpResponse response;
	
	public AutoPAYRestTemplate(ClientHttpRequestFactory clientHttpRequestFactory)
	{
		super(clientHttpRequestFactory);
	}
	
	@Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        
		Assert.notNull(url, "'url' must not be null");
        Assert.notNull(method, "'method' must not be null");
        try {
            ClientHttpRequest request = createRequest(url, method);
            
            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }
            response = request.execute();
 
            if (!getErrorHandler().hasError(response)) {
                logResponseStatus(method, url, response);
            } else {
                handleResponseError(method, url, response);
            }
            HttpStatus httpStatus = response.getStatusCode();
            MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
            HttpHeaders httpHeaders = response.getHeaders();
            Set set = httpHeaders.entrySet();
			Iterator<Object> it = set.iterator();
			while(it.hasNext())
			{
				Map.Entry entry = (Map.Entry) it.next();
				String key = String.valueOf(entry.getKey());
				List<Object> value = (List<Object>) entry.getValue();
				headers.put(key, value);
			}
            
            if (httpStatus == HttpStatus.CREATED || httpStatus == HttpStatus.NO_CONTENT) 
            {
            	ResponseEntity responseEntity = new ResponseEntity(null,  headers, httpStatus);
            	return (T) responseEntity;
            }
            
            if (responseExtractor != null) {
                return responseExtractor.extractData(response);
            } else {
            	ResponseEntity responseEntity = new ResponseEntity(null, headers, httpStatus);
            	return (T) responseEntity;
//                return null;
            }
        } catch (IOException ex) {
            throw new ResourceAccessException("I/O error: " + ex.getMessage(), ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
	
	
	private void logResponseStatus(HttpMethod method, URI url, ClientHttpResponse response)
	{
	    if (logger.isDebugEnabled()) 
        {
        	try {
        		logger.debug(method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" + response.getStatusText() + ")");
        	}
        	catch (IOException e) {
        	}
        }
	}
	
	private void handleResponseError(HttpMethod method, URI url, ClientHttpResponse response) throws IOException
	{
		if (logger.isWarnEnabled()) 
		{
			try {
				logger.warn(method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" + response.getStatusText() + "); invoking error handler");
			}
			catch (IOException e) {
			}
		}
        getErrorHandler().handleError(response);
	}


	public ClientHttpResponse getResponse() {
		return response;
	}


	public void setResponse(ClientHttpResponse response) {
		this.response = response;
	}


	
}
