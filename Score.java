import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Score extends JFrame implements ActionListener {
	JLabel l1, l2,l3; 
	JButton b1;
	ArrayList<Integer> score = new ArrayList<Integer>();
	
	boolean credited = false;
	
	public Score(int total){
		score.add(total);
		l1 = new JLabel("Thank for your support");
		l2 = new JLabel("sorce this time: " +total+" points");
		l3 = new JLabel("total credit: "+total+" points");
		b1 = new JButton("EXIT");
		
		this.setSize(600,500);
		//f2.setVisible(true);
		this.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.getContentPane().add(b1);
		
		l1.setBounds(100,30,150,30);
		l2.setBounds(100,70,150,30);
		l3.setBounds(100,110,200,30);
		
		b1.setBounds(200,260,100,30);
		b1.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name2 = e.getActionCommand();
		if (name2.equals("EXIT")) {
			this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
		}
	}
	public void sendToStore(String mycontent){
		FileOutputStream fos = null;
	      File file;
	      try {
	          //Specify the file path here
		  file = new File("C:\\Users\\sstf1\\iCloudDrive\\loyola\\2021 spring\\comp 363\\FinalProject2\\src\\store.txt");
		  fos = new FileOutputStream(file);

	          /* This logic will check whether the file
		   * exists or not. If the file is not found
		   * at the specified location it would create
		   * a new file*/
		  if (!file.exists()) {
		     file.createNewFile();
		  }

		  /*String content cannot be directly written into
		   * a file. It needs to be converted into bytes
		   */
		  byte[] bytesArray = mycontent.getBytes();

		  fos.write(bytesArray);
		  fos.flush();
		  System.out.println("File Written Successfully");
	       } 
	       catch (IOException ioe) {
		  ioe.printStackTrace();
	       } 
	       finally {
		  try {
		     if (fos != null) 
		     {
			 fos.close();
		     }
	          } 
		  catch (IOException ioe) {
		     System.out.println("Error in closing the Stream");
		  }
	       }
	}
	public int readFromStore() {
		String fileName = "C:\\Users\\sstf1\\iCloudDrive\\loyola\\2021 spring\\comp 363\\FinalProject2\\src\\store.txt";
		File file = new File(fileName);
		int ans = 0;
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			ans = scanner.nextInt();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;		
	}

	public void setTotal(double total) {
		int prev = readFromStore();
		sendToStore(Integer.toString((int)total));

		int totalFinal = prev + (int)total; 
		sendToStore(Integer.toString(totalFinal));
		l2.setText("score this time " +(int)total+" points");
		l3.setText("total score"+ totalFinal +" points");
		
		
	}
}