package com.qa.api.gorest.restclient;

import com.qa.api.gorest.util.TestUtil;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * This class is having all http methods which will call the apis and having
 * generic methods for getting the response and fetch the values from response.
 */
public class RestClient {

    /**
     * @param baseUri
     * @param basePath
     * @param contentType
     * @param token
     * @param paramsMap
     * @param log
     * @return this method is returning response from the POST call
     */

    @Step("GET call with {0} , {1}, {2}, {3}, {4}, {5}")
    public static Response doGet(String baseUri, String basePath, String contentType, String token, Map<String, String> paramsMap, boolean log) {

        if (setBaseUri(baseUri)) {
            RequestSpecification request = createRequest(contentType, token, paramsMap, log);
            return getResponse("GET", request, basePath);
        }
        return null;
    }

    /**
     * @param baseUri
     * @param basePath
     * @param contentType
     * @param token
     * @param paramsMap
     * @param log
     * @param obj
     * @return this method is returning response from the POST call
     */
    @Step("POST call with {0} , {1}, {2}, {3}, {4}, {5}, {6}")
    public static Response doPost(String baseUri, String basePath, String contentType, String token, Map<String, String> paramsMap, boolean log, Object obj) {

        if (setBaseUri(baseUri)) {
            RequestSpecification request = createRequest(contentType, token, paramsMap, log);
            addRequestPayload(request, obj);
            return getResponse("POST", request, basePath);
        }
        return null;
    }

    @Step("PUT call with {0} , {1}, {2}, {3}, {4}, {5}, {6}")
    public static Response doPut(String baseUri, String basePath, String contentType, String token, Map<String, String> paramsMap, boolean log, Object obj) {

        if (setBaseUri(baseUri)) {
            RequestSpecification request = createRequest(contentType, token, paramsMap, log);
            addRequestPayload(request, obj);
            return getResponse("PUT", request, basePath);
        }
        return null;
    }

    @Step("DELETE call with {0} , {1}, {2}, {3}, {4}, {5}")
    public static Response doDelete(String baseUri, String basePath, String contentType, String token, Map<String, String> paramsMap, boolean log) {

        if (setBaseUri(baseUri)) {
            RequestSpecification request = createRequest(contentType, token, paramsMap, log);
            return getResponse("DELETE", request, basePath);
        }
        return null;
    }

    private static void addRequestPayload(RequestSpecification request, Object obj) {
        String jsonPayload = TestUtil.getSerialisedJson(obj);
        request.body(jsonPayload);
    }

    private static boolean setBaseUri(String baseUri) {
        if (baseUri == null || baseUri.isEmpty()) {
            System.out.println("Please pass the correct base URI....either it is null or blank/empty...");
            return false;
        }
        try {
            RestAssured.baseURI = baseUri;
            return true;
        } catch (Exception e) {
            System.out.println("some exception got occurred while assigning the base URI with Rest Assured....");
            return false;
        }

    }

    private static RequestSpecification createRequest(String contentType, String token, Map<String, String> paramsMap, boolean log) {
        RequestSpecification request;
        if (log) {
            request = RestAssured.given().config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                    .log().all();
        } else {
            request = RestAssured.given();
        }
        if (token != null) {
            request.header("Authorization", "Bearer " + token);
        }
        if (!(paramsMap == null)) {
            request.queryParams(paramsMap);
        }
        if (contentType != null) {
            if (contentType.equalsIgnoreCase("JSON")) {
                request.contentType(ContentType.JSON);
            } else if (contentType.equalsIgnoreCase("XML")) {
                request.contentType(ContentType.XML);
            } else if (contentType.equalsIgnoreCase("Text")) {
                request.contentType(ContentType.TEXT);
            }
        }
        return request;

    }

    private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
        return executeAPI(httpMethod, request, basePath);

    }

    private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
        Response response = null;
        switch (httpMethod) {
            case "GET":
                response = request.get(basePath);
                break;
            case "POST":
                response = request.post(basePath);
                break;
            case "PUT":
                response = request.put(basePath);
                break;
            case "DELETE":
                response = request.delete(basePath);
                break;

            default:
                System.out.println("Please pass the correct http method....");
                break;
        }
        return response;
    }
}
