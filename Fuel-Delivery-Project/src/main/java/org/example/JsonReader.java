package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

public class JsonReader {

    public static List<Shipment> readShipments() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream =
                JsonReader.class.getClassLoader()
                        .getResourceAsStream("shipments.json");

        return objectMapper.readValue(
                inputStream,
                new TypeReference<List<Shipment>>() {});
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
    }
}
