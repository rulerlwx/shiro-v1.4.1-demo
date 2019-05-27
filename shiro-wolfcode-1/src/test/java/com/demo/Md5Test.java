package com.demo;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Created by lwx on 2019/5/26.
 */
public class Md5Test {
    @Test
    public void testMd5(){
        String password = "123456";//明文

        Md5Hash md5Hash = new Md5Hash(password);//md5加密

        md5Hash = new Md5Hash(password, "zhangsan");//md5 + 盐值

        md5Hash = new Md5Hash(password, "zhangsan", 5);//md5 + 盐值 + 散列次数

        System.out.println(md5Hash);//5cb022bfe7cab9f9f6b043d32e58e617
    }
}
