import java.util.List;

public class Recorder {

    private final List<List<Order>> orderList;

    private List<List<String>> purchaseList;

    private List<List<String>> migrationList;

    private List<List<String>> allocateList;

    public Recorder(List<List<Order>> orderList) {
        this.orderList = orderList;
    }

    public void addPurchaseRecord(List<String> purchaseRecord) {
        purchaseList.add(purchaseRecord);
    }

    public void addMigrationRecord(List<String> migrationRecord) {
        purchaseList.add(migrationRecord);
    }

    public void addAllocateRecord(List<String> allocateRecord) {
        purchaseList.add(allocateRecord);
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
