package com.learn.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Download {
	public static void download(String filepath, String savepath) {
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bos = null;
		DataInputStream dis = null;
		OutputStream outputStream = null;
		DataOutputStream dos = null;
		BufferedInputStream bis = null;
		try {
			Socket socket = new Socket("127.0.0.1", 1314);
			InputStream inputStream = socket.getInputStream();
			dis = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dos = new DataOutputStream(outputStream);
			dos.writeUTF("DOWNLOAD");
			dos.writeUTF(filepath);
			int filesize = dis.readInt();
			bis = new BufferedInputStream(inputStream);

			fileOutputStream = new FileOutputStream(new File(savepath));
			bos = new BufferedOutputStream(fileOutputStream);

			// 读取服务端的文件数据
			int read = 0;
			byte[] buf = new byte[1024];
			double times = Math.ceil((float) filesize / (float) buf.length);
			for (int i = 0; i < times; i++) {
				while ((read = bis.read(buf)) != -1) {
					bos.write(buf, 0, read);
					bos.flush();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
