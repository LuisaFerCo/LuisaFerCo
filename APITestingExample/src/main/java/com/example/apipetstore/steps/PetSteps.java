package com.example.apipetstore.steps;

import com.example.apipetstore.client.PetClient;
import com.example.apipetstore.models.Pet;
import com.example.apipetstore.models.responses.PetResponse;
import com.example.apipetstore.utils.Conversions;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.apipetstore.utils.Conversions.responseBody;
import static com.example.apipetstore.utils.StringManager.ADDED_PET;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetSteps {


    @Step("add a new pet to the store '{0}'")
    public void createANewPet(Pet pet){
        Response addedPet = PetClient.addNewPet(pet);
        setSessionVariable(ADDED_PET).to(addedPet);
    }

    @Step("should see id a new pet")
    public void shouldSeeANewIdPet(int idCategory){
        assertThat(getPetAddedToStore().getCategory().getId(), equalTo(idCategory));
        Serenity.recordReportData().withTitle("reponse").andContents(getPetAddedToStore().toString());
    }

    @Step("should see pet available")
    public void shouldSeeAvailable(){
        lastResponse().then().assertThat().body("status",equalTo("available"));
        assertThat(getPetAddedToStore().getStatus(), equalTo("available"));
    }

    public PetResponse getPetAddedToStore(){
        Response response = sessionVariableCalled(ADDED_PET);
        return response.getBody().as(PetResponse.class);
    }

    @Step("Find pet By id '{0}'")
    public void findPetById(String id){
        Response foundedPet = PetClient.findPetById(id);
        setSessionVariable("Found_pet").to(foundedPet);
    }

    @Step("should see pet")
    public void shouldSeePet(){
        Response response = sessionVariableCalled("Found_pet");
        PetResponse petResponse = responseBody(response).toModel(PetResponse.class);
        assertThat("The pet previous added ",getPetAddedToStore().getName(),equalTo(petResponse.getName()));
    }


}
