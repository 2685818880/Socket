package com.learn.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Upload {
	public static String upload(String filename) throws Exception, IOException {
		Socket socket=null;
		OutputStream out=null;
		DataOutputStream dos=null;
		socket = new Socket("127.0.0.1", 1314);
		out = socket.getOutputStream();
		dos = new DataOutputStream(out);
		dos.writeUTF("upload");
		/*-------------------------------------------------------*/
		
		//读取本地文件
  		String suffix=filename.substring(filename.lastIndexOf(".")+1);
  	    //截取后缀名
  		dos.writeUTF(suffix);
  		File file=new File(filename);
  		FileInputStream fis=new FileInputStream(file);
  		int fileSize=fis.available();
  	    //文件的大小
  		dos.writeInt(fileSize);
		int read = 0;
		byte[] buf = new byte[1024];
		// 每次读取的大小
		while ((read = fis.read(buf)) != -1) {
			dos.write(buf, 0, read);
		}
  		fis.close();
  		dos.close();
		return filename;

	}
}
