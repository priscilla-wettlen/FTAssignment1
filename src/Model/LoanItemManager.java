package Model;

import java.util.ArrayList;
import java.util.List;

public class LoanItemManager {
    private final List<LoanItem> loanItems = new ArrayList<>();

    public LoanItem get(int index) {
        return checkIndex(index) ? loanItems.get(index) : null;
    }

    public boolean add(Product product, Member member) {
        if (!isProductAlreadyLoaned(product)) {
            loanItems.add(new LoanItem(product, member));
            return true;
        }
        return false;
    }

    public void remove(int index) {
        if (checkIndex(index)) {
            loanItems.remove(index);
        }
    }

    protected int size() {
        return loanItems.size();
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < loanItems.size();
    }

    public String[] getLoanInfoStrings() {
        if (loanItems.isEmpty()) {
            return new String[] { "Products on loan: ", " " };
        }

        String[] infoStrings = new String[loanItems.size() + 3];
        //infoStrings[1] = String.format("Number of products on loan: %s", loanItems.size());


        int j = 2;
        for (LoanItem loanItem : loanItems) {
            infoStrings[j++] = loanItem.toString();
        }

        infoStrings[infoStrings.length - 1] = "";
        return infoStrings;
    }

    public boolean noProductsAvailable() {
        return loanItems.isEmpty();
    }

    public boolean isProductAlreadyLoaned(Product product) {
        for (LoanItem loan : loanItems) {
            if (loan.getProduct().getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    public List<LoanItem> getLoanedItems() {
        return loanItems;
    }
}
