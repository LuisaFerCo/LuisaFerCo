package com.example.apipetstore.client;

import com.example.apipetstore.models.Pet;
import com.example.apipetstore.specs.PetSpecs;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;

public class PetClient {

    public static Response addNewPet(Pet pet){
        pet.setId(5869);
        return given()
                .spec(PetSpecs.createPetSpecs())
                .when()
                    .body(pet)
                    .post()
                .then()
                    .extract()
                    .response();
    }

    public static Response findPetById(String id){
        return given()
                .spec(PetSpecs.getPetById(id))
                .when()
                .get("/{petId}")
                .then()
                .spec(PetSpecs.successResponse())
                .extract().response();
    }






}
