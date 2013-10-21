package bigsky;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class SmallChat  {

	private JFrame frmBluetext;
	public static final JTextField textField = new JTextField();
	private JButton btnName;
	private JButton btnNewButton;

	
	
	private JButton send;
	private JScrollPane scrollPane;
	private JTextPane textPane;

	
	private int offset = 0;
	private static int textCount = -1;
	
	private Contact me;
	private Contact you;
	private TextMessage sent;

	//private TrayIcon notification = new TrayIcon(new ImageIcon(TaskBar.class.getResource("BlueText.gif"), "tray icon").getImage());
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SmallChat window = new SmallChat(new Contact("Jonathan", "Mielke", "6185204620", ""), new Contact("Friendly", "Friend", "55555555555", ""));
					window.frmBluetext.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * initiallizes a quick chat window
	 * @param me
	 * @param you
	 */
	public SmallChat(Contact me, Contact you) {
		initialize();
		
		this.me = me;
		this.you = you;
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//final SystemTray tray = SystemTray.getSystemTray();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		send = new JButton("Send");
		
		frmBluetext = new JFrame();
		frmBluetext.getRootPane().setDefaultButton(send);
		frmBluetext.setResizable(false);
		frmBluetext.getContentPane().setBackground(Color.DARK_GRAY);
		frmBluetext.setTitle("BlueText");
		frmBluetext.setBounds(gd.getDisplayMode().getWidth() - 243, gd.getDisplayMode().getHeight() - 385, 236, 340);
		frmBluetext.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBluetext.getContentPane().setLayout(null);
		
		frmBluetext.getContentPane().add(send);
		
		
		
		textField.setBounds(0, 289, 230, 23);
		frmBluetext.getContentPane().add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 24, 230, 264);
		frmBluetext.getContentPane().add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		
//		scrollPane.setViewportView(textArea);
//		textArea.setBackground(Color.LIGHT_GRAY);
//		textArea.setForeground(Color.BLUE);
//		textArea.setFont(new Font("Courier New", Font.PLAIN, 10));
//		textArea.setLineWrap(true);
//		textArea.setTabSize(2);
//		textArea.setWrapStyleWord(true);
//		textArea.setEditable(false);
		
		
//		try {
//			tray.add(notification);
//		} catch (AWTException e2) {
//
//		}
		
		
		btnName = new JButton("Jonathan");
		btnName.setOpaque(false);
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnName.setVerticalAlignment(SwingConstants.TOP);
		btnName.setBounds(0, 1, 139, 23);
		frmBluetext.getContentPane().add(btnName);
		
		btnNewButton = new JButton("Settings");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(137, 1, 93, 23);
		frmBluetext.getContentPane().add(btnNewButton);
		
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sent = new TextMessage(me, you, textField.getText());

				try {
								
					updateConv(sent);
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				textField.setText("");
			}
		});
		

	}
	
	
	protected void updateConv(TextMessage text) throws BadLocationException{
	
		if(!text.getContent().trim().isEmpty() && text.getSender().equals(me)){
			text.setContent(text.getSender().getFirstName() + ":\t" + text.getContent() + "\n\n");
			textPane.getDocument().insertString(offset, text.getContent(), null);
			offset+=text.getContent().length();
			textCount++;
			TaskBar.myTextHistory.add(text);
			
			try {
				MessageHost.ps2.writeObject(text);
				MessageHost.ps2.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//notification.displayMessage("Message", "MESSAGE", TrayIcon.MessageType.NONE);
		}
		
			
		if(!text.getContent().trim().isEmpty() && text.getSender().equals(you)){
			text.setContent(text.getSender().getFirstName() + ":\t" + text.getContent() + "\n\n");
			textPane.getDocument().insertString(offset, text.getContent(), null);
			offset+=text.getContent().length();
		}
	}

	public Contact getLocalContact()
	{
		return me;
	}
	
	public Contact getFromContact(){
		return you;
	}
	
	public void recievedText(TextMessage text) throws BadLocationException{
		updateConv(text);
	}

	public static int getMyTextCount(){
		return textCount;
	}
	
	public JFrame getFrmBluetext() {
		// TODO Auto-generated method stub
		return frmBluetext;
	}
}
