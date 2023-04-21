package com.qa.api.gorest.tests;

import com.qa.api.gorest.base.BaseTest;
import com.qa.api.gorest.restclient.RestClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

@Epic("EPIC-02 : get user go rest api feature implementation.....")
@Story("US-GET user API : get user api feature....")
public class GetUserTest extends BaseTest {

    @Description("get all users api test...")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void getAllUsersList(){
        Response response=RestClient.doGet(baseUri,basePath,"Json",token,null,true);
        response.statusCode();
        response.prettyPrint();
    }


    @Description("get all users api test...")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void getUsersWithQueryParams(){
        HashMap<String,String> params=new HashMap<String,String>();
        params.put("status","active");
        params.put("gender","male");
        Response response=RestClient.doGet(baseUri,basePath,"Json",token,params,true);
        response.statusCode();
        response.prettyPrint();
    }
}
