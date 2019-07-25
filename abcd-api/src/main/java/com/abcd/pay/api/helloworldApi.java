package com.abcd.pay.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yangxi on 2019/05/04.
 */
@RequestMapping("/helloworld")
public interface helloworldApi {  // 类名首字母请手工调整为大写

	@GetMapping("/{name}")
    String hello(@PathVariable("name") String name);
}