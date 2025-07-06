package org.example.ingstore.utils;

public final class AppConstants {
    private AppConstants() {
        // Prevent instantiation
    }

    // Role related messages
    public static final String ROLE_NOT_FOUND_WITH_ID = "Role not found with id {}";
    public static final String ROLE_CREATED_SUCCESS = "Creating new role with name: {} ";
    public static final String ROLE_DELETED_SUCCESS = "Role with ID: {} deleted successfully";
    public static final String ROLE_NOT_FOUND = "Role not found: %s";

    // Role Response Messages
    public static final String ROLE_CREATE_SUCCESS = "Role created successfully";
    public static final String ROLE_FETCH_ALL_SUCCESS = "All roles fetched successfully";
    public static final String ROLE_FETCH_SUCCESS = "Role fetched successfully";
    public static final String ROLE_UPDATE_SUCCESS = "Role updated successfully";
    public static final String ROLE_DELETE_SUCCESS = "Role deleted successfully";

    public static final String ROLE_BASE_PATH = "/api/roles";
    public static final String GET_BY_ID_PATH = "/{id}";
    public static final String PRODUCT_BASE_PATH = "/api/products";
    public static final String USER_BASE_PATH = "/api/users";
    public static final String CREATE_PATH = "/create";

    // Role constants
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    // Product Response Messages
    public static final String PRODUCTS_FETCH_SUCCESS = "Products fetched successfully";
    public static final String PRODUCT_FETCH_SUCCESS = "Product fetched successfully";
    public static final String PRODUCT_CREATE_SUCCESS = "Product created successfully";
    public static final String PRODUCT_UPDATE_SUCCESS = "Product updated successfully";
    public static final String PRODUCT_DELETE_SUCCESS = "Product deleted successfully";
    public static final String PRODUCT_NOT_FOUND_WITH_ID = "Product not found with id ";

    // Error Messages for JWT validation
    public static final String ERROR_INVALID_JWT = "Invalid JWT token: {}";
    public static final String ERROR_EXPIRED_JWT = "JWT token is expired: {}";
    public static final String ERROR_UNSUPPORTED_JWT = "JWT token is unsupported: {}";
    public static final String ERROR_INVALID_SIGNATURE = "Invalid JWT signature: {}";
    public static final String ERROR_EMPTY_CLAIMS = "JWT claims string is empty: {}";

    // User Response Messages
    public static final String USER_CREATE_SUCCESS = "User created successfully";
    public static final String USER_FETCH_SUCCESS = "User fetched successfully";
    public static final String USERS_FETCH_ALL_SUCCESS = "All users fetched successfully";
    public static final String USER_UPDATE_SUCCESS = "User updated successfully";
    public static final String USER_DELETE_SUCCESS = "User deleted successfully";

    // HTTP Status Codes
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_CREATED = 201;
    public static final int HTTP_STATUS_NO_CONTENT = 204;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;

    // Security
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int BEARER_TOKEN_START_INDEX = 7;
    public static final int JWT_EXPIRATION_MS = 86400000; // 1 day in milliseconds
    public static final String ADMIN_PASSWORD_KEY = "${admin.password}";
    public static final String JWT_SECRET_KEY = "${jwt.secret}";

    // User constants
    public static final String ADMIN_USERNAME = "Samuel-Catalin";
    public static final String ADMIN_EMAIL = "m.samuelcatalin@gmail.com";
}

