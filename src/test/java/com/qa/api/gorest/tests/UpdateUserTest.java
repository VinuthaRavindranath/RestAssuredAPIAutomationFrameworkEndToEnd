package com.qa.api.gorest.tests;

import com.qa.api.gorest.base.BaseTest;
import com.qa.api.gorest.data.CreateUserData;
import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Epic("EPIC-03 : update user go rest api feature implementation.....")
@Story("US-UPDATE user API : get user api feature....")
public class UpdateUserTest extends BaseTest {

    @Description("update a users api test...")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public String updateUserPutApiTest(){
        User  user = new User(CreateUserData.createUserName(), CreateUserData.createUserEmail(), "female", "active");
        Response postResponse= RestClient.doPost(baseUri,basePath,"Json",token,null,true,user);
        postResponse.prettyPrint();
        String userId = postResponse.jsonPath().getString("id");

        user.setStatus("inactive");
        Response putResponse= RestClient.doPut(baseUri,basePath+userId,"Json",token,null,true,user);
        System.out.println( putResponse.statusCode());
        putResponse.prettyPrint();
        return userId;
    }

    @Description("get the updated user api test...")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void getUserApiTest(){
        Response response=RestClient.doGet(baseUri,basePath+updateUserPutApiTest(),"Json",token,null,true);
        response.statusCode();
        response.prettyPrint();
        assertThat(response.path("status").toString(), is(equalTo("inactive")));
    }
}
