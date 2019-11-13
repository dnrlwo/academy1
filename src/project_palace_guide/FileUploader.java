package project_palace_guide;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUploader extends Thread{
	String fileName;
	String targetFile = "C:\\workspaceN\\SemiProject\\server_icon\\";
	
	public void run() {
		upload();
	}
	
	
	public boolean upload() {
		boolean b = false;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream( fileName);
			
			int pos = fileName.lastIndexOf("\\");
			String temp = fileName.substring(pos+1);
				
			fos = new FileOutputStream(targetFile + temp);
			byte[] data = new byte[4096];
			int len = 0;
			while( (len = fis.read(data)) != -1) {
				fos.write(data, 0, len);
			}
			fos.flush();
			fos.close();
			fis.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return b;
		}
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
