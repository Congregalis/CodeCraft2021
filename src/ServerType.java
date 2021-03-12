public class ServerType {
    private String type;

    private int core;

    private int mem;

    private int hardwareCost;

    private int energyCost;

    public String getType() {
        return type;
    }

    public ServerType(String type, int core, int mem, int hardwareCost, int energyCost) {
        this.type = type;
        this.core = core;
        this.mem = mem;
        this.hardwareCost = hardwareCost;
        this.energyCost = energyCost;
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

    public int getHardwareCost() {
        return hardwareCost;
    }

    public void setHardwareCost(int hardwareCost) {
        this.hardwareCost = hardwareCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }
}
