import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Object-oriented implementation of the Tool Runner CLI.
 * 
 * This class provides the entry point for the tool runner application,
 * registering tools from the ToolRegistry and executing them based on
 * command-line arguments.
 * 
 * Usage:
 * java ToolRunnerOOP <tool_name> [parameters...]
 * 
 * Example:
 * java ToolRunnerOOP kv_get myNamespace myKey
 */
public class ToolRunnerOOP {

    private final Map<String, ToolRegistry.Tool> toolMap = new HashMap<>();

    /**
     * Initializes the tool runner with a set of registered tools
     */
    public ToolRunnerOOP() {
        // Register KV tools
        registerTool(new ToolRegistry.KVGetTool());
        registerTool(new ToolRegistry.KVPutTool());
        registerTool(new ToolRegistry.KVListTool());
        registerTool(new ToolRegistry.KVDeleteTool());

        // Register R2 tools
        registerTool(new ToolRegistry.R2ListBucketsTool());
        registerTool(new ToolRegistry.R2GetObjectTool());

        // Register Claude tools
        registerTool(new ToolRegistry.ClaudeCompletionTool());

        // Additional tools would be registered here
    }

    /**
     * Registers a tool in the tool map
     */
    private void registerTool(ToolRegistry.Tool tool) {
        toolMap.put(tool.getName(), tool);
    }

    /**
     * Executes a tool by name with the provided arguments
     */
    public void executeTool(String toolName, String[] args) {
        if (!toolMap.containsKey(toolName)) {
            System.err.printf("Tool '%s' not recognized.%n", toolName);
            System.exit(1);
        }

        toolMap.get(toolName).execute(args);
    }

    /**
     * Displays the list of available tools
     */
    public void listTools() {
        System.out.println("Available tools:");

        toolMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.printf("  %s - %s%n",
                            entry.getKey(), entry.getValue().getUsage());
                });
    }

    /**
     * Main entry point for the application
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java ToolRunnerOOP <tool_name> [parameters...]");
            System.err.println("       java ToolRunnerOOP --list  (to list available tools)");
            System.exit(1);
        }

        ToolRunnerOOP runner = new ToolRunnerOOP();

        if (args[0].equals("--list")) {
            runner.listTools();
            return;
        }

        String toolName = args[0];
        String[] toolArgs = Arrays.copyOfRange(args, 1, args.length);

        runner.executeTool(toolName, toolArgs);
    }
}