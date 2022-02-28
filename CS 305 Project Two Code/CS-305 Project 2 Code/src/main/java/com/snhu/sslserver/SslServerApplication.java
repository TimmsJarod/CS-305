package com.snhu.sslserver;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{

//Function converts byte array to hex string
public static String bytesToHex(byte[] bytes) {
	
	BigInteger number = new BigInteger(1, bytes);  
    StringBuilder hexString = new StringBuilder(number.toString(16));  
    while (hexString.length() < 32){  
        hexString.insert(0, '0');  
   }
    return hexString.toString();
}
	
//Hash calculator using java security library and SHA3-512 algorithm
public static String calculateHash(String name) throws NoSuchAlgorithmException { 
	
	MessageDigest md = MessageDigest.getInstance("SHA3-512");  
    byte[] hash =  md.digest(name.getBytes(StandardCharsets.UTF_8));
    return bytesToHex(hash);
     
    } 


@RequestMapping("/hash")
public String myHash() throws NoSuchAlgorithmException{
	String myName = "Jarod Timms";
	String data = "Hello World Check Sum!";
	String hash = calculateHash(data);

	return "<p>Name: "+myName+"<p>data:"+data+" <p>SHA3-512 : Checksum Value : "+hash;

	}

}