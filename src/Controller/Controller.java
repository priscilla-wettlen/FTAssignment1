package Controller;

import Model.*;
import Model.tasks.AdminTask;
import Model.tasks.LoanTask;
import Model.tasks.ReturnTask;
import Model.tasks.UpdateGUI;
import View.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {
   private MainFrame view;
   private MemberManager memberManager;
   private ProductManager productManager;
   private LoanItemManager loanItemManager;
   private UpdateGUI updateGUI;
   private AdminTask adminTask;
   private LoanTask loanTask;
   private ReturnTask returnTask;
   private Thread adminThread;
   private Thread loanThread;
   private Thread returnThread;

    //constructor, create view and model
    public Controller() {
        //loanSystem = new LoanSystem();  //model
        view = new MainFrame(this);  //view
        memberManager = new MemberManager();
        productManager = new ProductManager();
        loanItemManager = new LoanItemManager();
        adminTask = new AdminTask(productManager, memberManager, this);
        loanTask = new LoanTask(productManager, memberManager, loanItemManager, this);
        returnTask = new ReturnTask(loanItemManager, productManager, this);
        updateGUI = new UpdateGUI(this);

    }


    //This method starts LoanSystem manager where the threads
    //are created and started to perform their Model.managers.tasks.
    //TO DO: Write code for the  case Start to start the loan system simulation
    //       Write code for the case stop to stop the threads
    public void buttonPressed(ButtonType button) throws IOException {

        switch (button) {
            case Start:
                adminThread = new Thread(adminTask);
                adminThread.start();

                loanThread = new Thread(loanTask);
                loanThread.start();

                returnThread = new Thread(returnTask);
                returnThread.start();

                updateAllItems();
                break;
            case Stop:
                updateAllItems();
                adminThread.interrupt();
                loanThread.interrupt();
                returnThread.interrupt();
                break;
        }
    }


    //This method prepares a list containing all products on loan item list and all products
    //saved int the product list.  The lists come from the respective manager class,
    //LoanItemManager and ProductManager.  The lists are merged together and
    //the resulting string array is sent to EastPanel via a call to the view, for displaying.

    public void updateAllItems() throws IOException {
        String[] infoStrings1 = loanItemManager.getLoanInfoStrings();
        String[] infoStrings2 = productManager.getProductInfoStrings();

         String[] infoStrings = new String[infoStrings1.length + infoStrings2.length];
         System.arraycopy(infoStrings1, 0, infoStrings, 0, 2);
         System.arraycopy(infoStrings2, 0, infoStrings, infoStrings1.length, infoStrings2.length);

        String dateTime = getCurrentDateTime();

        infoStrings[0] = dateTime;


        view.updateItemsList(infoStrings, true);

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
