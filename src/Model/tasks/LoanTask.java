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
    public synchronized void run() {
        while (true) {
            try {
                List<Product> products = productManager.getProducts();
                if (products.isEmpty()) {
                    Thread.sleep(5000);
                    continue;
                }

                int productIndex = random.nextInt(products.size());
                Product product = productManager.get(productIndex);
                Member member = getRandomMember();

                String statusMessage = "";

                if (product != null && member != null) {
                    boolean success = loanItemManager.add(product, member);
                    if (success) {
                        statusMessage = "Product " + product.getName() + " loaned to member " + member.getName();
                        productManager.remove(productIndex);
                        System.out.println("Removed product at index: " + productIndex);
                    } else {
                        statusMessage = "Product " + product.getName() + " is already loaned.";
                    }
                }

                Thread.sleep(7000);
                controller.updateAllItems();
                controller.updateView(statusMessage);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }



    //    private Product getRandomProduct() {
//        List<Product> products = productManager.getProducts();
//        if (products.isEmpty()) return null;
//        return products.get(random.nextInt(products.size()));
//    }
private int getRandomProductIndex() {
    List<Product> products = productManager.getProducts();
    if (products.isEmpty()) return -1;
    return random.nextInt(products.size());
}

    private Member getRandomMember() {
        List<Member> members = memberManager.getMembers();
        if (members.isEmpty()) return null;
        return members.get(random.nextInt(members.size()));
    }
}
