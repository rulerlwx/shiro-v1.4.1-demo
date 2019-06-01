package cn.wolfcode.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 对密码进行md5加密
 *
 * Created by lwx on 2019/6/1.
 */
public class MD5Test {

    public static void main(String[] args) {

        String password = "666";//明文密码
        String salt = "admin";//盐值
        int hashIteration = 3;//加密次数

        Md5Hash md5Hash = new Md5Hash(password, salt, hashIteration);
        System.out.println(md5Hash.toString());//b649682ba1f39837f195b4aaaf24ec5f
    }
}
