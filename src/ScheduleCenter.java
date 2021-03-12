import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleCenter {
    private List<Server> serverList;

    private List<VM> vmList;

    private List<ServerType> serverTypeList;

    private List<VMType> vmTypeList;

    private Long cost;

    private List<String> purchaseList;

    private List<String> migrationList;

    private List<String> allocateList;

    private Map<String, VMType> vmTypeMap = new HashMap<>();

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
     * baseline策略：第一天就根据所有输入的用户请求一次性买够服务器
     * @param orderList : 所有输入的用户请求
     */
    public void initialize(List<List<Order>> orderList) {
        int needCPU = 0, needMem = 0;

        for (VMType vmType : vmTypeList) {
            vmTypeMap.put(vmType.getType(), vmType);
        }

        for (List<Order> orders : orderList) {
            for (Order order : orders) {
                if (order.isAdd()) {
                    needCPU += vmTypeMap.get(order.getVmType()).getCore();
                    needMem += vmTypeMap.get(order.getVmType()).getMem();

                }
            }
        }

        // 根据add需求一次性购买服务器
        Long count = 0L; // 用来有序赋予id
        while (needCPU > 0 && needMem > 0) {
            serverList.add(new Server(count, serverTypeList.get((0))));
            count++;
            needCPU -= serverTypeList.get(0).getCore();
            needMem -= serverTypeList.get(0).getMem();
        }
        System.out.println("购买了" + serverList.size()  + "个" + serverTypeList.get(0).getType() + "服务器");
    }

    /**
     * 调度中心每天接受请求序列执行调度，期间会根据服务器使用情况用到purchase(), migrate(), allocate()
     */
    public void dailyOperate(List<Order> orders) {
        clearRecord(); // 清除掉上一天的迁移、加购、分配记录数据

        // 迁移


        // 加购


        // 分配
        for (Order order : orders) {
            if (order.isAdd()) {
                if (vmTypeMap.get(order.getVmType()).isMultNode) {

                } else {

                }
            } else {

            }
        }

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
