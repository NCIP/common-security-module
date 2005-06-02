package gov.nih.nci.SpringHttp.http;

import gov.nih.nci.SpringHttp.HelloWorld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class HelloWorldClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
        "src/gov/nih/nci/SpringHttp/conf/helloWorld.xml");

        HelloWorld helloWorld = (HelloWorld)ctx.getBean("helloWorldService");
        System.out.println(helloWorld.getMessage());
	}
}
