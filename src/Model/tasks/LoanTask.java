package Model.tasks;

import Model.*;
import Controller.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoanTask implements Runnable {
    private ProductManager productManager;
    private MemberManager memberManager;
    private LoanItemManager loanItemManager;
    private Random random = new Random();
    private Controller controller;

    public LoanTask(ProductManager productManager, MemberManager memberManager, LoanItemManager loanItemManager, Controller controller) {
        this.productManager = productManager;
        this.memberManager = memberManager;
        this.loanItemManager = loanItemManager;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis();
            long duration = 5000; // Run for 5 seconds

            while (System.currentTimeMillis() - startTime < duration) {
                try {
                    Product product = getRandomProduct();
                    Member member = getRandomMember();

                    String statusMessage = "";

                    if (product != null && member != null) {
                        boolean success = loanItemManager.add(product, member);
                        if (success) {
                            statusMessage = "Product " + product.getName() + " loaned to member " + member.getName();
                            int prodId = Integer.parseInt(product.getId());
                            productManager.remove(prodId);
                            controller.updateAllItems();
                        } else {
                            statusMessage = "Product " + product.getName() + " is already loaned.";
                        }
                    }

                    int sleepTime = 200 + random.nextInt(4003); // 2000 to 6000 ms
                    Thread.sleep(sleepTime);
                    controller.updateView(statusMessage);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("LoanTask was interrupted.");
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                System.out.println("LoanTask sleeping for 5 seconds...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }


    private Product getRandomProduct() {
        List<Product> products = productManager.getProducts();
        if (products.isEmpty()) return null;
        return products.get(random.nextInt(products.size()));
    }

    private Member getRandomMember() {
        List<Member> members = memberManager.getMembers();
        if (members.isEmpty()) return null;
        return members.get(random.nextInt(members.size()));
    }
}
