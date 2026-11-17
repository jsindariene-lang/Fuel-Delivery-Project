package org.example;

import java.time.LocalDate;
import java.util.*;

public class Validator {

    public static List<ErrorRecord> validate(
            List<Shipment> shipments) {

        List<ErrorRecord> errors = new ArrayList<>();

        Set<String> shipmentIds = new HashSet<>();

        for (Shipment shipment : shipments) {

            // MISSING_FIELD

            if (shipment.getId() == null ||
                    shipment.getType() == null ||
                    shipment.getClient() == null ||
                    shipment.getDate() == null) {

                errors.add(
                        new ErrorRecord(
                                shipment.getId(),
                                "MISSING_FIELD"));
            }

            // UNKNOWN_TYPE

            if (shipment.getType() != null &&
                    !shipment.getType().equals("DIESEL") &&
                    !shipment.getType().equals("GASOLINE") &&
                    !shipment.getType().equals("LPG")) {

                errors.add(
                        new ErrorRecord(
                                shipment.getId(),
                                "UNKNOWN_TYPE"));
            }

            // UNKNOWN_CLIENT

            if (shipment.getClient() != null &&
                    !shipment.getClient().equals("PRIVATE") &&
                    !shipment.getClient().equals("EXEMPT")) {

                errors.add(
                        new ErrorRecord(
                                shipment.getId(),
                                "UNKNOWN_CLIENT"));
            }

            // INVALID_DATE

            try {

                if (shipment.getDate() != null) {
                    LocalDate.parse(shipment.getDate());
                }

            } catch (Exception e) {

                errors.add(
                        new ErrorRecord(
                                shipment.getId(),
                                "INVALID_DATE"));
            }

            // INVALID_VALUE

            if (shipment.getVolume() < 0 ||
                    shipment.getDistance() < 0 ||
                    shipment.getBasePrice() == null ||
                    shipment.getBasePrice().doubleValue() < 0) {

                errors.add(
                        new ErrorRecord(
                                shipment.getId(),
                                "INVALID_VALUE"));
            }

            // DUPLICATE_ID

            if (shipment.getId() != null) {

                if (!shipmentIds.add(shipment.getId())) {

                    errors.add(
                            new ErrorRecord(
                                    shipment.getId(),
                                    "DUPLICATE_ID"));
                }
            }
        }

        return errors;
    }
}
