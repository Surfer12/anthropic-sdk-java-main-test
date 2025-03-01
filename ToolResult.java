/**
 * Interface representing a tool defined in the MCP schema
 */
public interface MCPTool {
    /**
     * Get the name of the tool as defined in MCP
     */
    String getName();

    /**
     * Get the description of the tool
     */
    String getDescription();

    /**
     * Execute the tool with the provided parameters
     * 
     * @param params Map of parameter names to their values
     * @return Result of the tool execution
     */
    ToolResult execute(Map<String, Object> params) throws ToolExecutionException;
}

/**
 * Represents the result of a tool execution
 */
public class ToolResult {
    private final boolean success;
    private final Object data;
    private final String message;

    public ToolResult(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    // Getters and utility methods
}

/**
 * Exception thrown when tool execution fails
 */
public class ToolExecutionException extends Exception {
    public ToolExecutionException(String message) {
        super(message);
    }

    public ToolExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}