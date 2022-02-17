package utilities;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BodyBuilder extends ApiBase {



    public static String processBody(String strBodyData) {

        if (strBodyData == null | "".equalsIgnoreCase(strBodyData)) {
            log.info("No Body data to process..");
            return null;
        }
        strBodyData = strBodyData.replaceAll("(\\r|\\n)", "");
        strBodyData = ApiBase.updateValues(strBodyData);

        if (strBodyData.contains("AutoUUID")) {
            strBodyData = strBodyData.replace("AutoUUID", UUID.randomUUID().toString());
        }
        if (strBodyData.contains("AutoNameTitle")) {
            strBodyData = strBodyData.replace("AutoNameTitle", fk.name().prefix().replace(".", ""));
        }
        if (strBodyData.contains("AutoName")) {
            if (Globals.fullName == null || Globals.fullName.equals("")) {
                Globals.fullName = fk.name().fullName();
            }
            strBodyData = strBodyData.replace("AutoName", Globals.fullName);
        }

        if (strBodyData.contains("AutoIban")) {
            strBodyData = strBodyData.replace("AutoIban", Globals.autoIban);
        }

        if (strBodyData.contains("BeneficiaryName")) {
            strBodyData = strBodyData.replace("BeneficiaryName", Globals.beneficiaryName);
        }

        if (strBodyData.contains("AutoSaudiMobile13")) {
            strBodyData = strBodyData.replace("AutoSaudiMobile13", fk.numerify("+966##########"));
        }
        if (strBodyData.contains("AutoSaudiMobile")) {
            if (Globals.autoSaudiMobile == null) {
                Globals.autoSaudiMobile = fk.numerify("+966#########");
            }
            strBodyData = strBodyData.replace("AutoSaudiMobile", Globals.autoSaudiMobile);
        }
        if (strBodyData.contains("AutoUniqueApiIdentifier")) {
            strBodyData = strBodyData.replace("AutoUniqueApiIdentifier",
                    fk.numerify("sandbox#######################"));
        }
        if (strBodyData.contains("AutoClientId")) {
            strBodyData = strBodyData.replace("AutoClientId", fk.numerify("1#####"));
        }
        if (strBodyData.contains("AutoAccountNum")) {
            strBodyData = strBodyData.replace("AutoAccountNum", fk.numerify("1########"));
        }
        if (strBodyData.contains("AutoUniqueApiIdentifier")) {
            strBodyData = strBodyData.replace("AutoUniqueApiIdentifier",
                    fk.numerify("sandbox#######################"));
        }
        if (strBodyData.contains("AutoClientId")) {
            strBodyData = strBodyData.replace("AutoClientId", fk.numerify("1#####"));
        }
        if (strBodyData.contains("AutoRandomNum9")) {
            strBodyData = strBodyData.replace("AutoRandomNum9", fk.numerify("#########"));
        }
        if (strBodyData.contains("AutoStreetAddress")) {
            strBodyData = strBodyData.replace("AutoStreetAddress", fk.address().streetAddress());
        }
        if (strBodyData.contains("AutoPostalCode")) {
            strBodyData = strBodyData.replace("AutoPostalCode", fk.numerify("1###"));
        }
        if (strBodyData.contains("AutoCity")) {
            strBodyData = strBodyData.replace("AutoCity", fk.address().city());
        }
        if (strBodyData.contains("AutoCountry")) {
            strBodyData = strBodyData.replace("AutoCountry", fk.address().country().replace(".", ""));
        }
        if (strBodyData.contains("AutoEmail")) {
            if (Globals.autoEmail == null) {
                Globals.autoEmail = fk.internet().emailAddress();
            }
            strBodyData = strBodyData.replace("AutoEmail", Globals.autoEmail);
        }
        if (strBodyData.contains("AutoRandomNum1")) {
            strBodyData = strBodyData.replace("AutoRandomNum1", fk.numerify("#"));
        }
        if (strBodyData.contains("AutoRandomNum2")) {
            strBodyData = strBodyData.replace("AutoRandomNum2", fk.numerify("##"));
        }
        if (strBodyData.contains("AutoRandomNum3")) {
            strBodyData = strBodyData.replace("AutoRandomNum3", getNDigitFakeNumber(3));
        }
        if (strBodyData.contains("AutoRandomNum4")) {
            if (Globals.autoRandomNum4 == null || Globals.autoRandomNum4.isEmpty()) {
                Globals.autoRandomNum4 = getNDigitFakeNumber(4);
            }
            strBodyData = strBodyData.replace("AutoRandomNum4", Globals.autoRandomNum4);
        }
        if (strBodyData.contains("AutoRandomNum5")) {
            strBodyData = strBodyData.replace("AutoRandomNum5", getNDigitFakeNumber(5));
        }
        if (strBodyData.contains("AutoRandomNum6")) {
            strBodyData = strBodyData.replace("AutoRandomNum6", getNDigitFakeNumber(6));
        }
        if (strBodyData.contains("AutoRandomNum7")) {
            strBodyData = strBodyData.replace("AutoRandomNum7", getNDigitFakeNumber(7));
        }
        if (strBodyData.contains("AutoRandomNum8")) {
            strBodyData = strBodyData.replace("AutoRandomNum8", getNDigitFakeNumber(8));
        }
        if (strBodyData.contains("AutoRandomNum9")) {
            strBodyData = strBodyData.replace("AutoRandomNum9", getNDigitFakeNumber(9));
        }
        if (strBodyData.contains("AutoRandomNum10")) {
            strBodyData = strBodyData.replace("AutoRandomNum10", getNDigitFakeNumber(10));
        }
        if (strBodyData.contains("AutoRandomNum11")) {
            strBodyData = strBodyData.replace("AutoRandomNum11", getNDigitFakeNumber(11));
        }
        if (strBodyData.contains("AutoRandomNum12")) {
            strBodyData = strBodyData.replace("AutoRandomNum12", getNDigitFakeNumber(12));
        }
        if (strBodyData.contains("AutoFirstName")) {
            if (Globals.autoFirstName == null || Globals.autoFirstName.isEmpty()) {
                Globals.autoFirstName = fk.name().firstName();
            }
            strBodyData = strBodyData.replace("AutoFirstName", Globals.autoFirstName);
        }
        if (strBodyData.contains("AutoTokFlag")) {
            strBodyData = strBodyData.replace("AutoTokFlag", getNDigitFakeNumber(32));
        }
        if (strBodyData.contains("AutoRandomFutureDateTime")) {
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strBodyData = strBodyData.replace("AutoRandomFutureDateTime",
                    dateTimeFormatter.format(fk.date().future(12, TimeUnit.DAYS)));
        }
        if (strBodyData.contains("AutoLoremLowercase12")) {
            strBodyData = strBodyData.replace("AutoLoremLowercase12",
                    fk.lorem().characters(12, false, false));
        }
        if (strBodyData.contains("AutoRandomPastDateTime")) {
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strBodyData = strBodyData.replace("AutoRandomPastDateTime",
                    dateTimeFormatter.format(fk.date().past(12, TimeUnit.DAYS)));
        }
        if (strBodyData.contains("AutoRandomHttpFilter")) {
            strBodyData = strBodyData.replace("AutoRandomHttpFilter", fk.regexify("[pPgG]{1}"));
        }
        if (strBodyData.contains("AutoRandomPaymentToken")) {
            strBodyData = strBodyData.replace("AutoRandomPaymentToken", fk.numerify("1########"));
        }
//        if(strBodyData.contains("AutoCvv2")) {
//            strBodyData = strBodyData.replace("AutoCvv2",
//                    Encryption.encryptStringByAes(new Faker().number().digits(3)));
//        }
//        if(strBodyData.contains("AutoIv")) {
//            strBodyData = strBodyData.replace("AutoIv", new Faker().letterify("????????????????"));
//        }
//        if(strBodyData.contains("AutoPaymentsCardPin")) {
//            strBodyData = strBodyData.replace("AutoPaymentsCardPin",
//                    Encryption.encryptStringByAes("24" +
//                            new Faker().number().digits(4) + "FFFFFFFFFF"));
//        }
//        if(strBodyData.contains("AutoPaymentsPan")) {
//            strBodyData = strBodyData.replace("AutoPaymentsPan",
//                    Encryption.encryptStringByAes(new Faker().number().digits(16)));
//        }
        if(strBodyData.contains("AutoPaymentsAccountInputType")) {
            strBodyData = strBodyData.replace("AutoPaymentsAccountInputType", fk.regexify("[AaTt]{1}"));
        }
        if(strBodyData.contains("AutoPaymentsCardProductId")) {
            strBodyData = strBodyData.replace("AutoPaymentsCardProductId", fk.numerify("1##"));
        }
        if(strBodyData.contains("AutoRandomMoneyAmountPeriodDecimal")) {
            strBodyData = strBodyData.replace("AutoRandomMoneyAmountPeriodDecimal", fk.commerce().price().replace(",", "."));
        }
        if(strBodyData.contains("AutoRandomMoneyAmount")) {
            strBodyData = strBodyData.replace("AutoRandomMoneyAmount", fk.commerce().price());
        }
        if(strBodyData.contains("AutoPaymentsCrDrType")) {
            strBodyData = strBodyData.replace("AutoPaymentsCrDrType", fk.regexify("[cCdD]{1}"));
        }
        if(strBodyData.contains("AutoCompanyName")) {
            strBodyData = strBodyData.replace("AutoCompanyName", fk.company().name());
        }
        if(strBodyData.contains("AutoBldgNum")) {
            strBodyData = strBodyData.replace("AutoBldgNum", fk.bothify("?####"));
        }
        if(strBodyData.contains("AutoUpperAlphabetic3")) {
            strBodyData = strBodyData.replace("AutoUpperAlphabetic3", fk.letterify("???",
                    true));
        }
        if(strBodyData.contains("AutoNationality")) {
            strBodyData = strBodyData.replace("AutoNationality", fk.nation().nationality());
        }
        if (strBodyData.contains("AutoDateOfBirth")) {
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
            strBodyData = strBodyData.replace("AutoDateOfBirth",
                    dateTimeFormatter.format(fk.date().past(12, TimeUnit.DAYS)));
        }
        if(strBodyData.contains("AutoRandomBoolean")) {
            Random random = new Random();
            strBodyData = strBodyData.replace("AutoRandomBoolean", Boolean.toString(random.nextBoolean()));
        }
        if(strBodyData.contains("AutoCuIdNumber")) {
            strBodyData = strBodyData.replace("AutoCuIdNumber", fk.numerify("###/####"));
        }
        if(strBodyData.contains("AutoKycStatus")) {
            strBodyData = strBodyData.replace("AutoKycStatus", fk.regexify("[1-7]{1}"));
        }
        if(strBodyData.contains("AutoRandomNumberOneToThree")) {
            strBodyData = strBodyData.replace("AutoRandomNumberOneToThree", fk.regexify("[1-3]{1}"));
        }
        if (strBodyData.contains("AutoBirthDate")) {
            strBodyData = strBodyData.replace("AutoBirthDate", Globals.autoBirthDate);
        }
        // TODO @Charlo: Only biller codes 001 and 003 are available at 29/03/2021
        if (strBodyData.contains("AutoRandomBillerCode")) {
            if (Globals.autoBillerCode == null) {
                Globals.autoBillerCode = String.format("%03d", new Random().nextBoolean() ? 1 : 3);
            }
            strBodyData = strBodyData.replace("AutoRandomBillerCode", Globals.autoBillerCode);
        }
        if (strBodyData.contains("AutoRandomSubscriberNumber")) {
            if (Globals.autoSubscriberNumber == null) {
                Globals.autoSubscriberNumber = getNDigitFakeNumber(9);
            }
            strBodyData = strBodyData.replace("AutoRandomSubscriberNumber", Globals.autoSubscriberNumber);
        }
        if (strBodyData.contains("AutoBillerName")) {
            if (Globals.autoBillerName == null) {
                Globals.autoBillerName = "test" + getNDigitFakeNumber(3);
            }
            strBodyData = strBodyData.replace("AutoBillerName", Globals.autoBillerName);
        }
        if (strBodyData.contains("AutoToggleStatus")) {
            strBodyData = strBodyData.replace("AutoToggleStatus", Globals.toggleStatus.toString());
        }

        if (strBodyData.contains("AutoAcceptLanguage")) {
            strBodyData = strBodyData.replace("AutoAcceptLanguage", Globals.acceptLanguage);
        }

        if (strBodyData.contains("AutoSearchText")) {
            strBodyData = strBodyData.replace("AutoSearchText", Globals.searchText);
		}

        if (strBodyData.contains("AutoRandomAccountNumber")) {
            if (Globals.autoAccountNumber == null) {
                Globals.autoAccountNumber = getNDigitFakeNumber(6);
            }
            strBodyData = strBodyData.replace("AutoRandomAccountNumber", Globals.autoAccountNumber);
        }

        if (strBodyData.contains("AutoPaymentAmount")) {
            if (Globals.autoPaymentAmount == null) {
                Globals.autoPaymentAmount = fk.number().numberBetween(1, 999) + "." + getNDigitFakeNumber(2);
            }
            strBodyData = strBodyData.replace("AutoPaymentAmount", Globals.autoPaymentAmount);
        }

        if (strBodyData.contains("AutoPaymentType")) {
            if (Globals.autoPaymentType == null) {
                Globals.autoPaymentType = fk.letterify("????", true);
            }
            strBodyData = strBodyData.replace("AutoPaymentType", Globals.autoPaymentType);
        }

        if (strBodyData.contains("AutoServiceType")) {
            if (Globals.autoServiceType == null) {
                Globals.autoServiceType = fk.letterify("???", true);
            }
            strBodyData = strBodyData.replace("AutoServiceType", Globals.autoServiceType);
        }

        if (strBodyData.contains("AutoOtpType")) {
            strBodyData = strBodyData.replace("AutoOtpType", Globals.autoOtpType);
        }

        if (strBodyData.contains("autoIqamaNumber")) {
            strBodyData = strBodyData.replace("c", 1 + fk.random().nextInt(2) + getNDigitFakeNumber(9));
        }

        // TODO @Charlo: Only service codes 001, 025 and 035 are available at 11/05/2021:
        if (strBodyData.contains("AutoServiceCode")) {
            if (Globals.autoServiceCode == null) {
                int[] availableCodes = new int[] { 1, 25, 35 };
                int randomIndex = new Random().nextInt(availableCodes.length);
                Globals.autoServiceCode = String.format("%03d", availableCodes[randomIndex]);
            }
            strBodyData = strBodyData.replace("AutoServiceCode", Globals.autoServiceCode);
        }

        if (strBodyData.contains("AutoSaudiIdNumber")) {
            strBodyData = strBodyData.replace("AutoSaudiIdNumber", 1 + getNDigitFakeNumber(9));
        }

        if (strBodyData.contains("AutoFeeBillerCode")) {
            if (Globals.autoFeeBillerCode == null || Globals.autoFeeBillerCode.isEmpty()) {
                int[] availableCodes = new int[]{90, 91, 92};
                int randomIndex = new Random().nextInt(availableCodes.length);
                Globals.autoFeeBillerCode = String.format("%03d", availableCodes[randomIndex]);
            }
            if (Globals.autoFeeBillerCode == "BLANK") Globals.autoFeeBillerCode = "";
            strBodyData = strBodyData.replace("AutoFeeBillerCode", Globals.autoFeeBillerCode);
        }

        if (strBodyData.contains("AutoIqamaNumber")) {
            strBodyData = strBodyData.replace("AutoIqamaNumber", Globals.autoIqamaNumber);
        }

        if (strBodyData.contains("AutoBirthDate")) {
            strBodyData = strBodyData.replace("AutoBirthDate", Globals.autoBirthDate);
        }

        if (strBodyData.contains("AutoCustomerId")) {
            if (Globals.autoCustomerId == null) {
                Globals.autoCustomerId = getNDigitFakeNumber(6);
            }
            strBodyData = strBodyData.replace("AutoCustomerId", Globals.autoCustomerId);
        }

        strBodyData = assignValueIfNotNull(strBodyData, "AutoBeneficiaryId", Globals.autoBeneficiaryId, getNDigitFakeNumber(10));

        strBodyData = assignValueIfNotNull(strBodyData, "AutoSadadReferenceNumber", Globals.autoSadadReferenceNumber, getNDigitFakeNumber(10));

        if (strBodyData.contains("AutoMobileNumberForCoreBanking")) {
            strBodyData = strBodyData.replace("AutoMobileNumberForCoreBanking", fk.numerify("9#########"));
        }

        if (strBodyData.contains("AutoEmploymentStatus")) {
            strBodyData = strBodyData.replace("AutoEmploymentStatus", Globals.employmentStatus);
        }

        if (strBodyData.contains("AutoEmployerName")) {
            strBodyData = strBodyData.replace("AutoEmployerName", Globals.autoEmployerName);
        }
        if (strBodyData.contains("AutoBrillioId")) {
            strBodyData = strBodyData.replace("AutoBrillioId", Globals.autoEmployerName);
        }

        strBodyData = assignValueIfNotNull(strBodyData, "AutoFeeInquiryParameters", Globals.autoFeeInquiryParameters, "{\"parameterName\": \"AutoParameterName\",\"parameterValue\": \"AutoParameterValue\"}");
        strBodyData = assignValueIfNotNull(strBodyData, "AutoParameterName", Globals.autoParameterName, getFakeString(10));
        strBodyData = assignValueIfNotNull(strBodyData, "AutoParameterValue", Globals.autoParameterValue, getFakeString(10));

        return strBodyData;
    }

    private static String assignValueIfNotNull(String strBodyData, String autoVariableBody, String globalVariableName, String globalVariableValue) {
        if (strBodyData.contains(autoVariableBody)) {
            if (globalVariableName == null)
                globalVariableName = globalVariableValue;
            if (globalVariableName == "BLANK")
                globalVariableName = "";
            strBodyData = strBodyData.replace(autoVariableBody, globalVariableName);
        }
        return strBodyData;
    }

    private static String getNDigitFakeNumber(int length) {
        return fk.number().digits(length);
    }

    private static String getFakeString(int length) {
        return fk.lorem().fixedString(length);
    }
}
