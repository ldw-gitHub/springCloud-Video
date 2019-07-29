package com.itcast.dw.test.commitRespect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @CacheLocks(prefix = "books")
    @GetMapping("/books")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }
    
    @CacheLocks(prefix = "commit")
    @PostMapping("/commit")
    public String commit(@CacheParam(name = "token") @RequestParam String token,
    		@CacheParam(name = "name") @RequestParam String name) {
    	return "success - " + token;
    }

}
