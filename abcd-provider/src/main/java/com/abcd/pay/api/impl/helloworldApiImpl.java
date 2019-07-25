package com.abcd.pay.api.impl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abcd.pay.api.helloworldApi;

/**
 * Created by yangxi on 2019/05/04.
 */
@RestController
public class helloworldApiImpl implements helloworldApi { // 类名首字母请手工调整为大写

	@GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
		return "hello, " + name;
	}
}