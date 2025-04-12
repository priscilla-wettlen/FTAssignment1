package Model.tasks;

import Model.ProductManager;

import java.util.Arrays;

public class AdminTask implements Runnable {
    private ProductManager productManager;

    public AdminTask(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();
        long duration = 2000; // 10 seconds

        while (System.currentTimeMillis() - startTime < duration) {
            productManager.addTestProducts(); // Add one product
            System.out.println(Arrays.toString(productManager.getProductInfoStrings()));
            try {
                Thread.sleep(500); // Wait for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Good practice
                break;
            }
        }
    }
}
