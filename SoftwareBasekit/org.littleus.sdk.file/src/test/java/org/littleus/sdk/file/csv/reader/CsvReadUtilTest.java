package org.littleus.sdk.file.csv.reader;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.littleus.sdk.file.csv.reader.bean.Fruit;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

class CsvReadUtilTest {
    @SneakyThrows
    @Test
    void test_read_csv() {
        String csvPath = new ClassPathResource("csv/csv1_with_no_header.csv").getFile().getCanonicalPath();
        List<Fruit> fruits = CsvReadUtil.readByIndex(csvPath, Fruit.class, true);

        fruits.size();
    }

}