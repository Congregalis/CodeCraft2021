public class VM {
    private Long id;

    private VMType type;

    private Server runningServer;

    private char nodeName;

    public VM(Long id, VMType type, Server runningServer) {
        this.id = id;
        this.type = type;
        this.runningServer = runningServer;
    }

    public VM(Long id, VMType type, Server runningServer, char name) {
        this.id = id;
        this.type = type;
        this.runningServer = runningServer;
        this.nodeName = name;
    }

    public void deploy(Server server) {
        this.runningServer = server;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VMType getType() {
        return type;
    }

    public void setType(VMType type) {
        this.type = type;
    }

    public Server getRunningServer() {
        return runningServer;
    }

    public void setRunningServer(Server runningServer) {
        this.runningServer = runningServer;
    }

    public char getNodeName() {
        return nodeName;
    }

    public void setNodeName(char nodeName) {
        this.nodeName = nodeName;
    }
}
