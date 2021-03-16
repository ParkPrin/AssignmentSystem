package me.parkprin.assignment.initdata;

import me.parkprin.assignment.utils.ApiUtils;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static me.parkprin.assignment.utils.ApiUtils.success;
import static me.parkprin.assignment.utils.ApiUtils.ApiResult;

@RestController
@RequestMapping("api/initdata")
public class InitDataController {

    private final InitDataService initDataService;

    public InitDataController(InitDataService initDataService){
        this.initDataService = initDataService;
    }

    @GetMapping
    public ApiResult setting() throws IOException, ParseException {
        if (initDataService.tableIsNull()) initDataService.settingData();
        return success(true);
    }
}
