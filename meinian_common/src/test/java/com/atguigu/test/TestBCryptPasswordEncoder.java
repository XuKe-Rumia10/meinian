package com.atguigu.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/29  20:21
 */
public class TestBCryptPasswordEncoder {
    @Test
    public void test(){
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        String admin = bpe.encode("admin");
        System.out.println(admin);
    }
}
