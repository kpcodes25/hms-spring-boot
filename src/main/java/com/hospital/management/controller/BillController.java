package com.hospital.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.models.Bill;
import com.hospital.management.services.BillService;

@RestController
@RequestMapping("/api/v1.0/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public Bill create(@RequestBody Bill bill) {
        return billService.save(bill);
    }

    @GetMapping
    public List<Bill> getAll() {
        return billService.getAll();
    }

    @GetMapping("/{id}")
    public Bill getById(@PathVariable("id") Long id) {
        return billService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        billService.delete(id);
    }
}
