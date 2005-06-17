package com.prototype.remoting.SpringHttp.http;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.prototype.remoting.SpringHttp.HelloWorld;


public class HelloWorldClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
        "src/com/prototype/remoting/SpringHttp/conf/helloWorld.xml");

        HelloWorld helloWorld = (HelloWorld)ctx.getBean("helloWorldService");
        System.out.println(helloWorld.getMessage());
	}
}
