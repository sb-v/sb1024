package com.sb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.sb.domain.RentalAgreement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalCheckoutServiceTest {

   private RentalCheckoutService rentalCheckoutService;

   @BeforeEach
   public void setUp() {
       rentalCheckoutService = new RentalCheckoutService();
   }

    @Test
    void test1() {
        String toolCode = "JKAR";
        int rentalDayCount = 5;
        String checkoutDate = "9/3/15";
        int discountPercent = 101;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalCheckoutService.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);
        });

        String expectedMessage = "Discount percent must be in the range of 0 to 100.";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void test2() {
        String toolCode = "LADW";
        int rentalDayCount = 3;
        String checkoutDate = "7/2/20";
        int discountPercent = 10;

        RentalAgreement actual = rentalCheckoutService.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);

        assertNotNull(actual);
        assertEquals("LADW", actual.getToolCode());
        assertEquals("Ladder", actual.getToolType());
        assertEquals("Werner", actual.getBrand());
        assertEquals(3, actual.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), actual.getCheckOutDate());
        assertEquals(LocalDate.of(2020, 7, 5), actual.getDueDate());
        assertEquals(BigDecimal.valueOf(1.99), actual.getDailyRentalCharge());
        assertEquals(2, actual.getChargeDays());
        assertEquals(BigDecimal.valueOf(3.98), actual.getPreDiscountCharge());
        assertEquals(10, actual.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(0.40).setScale(2, RoundingMode.HALF_UP), actual.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(3.58), actual.getFinalCharge());
    }

    @Test
    void test3() {
        String toolCode = "CHNS";
        int rentalDayCount = 5;
        String checkoutDate = "7/2/15";
        int discountPercent = 25;

        RentalAgreement actual = rentalCheckoutService.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);

        assertNotNull(actual);
        assertEquals("CHNS", actual.getToolCode());
        assertEquals("Chainsaw", actual.getToolType());
        assertEquals("Stihl", actual.getBrand());
        assertEquals(5, actual.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), actual.getCheckOutDate());
        assertEquals(LocalDate.of(2015, 7, 7), actual.getDueDate());
        assertEquals(BigDecimal.valueOf(1.49), actual.getDailyRentalCharge());
        assertEquals(3, actual.getChargeDays());
        assertEquals(BigDecimal.valueOf(4.47), actual.getPreDiscountCharge());
        assertEquals(25, actual.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(1.12).setScale(2, RoundingMode.HALF_UP), actual.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(3.35), actual.getFinalCharge());
    }

    @Test
    void test4() {
        String toolCode = "JAKD";
        int rentalDayCount = 6;
        String checkoutDate = "9/3/15";
        int discountPercent = 0;

        RentalAgreement actual = rentalCheckoutService.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);

        assertNotNull(actual);
        assertEquals("JAKD", actual.getToolCode());
        assertEquals("Jackhammer", actual.getToolType());
        assertEquals("DeWalt", actual.getBrand());
        assertEquals(6, actual.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), actual.getCheckOutDate());
        assertEquals(LocalDate.of(2015, 9, 9), actual.getDueDate());
        assertEquals(BigDecimal.valueOf(2.99), actual.getDailyRentalCharge());
        assertEquals(3, actual.getChargeDays());
        assertEquals(BigDecimal.valueOf(8.97), actual.getPreDiscountCharge());
        assertEquals(0, actual.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP), actual.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(8.97), actual.getFinalCharge());
    }

    @Test
    void test5() {
        String toolCode = "JAKR";
        int rentalDayCount = 9;
        String checkoutDate = "7/2/15";
        int discountPercent = 0;

        RentalAgreement actual = rentalCheckoutService.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);

        assertNotNull(actual);
        assertEquals("JAKR", actual.getToolCode());
        assertEquals("Jackhammer", actual.getToolType());
        assertEquals("Ridgid", actual.getBrand());
        assertEquals(9, actual.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), actual.getCheckOutDate());
        assertEquals(LocalDate.of(2015, 7, 11), actual.getDueDate());
        assertEquals(BigDecimal.valueOf(2.99), actual.getDailyRentalCharge());
        assertEquals(5, actual.getChargeDays());
        assertEquals(BigDecimal.valueOf(14.95), actual.getPreDiscountCharge());
        assertEquals(0, actual.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP), actual.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(14.95), actual.getFinalCharge());
    }

    @Test
    void test6() {
        String toolCode = "JAKR";
        int rentalDayCount = 4;
        String checkoutDate = "7/2/20";
        int discountPercent = 50;

        RentalAgreement actual = rentalCheckoutService.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);

        assertNotNull(actual);
        assertEquals("JAKR", actual.getToolCode());
        assertEquals("Jackhammer", actual.getToolType());
        assertEquals("Ridgid", actual.getBrand());
        assertEquals(4, actual.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), actual.getCheckOutDate());
        assertEquals(LocalDate.of(2020, 7, 6), actual.getDueDate());
        assertEquals(BigDecimal.valueOf(2.99), actual.getDailyRentalCharge());
        assertEquals(1, actual.getChargeDays());
        assertEquals(BigDecimal.valueOf(2.99), actual.getPreDiscountCharge());
        assertEquals(50, actual.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP), actual.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(1.49), actual.getFinalCharge());
    }

    @Test
    void checkout_Throws_WhenRentalDayCountIsZero() {
        String toolCode = "JKAR";
        int rentalDayCount = 0;
        String checkoutDate = "9/3/15";
        int discountPercent = 90;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalCheckoutService.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);
        });

        String expectedMessage = "Rental day count must be equal to or greater than 1.";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}