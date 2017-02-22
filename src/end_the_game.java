import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class end_the_game extends Frame {
	JFrame frame=new JFrame("snakegame");
	JButton button1=new JButton("another game");
	JButton button2=new JButton("quit");
	JPanel jp1 = new JPanel();JPanel jp2 = new JPanel();  
	
	public end_the_game(int i)
	{	JLabel l1=new JLabel();
		if(i==1)
		{l1=new JLabel("YOU WIN!Victory!");}
		if(i==2)
		{l1=new JLabel("LOSER!PLZ STOP PLAYING THE GAME AGAINE!");}
		jp1.add(button1);
		jp1.add(button2);frame.add(jp1);
		jp2.add(l1);frame.add(jp2);
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
		/*button1.addActionListener(new ActionListener() {  
            
            public void actionPerformed(ActionEvent e) {  
            	y.setVisible(false);
            	frame.setVisible(false);
            	Yard n=new Yard();
        		n.launch();
            }     
        }); */
		
		
	}
}

