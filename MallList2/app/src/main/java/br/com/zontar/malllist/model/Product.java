package br.com.zontar.malllist.model;

/**
 * Created by matheusoliveira on 04/09/2017.
 */

public class Product {

    private int idProduct;
    private String productName;
    private int productQnt;
    private float productPrice;

    public Product() {
    }

    public Product(int idProduct, String productName, int productQnt, float productPrice) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.productQnt = productQnt;
        this.productPrice = productPrice;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQnt() {
        return productQnt;
    }

    public void setProductQnt(int productQnt) {
        this.productQnt = productQnt;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
