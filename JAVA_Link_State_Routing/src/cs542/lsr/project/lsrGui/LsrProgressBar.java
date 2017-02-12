package cs542.lsr.project.lsrGui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LsrProgressBar extends JFrame implements Runnable
{
	JProgressBar pb;
	Thread t;
	Container c;
	JFrame jf  = new JFrame();
	LsrProgressBar()
	{
		setTitle("Loading Link State Route Simulator......");
		
		//pb.setIconImage("loading.gif");
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		t = new Thread(this); // intialize object of thread.
		pb = new JProgressBar(0,100); // intialize object of JProgressBar
		pb.setStringPainted(true);
		pb.setForeground(Color.BLUE);
		pb.setFont(new Font("Georgia", Font.BOLD, 20));
		c.add(pb,BorderLayout.CENTER); // adding container to window
		t.start(); //thread starts
		setSize(600,60);
		setLocation(400,300);
		setVisible(true);
		setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	
	public void run()
	{
		for(int i=0;i<=100;i++)
		{
			try
			{	
				Thread.sleep(13);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			pb.setValue(i);
		}
		dispose();
	}
}