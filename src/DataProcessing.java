import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessing {

    private Map<String, int[]> server_info; // 可购服务器类型表
    private Map<String, int[]> vm_info; // 可创建虚拟机类型表
    List<List<Order>> order_list; // 用户命令序列 （两个维度 天/条）
    private String fileName;

    DataProcessing(String name) {
        server_info = new HashMap<>();
        vm_info = new HashMap<>();
        order_list = new ArrayList<>();
        this.fileName = name;
    }

    public void process() {
        try {
            File file = new File(fileName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);

            String line;
            line = br.readLine();

            while (line != null) {
                int N = Integer.parseInt(line); // 可采购的服务器类型数量
//                System.out.println("可采购的服务器类型数量：" + N);
//                System.out.println("可采购的服务器分别为：");
                for (int i = 0; i < N; i++) {
                    line = br.readLine();
                    processServer(line);
                }

                line = br.readLine();
                int M = Integer.parseInt(line); // 待售卖的虚拟机类型数量
//                System.out.println("待售卖的虚拟机类型数量：" + M);
//                System.out.println("待售卖的虚拟机分别为：");
                for (int i = 0; i < M; i++) {
                    line = br.readLine();
                    processVM(line);
                }

                line = br.readLine();
                int T = Integer.parseInt(line);
//                System.out.println("用户请求天数：" + T);
//                System.out.println("用户请求序列分别为：");
                for (int i = 0; i < T; i++) {
//                    System.out.print("第" + i + "天，" );
                    List<Order> list = new ArrayList<>();
                    line = br.readLine();
                    int R = Integer.parseInt(line);
//                    System.out.println("共有" + R + "条请求，分别为");
                    for (int j = 0; j < R; j++) {
                        line = br.readLine();
                        list.add(processReq(line));
                    }
                    order_list.add(list);
                }
                line = br.readLine(); // 下一个输入
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean isStr2Num(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public void processServer(String s) {
        s = s.substring(1, s.length() - 1);
        String[] serverInfo = s.split(", ");
        server_info.put(serverInfo[0], new int[]{Integer.parseInt(serverInfo[1]),
                Integer.parseInt(serverInfo[2]),
                Integer.parseInt(serverInfo[3]),
                Integer.parseInt(serverInfo[4])});
//        System.out.println(\"服务器型号：\" + serverInfo[0] +\n" +
//                "                \"，CPU核数：\" + Integer.parseInt(serverInfo[1]) +\n" +
//                "                \"，内存大小：\" + Integer.parseInt(serverInfo[2]) +\n" +
//                "                \"，硬件成本：\" + Integer.parseInt(serverInfo[3]) +\n" +
//                "                \"，每日能耗成本：" + Integer.parseInt(serverInfo[4]));
    }

    public void processVM(String s) {
        s = s.substring(1, s.length() - 1);
        String[] vmInfo = s.split(", ");
        vm_info.put(vmInfo[0], new int[]{Integer.parseInt(vmInfo[1]),
                Integer.parseInt(vmInfo[2]),
                Integer.parseInt(vmInfo[3])});
//        System.out.println("虚拟机型号：" + vmInfo[0] +
//                "，CPU核数：" + Integer.parseInt(vmInfo[1]) +
//                "，内存大小：" + Integer.parseInt(vmInfo[2]) +
//                "，是否双节点部署：" + (vmInfo[3].equals("1")));
    }

    public Order processReq(String s) {
        Order order;
        s = s.substring(1, s.length() - 1);
        String[] request = s.split(", ");

        if (request[0].equals("add")) {
            order = new Order(true, request[1], Long.parseLong(request[2]));
        } else {
            order = new Order(false, Long.parseLong(request[1]));
        }

        return order;
    }

    public Map<String, int[]> getServerInfo() {
        return server_info;
    }

    public Map<String, int[]> getVminfo() {
        return vm_info;
    }

    public List<List<Order>> getOrderList() {
        return order_list;
    }

}
