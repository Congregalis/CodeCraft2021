import java.util.*;

public class Main {

    public static void main(String[] args) {
        DataProcessing dataProcessing = new DataProcessing("../training-1.txt");
        dataProcessing.process();
        Recorder recorder = new Recorder(dataProcessing.getOrderList());
        ScheduleCenter scheduleCenter = new ScheduleCenter(dataProcessing.getServerTypeList(),
                dataProcessing.getVmTypeList());

        scheduleCenter.initialize(recorder.getOrderList());
        recorder.addPurchaseRecord(scheduleCenter.getPurchaseList());

        int day = 0;
        for (List<Order> orders : recorder.getOrderList()) {
            day++;
            scheduleCenter.dailyOperate(orders);
            recorder.addAllocateRecord(scheduleCenter.getAllocateList());
            if (!(day == 1)) {
                recorder.addPurchaseRecord(new ArrayList<String>() {
                    {
                        add("(" + "purchase, " + 0 + ")");
                    }
                });
            }
            recorder.addMigrationRecord(new ArrayList<String>() {
                {
                    add("(" + "migration, " + 0 + ")");
                }
            });
        }

        List<List<String>> purchaseList = recorder.getPurchaseList();
        List<List<String>> migrationList = recorder.getMigrationList();
        List<List<String>> allocateList = recorder.getAllocateList();
        for (int i = 0; i < recorder.getOrderList().size(); i++) {

            for (String purchase : purchaseList.get((i))) {
                System.out.println(purchase);
            }

            for (String migration : migrationList.get(i)) {
                System.out.println(migration);
            }

            for (String allocate : allocateList.get(i)) {
                System.out.println(allocate);
            }
        }
        System.out.println("总成本为" + scheduleCenter.getCost());
    }
}
