package com.sb;

import com.sb.domain.RentalAgreement;
import com.sb.service.RentalCheckoutService;

public class ToolRentalApplication {
    public static void main(String[] args) {
        RentalCheckoutService rentalCheckoutService = new RentalCheckoutService();
        RentalAgreement rentalAgreement = rentalCheckoutService.checkout("JAKR", 4, 50,"7/2/20");
        rentalAgreement.printAgreement();
    }
}