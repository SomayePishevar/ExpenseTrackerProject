package com.snapp.expensetracker.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValueId;
    private String fieldValueName;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValueId){
        super(String.format("%s not found with %s : %s",resourceName, fieldName, fieldValueId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueId = fieldValueId;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueName){
        super(String.format("%s not found with %s : %s",resourceName, fieldName, fieldValueName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueName = fieldValueName;
    }
}
