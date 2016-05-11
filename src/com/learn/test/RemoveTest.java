package com.learn.test;

import java.io.IOException;

import org.junit.Test;

public class RemoveTest {
	@Test
    public void removetest() throws IOException, Exception{
		  String filepath="E:/use/aa.rar";
		  com.learn.client.Remove.remove(filepath);

    }
}
