package Controller;

import Model.LoanItemManager;
import Model.LoanSystem;
import Model.MemberManager;
import Model.ProductManager;
import Model.tasks.AdminTask;
import Model.tasks.UpdateGUI;
import View.*;

import javax.swing.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Controller {
   private MainFrame view;
   private MemberManager memberManager;
   private ProductManager productManager;
   //private LoanSystem loanSystem;  //model
   private LoanItemManager loanItemManager;
   private UpdateGUI updateGUI;
   private AdminTask adminTask;

    //constructor, create view and model
    public Controller() {
        //loanSystem = new LoanSystem();  //model

        view = new MainFrame(this);  //view
        memberManager = new MemberManager();
        productManager = new ProductManager();
        adminTask = new AdminTask(productManager);
        loanItemManager = new LoanItemManager();
        updateGUI = new UpdateGUI(this);

    }



    //This method starts LoanSystem manager where the threads
    //are created and started to perform their Model.managers.tasks.
    //TO DO: Write code for the  case Start to start the loan system simulation
    //       Write code for the case stop to stop the threads
    public void buttonPressed(ButtonType button) throws IOException {

        switch (button) {
            case Start:
                new Thread(() -> {
                    try {
                        Thread adminThread = new Thread(new AdminTask(productManager));
                        adminThread.start();
                        adminThread.join(); // Wait for AdminTask to finish


                        //SwingUtilities.invokeLater(() -> updateGUI.start());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start(); // wrap the whole thing in a new background thread
                break;
            case Stop:
                //loanSystem.stop();

                updateAllItems();
                break;
        }
    }


    //This method prepares a list containing all products on loan item list and all products
    //saved int the product list.  The lists come from the respective manager class,
    //LoanItemManager and ProductManager.  The lists are merged together and
    //the resulting string array is sent to EastPanel via a call to the view, for displaying.

    public void updateAllItems() throws IOException {
        //String[] infoStrings1 = list of products on loan  (LoanItemManager)
        String[] infoStrings2 = this.productManager.getProductInfoStrings();
        System.out.println(infoStrings2.length);

        // String[] infoStrings = combine the above

        //String dateTime = getCurrentDateTime();

        //infoStrings[0] = dateTime;


//        if (infoStrings2 != null)
//           view.updateItemsList(infoStrings2, true);


        //only for testing, delete the code when
        // have tested the UI
//        boolean stop = false;
//        String [] test = new String[10];
//        for (int i=0; i < 10; i++)
//            test[i] = "Item" + i;

        view.updateItemsList(infoStrings2, true);



    }



    //Returns the current date and time to be displayed with updating
    //product and loan item lists.
    public String getCurrentDateTime() {
        java.time.LocalDateTime localDateTime = java.time.LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }

    public void updateView(String stringInfo) {
        view.updateEventLog(stringInfo);
    }

    //This method can be  called from the Central Panel
    //when an item is highlighted in the JList by the user.
    //This method is not used in this assignment. It
    //remains for future use.
    public void productLisIndexChanged(int index) {
        //Take an action on the index.

    }


}
