package chatting;

import java.io.Serializable;//직렬화 작업
import java.util.Vector;
//여러개의 object를 전송하도록~
public class ChattData implements Serializable{
	//서로 다른 컴퓨터에서 클래스를 컴파일 하더라도 동일한 클래스임을 나타내기 위해, 없으면 통신 안될 수도
	private static final long serialVersionUID = 1L; 
	
	String id;
	
	String message;
	int command;

	//command list
	static final int MESSAGE = 0; //메세지 보내기
	static final int LOG_IN = 1; //연결시
	static final int LOG_OUT = 2; //연결종료시
	static final int WHISPER = 3; //귓속말
	static final int USERS = 4; //접속자 명단을 전송할 경우
	static final int SERVER_STOP = 5;
	
	Vector<String> users = new Vector<String>(); //유저 명단, 귓속말 할 때 사용
	

	public String getMessage() {
		return message;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCommand() {
		return command;
	}
	
	public void setCommand(int command) {
		this.command = command;
	}
	
	public Vector<String> getUsers() {
		return users;
	}
	
	public void setUsers(Vector<String> users) {
		this.users = users;
	}
	
	
}
