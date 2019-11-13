package chatting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	
	Socket socket;
//	InputStreamReader reader;
	ObjectInputStream ois;
	
//	BufferedReader read;
	ChattClient client;
	boolean threadFlag = true;
	
	public ClientThread(ChattClient client, Socket socket) {
		try {
			this.client = client; //textArea등을 접근하기 위해서 필요.
			this.socket = socket;
			//stream생성시 outputstream을 먼저 만들어야 통신이 원활. input 먼저 만들면 random. 아마도 버그
			//출력스트림 항상 먼저!
						
			InputStream is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			
			start(); //스레드를 시작
			}catch(Exception ex) {
			}
		}
	@Override
	public void run() {
		
		while(threadFlag) {
			try {
				ChattData data = (ChattData)ois.readObject();
				switch(data.getCommand()) {
				case ChattData.LOG_OUT:
					client.userList.remove(data.getId());
					client.viewMessage(data.getId() + data.getMessage());
//					client.getList().updateUI();
					break;
					
				case ChattData.LOG_IN:
					for(String u : data.getUsers()) {
						client.userList.add(u);
					}
					client.getList().setListData(client.userList); //내가 로그인 했을 때
					
					
					
//					client.getList().updateUI();
					break;
					
				case ChattData.USERS:
					client.userList.add(data.getUsers().get(0));
					client.getList().setListData(client.userList);
					client.viewMessage(data.getId() + ":" + data.getMessage());
//					client.getList().updateUI();
					break;
					
				case ChattData.SERVER_STOP:
					threadFlag = false;
					client.userList.clear();
					client.getList().setListData(client.userList);
					client.viewMessage(data.getId() + ":"+ data.getMessage());
					break;
					
				case ChattData.MESSAGE:
					client.viewMessage(data.getId() + ":" + data.getMessage());
					break;
					
				case ChattData.WHISPER:
					client.viewMessage(data.getId() + "의 귓속말 : " + data.getMessage());
					break;
				}
				
				
				
				} catch (Exception e) {
//					client.viewMessage(e.toString());
				}
		}
		try {
			ois.close();
			client.oos.close();
			socket.close();
		}catch(Exception ex){
			
		}
	}
}
