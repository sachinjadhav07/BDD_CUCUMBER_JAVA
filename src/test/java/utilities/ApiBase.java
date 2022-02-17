package utilities;

import static utilities.BodyBuilder.processBody;
import static utilities.Globals.apiRootName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.junit.Assert;

import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class ApiBase extends TestBase{
	
	
//	public static PropertyReader prop;
	public static Logger log;
	public static Faker fk;
	static Properties prop = new Properties();
    static OutputStream fr = null;
    public static String objectPropFilePath = "C:\\brillio\\lineage\\src\\test\\resources\\" + File.separator + "objects.properties";
    static {
    	log = Logger.getLogger("LineageBASA");
    	fk = new Faker();
		PropertyConfigurator.configure("log4j.properties");
    }


	private static void validateJsonPaths(Response resp, String jsonPathsToValidate) throws Exception {

        if (jsonPathsToValidate == null || jsonPathsToValidate.equalsIgnoreCase("")) {
            log.info("No JSON path to validate");
            return;
        }
        String jPathsToValidate = jsonPathsToValidate.replaceAll("(\\r|\\n)", "");

        String[] allJsonPaths = jPathsToValidate.split(";");
        String jsonPathValueInResponse = null;

        for (String path : allJsonPaths) {
            String jsonPath = path.split("=")[0];
            String expectedValue = path.split("=", 2)[1];
            log.info("JSON path to validate in response is : " + jsonPath + " , expected value is : " + expectedValue);
            jsonPathValueInResponse = resp.jsonPath().get(jsonPath).toString();
            if (jsonPathValueInResponse == null | "".equalsIgnoreCase(jsonPathValueInResponse)) {
                log.fatal("No value found in response for JSON path  : " + jsonPath + " skipping Json path validation");
                Assert.fail("JSON path validation failed for path - " + jsonPath);
            }
            AssertUtils.assertEquals(jsonPathValueInResponse, expectedValue);
        }

    }

    public static Response makeApiCallExcel(String sheetName, String apiRootName) {

        String Headers = "";
        String RequestType = "";
        String BodyData = "";
        String FormData = "";
        String BaseUri = null;
        String EndPointUri = null;
        RequestSpecification requestSpec;
        RequestSpecBuilder builder = new RequestSpecBuilder();
        Response response = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> finalForm = new HashMap<>();
        String WsValidation = "";
        String curkeyJsonPaths = "";
        Map<String, String> jsonPaths = new HashMap<>();


        try {
            String strQuery = "Select * from " + sheetName + " where functionName='" + apiRootName + "'";
            Recordset recordset = new Fillo().getConnection(Globals.dataSheetPath).executeQuery(strQuery);

            while (recordset.next()) {
                Headers = recordset.getField("Headers");
                RequestType = recordset.getField("RequestType");
                BaseUri = recordset.getField("BaseUri");
                EndPointUri = recordset.getField("EndPointUri");
                BodyData = recordset.getField("BodyData");
                FormData = recordset.getField("FormData");
                WsValidation = recordset.getField("WsValidation");
                curkeyJsonPaths = recordset.getField("ExtractJsonVal");
            }
            String finalBaseUri = processBaseUri(BaseUri);
            String processedHeaders = processHeadersExcel(Headers);
            String processedBody = processBody(BodyData);
            String finalEndPointUri = processUri(EndPointUri);

            String[] allHeaders = processedHeaders.split(";");

            for (String header : allHeaders) {
                headers.put(header.split("=")[0], header.split("=")[1]);
            }

            if (BodyData.equalsIgnoreCase("")) {

                try {
                    String[] formArray = FormData.split(";");

                    for (String eachForm : formArray) {
                        finalForm.put(eachForm.split("=")[0], eachForm.split("=")[1]);
                    }
                    builder.addFormParams(finalForm);
                } catch (Exception e) {
                    //No action needed
                }

            } else {
                try {
                    builder.setBody(processedBody);
                } catch (Exception e) {
                    //no action needed
                }
            }

            builder.setBaseUri(finalBaseUri);
            builder.setBasePath(finalEndPointUri);
            builder.addHeaders(headers);
            requestSpec = builder.build();

            logApiCallDetails(apiRootName, headers, finalForm, finalBaseUri + finalEndPointUri, processedBody, RequestType);

            if (RequestType.equalsIgnoreCase("post")) {
                response = RestAssured.given().spec(requestSpec).when().post();
            }
            if (RequestType.equalsIgnoreCase("put")) {
                response = RestAssured.given().spec(requestSpec).when().put();
            }
            if (RequestType.equalsIgnoreCase("get")) {
                response = RestAssured.given().spec(requestSpec).when().get();
            }

            verifyResponseStatus(response, WsValidation);

            extractJsonValues(response, curkeyJsonPaths);

        } catch (Exception e) {
            log.error("Make Request failed.." + ExceptionUtils.getStackTrace(e));

        }

        return response;

    }

    public static Response makeMultipleApiCallsExcel(String sheetName, String[] apiRootNames) {

        Response response = null;
        for (String rootName : apiRootNames) {
            apiRootName = rootName;
            String Headers = "";
            String RequestType = "";
            String BodyData = "";
            String FormData = "";
            String BaseUri = null;
            String EndPointUri = null;
            RequestSpecification requestSpec;
            RequestSpecBuilder builder = new RequestSpecBuilder();
            response = null;
            Map<String, String> headers = new HashMap<>();
            Map<String, String> finalForm = new HashMap<>();
            String WsValidation = "";
            String curkeyJsonPaths = "";
            Map<String, String> jsonPaths = new HashMap<>();


            try {
                String strQuery = "Select * from " + sheetName + " where functionName='" + apiRootName + "'";
                Recordset recordset = new Fillo().getConnection(Globals.dataSheetPath).executeQuery(strQuery);

                while (recordset.next()) {
                    Headers = recordset.getField("Headers");
                    RequestType = recordset.getField("RequestType");
                    BaseUri = recordset.getField("BaseUri");
                    EndPointUri = recordset.getField("EndPointUri");
                    BodyData = recordset.getField("BodyData");
                    FormData = recordset.getField("FormData");
                    WsValidation = recordset.getField("WsValidation");
                    curkeyJsonPaths = recordset.getField("ExtractJsonVal");
                }
                String finalBaseUri = processBaseUri(BaseUri);
                String processedHeaders = processHeadersExcel(Headers);
                String processedBody = processBody(BodyData);
                String finalEndPointUri = processUri(EndPointUri);

                String[] allHeaders = processedHeaders.split(";");

                for (String header : allHeaders) {
                    headers.put(header.split("=")[0], header.split("=")[1]);
                }

                if (BodyData.equalsIgnoreCase("")) {

                    try {
                        String[] formArray = FormData.split(";");

                        for (String eachForm : formArray) {
                            finalForm.put(eachForm.split("=")[0], eachForm.split("=")[1]);
                        }
                        builder.addFormParams(finalForm);
                    } catch (Exception e) {
                        //No action needed
                    }

                } else {
                    try {
                        builder.setBody(processedBody);
                    } catch (Exception e) {
                        //no action needed
                    }
                }

                builder.setBaseUri(finalBaseUri);
                builder.setBasePath(finalEndPointUri);
                builder.addHeaders(headers);
                requestSpec = builder.build();

                logApiCallDetails(apiRootName, headers, finalForm, finalBaseUri + finalEndPointUri, processedBody, RequestType);

                if (RequestType.equalsIgnoreCase("post")) {
                    response = RestAssured.given().relaxedHTTPSValidation().spec(requestSpec).when().post();
                }
                if (RequestType.equalsIgnoreCase("put")) {
                    response = RestAssured.given().relaxedHTTPSValidation().spec(requestSpec).when().put();
                }
                if (RequestType.equalsIgnoreCase("get")) {
                    response = RestAssured.given().relaxedHTTPSValidation().spec(requestSpec).when().get();
                }

                verifyResponseStatus(response, WsValidation);

                extractJsonValues(response, curkeyJsonPaths);

            } catch (Exception e) {
                log.error("Make Request failed.." + ExceptionUtils.getStackTrace(e));

            }
        }
        return response;

    }


    public static void logApiCallDetails(String apiRootName, Map<String, String> headers, Map<String, String> finalForm,
                                         String finalUri, String processedBody, String RequestType) {

        log.info("Current root is => " + apiRootName);
        log.info("RequestType is : " + RequestType);
        log.info("Final Uri is : " + finalUri);
        log.info("Processed Headers are : " + headers.toString());
        log.info("Processed BodyData is : " + processedBody);
        log.info("Processed FormData is : " + finalForm.toString());

    }

    public static void verifyResponseStatus(Response respResponse, String WsValidation) {

        try {
            if (respResponse.asString().equalsIgnoreCase("")) {
                log.info("Response Status : " + respResponse.getStatusLine());
                log.info("Response Headers : " + respResponse.headers().toString());
            } else {
                log.info("Response is : " + respResponse.asString());
                log.info("Response status is  : " + respResponse.statusLine());
                if (!WsValidation.equalsIgnoreCase("")) {
                    Assert.assertEquals(respResponse.statusCode(), Integer.parseInt(WsValidation));
                    log.info("Response code assertion passes as : " + respResponse.statusCode());
                }
            }
        } catch (Exception e) {
            log.error("Response verification failed");
            throw e;
        }
    }

    public static String processBaseUri(String strBaseUri) {

        if (strBaseUri.contains("globalBaseUri")) {
            strBaseUri = strBaseUri.replace("globalBaseUri", getGlobalSheetValuesExcel("globalBaseUri"));
        }
        return strBaseUri;
    }
    public static String processUri(String strURI) {
        strURI = updateValues(strURI);
        if (strURI.contains("AutoRandomCustomerId")) {
            strURI = strURI.replace("AutoRandomCustomerId", fk.number().digits(6));
        }
        if (strURI.contains("AutoRandomBillerCode")) {
            if (Globals.autoBillerCode == null || Globals.autoBillerCode.isEmpty()) {
                Globals.autoBillerCode = String.format("%03d", new Random().nextBoolean() ? 1 : 3);
            }
            strURI = strURI.replace("AutoRandomBillerCode", Globals.autoBillerCode);
        }
        if (strURI.contains("AutoBillId")) {
            strURI = strURI.replace("AutoBillId", Globals.autoBillId);
        }
        if (strURI.contains("AutoFeeBillerCode")) {
            if (Globals.autoFeeBillerCode == null || Globals.autoFeeBillerCode.isEmpty()) {
                int[] availableCodes = new int[]{90, 91, 92};
                int randomIndex = new Random().nextInt(availableCodes.length);
                Globals.autoFeeBillerCode = String.format("%03d", availableCodes[randomIndex]);
            }
            strURI = strURI.replace("AutoFeeBillerCode", Globals.autoFeeBillerCode);
        }
        if (strURI.contains("billerId") && strURI.contains("serviceCode")) {
            strURI = strURI.replace("billerId", Globals.auto_FeeCode).replace("serviceCode", Globals.auto_ServiceCode);
        }
        return strURI;
    }

    public static void saveProperties(String Key, String Value) throws IOException {
        fr = new FileOutputStream(objectPropFilePath);
        prop.setProperty(Key, Value);
        prop.getProperty(Key);
        prop.store(fr, null);
        fr.close();
    }

    public static String getObjectProperty(String Key) {
        String strVal = "";
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(objectPropFilePath);
            prop.load(fis);
            strVal = prop.getProperty(Key);
            fis.close();
        } catch (Exception e) {
            log.error("Error while accessing objects.properties file.." + ExceptionUtils.getStackTrace(e));
        }
        return strVal;
    }

    public static Set<Object> getAllObjPropKeys() {
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(objectPropFilePath);
            prop.load(fis);
            return prop.keySet();
        } catch (Exception e) {
            log.error("Unable to read objects.properties file..." + ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static void extractJsonValues(Response response, String curkeyJsonPaths) {
        if (curkeyJsonPaths == null || curkeyJsonPaths.equalsIgnoreCase("")) {
            log.info("No JSON path to store from this response.");
            return;
        }
        try {
            String[] properties = curkeyJsonPaths.split(";");
            for (String property : properties) {
                String propName = property.split("=")[0];
                String propJsonPath = property.split("=")[1];
                saveProperties(propName, response.jsonPath().get(propJsonPath).toString());
            }
        } catch (Exception e) {
            // No action needed.
        }
    }

    /***
     * @description- This function gets keys and values from objects.properties file and injects them in the raw data
     * @param rawData
     * @return
     */
    public static String updateValues(String rawData) {
        Set<Object> allKeys = getAllObjPropKeys();
        for (Object key : allKeys) {
            if (rawData.contains(key.toString())) {
                rawData = rawData.replace(key.toString(), getObjectProperty(key.toString()));
            }
        }
        return rawData;
    }

       public static String processHeadersExcel(String strHeaders) {

        strHeaders = strHeaders.replaceAll("(\\r|\\n)", "");
        if (strHeaders.contains("globalHeaders")) {
            strHeaders = strHeaders.replace("globalHeaders", getGlobalSheetValuesExcel("globalHeaders"));
        }
        strHeaders = updateValues(strHeaders);
        if (strHeaders.contains("AutoUUID")) {
            strHeaders = strHeaders.replace("AutoUUID", UUID.randomUUID().toString());
        }
        return strHeaders;
    }


    public static String getGlobalSheetValuesExcel(String strPropName) {
        try {
            String strQuery = "Select * from globals where strPropName='" + strPropName + "'";
            Recordset recordset = new Fillo().getConnection(Globals.dataSheetPath).executeQuery(strQuery);
            String PropValue = "";
            while (recordset.next()) {
                PropValue = recordset.getField("PropValue");

            }
            return PropValue;
        } catch (Exception e) {
            //No action needed
        }
        return "prop value not found for key : " + strPropName;
    }



    public static void validateCompleteResponseData(Response response, String strTestData) {
        if (strTestData == null || strTestData.equalsIgnoreCase("")) {
            log.info("No test data present, complete response will not be validated.");
            return;
        }
        try {
            JSONAssert.assertEquals(strTestData, response.asString(), JSONCompareMode.LENIENT);
        } catch (Exception e) {
            log.error("Validate response data failed : " + e.getMessage());
        }
    }
    }
	
    
