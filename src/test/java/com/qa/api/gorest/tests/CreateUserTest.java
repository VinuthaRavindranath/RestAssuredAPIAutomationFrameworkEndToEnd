package com.qa.api.gorest.tests;

import com.qa.api.gorest.base.BaseTest;
import com.qa.api.gorest.data.CreateUserData;
import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("EPIC-01 : create user go rest api feature implementation.....")
@Story("US-Create user API : create user api feature....")
public class CreateUserTest extends BaseTest {

    @Description("create a user api test...verify create user from post call....")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void createUserPostApiTest(){
        User user = new User(CreateUserData.createUserName(), CreateUserData.createUserEmail(), "female", "active");
        Response response=RestClient.doPost(baseUri,basePath,"Json",token,null,true,user);
        System.out.println( response.statusCode());
        response.prettyPrint();
    }

    @Description("create a user api test...verify create user from post call using excel sheet and data provider....")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "getUserData")
    public void createMultipleUsersPostApiTest(String name,String gender,String status){
        User user = new User(name,CreateUserData.getRandomEmail(),gender,status);
        Response response=RestClient.doPost(baseUri,basePath,"Json",token,null,true,user);
        System.out.println( response.statusCode());
        response.prettyPrint();
    }

    @DataProvider
    public Object[][] getUserData(){
       Object userData[][]= ExcelUtil.getTestData("UserData");
       return userData;
    }
}
