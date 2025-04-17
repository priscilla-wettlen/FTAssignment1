package Model.tasks;

import Model.MemberManager;
import Model.ProductManager;
import Controller.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class AdminTask implements Runnable {
    private ProductManager productManager;
    private MemberManager memberManager;
    Controller controller;
    private Random random;


    public AdminTask(ProductManager productManager, MemberManager memberManager, Controller controller) {
        this.productManager = productManager;
        this.memberManager = memberManager;
        this.controller = controller;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis();
            long duration = 5000; // run for 5 seconds

            // Inner loop runs for 5 seconds
            while (System.currentTimeMillis() - startTime < duration) {
                productManager.addTestProducts();
                memberManager.addTestMember();

                System.out.println(memberManager.getMembers().toString());

                try {
                    int sleepTime = 200 + random.nextInt(1000); // simulate varied work time
                    Thread.sleep(sleepTime);
                    controller.updateAllItems();
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            // Wait for 5 seconds before next 5-second task cycle
            try {
                System.out.println("Pausing for 5 seconds...");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

}
