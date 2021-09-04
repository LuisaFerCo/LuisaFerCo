package com.example.apipetstore.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conversions {

        private Response response;
        private static final Logger logger = LoggerFactory.getLogger(Conversions.class);

        private Conversions(Response response) {
                this.response = response;
        }

        public static Conversions responseBody(Response response){
                return new Conversions(response);
        }

        public <T> T toModel(Class<T> modelType){
        T object = null;
                try{
                        object = response.getBody().as(modelType);
                }catch (Exception e){
                        logger.info(e.getMessage());
                }
         return object;
        }
}
