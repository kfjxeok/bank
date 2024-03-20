package com.junseok.bankservice.controller;

import com.junseok.bankservice.dto.TestDTO;
import com.junseok.bankservice.entity.TestEntity;
import com.junseok.bankservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/all")
    public Iterable<TestEntity> getAllBoard() {
        return testService.getAllBoard();
    }

    @PostMapping("/create")
    public String setBoard(@RequestBody TestDTO testDto){
        testService.setBoard(testDto);
        return "성공";
    }

}

