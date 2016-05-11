package com.learn.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Remove {

	public static String remove(String filepath) {
		String result = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			Socket socket = new Socket("127.0.0.1", 1314);
			OutputStream out = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			dis = new DataInputStream(inputStream);
			dos = new DataOutputStream(out);
			dos.writeUTF("REMOVE");
			dos.writeUTF(filepath);
			result = dis.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
