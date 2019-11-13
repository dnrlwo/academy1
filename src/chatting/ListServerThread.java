package chatting;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ListServerThread extends Thread {

	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	ListServer server;

	public ListServerThread(ListServer server, Socket socket) {
		try {
			this.server = server;
			this.socket = socket;
			OutputStream os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);

			InputStream is = socket.getInputStream();
			ois = new ObjectInputStream(is);

			start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				ListData data = (ListData) ois.readObject();
				server.list.add(data);
				for(ListServerThread st : server.lst) {
					st.oos.writeObject(data);
					st.oos.flush();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
