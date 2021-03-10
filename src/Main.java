import java.util.*;

public class Main {
    private Long cost = 0L; // 成本
    private Set<String> running_server = new HashSet<>(); // 使用中的主机 （开关机的主机是否都需要记录？）
    private Map<String, int[]> own_server = new HashMap<>(); // 记录已购主机的资源情况
    private Map<String, String> vm_to_server = new HashMap<>(); // 记录哪台虚拟机在哪台服务器上

    public static void main(String[] args) {
        DataProcessing dp = new DataProcessing("data/test.txt");
        Map<String, int[]> server_info; // 可购服务器类型表
        Map<String, int[]> vm_info; // 可创建虚拟机类型表
        List<List<Order>> order_list;

        dp.process();
        order_list = dp.getOrderList();

        System.out.println(order_list.get(2).get(0).isAdd());
    }
}
