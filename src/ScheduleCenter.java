import java.util.*;

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
                // 添加vm需要做的事： 1.创建新的vm，加入vmList 2.减少该vm占用的server的对应参数

                if (vmTypeMap.get(order.getVmType()).isMultNode) {
                    for (Server server : serverList) {
                        // 遍历server列表，选可以的进行分配
                        if (checkMulti(server, vmTypeMap.get(order.getVmType()))) {
                            allocateMultiNode(server, order.getVmId(), vmTypeMap.get(order.getVmType()));
                        }
                        // 此处还应考虑如果遍历完了都没有可分配的时，怕抛出异常（后面再考虑）
                    }
                } else {
                    for (Server server : serverList) {
                        // 遍历server列表中所有server下的结点，选可以的进行分配
                        if (checkSingle(server, vmTypeMap.get(order.getVmType()), 'A')) {
                            allocateSingleNode(server, 'A', order.getVmId(), vmTypeMap.get(order.getVmType()));
                        } else if (checkSingle(server, vmTypeMap.get(order.getVmType()), 'B')) {
                            allocateSingleNode(server, 'B', order.getVmId(), vmTypeMap.get(order.getVmType()));
                        }
                        // 此处还应考虑如果遍历完了都没有可分配的时，抛出异常（后面再考虑）
                    }
                }

                // 以下写记录，用以输出

            } else {
                // 删除vm需要做的事： 1.找到对应vm，从vmList中将其删除 2.释放对应server上的资源

                Iterator<VM> vmIterator = vmList.iterator();
                while (vmIterator.hasNext()) {
                    // 这里遍历vm的方法后期可以改成创建一个哈希表（key为vmID，value为vm在vmList中的位置）
                    VM vm = vmIterator.next();
                    if (vm.getId().equals(order.getVmId())) {
                        deleteVM(vm);
                        // 从列表中删除
                        vmIterator.remove();
                    }
                }
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

    /**
     * 其中要更改server的剩余性能
     * @param server
     * @param nodeName
     * @return 返回分配成功的虚拟机
     */
    public VM allocateSingleNode(Server server, char nodeName, Long vmId, VMType vmType) {
        VM vm = new VM(vmId, vmType, server, nodeName);

        vmList.add(vm);

        if (nodeName == 'A') {
            server.getA().core -= vmType.getCore();
            server.getA().mem -= vmType.getMem();
        } else {
            server.getB().core -= vmType.getCore();
            server.getB().mem -= vmType.getMem();
        }

        // 添加记录
        allocateList.add("(" + server.getId() + ", " + nodeName + ")");

        return vm;
    }

    public VM allocateMultiNode(Server server, Long vmId, VMType vmType) {
        VM vm = new VM(vmId, vmType, server);

        vmList.add(vm);

        // 修改server中的剩余CPU和内存
        server.getA().core -= vmType.getCore() / 2;
        server.getA().mem -= vmType.getMem() / 2;
        server.getB().core -= vmType.getCore() / 2;
        server.getB().mem -= vmType.getMem() / 2;

        // 添加记录
        allocateList.add("(" + server.getId() + ")");

        return vm;
    }

    public VM deleteVM(VM vm) {
        Server server = vm.getRunningServer();

        // 释放服务器资源
        if (vm.getType().isMultNode()) {
            server.getA().core += vm.getType().getCore() / 2;
            server.getA().mem += vm.getType().getMem() / 2;
            server.getB().core += vm.getType().getCore() / 2;
            server.getB().mem += vm.getType().getMem() / 2;

            // 删除server上对应的vm占用
            server.getA().runningVM.removeIf(tmp -> tmp.getId().equals(vm.getId()));
            server.getB().runningVM.removeIf(tmp -> tmp.getId().equals(vm.getId()));

        } else {
            if (vm.getNodeName() == 'A') {
                server.getA().core += vm.getType().getCore();
                server.getA().mem += vm.getType().getMem();

                server.getA().runningVM.removeIf(tmp -> tmp.getId().equals(vm.getId()));
            } else {
                server.getB().core += vm.getType().getCore();
                server.getB().mem += vm.getType().getMem();

                server.getB().runningVM.removeIf(tmp -> tmp.getId().equals(vm.getId()));
            }
        }

        // 写入记录
        // 删除好像不用输出 Σ( ⚆൧⚆)

        return vm;
    }

    public void clearRecord() {
        purchaseList = new ArrayList<>();
        migrationList = new ArrayList<>();
        allocateList = new ArrayList<>();
    }

    public boolean checkMulti(Server server, VMType vmType) {
        int remainCoreInA = server.getA().core;
        int remainMemInA = server.getA().mem;
        int remainCoreInB = server.getB().core;
        int remainMemInB = server.getB().mem;
        int needCore = vmType.getCore() / 2;
        int needMem = vmType.getMem() / 2;

        return remainCoreInA >= needCore &&
                remainMemInA >= needMem &&
                remainCoreInB >= needCore &&
                remainMemInB >= needMem;
    }

    public boolean checkSingle(Server server, VMType vmType, char nodeName) {
        int needCore = vmType.getCore();
        int needMem = vmType.getMem();
        int remainCore = (nodeName == 'A') ? server.getA().core : server.getB().core;
        int remainMem = (nodeName == 'A') ? server.getA().mem : server.getB().mem;

        return remainCore >= needCore && remainMem >= needMem;
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
