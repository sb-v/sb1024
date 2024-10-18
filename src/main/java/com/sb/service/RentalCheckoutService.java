package com.sb.service;

import com.sb.repository.ToolRepository;
import com.sb.domain.Charge;
import com.sb.domain.RentalAgreement;
import com.sb.domain.Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalCheckoutService {

    public RentalAgreement checkout(String toolCode, int rentalDayCount,
                                    int discountPercent, String checkoutDate) {

        if(rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count must be equal to or greater than 1.");
        }
        if(discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be in the range of 0 to 100.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
        LocalDate localCheckoutDate = LocalDate.parse(checkoutDate, formatter);
        ToolRepository toolRepository = new ToolRepository();

        Tool tool = toolRepository.getToolByCode(toolCode);
        if( tool != null) {
            BigDecimal dailyRentalCharge = tool.getCharge().getDailyCharge();
            LocalDate dueDate = calculateDueDate(localCheckoutDate, rentalDayCount);
            int chargeDays = calculateChargeDays(localCheckoutDate, dueDate, tool.getCharge());
            BigDecimal preDiscountCharge = calculatePreDiscountCharge(dailyRentalCharge, chargeDays);
            BigDecimal discountAmount = calculateDiscountAmount(discountPercent, preDiscountCharge);
            BigDecimal finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);

            return new RentalAgreement.RentalAgreementBuilder()
                    .setToolCode(toolCode)
                    .setToolType(tool.getType())
                    .setBrand(tool.getBrand())
                    .setRentalDays(rentalDayCount)
                    .setCheckOutDate(localCheckoutDate)
                    .setDueDate(dueDate)
                    .setDailyRentalCharge(dailyRentalCharge)
                    .setChargeDays(chargeDays)
                    .setPreDiscountCharge(preDiscountCharge)
                    .setDiscountPercent(discountPercent)
                    .setDiscountAmount(discountAmount)
                    .setFinalCharge(finalCharge)
                    .build();
        }

        return null;
    }

    private BigDecimal calculateFinalCharge(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
        return preDiscountCharge.subtract(discountAmount);
    }

    private BigDecimal calculateDiscountAmount(int discountPercent, BigDecimal preDiscountCharge) {
        return preDiscountCharge.multiply(new BigDecimal(discountPercent)).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePreDiscountCharge(BigDecimal dailyCharge, int chargeDays) {
        return dailyCharge.multiply(new BigDecimal(chargeDays)).setScale(2, RoundingMode.HALF_UP);
    }

    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Charge charge) {
        int chargeableDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1);
        while (!currentDate.isAfter(dueDate)) {
            if(Charge.isChargeable(charge, currentDate)) {
                chargeableDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return chargeableDays;
    }


    private LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDayCount) {
        return checkoutDate.plusDays(rentalDayCount);

    }
}
