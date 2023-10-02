package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority =1, dataProvider = "Data", dataProviderClass = Dataproviders.class)
    public void testPostuser(String userID, String userName, String fname, String lname, String useremail, String pwd, String phone){

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));  // from faker lib generate id number. hashcode used to generate new id number everytime
        userPayload.setEmail(useremail);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);
        userPayload.setUsername(userName);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority =2, dataProvider = "UserNames", dataProviderClass= Dataproviders.class)
    public void testDeleteUser(String userName){

        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
