package com.learn.test;

import java.io.IOException;

import org.junit.Test;

import com.learn.client.Upload;


public class UploadTest {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 */
	  @Test
      public void uploadtest() throws IOException, Exception{
			//String filename="E:/user/001.jpg";
			String filename="D:/jrebel-5.3.1.rar"; 
		    Upload.upload(filename);
		    System.out.println("upload success");
      }
}
