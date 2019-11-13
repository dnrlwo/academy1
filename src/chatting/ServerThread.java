package chatting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

//메세지를 수신받기위한 쓰레드.
//클라이언트 실행 때마다 생성된다
//안만들면 blocking.
public class ServerThread extends Thread {

	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	boolean serverThreadFlag = true;

//	InputStreamReader reader; 여기서 objectinputstream으로 바꿈. 그래야 chattdata정보를 사용 가능. 그러나 다른언어랑 호환안된다. bytestream으로 써야 호환
//	OutputStreamWriter writer;

//	BufferedReader read;
	ChattServer server;
	// 받은 정보를 각 클라이언트에게 뿌리자

	public ServerThread(ChattServer server, Socket socket) {
		try {
			this.server = server; // textArea등을 접근하기 위해서 필요.
			this.socket = socket;

			// stream생성시 outputstream을 먼저 만들어야 통신이 원활. input 먼저 만들면 random. 아마도 버그
			// 출력스트림 항상 먼저!
			OutputStream os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);

			InputStream is = socket.getInputStream();
			ois = new ObjectInputStream(is);

			start(); // 스레드를 시작
		} catch (Exception ex) {
		}
	}

	@Override
	public void run() {

		while (serverThreadFlag) {
			try {
				ChattData data = (ChattData) ois.readObject();
				switch (data.getCommand()) {
				case ChattData.LOG_IN:
					// 서버가 갖고있는 유저명단을 현 접속자에게 전달
					ChattData sendData = new ChattData();
					sendData.setCommand(ChattData.LOG_IN);
					for (String u : server.userList) {
						sendData.getUsers().add(u);
					}
					// 로그인 유저 명단을 Jlist에 추가
					oos.writeObject(sendData);
					oos.flush();

					server.userList.add(data.id); // Vector
					server.getList().setListData(server.userList); // Jlist 갱신

					// 접속한 모든 유저에게 현재 접속한 유저명을 전송
					ChattData addUser = new ChattData();
					addUser.setCommand(ChattData.USERS);
					addUser.setId(data.id);
					addUser.setMessage("님이 로그인 함.");
					addUser.users.add(data.id);// 마지막에 들어온 놈

					//
					for (ServerThread st : server.users) {
						st.oos.writeObject(addUser);
						st.oos.flush();
					}

					break;

				case ChattData.LOG_OUT:
					// Jlist와 serverthread에서 자신을 찾아 index값 가져오기
					for (int i = 0; i < server.userList.size(); i++) { // 또는 (int i =server.userList.size()-1 ; i>=0 ;
																		// i--) 이렇게 이게 정석임
						String str = server.userList.get(i);
						if (str.equals(data.getId())) {
							server.users.remove(i);
							server.userList.remove(i);
							socket.close();
							serverThreadFlag = false;
							break;
						}
					}
					// 모든유저에게 자신이 logout 할 것임을 전달
					for (ServerThread st : server.users) {
						st.oos.writeObject(data);
						st.oos.flush();
					}
					break;

				case ChattData.WHISPER:
					for (String findUser : data.users) {
						for (int i = 0; i < server.userList.size(); i++) {
							String temp = server.userList.get(i);
							if (temp.equalsIgnoreCase(findUser)) {
								ServerThread st = server.users.get(i);
								st.oos.writeObject(data);
								st.oos.flush();
								break; // 한 명 찾았으면 루프에서 나가기
							}
						}
					}
					break;

				case ChattData.MESSAGE:
					server.viewMessage(data.getId() + ":" + data.getMessage());
					for (int i = server.userList.size() - 1; i >= 0; i--) {
						ServerThread st = server.users.get(i);
						st.oos.writeObject(data);
						st.oos.flush();

					}
					break;

				}
			} catch (Exception e) {

			}
		}
	}
}
