package com.qa.api.gorest.data;

import com.github.javafaker.Faker;

public class CreateUserData {
    static Faker data=new Faker();

    public static String createUserName() {
        return data.name().fullName();
    }

    public static String createUserEmail() {
        return data.internet().emailAddress();
    }

    public static String getRandomEmail(){
        String email = "automation"+System.currentTimeMillis()+"@gmail.com";
        return email;
    }
}
