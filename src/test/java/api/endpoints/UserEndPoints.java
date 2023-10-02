package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// This class is to perform CRUD operations (Create Retrieve UUpdate Delete)

public class UserEndPoints {

    public static Response createUser(User payload){ // one parameter needed - payload
        Response response = given()
            .contentType(ContentType.JSON)      // header content type
            .accept(ContentType.JSON)           // header accept
            .body(payload)              // body of the request
        .when()
                .post(Routes.post_url);  // refer url from Routes clss

        return response;
    }

    public static Response readUser(String userName){ // one parameter needed - username. payload is not needed for get method
        Response response = given()
                .pathParam("username", userName)
        .when()
                .get(Routes.get_url);  // refer url from Routes clss

        return response;
    }

    public static Response updateUser(User payload, String userName){   // two parameter needed. Payload and username
        Response response = given()
                .pathParam("username", userName)
                .contentType(ContentType.JSON)      // header content type
                .accept(ContentType.JSON)           // header accept
                .body(payload)                   // body of the request
        .when()
                .put(Routes.update_url);  // refer url from Routes clss

        return response;
    }

    public static Response deleteUser(String userName){ // one parameter needed - username. payload is not needed for delete method
        Response response = given()
                .pathParam("username",userName)
        .when()
                .delete(Routes.delete_url);  // refer url from Routes clss

        return response;
    }

}
