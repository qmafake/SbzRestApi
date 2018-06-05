package com.isorest.domain;

public interface StewardBankConstants {

    public interface MNO {

        final String ECONET = "ECONET";
        final String TELECEL = "TELECEL";
        final String NETONE = "NETONE";
        final String TXNOFS = "TXN OFS MESSAGE ";
        final String PLOFS = "PL OFS MESSAGE ";
    }

    public interface MNO_AIRTIME_MATHS {

        final String ECONET_TXN = "0.95";
        final String ECONET_CHG = "0.05";
        final String TELECEL_TXN = "0.8877";
        final String TELECEL_CHG = "0.1123";
        final String NETONE_TXN = "0.90";
        final String NETONE_CHG = "0.10";
        final String LOGGER_VALUE = "Calculated Amount is equal to ";

    }

    public interface FIELD {

        final String VERSIONECONET = "FUNDS.TRANSFER,ECONETTXN/I/PROCESS,";
        final String VERSIONTELECEL = "FUNDS.TRANSFER,TELECELTXN/I/PROCESS,";
        final String VERSIONNETONE = "FUNDS.TRANSFER,NETONETXN/I/PROCESS,";
        final String VERSIONECONETCHG = "FUNDS.TRANSFER,ECONETCHG/I/PROCESS,";
        final String USERNAME = "ARCMOBILE/";
        final String PASSWORD = "123456&&7/";
        final String BRANCH = "ZW0010001,,";
        final String DEBITACCOUNT = "DEBIT.ACCT.NO=";
        final String DEBITCURRENCY = ",DEBIT.CURRENCY=";
        final String DEBITAMOUNT = ",DEBIT.AMOUNT=";
        final String SWIFTCODE = ",ACCT.WITH.BANK=";
        final String BENEFICIARYNAME = ",BENEF.NAME=";
        final String CREDITREFERENCE = ",CREDIT.THEIR.REF=";
        final String BENEFICIARYACCOUNT = ",BEN.ACCT.NO=";
        final String BENEFICIARYCUSTOMER = ",BEN.CUSTOMER=";
        final String PAYMENTDETAILS = ",PAYMENT.DETAILS=";
        final String DEBITREFERENCE = ",DEBIT.THEIR.REF=";
    }

    interface T24 {

        final String CBS_TCSERVER_IP = "192.168.0.21";
        final int CBS_TCSERVER_PORT = 7099;
        final int CBS_TXN_TIMEOUT = 30;
        final String FORMAT_CBS_RESPONSE = "//1,";
        final int RECORD_ID = 0;
    }

    interface LOGGER {

        final String DUPLICATE = "Duplicate Request";
        final String SUCCESS = "Successful Request";
        final String FAILED = "Failed Request";
        final String EXCEPTION = "There is an Exception";
        final String MESSAGE = "Value retrieved is ";
    }

    interface ApiResponse {

        final String ACCOUNT_EXISTS = "1";
        final String INVALID_CARDNUMBER = "2";
        final String OTP_SENT = "3";
        final String INVALID_PHONENUMBER = "4";
        final String INVALID_OTP = "5";
        final String ACCOUNT_CREATED = "6";
        final String IMEI_MISMATCH = "7";
        final String LOGIN_SUCCESS = "8";
        final String SERVICE_UNAVAILABLE = "9";
        final String INSUFFICIENT_FUNDS = "10";
        final String INVALID_LOGIN_DETAILS = "78";
        final String SUCCESS = "SUCCESS";
        final String FAILED = "FAILED";
    }

    interface InboundRequests {

        final String POSTILLION_REGISTRATION = "Sending credentials to Postillion for registration";
        final String ACCOUNT_ACTIVATION = "Account Activation";
        final String LOGIN = "Login";
        final String BALANCE_ENQUIRY = "Balance Enquiry";
        final String INTERNAL_TRANSFER = "Internal Transfer";
        final String RTGS = "RTGS";
        final String ECOCASH_REGISTRATION = "Ecocash Registration";
        final String MINISTATEMENT = "Ministatement Request";
        final String AUTHORIZE_BILL = "Authorize Bill";
        final String PAY_BILL = "Bill Payment";
        final String AIRTIME = "Airtime Purchase";
    }

    interface Patterns {

        final String SMS_REGEX = "\\d{9,12}";
    }

    interface StatusCodes {

        final String SUCCESS = "200";
    }

    interface Messages {

        final String SUCCESS = "SUCCESS";
        final String FAILED = "FAILED";
        final String OK = "SUCCESS";
        final String SERVICE_UNAVAILABLE = "Service Unavailable";
        final String TRANSACTION_FAILED = "Transaction Failed";
        final String IMEI_MISMATCH = "IMEI Mismatch";
        final String SUCCESSFUL_LOGIN = "Welcome to Square";
        final String ACCOUNT_EXISTS = "You are already registered";
        final String INVALID_DETAILS = "There is a mismatch in the details you supplied";
        final String OTP_SENT = "An OTP was sent to your mobile";
        final String INVALID_PHONENUMBER = "Your account does not have a valid phonenumber please visit the branch to update";
        final String INVALID_OTP = "The OTP you supplied is invalid";
        final String ACCOUNT_CREATED = "Account Successfully Created";
        final String INSUFFICIENT_FUNDS = "Insuficcient funds";
        final String INVALID_LOGIN_CREDENTIALS = "Invalid Details";
    }

    interface Generics {

        final String ACTIVE = "Active";
    }

    interface ZesaKeys {

        final String ARREARS_OUTSTANDING_BALANCE = "ArrearsOutstandingBalance";
        final String DATE_OF_TRANSACTION = "ProviderOriginalDateSubmitted";
        final String TOKEN_MERCHANT_RECEIPT_NUMBER = "TokenMerchantReceiptNr";
        final String TOKEN_VOUCHER = "TokenVoucher";
        final String DETAILS = "PromptVerificationData";
        final String ZESA_CODE = "ZETDC_PPE";
        final String TOKEN_UNITS = "TokenUnits";
        final String TOKEN_RATE = "TokenRate";
        final String AMOUNT = "TokenAmount";
        final String ARREARS_AMOUNT = "ArrearsAmount";
        final String ARREARS_OUTSTANDING = "ArrearsAmount";
    }

    interface KweseKeys {

        final String CONFIRM_DATA = "ConfirmData";
        final String DISPLAY_NAME = "ProviderDisplayName";
        final String KWESE_CODE = "KWESETV";
    }

    interface AirtimeKeys {

        final String ReceiptData_SMS = "ReceiptData.SMS";
    }

    interface GenericKeys {

        final String CONFIRM_DATA = "ConfirmData";
        final String DISPLAY_NAME = "ProviderDisplayName";
        final String GENERIC_CODE = "KWESETV";
    }

    interface TeloneKeys {

        final String CONFIRM_DATA = "ASResponseDesc";
        final String DISPLAY_NAME = "ProviderDisplayName";
        final String TELONE_CODE = "TELONE";
    }

    interface MasvingoKeys {

        final String CONFIRM_DATA = "ASResponseDesc";
        final String DISPLAY_NAME = "ProviderDisplayName";
        final String MASVINGO_CODE = "COMASVINGO";
    }

    interface HarareKeys {

        final String CONFIRM_DATA = "ASResponseDesc";
        final String DISPLAY_NAME = "ProviderDisplayName";
        final String HARARE_CODE = "COHONLINE";

    }

    interface ZolKeys {

        final String ZOL_CODE = "ZOL";
        final String CUSTOMER_NAME = "ZolCustomerName";
    }

    interface SPCAKeys {

        final String CONFIRM_DATA = "ASResponseDesc";
        final String DISPLAY_NAME = "ProviderDisplayName";
        final String SPCA_CODE = "SPCA";

    }

    interface SCHOOLKeys {

        final String PETERHOUSE = "PETERHOUSE";

    }

    interface BARWICKKeys {

        final String BARWICK_CODE = "BARWICK";
    }

    interface Message {

        final String OTP_MESSAGE = "Thank you for subscribing to square mobile application your OTP is ";
        final String IMEI_MISMATCH_MESSAGE = "We have discovered that you are trying to login from a diffrerent device than the one you used to register.If this is not you it is advisable that you change your password However to make this your new device Your OTP is ";
        final String RTGS_MESSAGE = "Steward Bank has processed an RTGS  on your behalf expect the funds soon ";

    }

    interface Postillion {

        final String SUCCESSFUL = "successfull";
        final String USER_EXISTS = "00";
        final String INVALID_PHONE = "Invalid phone number";
    }

    interface PostillionMessages {

        final String SuccessfulTransaction = "00";
        //	final String REFER
    }

    interface EDGARSKeys {

        final String EDGARS_CODE = "EDGARS";

    }
}
