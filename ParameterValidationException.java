/**
 * Represents a parameter definition from the MCP schema
 */
public class MCPParameter {
    private final String name;
    private final String type;
    private final String description;
    private final boolean required;
    private final Object defaultValue;

    public MCPParameter(String name, String type, String description, boolean required, Object defaultValue) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.required = required;
        this.defaultValue = defaultValue;
    }

    /**
     * Validates that a parameter value matches this definition
     */
    public void validate(Object value) throws ParameterValidationException {
        if (value == null) {
            if (required) {
                throw new ParameterValidationException("Parameter '" + name + "' is required");
            }
            return;
        }

        // Type validation based on the parameter type
        switch (type) {
            case "string":
                if (!(value instanceof String)) {
                    throw new ParameterValidationException("Parameter '" + name + "' must be a string");
                }
                break;
            case "integer":
                if (!(value instanceof Integer) && !(value instanceof Long)) {
                    throw new ParameterValidationException("Parameter '" + name + "' must be an integer");
                }
                break;
            case "number":
                if (!(value instanceof Number)) {
                    throw new ParameterValidationException("Parameter '" + name + "' must be a number");
                }
                break;
            case "boolean":
                if (!(value instanceof Boolean)) {
                    throw new ParameterValidationException("Parameter '" + name + "' must be a boolean");
                }
                break;
            case "array":
                if (!(value instanceof List)) {
                    throw new ParameterValidationException("Parameter '" + name + "' must be an array");
                }
                break;
            case "object":
                if (!(value instanceof Map)) {
                    throw new ParameterValidationException("Parameter '" + name + "' must be an object");
                }
                break;
            default:
                throw new ParameterValidationException("Unknown parameter type: " + type);
        }
    }

    // Getters
}

/**
 * Exception thrown when parameter validation fails
 */
public class ParameterValidationException extends Exception {
    public ParameterValidationException(String message) {
        super(message);
    }
}