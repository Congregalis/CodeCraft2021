import java.util.*;

public class Main {

    public static void main(String[] args) {
        DataProcessing dataProcessing = new DataProcessing("data/test.txt");
        Recorder recorder = new Recorder(dataProcessing.getOrderList());
        ScheduleCenter scheduleCenter = new ScheduleCenter(dataProcessing.getServerTypeList(),
                dataProcessing.getVmTypeList());

        for (List<Order> orders : recorder.getOrderList()) {
            scheduleCenter.dailyOperate(orders);
        }
    }
}
