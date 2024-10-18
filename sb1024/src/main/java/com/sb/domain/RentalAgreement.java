package com.sb.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {

    private final String toolCode;
    private final String toolType;
    private final String brand;
    private final int rentalDays;
    private final LocalDate checkOutDate;
    private final LocalDate dueDate;
    private final BigDecimal dailyRentalCharge;
    private final int chargeDays;
    private final BigDecimal preDiscountCharge;
    private final int discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;

    public RentalAgreement(RentalAgreementBuilder builder) {
        this.toolCode = builder.toolCode;
        this.toolType = builder.toolType;
        this.brand = builder.brand;
        this.rentalDays = builder.rentalDays;
        this.checkOutDate = builder.checkOutDate;
        this.dueDate = builder.dueDate;
        this.dailyRentalCharge = builder.dailyRentalCharge;
        this.chargeDays = builder.chargeDays;
        this.preDiscountCharge = builder.preDiscountCharge;
        this.discountPercent = builder.discountPercent;
        this.discountAmount = builder.discountAmount;
        this.finalCharge = builder.finalCharge;
    }

    public void printAgreement() {

        DecimalFormat percentFormatter = new DecimalFormat("##0%");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Brand: " + brand);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("CheckOut Date: " + checkOutDate.format(dateFormatter));
        System.out.println("Due Date: " + dueDate.format(dateFormatter));
        System.out.println("Daily Rental Charge: " +  currencyFormatter.format(dailyRentalCharge));
        System.out.println("Charge Days: " + chargeDays);
        System.out.println("Pre Discount Charge: " + currencyFormatter.format(preDiscountCharge));
        System.out.println("Discount percent: " + percentFormatter.format(discountPercent/100.0));
        System.out.println("Discount Amount: " + currencyFormatter.format(discountAmount));
        System.out.println("Final charge: " + currencyFormatter.format(finalCharge));
    }

    public static class RentalAgreementBuilder {
        private String toolCode;
        private String toolType;
        private String brand;
        private int rentalDays;
        private LocalDate checkOutDate;
        private LocalDate dueDate;
        private BigDecimal dailyRentalCharge;
        private int chargeDays;
        private BigDecimal preDiscountCharge;
        private int discountPercent;
        private BigDecimal discountAmount;
        private BigDecimal finalCharge;

        public RentalAgreementBuilder(){
        }

        public RentalAgreementBuilder setToolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public RentalAgreementBuilder setToolType(String toolType) {
            this.toolType = toolType;
            return this;
        }

        public RentalAgreementBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public RentalAgreementBuilder setRentalDays(int rentalDays) {
            this.rentalDays = rentalDays;
            return this;
        }

        public RentalAgreementBuilder setCheckOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }

        public RentalAgreementBuilder setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public RentalAgreementBuilder setDailyRentalCharge(BigDecimal dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        public RentalAgreementBuilder setChargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
            return this;
        }

        public RentalAgreementBuilder setPreDiscountCharge(BigDecimal preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        public RentalAgreementBuilder setDiscountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public RentalAgreementBuilder setDiscountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public RentalAgreementBuilder setFinalCharge(BigDecimal finalCharge) {
            this.finalCharge = finalCharge;
            return this;
        }

        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

}
