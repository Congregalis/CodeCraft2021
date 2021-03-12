import java.util.*;

public class Main {

    public static void main(String[] args) {
        DataProcessing dataProcessing = new DataProcessing("../training-1.txt");
        dataProcessing.process();
        Recorder recorder = new Recorder(dataProcessing.getOrderList());
        ScheduleCenter scheduleCenter = new ScheduleCenter(dataProcessing.getServerTypeList(),
                dataProcessing.getVmTypeList());

        scheduleCenter.initialize(recorder.getOrderList());
        for (List<Order> orders : recorder.getOrderList()) {
            scheduleCenter.dailyOperate(orders);
        }
    }
}
