package Model.tasks;

import Model.MemberManager;
import Model.ProductManager;
import Controller.Controller;

import java.io.IOException;
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
    public synchronized void run() {
        productManager.addTestProducts();

        while (true) {
            System.out.println(productManager.size());
            memberManager.addTestMember();

                try {
                    Thread.sleep(400);
                    controller.updateAllItems();
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

    }

}

