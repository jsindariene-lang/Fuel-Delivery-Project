package org.example;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class CsvWriter {

    public static void writeTask1(List<Shipment> shipments) throws Exception {

        File outputDir = new File("output");
        outputDir.mkdirs();

        PrintWriter writer =
                new PrintWriter("output/task1.csv");

        writer.println(
                "ShipmentID,NetFuelPrice,LogisticsCost,TotalTax,GrandTotal");

        for (Shipment shipment : shipments) {

            BigDecimal netFuelPrice =
                    PriceCalculator.calculateNetFuelPrice(shipment);

            BigDecimal logisticsCost =
                    PriceCalculator.calculateLogisticsCost(shipment);

            BigDecimal exciseTax =
                    PriceCalculator.calculateExciseTax(shipment);

            BigDecimal vat =
                    PriceCalculator.calculateVat(netFuelPrice, shipment);

            BigDecimal grandTotal =
                    PriceCalculator.calculateGrandTotal(
                            netFuelPrice,
                            logisticsCost,
                            exciseTax,
                            vat);

            BigDecimal totalTax =
                    exciseTax.add(vat);

            writer.println(
                    shipment.getId() + "," +
                            netFuelPrice + "," +
                            logisticsCost + "," +
                            totalTax + "," +
                            grandTotal);
        }

        writer.close();
    }

    public static void writeTask2(
            List<Shipment> shipments,
            BigDecimal customVatRate,
            BigDecimal customGasolineExcise)
            throws Exception {

        PrintWriter writer =
                new PrintWriter("output/task2.csv");

        writer.println(
                "ShipmentID,NetFuelPrice,LogisticsCost,TotalTax,GrandTotal");

        for (Shipment shipment : shipments) {

            BigDecimal netFuelPrice =
                    PriceCalculator.calculateNetFuelPrice(shipment);

            BigDecimal logisticsCost =
                    PriceCalculator.calculateLogisticsCost(shipment);

            BigDecimal exciseTax =
                    PriceCalculator.calculateCustomExciseTax(
                            shipment,
                            customGasolineExcise);

            BigDecimal vat =
                    PriceCalculator.calculateCustomVat(
                            netFuelPrice,
                            customVatRate);

            BigDecimal grandTotal =
                    PriceCalculator.calculateGrandTotal(
                            netFuelPrice,
                            logisticsCost,
                            exciseTax,
                            vat);

            BigDecimal totalTax =
                    exciseTax.add(vat);

            writer.println(
                    shipment.getId() + "," +
                            netFuelPrice + "," +
                            logisticsCost + "," +
                            totalTax + "," +
                            grandTotal);
        }

        writer.close();

        System.out.println(
                "task2.csv successfully created");
    }

    public static void writeErrors(
            List<ErrorRecord> errors) throws Exception {

        File outputDir = new File("output");
        outputDir.mkdirs();

        PrintWriter writer =
                new PrintWriter("output/errors.csv");

        writer.println("ShipmentID,ErrorCode");

        for (ErrorRecord error : errors) {

            writer.println(
                    error.getShipmentId()
                            + ","
                            + error.getErrorCode());
        }

        writer.close();
        System.out.println("errors.csv successfully created");
    }
}