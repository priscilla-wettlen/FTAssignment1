package Model;

import Model.LoanItem;

import java.util.ArrayList;
import java.util.List;

//This class administers a list of products, adding a new object,
//removing an existing, etc.  The list contains products that are
//available for loan.  When a product is loaned by a member the
//product is removed from this list and placed in the LoanItenmList,
//and when returned, the object is added to the list again.
public class LoanItemManager {
    private List<LoanItem> loanItems = new ArrayList<>();  //you may use other types of collections
    private LoanItem loanItem;

    //Products receive an Id starting from 100 and then
    //every time a new product is created, the id is
    //incremented.
    //private int lastProductID = 100; //Optional start id

    //returns a product at a position = index in the list
    public LoanItem get(int index)
    {
        if (checkIndex(index))
            return loanItems.get(index);
        else
            return null;
    }
    //Adds a given product at the end of the list
    public void add(LoanItem loanItem)
    {
        loanItems.add(loanItem);
    }

    //Removes a product from a position = index from
    //the list
    public void remove(int index)
    {
        if (checkIndex(index))
            loanItems.remove(index);
    }

    //Return the number of elements (products) in the list.
    public int size()
    {
        return loanItems.size();
    }

    //Validet the given index so it is not out of range.
    private boolean checkIndex(int index) {
        return (index >= 0) && (index < loanItems.size());
    }
    //Preapare and rturn a string array where each element contains
    //information about the loanObject, calling the object's
    //toString metod.  The return array can then be used to update
    //the GUI on the related view.
    public  String[] getProductInfoStrings()
    {
        if( (loanItems == null) || (loanItems.size() <= 0) )
            return new String []{"Products available: ", " "};

        //Create an array with a size equal to the number of items in the list.
        //one extra lines for the title, size and a blank line.
        String [] infoStrings = new String[loanItems.size()+3]; //See (x)

        infoStrings[0] =   String.format("Number of products on loan: %s", loanItems.size()); //(1)
        infoStrings[1] = "";  //(2)
        int j= 2;

        //Fill the list with info on each element, using the object's toString
        //method.
        for (int i=0; i < loanItems.size(); i++)
            infoStrings[j++] = loanItems.get(i).toString();

        infoStrings[infoStrings.length-1] = "";  //(3)
        return infoStrings;
    }

    //Fill the list with some test objects.
//    public void addTestProducts() {
//        for (int i = 0; i < 15; i++) {
//            addNewTestProduct();
//        }
//    }

    //Return true if the list of items is empty, an thus no product available for loaning.
    public boolean noProductsAvailable()
    {
        return (loanItems == null) || (loanItems.isEmpty());
    }

    //This method is called for adding a new tests object in the list.
//    public Product addNewTestProduct()
//    {
//        Product product = new Product(); // Assume Product constructor and methods are defined elsewhere
//        product.setId(Integer.toString( lastProductID));
//        product.setName("Product" + (lastProductID++));
//        products.add(product);
//        return product;
//    }
}