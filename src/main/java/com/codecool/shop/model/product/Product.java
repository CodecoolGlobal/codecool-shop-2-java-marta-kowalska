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
    private String image;
    private Currency defaultCurrency;
    private int categoryId;
    private int supplierId;
    private ProductCategory productCategory;
    private Supplier supplier;


    public Product(String name, float defaultPrice, String currencyString, String image, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        this.image = createImageName(image);
    }


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

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    public float getPriceForCart() {
        return this.defaultPrice;
    }

    public String getImage() {
        return image;
    }

    public String getImageWithoutPath() {
        return image.replace(IMG_PATH, "");
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                "name: %2$s, " +
                "defaultPrice: %3$f, " +
                "defaultCurrency: %4$s, ",
            this.id,
            this.name,
            this.defaultPrice,
            this.defaultCurrency.toString());
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getSupplierId() {
        return supplierId;
    }
}