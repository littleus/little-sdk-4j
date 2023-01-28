package org.littleus.sdk.file.csv.reader.bean;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Fruit {
    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private double price;
}
