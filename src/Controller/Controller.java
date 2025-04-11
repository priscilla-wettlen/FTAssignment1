package Controller;

import Model.LoanItemManager;
import Model.LoanSystem;
import Model.MemberManager;
import Model.ProductManager;
import View.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Controller {
   private MainFrame view;
   private MemberManager memberManager;
   private ProductManager productManager;
   //private LoanSystem loanSystem;  //model
   private LoanItemManager loanItemManager;

    //constructor, create view and model
    public Controller() {
        //loanSystem = new LoanSystem();  //model
        view = new MainFrame(this);  //view
        memberManager = new MemberManager();
        productManager = new ProductManager();
        loanItemManager = new LoanItemManager();

    }



    //This method starts LoanSystem manager where the threads
    //are created and started to perform their Model.managers.tasks.
    //TO DO: Write code for the  case Start to start the loan system simulation
    //       Write code for the case stop to stop the threads
    public void buttonPressed(ButtonType button) throws IOException {

        switch (button) {
            case Start:
                try {
                    /*
                    * It's adding products starting with id 115 and then
                    * printing the size of the array list, which
                    * reflects the number of added products
                    * */
                    System.out.println(productManager.addNewTestProduct());
                    System.out.println(productManager.size());
                    System.out.println(Arrays.toString(productManager.getProductInfoStrings()));

                    System.out.println(memberManager.addNewTestMember());
                    System.out.println(memberManager.size());
                    System.out.println(Arrays.toString(memberManager.getMemberInfoStrings()));
                    


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
        String[] infoStrings2 = productManager.getProductInfoStrings();
        //String[] memberInfoStrings = memberManager.readMember();
        //String[] productInfoStrings = productManager.readProduct();

        // String[] infoStrings = combine the above

        //String dateTime = getCurrentDateTime();

        //infoStrings[0] = dateTime;


//        if (productInfoStrings != null)
//           view.updateItemsList(productInfoStrings, true);


        //only for testing, delete the code when
        // have tested the UI
        boolean stop = false;
        String [] test = new String[10];
        for (int i=0; i < 10; i++)
            test[i] = "Item" + i;

        view.updateItemsList(test, true);

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
