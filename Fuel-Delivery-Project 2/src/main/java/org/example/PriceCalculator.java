package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

//kuro islaidu paskaiciavimai

    public static BigDecimal calculateNetFuelPrice(Shipment shipment) {

        BigDecimal discount = BigDecimal.ZERO;

        if (shipment.getVolume() > 20000) {
            discount = new BigDecimal("0.10");
        } else if (shipment.getVolume() > 5000) {
            discount = new BigDecimal("0.05");
        }

        BigDecimal volume =
                BigDecimal.valueOf(shipment.getVolume());

        BigDecimal result =
                volume.multiply(shipment.getBasePrice())
                        .multiply(BigDecimal.ONE.subtract(discount));

        return result.setScale(2, RoundingMode.HALF_UP);
    }

    //logistikos islaidu paskaiciavimai

    public static BigDecimal calculateLogisticsCost(Shipment shipment) {

        BigDecimal logisticsCost =
                BigDecimal.valueOf(shipment.getDistance())
                        .multiply(new BigDecimal("1.80"));

        if (shipment.getVolume() < 1000) {
            logisticsCost = logisticsCost.add(new BigDecimal("150.00"));
        }

        return logisticsCost.setScale(2, RoundingMode.HALF_UP);
    }

    //akcizo skaiciavimai

    public static BigDecimal calculateExciseTax(Shipment shipment) {

        BigDecimal exciseRate = BigDecimal.ZERO;

        switch (shipment.getType()) {

            case "GASOLINE":
                exciseRate = new BigDecimal("0.47");
                break;

            case "DIESEL":
                exciseRate = new BigDecimal("0.33");
                break;

            case "LPG":
                exciseRate = new BigDecimal("0.18");
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown fuel type: "
                                + shipment.getType());

        }


        return BigDecimal.valueOf(shipment.getVolume())
                .multiply(exciseRate)
                .setScale(2, RoundingMode.HALF_UP);

    }

    // Task 2 - Custom Excise Tax

    public static BigDecimal calculateCustomExciseTax(
            Shipment shipment,
            BigDecimal gasolineExciseRate) {

        BigDecimal exciseRate;

        switch (shipment.getType()) {

            case "GASOLINE":
                exciseRate = gasolineExciseRate;
                break;

            case "DIESEL":
                exciseRate = new BigDecimal("0.33");
                break;

            case "LPG":
                exciseRate = new BigDecimal("0.18");
                break;

            default:
                exciseRate = BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(
                        shipment.getVolume())
                .multiply(exciseRate)
                .setScale(2,
                        RoundingMode.HALF_UP);
    }


    //PVM skaiciavimai

    public static BigDecimal calculateVat(
            BigDecimal amount,
            Shipment shipment) {

        if ("EXEMPT".equals(shipment.getClient())) {
            return BigDecimal.ZERO;
        }

        BigDecimal vatRate = new BigDecimal("0.21");

        return amount.multiply(vatRate)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Task 2 - Custom VAT

    public static BigDecimal
    calculateCustomVat(BigDecimal amount, BigDecimal vatRatePercent) {

        BigDecimal vatRate =
                vatRatePercent.divide(
                        new BigDecimal("100"),
                        4,
                        RoundingMode.HALF_UP);

        return amount.multiply(vatRate)
                .setScale(
                        2,
                        RoundingMode.HALF_UP);
    }


    //Grand Total paskaiciavimai

    public static BigDecimal calculateGrandTotal(
            BigDecimal netFuelPrice,
            BigDecimal logisticsCost,
            BigDecimal exciseTax,
            BigDecimal vat) {

        return netFuelPrice
                .add(logisticsCost)
                .add(exciseTax)
                .add(vat)
                .setScale(2, RoundingMode.HALF_UP);
    }



}
