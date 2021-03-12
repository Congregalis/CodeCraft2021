import java.util.ArrayList;
import java.util.List;

public class Recorder {

    private List<List<Order>> orderList;

    private List<List<String>> purchaseList;

    private List<List<String>> migrationList;

    private List<List<String>> allocateList;

    public Recorder(List<List<Order>> orderList) {
        this.orderList = orderList;
        purchaseList = new ArrayList<>();
        migrationList = new ArrayList<>();
        allocateList = new ArrayList<>();
    }

    public void addPurchaseRecord(List<String> purchaseRecord) {
        purchaseList.add(purchaseRecord);
    }

    public void addMigrationRecord(List<String> migrationRecord) {
        migrationList.add(migrationRecord);
    }

    public void addAllocateRecord(List<String> allocateRecord) {
        allocateList.add(allocateRecord);
    }

    public List<List<Order>> getOrderList() {
        return orderList;
    }

    public List<List<String>> getPurchaseList() {
        return purchaseList;
    }

    public List<List<String>> getMigrationList() {
        return migrationList;
    }

    public List<List<String>> getAllocateList() {
        return allocateList;
    }
}
