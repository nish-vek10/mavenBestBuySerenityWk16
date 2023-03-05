package com.bestbuy.constants;

public class EndPoints {

    /**
     * This is the Endpoints of products API
     */
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String CREATE_NEW_PRODUCT = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productsID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productsID}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productsID}";

    /**
     * This is the Endpoints of categories API
     */
    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String CREATE_NEW_CATEGORIES = "/categories";
    public static final String GET_SINGLE_CATEGORY_BY_ID = "/categories/{categoriesID}";
    public static final String UPDATE_CATEGORY_BY_ID = "/categories/{categoriesID}";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{categoriesID}";

    /**
     * This is the Endpoints of stores API
     */
    public static final String GET_ALL_STORES = "/stores";
    public static final String CREATE_NEW_STORES = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storesID}";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storesID}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storesID}";

    /**
     * This is the Endpoints of services API
     */
    public static final String GET_ALL_SERVICES = "/services";
    public static final String CREATE_NEW_SERVICES = "/services";
    public static final String GET_SINGLE_SERVICE_BY_ID = "/services/{servicesID}";
    public static final String UPDATE_SERVICE_BY_ID = "/services/{servicesID}";
    public static final String DELETE_SERVICE_BY_ID = "/services/{servicesID}";

}
