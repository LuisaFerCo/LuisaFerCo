package com.example.apipetstore.test.pets;

import com.example.apipetstore.data.GenerateData;
import com.example.apipetstore.models.Pet;
import com.example.apipetstore.steps.PetSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("PageObjects pattern"),
        @WithTag("version:RELEASE-1"),
})
public class PetStoreTest {

    @Steps
    PetSteps petSteps;

    @Test
    public void shouldAddANewPetToStore(){
        Pet pet = GenerateData.getPetRequest();
        petSteps.createANewPet(pet);
        petSteps.shouldSeeANewIdPet(pet.getCategory().getId());
        petSteps.shouldSeeAvailable();
    }



}
