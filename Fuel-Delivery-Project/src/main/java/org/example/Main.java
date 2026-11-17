package org.example;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Shipment> shipments =
                JsonReader.readShipments();

        List<ErrorRecord> errors =
                Validator.validate(shipments);

        CsvWriter.writeErrors(errors);

        if (!errors.isEmpty()) {

            System.out.println("Rastos validacijos klaidos.");
            System.out.println("Patikrink failą output/errors.csv");

            return;
        }

        CsvWriter.writeTask1(shipments);

        for (Shipment shipment : shipments) {
            // skaičiavimai
            BigDecimal netFuelPrice =
                    PriceCalculator.calculateNetFuelPrice(shipment);

            BigDecimal logisticsCost =
                    PriceCalculator.calculateLogisticsCost(shipment);

            BigDecimal exciseTax =
                    PriceCalculator.calculateExciseTax(shipment);

            BigDecimal vat =
                    PriceCalculator.calculateVat(
                            netFuelPrice,
                            shipment);

            BigDecimal grandTotal =
                    PriceCalculator.calculateGrandTotal(
                            netFuelPrice,
                            logisticsCost,
                            exciseTax,
                            vat);

            System.out.println("Shipment ID: "
                    + shipment.getId());

            System.out.println("Net Fuel Price: "
                    + netFuelPrice);

            System.out.println("Logistics Cost: "
                    + logisticsCost);

            System.out.println("Excise Tax: "
                    + exciseTax);

            System.out.println("VAT: "
                    + vat);

            System.out.println("Grand Total: "
                    + grandTotal);

            System.out.println("------------------");
        }
    }
}


//Kuro kiekis: 6000 l
//Kaina: 1.50 €/l
//Suma be nuolaidos: 9000 €
//Nuolaida: 5%

//Galutinė suma: 8550 €
//Net Fuel Price: 8550.00
//Logistikos islaidos: 216.00
//Excise/Akcizas Tax: 1980.00
//VAT/PVM: 1795.50

//Grand Total 8550.00 + 216.00 +1980.00 +1795.50 = 12541.50