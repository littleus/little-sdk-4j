package org.littleus.sdk.config.dynamic.testcase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.littleus.sdk.config.dynamic.DynamicConfig;
import org.littleus.sdk.config.dynamic.TestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class JdbcSourceTest {

    @Test
    public void test_jdbc_source() {
        String first = DynamicConfig.getString("first", "");
        Assert.assertEquals("apple", first);
    }
}
