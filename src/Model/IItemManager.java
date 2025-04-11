package Model;

public interface IItemManager {
    Product get(int index);
    void add();
    void remove(int index);
    int size();
    boolean checkIndex(int index);
    String[] getInfoStrings();
    boolean noItemsAvailable();

}
