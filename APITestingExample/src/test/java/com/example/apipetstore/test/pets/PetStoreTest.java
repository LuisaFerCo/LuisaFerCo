package com.example.apipetstore.test.pets;

import com.example.apipetstore.data.GenerateData;
import com.example.apipetstore.models.Pet;
import com.example.apipetstore.steps.PetSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.apipetstore.utils.StringManager.ID_PET;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("PageObjects pattern"),
        @WithTag("version:RELEASE-1"),
})
public class PetStoreTest {

    @Steps
    PetSteps vendor;



    @Before
    public void setData(){

    }

    @Test
    public void shouldAddANewPetToStore(){
        Pet pet = GenerateData.getPetRequest();
        vendor.createANewPet(pet);
        vendor.shouldSeeANewIdPet(pet.getCategory().getId());
        vendor.findPetById(vendor.getPetAddedToStore().getId());
        vendor.shouldSeePet();
        vendor.shouldSeeAvailable();
        restAssuredThat(response -> response.body(ID_PET, Matchers.equalTo(pet.getId())));
    }



}
