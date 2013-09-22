package bigsky;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Conversation {

	private JFrame frmBluetext;
	private JTextField txtSearch;
	private Contact[] contactList = new Contact[500];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversation window = new Conversation();
					window.frmBluetext.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. 
	 */
	public Conversation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmBluetext = new JFrame();
		frmBluetext.setTitle("BlueText");
		frmBluetext.setBounds(100, 100, 800, 650);
		frmBluetext.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmBluetext.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mnu_new_contact = new JMenuItem("New Contact");
		mnu_new_contact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addContact();
				
			}
		});
		mnFile.add(mnu_new_contact);
		
		JMenuItem mnu_new_conversation = new JMenuItem("New Conversation");
		mnu_new_conversation.addMouseListener(new MouseAdapter() {
		});
		mnFile.add(mnu_new_conversation);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAboutBluetext = new JMenuItem("About BlueText");
		mnHelp.add(mntmAboutBluetext);
		
		JPanel panel = new JPanel();
		frmBluetext.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(16, 6, 190, 29);
		panel.add(txtSearch);
		txtSearch.setText("Search");
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 41, 188, 369);
		panel.add(scrollPane);
		
		for (int i=0;i<contactList.length;i++){
			contactList[i] = new Contact("", "", "", "");
		}
		Contact firstContact = new Contact("Create Contact", null, null, null);
		contactList[499] = firstContact;
		
		DefaultListModel listModel = new DefaultListModel();
		for (int i=0;i<contactList.length;i++){
			listModel.addElement(contactList[i].getFirstName());
		}
			
		
		
		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(226, 0, 150, 35);
		panel.add(tabbedPane_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("New Conversation", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(226, 25, 490, 385);
		panel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(16, 422, 190, 100);
		panel.add(panel_3);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(599, 493, 117, 29);
		panel.add(btnSend);
		
		JTextArea txtrEnterMessageHere = new JTextArea();
		txtrEnterMessageHere.setText("New Message...");
		txtrEnterMessageHere.setBounds(226, 429, 490, 93);
		panel.add(txtrEnterMessageHere);
	}
	
	private void addContact(){
		NewContact newCon = new NewContact();
		newCon.setVisible(true);
		//I still need to figure out how to wait until retContact is filled out
		Contact contactToAdd = newCon.getRetContact();
		
	}
}
