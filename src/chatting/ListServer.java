package chatting;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ListServer extends JFrame {
	
	public Vector<ListServerThread> lst = new Vector<ListServerThread>();
	Vector<ListData> list = new Vector<ListData>();
	
	ServerSocket serverSocket;
	AcceptThread acceptThread;
	
	class AcceptThread extends Thread{
		@Override
		public void run() {
			while(true) {
				try {
					Socket socket = serverSocket.accept();
					InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
					ListServerThread st = new ListServerThread(ListServer.this, socket);				
					lst.add(st);
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		
		}
	}
	
	
	private JPanel contentPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListServer frame = new ListServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
	}

	protected JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("ServerStart");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int p = 5001;
						serverSocket = new ServerSocket(p);
						acceptThread = new AcceptThread();
						acceptThread.setDaemon(true);
						acceptThread.start();
						
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(159, 68, 97, 23);
		}
		return btnNewButton;
	}
}
