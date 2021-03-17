package me.parkprin.assignment.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class tempTest {

    @Value("${server.servlet.context-path}")
    String contextPath;

    @Test
    public void testProperties(){
        Assert.assertEquals("/assignment", contextPath);
    }
}
