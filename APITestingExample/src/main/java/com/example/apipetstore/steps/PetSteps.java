package com.example.apipetstore.steps;

import com.example.apipetstore.client.PetClient;
import com.example.apipetstore.models.Pet;
import com.example.apipetstore.models.responses.PetResponse;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetSteps {


    @Step("add a new pet to the store '{0}'")
    public void createANewPet(Pet pet){
        Response addedPet = PetClient.AddNewPet(pet);
        addedPet.getBody().print();
        Serenity.setSessionVariable("Added_Pet").to(addedPet);
    }

    @Step("should see id a new pet")
    public void shouldSeeANewIdPet(int idCategory){
        assertThat(getPetAddedToStore().getCategory().getId(), equalTo(idCategory));
    }

    @Step("should see pet available")
    public void shouldSeeAvailable(){
        SerenityRest.lastResponse().then().assertThat().body("status",equalTo("available"));
        assertThat(getPetAddedToStore().getStatus(), equalTo("available"));
    }

    public PetResponse getPetAddedToStore(){
        Response response = Serenity.sessionVariableCalled("Added_Pet");
        return response.getBody().as(PetResponse.class);
    }


}
