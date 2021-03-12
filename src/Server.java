import java.util.ArrayList;
import java.util.List;

public class Server {
    private Long id;

    private ServerType type;

    private Node A;

    private Node B;

    private boolean isRunning;

    public Server(Long id, ServerType type) {
        this.id = id;
        this.type = type;
        A = new Node(type.getCore() / 2, type.getMem() / 2);
        B = new Node(type.getCore() / 2, type.getMem() / 2);
        isRunning = false;
    }

    public class Node {
        int core;

        int mem;

        List<VM> runningVM;

        public Node(int core, int mem) {
            this.core = core;
            this.mem = mem;
            runningVM = new ArrayList<>();
        }
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
