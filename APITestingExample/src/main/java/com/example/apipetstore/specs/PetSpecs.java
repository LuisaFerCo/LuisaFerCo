package com.example.apipetstore.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class PetSpecs {

    public static RequestSpecification createPetSpecs(){
        return new RequestSpecBuilder()
                        .addRequestSpecification(InitialStateSpecs.set())
                        .setContentType(ContentType.JSON)
                        .build();
    }

}
