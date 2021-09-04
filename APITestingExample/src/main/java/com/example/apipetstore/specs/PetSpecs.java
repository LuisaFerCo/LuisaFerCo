package com.example.apipetstore.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class PetSpecs {

    public static RequestSpecification createPetSpecs(){
        return new RequestSpecBuilder()
                        .addRequestSpecification(InitialStateSpecs.set())
                        .setContentType(ContentType.JSON)
                        .build();
    }

    public static RequestSpecification getPetById(String id){
        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).
                addPathParam("petId", id).
                build();
    }

    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }


    public static ResponseSpecification notFoundResponse() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_NOT_FOUND).
                build();
    }

}
