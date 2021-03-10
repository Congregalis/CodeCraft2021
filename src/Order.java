public class Order {
    boolean isAdd;
    String vmType;
    Long vmId;

    Order(boolean b, String type, Long id) {
        this.isAdd = b;
        this.vmType = type;
        this.vmId = id;
    }

    Order(boolean b, Long id) {
        this.isAdd = b;
        this.vmId = id;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public String getVmType() {
        return vmType;
    }

    public void setVmType(String vmType) {
        this.vmType = vmType;
    }

    public Long getVmId() {
        return vmId;
    }

    public void setVmId(Long vmId) {
        this.vmId = vmId;
    }
}
