package com.aion.dashboard.controller;


import com.aion.dashboard.datatransferobject.MessageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/v2/dashboard")
public class DashboardController {


    /**
     * Called whenever an error is thrown
     * @param e Exception caught
     * @return a MessageDTO object
     */

    static MessageDTO handleError(Exception e){


        if (e != null) {
            e.printStackTrace();
        }
        return handleError("0x201");
    }


    /**
     * Creates an error message based on the error code
     * @param errorCode Error code should never be null and are given based on the documentation.
     * @return A message carrying the specified error.
     */
    static MessageDTO handleError(String errorCode){

        if (errorCode == null) {
            errorCode = "0x201";
        }
        MessageDTO messageDTO = new MessageDTO();
        switch (errorCode){
            case "0x001":
                messageDTO.setErrorMessage("Missing parameter in request.");
                break;
            case "0x002":
                messageDTO.setErrorMessage("Malformed parameters.");
                break;
            case "0x201":
                messageDTO.setErrorMessage("Internal server error. Please Retry");
                break;
            default:
                messageDTO.setErrorMessage("Invalid Request.");
                break;
        }

        messageDTO.setErrorCode(errorCode);

        return messageDTO;
    }


    /**
     * Generic packaging method.
     * Status Code: 200 OK
     * Content type: Application/json
     *
     * @param object
     * @return Entity to be returned to the user
     */
    static ResponseEntity<Object> packageAsEntity(Object object){

        if (object == null) {
            object = new Object();
        }
        return packageAsEntity(HttpStatus.OK, object);


    }


    /**
     *
     * Content type: Application/json
     *
     * @param statusCode the status code to give to the response entity
     * @param object the object to be returned to the client
     * @return Entity to be returned to the user
     */

    static ResponseEntity<Object> packageAsEntity(HttpStatus statusCode, Object object){
        if (object == null) {
            object = new Object();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(object, headers, statusCode);
    }


}
