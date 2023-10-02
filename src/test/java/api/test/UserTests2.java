package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {
    Faker faker;
    User userPayload;

    public static Logger logger;

    @BeforeClass
    public void setupDate(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());  // from faker lib generate id number. hashcode used to generate new id number everytime
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUsername(faker.name().username());

        //logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority =1)
    public void testPostUser(){

        logger.info("***** Creating User ***********");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();

        //Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***** User is Created ***********");

    }

    @Test(priority=2)
    public void testGetUserByName(){
        logger.info("***** Creating User by Name***********");

        Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();

       // Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority=3)
    public void testUpdateUser(){

        logger.info("***** Updating User***********");

        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());

        Response response = UserEndPoints2.updateUser(userPayload, this.userPayload.getUsername());
        response.then().log().all();

      //  Assert.assertEquals(response.getStatusCode(),200);

        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),500);
    }

    @Test(priority=4)
    public void deleteUserByName(){

        logger.info("***** Deleting User***********");

        Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

       // Assert.assertEquals(response.getStatusCode(),200);
    }


}
