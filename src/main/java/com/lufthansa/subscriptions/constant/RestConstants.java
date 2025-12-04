package com.lufthansa.subscriptions.constant;

public interface RestConstants {

    String ROOT = "/api";

    String ID = "id";
    String ID_PATH = "/{" + ID + "}";

    String DEFAULT_PAGE_SIZE = "10";
    String DEFAULT_PAGE_NUMBER = "0";
    String PAGE = "page";
    String SIZE = "size";

    interface CustomerController {
        String BASE_PATH = ROOT + "/customer";
        String CUSTOMER_INVOICE = ID_PATH + "/invoice";
    }

    interface SubscriptionPlanController {
        String BASE_PATH = ROOT + "/subscription-plan";
    }

    interface SubscriptionController {
        String BASE_PATH = ROOT + "/subscription";
    }

    interface InvoiceController {
        String BASE_PATH = ROOT + "/invoice";
        String INVOICE_PAYMENT = ID_PATH + "/payment";
        String INVOICE_ADMIN = "/admin";
    }
}