package org.littleus.sdk.file.excel.reader;

import com.alibaba.excel.EasyExcel;

import static org.junit.jupiter.api.Assertions.*;

class ExcelReaderTest {
    public void test_read1() {
        EasyExcel.read().doReadAll();
    }
}