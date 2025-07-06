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

    public static final String PRODUCT_NOT_FOUND_WITH_ID = "Product not found with id ";
}
