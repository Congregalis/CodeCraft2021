import java.util.List;

public class Server {
    Long id;

    ServerType type;

    Node A;

    Node B;

    boolean isRunning;

    private class Node {
        int core;

        int mem;

        List<VM> runningVM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServerType getType() {
        return type;
    }

    public void setType(ServerType type) {
        this.type = type;
    }

    public Node getA() {
        return A;
    }

    public void setA(Node a) {
        A = a;
    }

    public Node getB() {
        return B;
    }

    public void setB(Node b) {
        B = b;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
