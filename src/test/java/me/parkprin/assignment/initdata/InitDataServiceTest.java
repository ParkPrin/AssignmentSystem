package me.parkprin.assignment.initdata;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitDataServiceTest {

    @Autowired
    InitDataService initDataService;

    @Test
    public void data파일_불러오고_데이터세팅() throws IOException, ParseException {
        if (initDataService.tableIsNull()) initDataService.settingData();
    }
}
