package com.learn.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class UploadMethod {
	/**
	 * @param args
	 */
	private String basepath;

	public UploadMethod(String basepath, String stuffix, int fileSize) {
		super();
		this.basepath = basepath;
		this.stuffix = stuffix;
		this.fileSize = fileSize;
	}

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public String getStuffix() {
		return stuffix;
	}

	public void setStuffix(String stuffix) {
		this.stuffix = stuffix;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	private String stuffix;
	private int fileSize;

	public String upload(InputStream inputStream) {
		String result = "ERROR";
		File file = new File(basepath);
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println("upload path:" + basepath);
		// ������ֽ���ȥд���ļ�����Ϊ��������ݿ������ı����߶������ļ�
		FileOutputStream fos = null;
		String uploadFileName = "";
		try {
			// ׼��д·��
			UUID uuid = UUID.randomUUID();
			String path = uuid.toString().replaceAll("-", "");
			String folder1 = path.substring(0, 2).toUpperCase();
			String folder2 = path.substring(2, 4).toUpperCase();
			String fileName = path.substring(4);
			File subFile = new File(basepath + "/" + folder1 + "/" + folder2
					+ "/");
			if (!subFile.exists()) {
				subFile.mkdirs();
				System.out.println(basepath + "/" + folder1 + "/" + folder2
						+ "/" + " create folder success");
			}
			uploadFileName = basepath + "/"
					+ (folder1 + "/" + folder2 + "/" + fileName).toUpperCase()
					+ "." + stuffix;
			// ��ʼд�ļ�
			fos = new FileOutputStream(uploadFileName);

			int read = 0;
			byte[] buf = new byte[1024];
			System.out.println("virtual times:" + (float) fileSize/ (float) buf.length);
			double times = Math.ceil((float) fileSize / (float) buf.length);
			// ��socket.inputsteam ��ȡ���
			for (int i = 0; i < times; i++) {
				// socket.inputsteam һ������ȡ512k������
				while ((read = inputStream.read(buf)) != -1) {
					fos.write(buf, 0, read);
					fos.flush();
				}
			}
			System.out.println("file save path:" + uploadFileName);
			result = "SUCCESS";
			System.out.println(uploadFileName + " file save success");
		} catch (Exception e) {
			result = "ERROR";
			System.out.println(uploadFileName + " file save error");
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
