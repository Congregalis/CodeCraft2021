import java.util.ArrayList;
import java.util.List;

public class ScheduleCenter {
    private List<Server> serverList;

    private List<VM> vmList;

    private List<ServerType> serverTypeList;

    private List<VMType> vmTypeList;

    private Long cost;

    private List<String> purchaseList;

    private List<String> migrationList;

    private List<String> allocateList;

    public ScheduleCenter(List<ServerType> serverTypeList, List<VMType> vmTypeList) {
        this.serverTypeList = serverTypeList;
        this.vmTypeList = vmTypeList;
        serverList = new ArrayList<>();
        vmList = new ArrayList<>();
        purchaseList = new ArrayList<>();
        migrationList = new ArrayList<>();
        allocateList = new ArrayList<>();
        cost = 0L;
    }

    /**
     * 调度中心每天接受请求序列执行调度，期间会根据服务器使用情况用到purchase(), migrate(), allocate()
     */
    public void dailyOperate(List<Order> orders) {
        purchaseList = new ArrayList<>();
        migrationList = new ArrayList<>();
        allocateList = new ArrayList<>();


    }

    public void purchase() {

    }

    public void migrate() {

    }

    public void allocate() {

    }

    public List<String> getPurchaseList() {
        return purchaseList;
    }

    public List<String> getMigrationList() {
        return migrationList;
    }

    public List<String> getAllocateList() {
        return allocateList;
    }

    public Long getCost() {
        return cost;
    }
}
