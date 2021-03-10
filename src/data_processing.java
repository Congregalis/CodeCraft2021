import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class data_processing {
    public static void main(String[] args) {
        String fileName = new String("data/test.txt");
        File file = new File(fileName);

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);

            String line = "";
            line = br.readLine();

            while (line != null) {
                int N = Integer.parseInt(line);
                System.out.println("采购的服务器数量：" + N);
                System.out.println("采购的服务器分别为：");
                for (int i = 0; i < N; i++) {
                    line = br.readLine();
                    processServer(line);
                }

                System.out.println();
                line = br.readLine();
                int M = Integer.parseInt(line);
                System.out.println("待售卖的虚拟机数量：" + M);
                System.out.println("待售卖的虚拟机分别为：");
                for (int i = 0; i < M; i++) {
                    line = br.readLine();
                    processVM(line);
                }

                System.out.println();
                line = br.readLine();
                int T = Integer.parseInt(line);
                System.out.println("用户请求天数：" + T);
                System.out.println("用户请求序列分别为：");
                for (int i = 0; i < T; i++) {
                    System.out.print("第" + i + "天，" );
                    line = br.readLine();
                    int R = Integer.parseInt(line);
                    System.out.println("共有" + R + "条请求，分别为");
                    for (int j = 0; j < R; j++) {
                        line = br.readLine();
                        processReq(line);
                    }
                }
                line = br.readLine(); // 下一个输入
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isStr2Num(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static void processServer(String s) {
        s = s.substring(1, s.length() - 1);
        String[] serverInfo = s.split(", ");
        System.out.println("服务器型号：" + serverInfo[0] +
                "，CPU核数：" + Integer.parseInt(serverInfo[1]) +
                "，内存大小：" + Integer.parseInt(serverInfo[2]) +
                "，硬件成本：" + Integer.parseInt(serverInfo[3]) +
                "，每日能耗成本：" + Integer.parseInt(serverInfo[4]));
    }

    public static void processVM(String s) {
        s = s.substring(1, s.length() - 1);
        String[] vmInfo = s.split(", ");
        System.out.println("虚拟机型号：" + vmInfo[0] +
                "，CPU核数：" + Integer.parseInt(vmInfo[1]) +
                "，内存大小：" + Integer.parseInt(vmInfo[2]) +
                "，是否双节点部署：" + (vmInfo[3].equals("1")));
    }

    public static void processReq(String s) {
        s = s.substring(1, s.length() - 1);

        String[] request = s.split(", ");
        if (request[0].equals("add")) {
            System.out.println("请求创建虚拟机，型号为 " + request[1] + "，ID为 " + request[2]);
        } else if (request[0].equals("del")) {
            System.out.println("请求删除虚拟机，ID为 " + request[1]);
        }
    }

}
