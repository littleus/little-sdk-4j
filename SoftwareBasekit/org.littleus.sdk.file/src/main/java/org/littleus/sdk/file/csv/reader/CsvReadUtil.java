package org.littleus.sdk.file.csv.reader;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hyzhangj
 */
@Slf4j
public class CsvReadUtil {
    public static <T> Stream<T> readAsStream(String csvPath, Class<T> mappingBean, String profile) {
        try (Reader reader = new FileReader(csvPath); BufferedReader bufferedReader = new BufferedReader(reader)) {
            return new CsvToBeanBuilder<T>(bufferedReader).withType(mappingBean).withProfile(profile).build().stream();
        } catch (IOException e) {
            log.error("read csv error", e);
            return Stream.empty();
        }
    }

    public static <T> Stream<T> readAsStream(String csvPath, Class<T> mappingBean) {
        return readAsStream(csvPath, mappingBean, "");
    }

    public static <T> List<T> readAsList(String csvPath, Class<T> mappingBean, String profile) {
        return readAsStream(csvPath, mappingBean, profile).collect(Collectors.toList());
    }

    public static <T> List<T> readAsList(String csvPath, Class<T> mappingBean) {
        return readAsStream(csvPath, mappingBean).collect(Collectors.toList());
    }
}
