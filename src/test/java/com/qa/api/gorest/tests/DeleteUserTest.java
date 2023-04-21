package com.qa.api.gorest.tests;

import com.qa.api.gorest.base.BaseTest;
import com.qa.api.gorest.data.CreateUserData;
import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("EPIC-04 : delete user go rest api feature implementation.....")
@Story("US-DELETE user API : get user api feature....")
public class DeleteUserTest extends BaseTest {

    @Description("Create a user api test...")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public String createUserPostApiTest(){
        User user = new User(CreateUserData.createUserName(), CreateUserData.createUserEmail(), "female", "active");
        Response response= RestClient.doPost(baseUri,basePath,"Json",token,null,true,user);
        System.out.println( response.statusCode());
        response.prettyPrint();
        String id=response.jsonPath().getString("id");
        return id;
    }

    @Description("Delete a user api test...")
    @Severity(SeverityLevel.MINOR)
    @Test
    public String deleteUserApiTest(){
        String id=createUserPostApiTest();
        Response response=RestClient.doDelete(baseUri,basePath+id,"Json",token,null,true);
        System.out.println(response.statusCode());
        return id;
    }

    @Description("get a user that was deleted api test...")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void getDeletedUserApiTest(){
        String id=deleteUserApiTest();
        Response response=RestClient.doGet(baseUri,basePath+id,"Json",token,null,true);
        System.out.println( response.statusCode());
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),404);
        Assert.assertEquals(response.jsonPath().getString("message"),"Resource not found");
    }


}
