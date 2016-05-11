package com.learn.test;

import java.io.IOException;
import org.junit.Test;

public class Download {
	  @Test
      public void downloadtest() throws IOException, Exception{
		  String filepath="E:/user/data/B1/1F/E49D183A466A805375560CFE0F0F.rar";
		  String savepath="E:/use/aa.rar";
		  com.learn.client.Download.download(filepath, savepath);
          System.out.println("download success");
      }

}
