package com.qianxunclub.permanent.controller;


import com.qianxunclub.permanent.model.Result;
import com.qianxunclub.permanent.service.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbin
 */
@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
public class CustomersController {

    private final CustomersService customersService;

    @GetMapping("{id}")
    public Result getById(
        @PathVariable Long id
    ) {
        return Result.success(customersService.get(id));
    }
}
