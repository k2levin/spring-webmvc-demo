package com.k2studio.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class HttpClientController {

    @GetMapping(value = "/httpclient/async/get", produces = "application/json")
    public String getHttpClientAsync() {
		HttpRequest httpRequest = HttpRequest.newBuilder()
			.uri(URI.create("https://petstore.swagger.io/v2/pet/9222968140497181029"))
			.GET()
			.header("Content-Type", "application/json")
			.timeout(Duration.ofSeconds(20))
			.build();

		String response = getHttpClient().sendAsync(httpRequest, BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.join();

		return response;
    }

    @GetMapping(value = "/httpclient/sync/get", produces = "application/json")
    public String getHttpClientSync() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
			.uri(URI.create("https://petstore.swagger.io/v2/pet/9222968140497181029"))
			.GET()
			.header("Content-Type", "application/json")
			.timeout(Duration.ofSeconds(20))
			.build();

        String response = getHttpClient().send(httpRequest, BodyHandlers.ofString()).body();

		return response;
    }

    @PostMapping(value = "/httpclient/async/post", produces = "application/json")
    public String postHttpClientAsync() throws JsonProcessingException {
		HttpRequest httpRequest = HttpRequest.newBuilder()
			.uri(URI.create("https://petstore.swagger.io/v2/pet"))
          	.POST(BodyPublishers.ofString(getRequestBody()))
			.header("Content-Type", "application/json")
			.timeout(Duration.ofSeconds(10))
			.build();

		String response = getHttpClient().sendAsync(httpRequest, BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.join();

        return response;
    }

    @PostMapping(value = "/httpclient/sync/post", produces = "application/json")
    public String postHttpClientSync() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
			.uri(URI.create("https://petstore.swagger.io/v2/pet"))
          	.POST(BodyPublishers.ofString(getRequestBody()))
			.header("Content-Type", "application/json")
			.timeout(Duration.ofSeconds(10))
			.build();

        String response = getHttpClient().send(httpRequest, BodyHandlers.ofString()).body();

        return response;
    }

    private HttpClient getHttpClient() {
        HttpClient httpClient = HttpClient.newBuilder()
			.version(Version.HTTP_2)
			.followRedirects(Redirect.NORMAL)
			.build();
        return httpClient;
    }

    private String getRequestBody() {
        String requestBody = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"kkkvvvnnn\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
        return requestBody;
    }

    // private String getRequestBody2() throws JsonProcessingException {
    //     HashMap<String, String> data = new HashMap<String, String>();
	// 	data.put("username", "kkkvvv");
	// 	data.put("password", "password");

	// 	String requestBody = new ObjectMapper().writeValueAsString(data);
    //     return requestBody;
    // }

}
