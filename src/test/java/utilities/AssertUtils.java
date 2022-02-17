package utilities;

import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;

public class AssertUtils extends ApiBase{

    public static void assertMultipleBodyPaths(Response resp, HashMap<String, String> data) {
        for (String jsonPath : data.keySet()) {
            String headValue = data.get(jsonPath);
            log.info("Body JSON path is : " + jsonPath + " and value expected is : " + headValue);
            assertEquals(resp.body().path(jsonPath), headValue);
        }
    }

    public static void assertEqualsMultiple(HashMap<String, String> data) {
        for (String key : data.keySet()) {
            String value = data.get(key);
            assertEquals(key, value);
        }
    }

    public static void assertEquals(Object objActual, Object objExpected) {
        try {
            SoftAssert sAssert = new SoftAssert();
            sAssert.assertEquals(objActual.toString().trim(), objExpected.toString().trim());
            sAssert.assertAll();
            log.info(objActual + " is equal to " + objExpected + " as expected.");
        } catch (AssertionError | Exception e) {
            log.error(objActual + " is NOT equal to " + objExpected + " Error..."+ ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }
    public static void assertNotEquals(Object objActual, Object objExpected) {
        try {
            SoftAssert sAssert = new SoftAssert();
            sAssert.assertNotEquals(objActual.toString().trim(), objExpected.toString().trim());
            sAssert.assertAll();
            log.info(objActual + " is NOT equal to " + objExpected + " as expected.");
        } catch (AssertionError | Exception e) {
            log.error(objActual + " is equal to " + objExpected + " against expectations. Error...");
            throw e;
        }
    }

    public static void assertContains(String objFullData, String objExpected) {
        try {
            SoftAssert sAssert = new SoftAssert();
            sAssert.assertTrue(objFullData.trim().contains(objExpected.trim()));
            sAssert.assertAll();
            log.info("Response contains => " + objExpected + " as expected.");
        } catch (AssertionError | Exception e) {
            log.error("Response does NOT contain => " + objExpected + " as expected. Error...");
            throw e;
        }
    }

    public static void assertContainsMultiple(String objFullData, String objsExpected) {
        String[] allObjects = objsExpected.split(",");
        Boolean isFailed = false;
        for (String objExpected : allObjects) {
            SoftAssert sAssert = new SoftAssert();
            try {
                sAssert.assertTrue(objFullData.trim().contains(objExpected.trim()));
                sAssert.assertAll();
                log.info("Response contains => " + objExpected + " as expected.");
            } catch (AssertionError e) {
                log.fatal("Response does NOT contain => " + objExpected + " as expected. Error...");
                isFailed = true;
            }
        }
        if (isFailed) {
            Assert.fail("Some assertions hav failed above... Error..");
        }

    }


    public static void assertNotNull(Object objFullData) {
        try {
            SoftAssert sAssert = new SoftAssert();
            sAssert.assertNotNull(objFullData);
            sAssert.assertAll();
            log.info(objFullData + " -> is not null as expected.");
        } catch (AssertionError | Exception e) {
            log.error(objFullData + " -> is null.");
            throw e;
        }
    }

    public static void assertIsNull(Object objFullData) {
        try {
            SoftAssert sAssert = new SoftAssert();
            sAssert.assertNull(objFullData);
            sAssert.assertAll();
            log.info(objFullData + " -> is null as expected.");
        } catch (AssertionError | Exception e) {
            log.error(objFullData + " -> is not null.");
            throw e;
        }
    }

    /**
     * This method validates that the values of a list of fields are not null
     * @param objectList accepts list of objects from jsonPath.
     * Example: response.getBody().jsonPath().getList("data.id")
     */
    public static void assertListNotNull(List<Object> objectList, boolean acceptEmptyStrings) {
        try {
            SoftAssert softAssert = new SoftAssert();
            for (int i=0;i<objectList.size();i++) {
                softAssert.assertNotNull(objectList.get(i));
                if (!acceptEmptyStrings) {
                    softAssert.assertFalse(objectList.get(i).equals(""));
                }
            }
            softAssert.assertAll();
            log.info(objectList + " -> has no null or empty string values as expected");
        } catch (AssertionError | Exception e) {
            log.error(objectList + " -> has null or empty string values");
            throw e;
        }
    }
}
