import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	//initialize variables; 
	JLabel l1,l2,l3;
	JTextField t1;
	JPasswordField t2;
	JButton b1;
	boolean loggedIn=false;
	
	public Login(){
		super("Snake Game");
		l1=new JLabel("User Name");
		l2=new JLabel("Password");
		l3=new JLabel("Wrong User Name Or Password");
		t1=new JTextField(20);
		t2=new JPasswordField(20);
		b1=new JButton("OK");

		this.setSize(500, 500);
		this.setLayout(null);//if we do not add this coding, it will use default grid.
	
		//this code seems not work in the program.  
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		this.add(l1);
		this.add(t1);
		this.add(l2);
		this.add(t2);
		this.getContentPane().add(b1);
		//add (b1)in to ContenPane container; 
		
		//(x component; y component; width, height )
		l1.setBounds(150,150,100,30);
		t1.setBounds(250,150,100,30);
		l2.setBounds(150,200,100,30);
		t2.setBounds(250,200,100,30);
		b1.setBounds(200,300,100,30);
		b1.addActionListener(this);	
		}
	
	//call above action listener. 	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String username=t1.getText();
		String password=t2.getText();
		
		//dispatchEven works when press the "log in" button, then create a new window. 
		if(username.equals("snake") && password.equals("1234")){	
			loggedIn=true;		
			this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
		}else {
		loggedIn=false;
		this.add(l3);
		l3.setBounds(150, 250, 200, 30);		
		}
	}
}
	