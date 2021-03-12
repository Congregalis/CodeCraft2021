public class VM {
    Long id;

    VMType type;

    Server runningServer;

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
}
