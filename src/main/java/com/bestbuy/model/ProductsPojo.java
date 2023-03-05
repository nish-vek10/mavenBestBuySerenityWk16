package com.bestbuy.model;

public class ProductsPojo {

    private String name;
    private String type;
    private Double price;
    private String upc;
    private Integer shipping;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public static ProductsPojo setProductsPojo(String name, String type, Double price, String upc,
//                                               Integer shipping, String description, String manufacturer,
//                                               String model, String url, String image) {
//        ProductsPojo productsPojo = new ProductsPojo();
//        productsPojo.setName(name);
//        productsPojo.setType(type);
//        productsPojo.setPrice(price);
//        productsPojo.setUpc(upc);
//        productsPojo.setShipping(shipping);
//        productsPojo.setDescription(description);
//        productsPojo.setManufacturer(manufacturer);
//        productsPojo.setModel(model);
//        productsPojo.setUrl(url);
//        productsPojo.setImage(image);
//        return productsPojo;
//    }
}