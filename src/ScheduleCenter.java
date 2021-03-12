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
        clearRecord(); // 清除掉上一天的迁移、加购、分配记录数据

        // 迁移


        // 加购


        // 分配


    }

    public void purchase(ServerType type, int number) {

    }

    /**
     * 单结点部署，需要考虑部署在哪个结点上
     * @param vmId : 虚拟机id
     * @param serverId : 服务器id
     * @param nodeName : 'A' 或者 'B', 表示部署在A或者B结点
     */
    public void migrateSingleNode(Long vmId, Long serverId, char nodeName) {

    }

    public void migrateMultiNode(Long vmId, Long serverId) {

    }

    public void allocateSingleNode(Long serverId, char nodeName) {

    }

    public void allocateMultiNode(Long serverId) {

    }

    public void clearRecord() {
        purchaseList = new ArrayList<>();
        migrationList = new ArrayList<>();
        allocateList = new ArrayList<>();
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
