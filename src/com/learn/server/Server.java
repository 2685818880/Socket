package com.learn.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

	public static void server() throws Exception {
		ServerSocket serverSocket=new ServerSocket(1314);
		final Socket socket=serverSocket.accept();
		ExecutorService executorService=Executors.newFixedThreadPool(5);
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					InputStream input=socket.getInputStream();
					DataInputStream dis=new DataInputStream(input);
					String receive=dis.readUTF();
					
					if (receive.equals("upload")) {
                        //按顺序接收
						String stuffix=dis.readUTF();
						int fileSize=dis.readInt();
						String basepath="E:/user/data/";
						UploadMethod load=new UploadMethod(basepath,stuffix,fileSize);
						load.upload(input);
						}
					else if (receive.equals("DOWNLOAD")) {
						String filepath = dis.readUTF();
						OutputStream outputStream = socket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(outputStream);
						BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filepath)));
						// 获取输出流
						OutputStream os = socket.getOutputStream();
						DataOutputStream dos = new DataOutputStream(os);
						// 将文件输出
						int filesize = bis.available();
						dos.writeInt(filesize);
						
						int read = 0;
						byte[] buf = new byte[1024];
						while ((read = bis.read(buf)) != -1) {
							bos.write(buf, 0, read);
							bos.flush();
						}
						if (bos != null) {
							bos.close();
						}
						if (bis != null) {
							bis.close();
						}
					}
                else if (receive.equals("REMOVE")) {
					String filepath = dis.readUTF();
					File file=new File(filepath);
					if(file.exists()){
						file.delete();
					}
                  }
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
