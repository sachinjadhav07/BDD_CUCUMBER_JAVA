package utilities;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Globals {

    public static String accessToken = "";
    public static String deviceId = "";
    public static String customerId = "";
    public static String RequestType = "";
    public static String BaseUri = "";
    public static String EndPointUri = "";
    public static String searchText = "Acc";

    public static String resp = "";

    public static Map<String, String> Headers = new HashMap<>();

    public static Map<String, String> BodyData = new HashMap<>();
    public static Map<String, String> FormData = new HashMap<>();
    public static Map<Object, Object> apiRootAllData = new LinkedHashMap<>();
    public static Map<Object, Object> apiRootKeyData = new LinkedHashMap<>();

    public static String deviceHash="";
    public static String fullName="";
    public static String autoFirstName = "";
    public static String notificationType = null;
    public static String AutoNum10="";
    public static String autoSaudiMobile = null;
    public static String sheetName;
    public static String apiRootName;
    public static String dataSheetPath= "C:\\brillio\\lineage\\src\\test\\resources\\DataSheet_Master.xlsx";
    public static String autoBirthDate = null;
    public static String autoBillerCode = null;
    public static String autoSubscriberNumber = null;
    public static String autoBillerName = null;
    public static String acceptLanguage = "en";
    public static Object toggleStatus;
    public static String autoPaymentAmount = null;
    public static String autoAccountNumber = null;
    public static String autoPaymentType = null;
    public static String autoServiceType = null;
    public static String autoCustomerId = null;
    public static String autoOtpType = null;
    public static String autoBillId = "";
    public static String autoEmail = "";
    public static String autoIban = "";
    public static String beneficiaryName = "";
    public static String autoIqamaNumber = null;
    public static String autoFeeBillerCode = null;
    public static String autoServiceCode = null;
    public static String auto_ServiceCode = null;
    public static String auto_FeeCode = "";
    public static String autoRandomNum4 = null;
    public static String autoBeneficiaryId = null;
    public static String autoSadadReferenceNumber = null;
    public static String employmentStatus = "";
    public static int autoIntAmount = 0;
    public static String autoFeeInquiryParameters = null;
    public static String autoParameterName = null;
    public static String autoParameterValue = null;
    public static String autoEmployerName = null;

    public static String demoBlazeUrl="https://www.demoblaze.com/index.html";

    public static void resetVariables() {

        // Globals.accessToken = "";
        Globals.deviceId = "";
        Globals.customerId = "";
        Globals.RequestType = "";
        Globals.BaseUri = "";
        Globals.EndPointUri = "";
        //Globals.resp = "";

        Globals.Headers = new HashMap<>();
        Globals.BodyData = new HashMap<>();
        Globals.FormData = new HashMap<>();
        Globals.apiRootAllData = new LinkedHashMap<>();
        autoPaymentAmount = null;
        autoAccountNumber = null;
        autoPaymentType = null;
        autoServiceType = null;
        autoCustomerId = null;
        autoBillerCode = null;
        autoSubscriberNumber = null;
        autoBillerName = null;
        autoBillId = "";
        autoEmail = "";
        autoIban = "";
        beneficiaryName = "";
        autoFeeBillerCode = null;
        autoServiceCode = null;
        autoFirstName = "";
        autoRandomNum4 = null;
        autoBeneficiaryId = null;
        autoSadadReferenceNumber = null;
        autoFeeInquiryParameters = null;
        autoParameterName = null;
        autoParameterValue = null;
    }

}
