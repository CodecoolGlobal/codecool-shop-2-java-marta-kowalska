package com.codecool.shop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Currency;

public class Product extends BaseModel {
    private final static String IMG_PATH = "/static/img/";

    @Expose
    @SerializedName("price")
    private float defaultPrice;
    @Expose
    @SerializedName("image")
    private final String image;
    private Currency defaultCurrency;
    private int categoryId;
    private int supplierId;


    public Product(int id,
                   String name,
                   String description,
                   float defaultPrice,
                   String currencyString,
                   String image,
                   int categoryId,
                   int supplierId) {
        super(name, description);
        this.setId(id);
        this.setPrice(defaultPrice, currencyString);
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.image = createImageName(image);

    }

    public String getImage() {
        return image;
    }

    private String createImageName(String imgName) {
        return IMG_PATH + imgName;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public float getPriceForCart() {
        return this.defaultPrice;
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public int getProductCategoryId() {
        return categoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.categoryId = productCategoryId;

    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
//        this.supplier.addProduct(this);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategoryId: %5$d, " +
                        "supplierId: %6$d",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.categoryId,
                this.supplierId);
    }
}
