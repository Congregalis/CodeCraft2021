import java.util.*;

public class Main {

    public static void main(String[] args) {
        DataProcessing dataProcessing = new DataProcessing("data/test.txt");
        dataProcessing.process();
        Recorder recorder = new Recorder(dataProcessing.getOrderList());
        ScheduleCenter scheduleCenter = new ScheduleCenter(dataProcessing.getServerTypeList(),
                dataProcessing.getVmTypeList());

        scheduleCenter.initialize(recorder.getOrderList());

        int day = 0;
        for (List<Order> orders : recorder.getOrderList()) {
            day++;
            scheduleCenter.dailyOperate(orders);
            System.out.println("第" + day + "天" + scheduleCenter.getAllocateList());
        }
    }
}
