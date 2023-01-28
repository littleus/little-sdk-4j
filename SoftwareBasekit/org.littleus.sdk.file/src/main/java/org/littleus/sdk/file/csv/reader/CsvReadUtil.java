package org.littleus.sdk.file.csv.reader;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

/**
 * @author hyzhangj
 */
@Slf4j
public class CsvReadUtil {
    public static <T> List<T> readByIndex(String csvPath, Class<T> mappingBean, boolean skipHeader) {
        try (Reader reader = new FileReader(csvPath); BufferedReader bufferedReader = new BufferedReader(reader)) {
            if (skipHeader) {
                bufferedReader.readLine();
            }
            return new CsvToBeanBuilder<T>(bufferedReader).withType(mappingBean).build().parse();
        } catch (IOException e) {
            log.error("read by index error", e);
            return Collections.emptyList();
        }
    }
}
