package com.sb.domain;

import com.sb.domain.Charge;

public class Tool {
    private String code;
    private String type;
    private String brand;
    private Charge charge;

    public Tool(String code, String type, String brand, Charge charge) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.charge = charge;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }
}
