public class VMType {
    String type;

    int core;

    int mem;

    boolean isMultNode;

    public String getType() {
        return type;
    }

    public VMType(String type, int core, int mem, boolean isMultNode) {
        this.type = type;
        this.core = core;
        this.mem = mem;
        this.isMultNode = isMultNode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public int getMem() {
        return mem;
    }

    public void setMem(int mem) {
        this.mem = mem;
    }

    public boolean isMultNode() {
        return isMultNode;
    }

    public void setMultNode(boolean multNode) {
        isMultNode = multNode;
    }
}
