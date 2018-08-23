package com.ysdevelop.lorchard.shiro.core.helper;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.ysdevelop.lorchard.shiro.entity.BaseAuth;

public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator =new SecureRandomNumberGenerator();
	
	private String algorithmName = "md5";
	
	private int hashIterations = 2;
	
	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public  void encryptPassword(BaseAuth user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user
                .getSalt()), hashIterations).toHex();

        user.setPassword(newPassword);
        System.out.println(user.getSalt()+"    "+user.getPassword());
    }
    
    public boolean checkPassword(BaseAuth user,String password) {
        String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(user
                .getSalt()), hashIterations).toHex();
        return newPassword.equals(user.getPassword());
    }

    
    public static void main(String[] args) {
//		User user = new User();
//		user.setLoginName("老黄");
//		user.setPswd("123456");
//		new PasswordHelper().encryptPassword(user);
	}
	
	
}
