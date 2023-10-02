package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

// This class is to perform CRUD operations (Create Retrieve UUpdate Delete)

public class UserEndPoints2 {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");   // load property file
        return routes;
    }

    public static Response createUser(User payload){ // one parameter needed - payload

        String post_url = getURL().getString("post_url");

        Response response = given()
            .contentType(ContentType.JSON)      // header content type
            .accept(ContentType.JSON)           // header accept
            .body(payload)              // body of the request
        .when()
                .post(post_url);  // refer url from Routes clss

        return response;
    }

    public static Response readUser(String userName){ // one parameter needed - username. payload is not needed for get method

        String get_url = getURL().getString("get_url");

        Response response = given()
                .pathParam("username", userName)
        .when()
                .get(get_url);  // refer url from Routes clss

        return response;
    }

    public static Response updateUser(User payload, String userName){   // two parameter needed. Payload and username

        String update_url = getURL().getString("update_url");

        Response response = given()
                .pathParam("username", userName)
                .contentType(ContentType.JSON)      // header content type
                .accept(ContentType.JSON)           // header accept
                .body(payload)                   // body of the request
        .when()
                .put(update_url);  // refer url from Routes clss

        return response;
    }

    public static Response deleteUser(String userName){ // one parameter needed - username. payload is not needed for delete method

        String delete_url = getURL().getString("delete_url");

        Response response = given()
                .pathParam("username",userName)
        .when()
                .delete(delete_url);  // refer url from Routes clss

        return response;
    }

}
