package com.example.apipetstore.client;

import com.example.apipetstore.models.Pet;
import com.example.apipetstore.specs.PetSpecs;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;

public class PetClient {

    public static Response AddNewPet(Pet pet){
        pet.getCategory().setId(589);
        return given()
                .spec(PetSpecs.createPetSpecs())
                .when()
                    .body(pet)
                    .post()
                .then()
                    .extract()
                    .response();
    }




}
