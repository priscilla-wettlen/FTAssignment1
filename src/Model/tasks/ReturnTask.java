package Model.tasks;

import Model.*;
import Controller.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ReturnTask implements Runnable {
    private Random random;
    private LoanItemManager loanItemManager;
    private ProductManager productManager;
    private Controller controller;

    public ReturnTask(LoanItemManager loanItemManager, ProductManager productManager, Controller controller) {
        this.loanItemManager = loanItemManager;
        this.productManager = productManager;
        this.controller = controller;
        random = new Random();
    }

    public LoanItem getRandomLoanItem() {
        List<LoanItem> loanedItems = loanItemManager.getLoanedItems();
        if (loanedItems.isEmpty()) return null;
        return loanedItems.get(random.nextInt(loanedItems.size()));
    }


    @Override
    public synchronized void run() {
        while (true) {
            long startTime = System.currentTimeMillis();
            long duration = 5000; // Run for 5 seconds

            while (System.currentTimeMillis() - startTime < duration) {
                try {
                    LoanItem loanItem = getRandomLoanItem();
                    String statusMessage = "";

                    if (loanItem != null && loanItemManager.getLoanedItems() != null) {
                        int loanedItemIndex = Integer.parseInt(loanItem.getProduct().getId());
                        loanItemManager.remove(loanedItemIndex);
                        productManager.add(loanItem.getProduct());

                        statusMessage = "Item with ID " + loanedItemIndex + " returned";
                        System.out.println(statusMessage);
                    }

                    Thread.sleep(8000); // Delay BEFORE updating view, so view and message sync
                    controller.updateAllItems();
                    controller.updateView(statusMessage);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    //System.out.println("ReturnTask was interrupted.");
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                //System.out.println("ReturnTask sleeping for 5 seconds...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }


}

