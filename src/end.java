import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class end extends Frame {
	JFrame frame=new JFrame("tankwar");
	JButton button1=new JButton("restart(1)");
	JButton button2=new JButton("quit");
	JPanel jp1 = new JPanel();JPanel jp2 = new JPanel();  
	JPanel jp3= new JPanel();
	JLabel l1=new JLabel();
	tankyard ty;
	public end(int i)
	{	JLabel l1=new JLabel();
		if(i==0)
		{l1=new JLabel("you lose the game!");}
		if(i==1)
		{l1=new JLabel("you win the game!");}	
		jp1.add(button1);
		JLabel l2=new JLabel("have u enjoyed the game?");
		jp1.add(button2);frame.add(jp1);
		jp2.add(l1);frame.add(jp2);
		jp3.add(l2);frame.add(jp3);
		frame.setBounds(300,100,600,500);
		frame.setLayout(new FlowLayout()); 
		myevent();
		frame.setLocation(250,250);
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setVisible(true); 
	}
	private void myevent() {
		button2.addActionListener(new ActionListener() {  
            
            public void actionPerformed(ActionEvent e) {  
            	System.exit(0);  
            }     
        });  
		button1.addActionListener(new ActionListener() {  
            
            public void actionPerformed(ActionEvent e) {  
            	ty.player.blood=100;ty.player.flag=0;ty.player.x=20;ty.player.y=20;
           }     
        });  
		
	}
}