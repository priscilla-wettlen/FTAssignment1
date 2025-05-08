package Model;

import Controller.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private final List<Product> products = new ArrayList<>();
    private int productID = 100;


    public Product get(int index) {
        return checkIndex(index) ? products.get(index) : null;
    }

    public void add(Product product) {
        products.add(product);
    }

    public void remove(int index) {
        if (checkIndex(index)) {
            products.remove(index);

        }
    }

    public int size() {
        return products.size();
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < products.size();
    }

    public String[] getProductInfoStrings() throws IOException {
        if (products.isEmpty()) {
            return new String[] { "Products available: ", " " };
        }

        String[] infoStrings = new String[products.size() + 3];

        infoStrings[0] = String.format("Number of products available: %s", products.size());

        int j = 2;
        for (Product product : products) {
            infoStrings[j++] = product.toString();
        }

        infoStrings[infoStrings.length - 1] = "";
        return infoStrings;
    }

    public void addTestProducts() {
        for (int i = 0; i < 15; i++) {
            addNewTestProduct();
        }
    }

    public boolean noProductsAvailable() {
        return products.isEmpty();
    }

    public Product addNewTestProduct() {
        Product product = new Product();
        product.setId(Integer.toString(productID));
        product.setName("Product" + (productID++));
        products.add(product);
        return product;
    }

    public List<Product> getProducts() {
        return products;
    }
}
