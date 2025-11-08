package ru.itis.tutorhub.models;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private boolean valid;
    private Map<String, String> errors;
    private Map<String, String> fieldValues;

    public ValidationResult() {
        this.valid = true;
        this.errors = new HashMap<>();
        this.fieldValues = new HashMap<>();
    }

    public void addError(String field, String message) {
        this.errors.put(field, message);
        this.valid = false;
    }

    public void addFieldValue(String field, String value) {
        this.fieldValues.put(field, value != null ? value.trim() : "");
    }

    public boolean isValid() {
        return valid;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Map<String, String> getFieldValues() {
        return fieldValues;
    }

    public String getFirstError() {
        return errors.isEmpty() ? null : errors.values().iterator().next();
    }
}