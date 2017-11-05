package rpgSource;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rpgSource.entity.Player;
import rpgSource.util.DescribableList;
import rpgSource.util.DescribableRenderer;

import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class is used to display everything the player sees.
 * @author FlightGuy
 *
 */
public class RPGGUI extends JFrame /*implements Runnable*/{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6038270283388256869L;
	
	/** Represents the choice the player chose.*/
	static int selector = -1;
	JScrollPane sPane;
	private FlowLayout layout;
	static DecimalFormat numberPrinter = new DecimalFormat("###");
	JTabbedPane menu;
	JLabel playerHealth;
	JLabel enemyHealth;
	JLabel playerMp;
	JButton one;
	JButton two;
	JButton three;
	JButton four;
	JTextArea console;
	String tpMp;
	String tpmMp;
	String tpHealth;
	String tpmHealth;
	String teHealth;
	String temHealth;
	String enemyName;
	static RPGGUI ui;
	
	public static RPGGUI getInstance(){
		if(ui == null){
			ui = new RPGGUI();
		}
		return ui;
	}
	
	public RPGGUI(){}
	
	/**
	 * This method updates the GUI so it displays the enemy's current health.
	 * @param eHealth A string representation of the enemy's current health
	 */
	 public void updateEnemyHealth(String eHealth){
		teHealth = eHealth;
		enemyHealth.setText(enemyName + ": " + teHealth + "/" + temHealth);
	}
	 
	 /**
	  * This method updates the GUI so it displays the player's current health.
	  * @param pHealth A string representation of the player's current health
	  */
	 public void updatePlayerHealth(String pHealth){
		tpHealth = pHealth;
		playerHealth.setText("Player: " + tpHealth + "/" + tpmHealth);
	 }
	 
	 public void updatePlayerMp(String pMp) {
		 tpMp = pMp;
		 playerMp.setText("MP: " + tpMp + "/" + tpmMp);
	 }
	 
	 public void startGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	public void appendToConsole(String s) {
		console.append(s);
		console.setCaretPosition(console.getText().length());
	}
	 
	/**
	 * Displays the GUI.
	 */
	public void showGUI(){
		Handler hand = new Handler();
		setAlwaysOnTop(true);
		tpHealth = BattleSim.getMaxPlayerHealth();
		tpmHealth = tpHealth;
		tpMp = BattleSim.getMaxPlayerMP();
		tpmMp = tpMp;
		teHealth = BattleSim.getMaxEnemyHealth();
		temHealth = teHealth;
		enemyName = BattleSim.getEnemyName();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 400, 400);
		layout = new FlowLayout();
		setLayout(layout);
		playerHealth = new JLabel("Player: " + tpHealth + "/" + tpmHealth);
		add(playerHealth);
		playerMp = new JLabel("MP: " + tpMp + "/" + tpmMp);
		add(playerMp);
		enemyHealth = new JLabel(enemyName + ": " + teHealth + "/" + temHealth);
		add(enemyHealth);
		console = new JTextArea(15, 30);
		console.setLineWrap(true);
		sPane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sPane.setBounds(250, 300, 200, 200);
		add(sPane);
		menu = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		menu.setPreferredSize(new Dimension(400, 100));
		menu.addChangeListener(hand);
		add(menu);
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		one.addActionListener(hand);
		two.addActionListener(hand);
		three.addActionListener(hand);
		four.addActionListener(hand);
		//add(one);
		//add(two);
		//add(three);
		//add(four);
		setVisible(true);
	}
	
	public void setActionMenu(Player p) {
		ArrayList<DescribableList> list = p.al;
		
		for(int i = 0; i < list.size(); i++) {
			DefaultListModel<Describable> dlm = new DefaultListModel<Describable>();
			DescribableList dl = list.get(i);
			for (int j = 0; j < dl.size(); j++) {
				dlm.addElement(dl.get(j));
			}
			
			JList<Describable> jl = new JList<Describable>(dlm);
			ListCellRenderer<Describable> lc = new DescribableRenderer();
			jl.setCellRenderer(lc);
			jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jl.setLayoutOrientation(JList.VERTICAL);
			jl.setVisibleRowCount(5);
			menu.addTab(list.get(i).getName(), jl);
		}
	}
    
	/**
	 * Resets the selector.
	 */
	public static void resetSelector(){
		selector = -1;
	}
	
	class Handler implements ActionListener, ChangeListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("1")){
				selector = 0;
			}else if(e.getActionCommand().equals("2")){
				selector = 1;
			}else if(e.getActionCommand().equals("3")){
				selector = 2;
			}else if(e.getActionCommand().equals("4")){
				selector = 3;
			}
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
