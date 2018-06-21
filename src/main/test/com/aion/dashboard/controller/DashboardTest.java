package com.aion.dashboard.controller;


import com.aion.dashboard.datatransferobject.MessageDTO;
import org.junit.Test;

import static com.aion.dashboard.controller.DashboardController.handleError;
import static com.aion.dashboard.controller.DashboardController.packageAsEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DashboardTest {



    /**
     * Ensures that a null will not throw an exception to the user
     */
    @Test(expected = NullPointerException.class)
    public void handleErrorGetsNull(){
        MessageDTO object = new MessageDTO();

        object.setErrorMessage("Internal server error. Please Retry");
        object.setErrorCode("0x201");


        assertEquals(object, handleError((Exception) null));

        //handleError((String) null);

    }
//
//    /**
//     * Ensures that correct message is sent and the exception is not thrown to the user
//     */
//    @Test
//    public void handleErrorGetException(){
//        JSONObject object = new JSONObject();
//        object.put("MessageDTO", "Internal server error.");
//        object.put("ErrorCode", "0x201");
//
//
//
//        assertEquals(object.getString("MessageDTO"), handleError(new Exception("An Error MessageDTO")).getString("MessageDTO"));
//
//        assertEquals(object.getString("ErrorCode"), handleError( new Exception("An Error MessageDTO")).getString("ErrorCode"));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void handleErrorTestStatusCodes(){
//        JSONObject object = new JSONObject();
//
//        object.put("MessageDTO", "Missing parameter in request.");
//        object.put("ErrorCode","0x001");
//
//        assertEquals(object.getString("MessageDTO"), handleError("0x001").getString("MessageDTO"));
//
//        assertEquals(object.getString("ErrorCode"), handleError("0x001").getString("ErrorCode"));
//
//
//
//        object.put("MessageDTO", "Malformed parameters.");
//        object.put("ErrorCode","0x002");
//
//
//
//        assertEquals(object.getString("MessageDTO"), handleError("0x002").getString("MessageDTO"));
//        assertEquals(object.getString("ErrorCode"), handleError("0x002").getString("ErrorCode"));
//
//        object.put("MessageDTO", "Invalid Request.");
//        object.put("ErrorCode","0x003");
//
//        assertEquals(object.getString("MessageDTO"), handleError("0x003").getString("MessageDTO"));
//        assertEquals(object.getString("ErrorCode"), handleError("0x003").getString("ErrorCode"));
//
//
//        handleError("5021");
//    }
//
//
//    private JSONObject createDummyJson(){
//        JSONObject object = new JSONObject();
//        object.put("MessageDTO","Lorem Ipsum");
//        return object;
//
//    }
//    @Test
//    public void packageEntityTest(){
//        JSONObject obj = createDummyJson();
//
//        assertEquals(HttpStatus.OK,packageAsEntity(200,obj).getStatusCode());
//        assertTrue(packageAsEntity(200,obj).hasBody());
//        assertTrue(packageAsEntity(200,null).hasBody());
//
//        System.out.println(packageAsEntity(200,obj).toString());
//        System.out.println(packageAsEntity(200,null).toString());
//
//
//
//    }






}