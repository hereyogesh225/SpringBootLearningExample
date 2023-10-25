package com.dummy.controller;

import com.dummy.model.BasicInfo;
import com.dummy.model.Input;
import com.dummy.model.UserAccount;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/validate/")
public class ValidationController {

    public final String VALIDATED = "Validated";

    @GetMapping
    public ResponseEntity<String> validateBody(@Valid @RequestBody Input input) {
        return ResponseEntity.ok(VALIDATED);
    }

    @GetMapping("/validateRequestParameter")
    public ResponseEntity<String> validateParameters(@RequestParam("param") @Min(5) int param) {
        return ResponseEntity.ok(VALIDATED);
    }

    @GetMapping("/validatePathVariable/{id}")
    ResponseEntity<String> validatePathVariable(
            @PathVariable("id") @Min(5) int id) {
        return ResponseEntity.ok(VALIDATED);
    }

    @GetMapping("/validateBasicInfo")
    ResponseEntity<String> saveBasicInfo(@Validated(BasicInfo.class)
    @RequestBody UserAccount userAccount) {
        return ResponseEntity.ok(VALIDATED);
    }
}
