package utils;

public class Constants {
    private Constants() {}

    // Error messages - Login
    public static final String ERROR_USERNAME_REQUIRED = "Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Password is required";
    public static final String ERROR_INVALID_CREDENTIALS = "Provided credentials do not match any user in this service.";
    
    // Error messages - Configuration
    public static final String ERROR_PLATFORM_NULL = "Platform value cannot be null or empty";
    public static final String ERROR_PLATFORM_INVALID = "Invalid platform value: %s. Expected: android or ios";
    public static final String ERROR_ENVIRONMENT_INVALID = "Invalid environment value: %s. Expected: true/false or local/cloud";
    public static final String ERROR_LOADING_CONFIG = "Error loading config.properties from project root: %s";
    public static final String ERROR_ACCESSING_CONFIG = "Error accessing config.properties: %s";
    
    // Data Generation
    public static final int DEFAULT_PASSWORD_MIN_LENGTH = 18;
    public static final int DEFAULT_PASSWORD_MAX_LENGTH = 20;
    public static final boolean INCLUDE_UPPERCASE = true;
    public static final boolean INCLUDE_SPECIAL_CHARS = true;
    public static final boolean INCLUDE_DIGITS = true;

    // Product names
    public static final String PRODUCT_BACKPACK = "Sauce Labs Backpack";
    public static final String PRODUCT_TSHIRT = "Test.allTheThings() T-Shirt";
    public static final String PRODUCT_ONESIE = "Sauce Labs Onesie";
    public static final String PRODUCT_JACKET = "Sauce Labs Fleece Jacket";

    // Assertion messages
    public static final String ASSERT_FIRST_PRODUCT_AFTER_SORTING = "First product name after sorting should be: ";
    public static final String ASSERT_PRODUCT_IN_CART = "Product in cart should be: ";
    public static final String ASSERT_ELEMENT_VISIBLE = "Element should be visible: ";
    public static final String ASSERT_ELEMENT_NOT_VISIBLE = "Element should not be visible: ";
    public static final String ASSERT_ELEMENT_EXISTS = "Element should exist: ";
    public static final String ASSERT_VALUE_NULL = "Element value is null";
    public static final String ASSERT_VALUE_CONTAINS = "Element value should contain '%s', but was: %s";
    public static final String ASSERT_LABEL_NULL = "Element label is null";
    public static final String ASSERT_LABEL_CONTAINS = "Element label should contain '%s', but was: %s";
}

