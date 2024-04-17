package com.group4.vms.controller;

import com.group4.vms.model.Entry;
import com.group4.vms.service.TimesheetService;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimesheetController {

    //fields
    @Autowired
    private TimesheetService timesheetService;

    //methods
    @GetMapping("/api/v1/timesheet/getAllEntries")
    public ResponseEntity<List<Entry>> getAllEntries(){
        return ResponseEntity.ok(this.timesheetService.getAllEntries());
    }
    @PostMapping("/api/v1/timesheet/postEntries")
    public HttpStatus postEntries(@RequestBody JsonObject requestBody){
        return this.timesheetService.postEntries(requestBody);
    }
}