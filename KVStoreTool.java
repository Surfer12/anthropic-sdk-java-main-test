/**
 * Abstract base class for MCP tool implementations
 */
public abstract class AbstractMCPTool implements MCPTool {
    private final String name;
    private final String description;

    protected AbstractMCPTool(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Logs the execution of a tool with its parameters
     */
    protected void logExecution(Map<String, Object> params) {
        System.out.println("Executing tool: " + getName());
        System.out.println("Parameters:");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

/**
 * Abstract base class for KV store tools
 */
public abstract class KVStoreTool extends AbstractMCPTool {
    protected KVStoreTool(String name, String description) {
        super(name, description);
    }

    /**
     * Gets a KV namespace ID from parameters
     */
    protected String getNamespaceId(Map<String, Object> params) {
        return (String) params.get("namespaceId");
    }

    /**
     * Gets a key from parameters
     */
    protected String getKey(Map<String, Object> params) {
        return (String) params.get("key");
    }
}

/**
 * Abstract base class for R2 bucket tools
 */
public abstract class R2BucketTool extends AbstractMCPTool {
    protected R2BucketTool(String name, String description) {
        super(name, description);
    }

    /**
     * Gets a bucket name from parameters
     */
    protected String getBucketName(Map<String, Object> params) {
        return (String) params.get("bucket");
    }
}

/**
 * Abstract base class for Claude AI tools
 */
public abstract class ClaudeTool extends AbstractMCPTool {
    protected ClaudeTool(String name, String description) {
        super(name, description);
    }

    /**
     * Gets the model name from parameters
     */
    protected String getModel(Map<String, Object> params) {
        return (String) params.getOrDefault("model", "claude-3-sonnet-20240229");
    }
}