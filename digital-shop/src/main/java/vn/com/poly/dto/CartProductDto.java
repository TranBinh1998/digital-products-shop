package vn.com.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


public class CartProductDto {

    private String userName;
    private String idProduct;
    private String soLuong;

    public CartProductDto(String userName, String idProduct, String soLuong) {
        this.userName = userName;
        this.idProduct = idProduct;
        this.soLuong = soLuong;

    }

    public CartProductDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
