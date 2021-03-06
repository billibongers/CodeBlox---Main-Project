import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		lblNewLabel.setBounds(117, 11, 257, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setFont(new Font("Arial", Font.PLAIN, 50));
		lblPin.setBounds(10, 11, 105, 53);
		contentPane.add(lblPin);
		 BackgroundWorkings process= new BackgroundWorkings();
		
		
		JButton[] keypad=new JButton[20];//Make the keypad array
		for (int y=0;y<3;y++)
			for (int x=0;x<4;x++)//Make Buttons on gui
			{	
				if((x+y*4)<10)
					keypad[x+y*4] = new JButton(x+y*4+"");
				else if((x+y*4)<11) keypad[x+y*4] = new JButton("C");
				else keypad[x+y*4] = new JButton("E");
					keypad[x+y*4].setFont(new Font("Arial", Font.PLAIN, 50));
				keypad[x+y*4].setBounds(29+80*x, 85+80*y, 70, 70);
				int p=x;int q=y;
				keypad[x+y*4].addMouseListener(new MouseAdapter() {@Override//Deal with clicks
					public void mouseClicked(MouseEvent arg0) 
						{
							
							if((p+q*4)<10)
								lblNewLabel.setText(lblNewLabel.getText()+(p+(q*4))+"");
							else if((p+q*4)<11) lblNewLabel.setText("");
							else {process.submitPin(Integer.parseInt(lblNewLabel.getText()));
								lblNewLabel.setText("");}
						}});
				contentPane.add(keypad[x+y*4]);
			}
		
		
	}
}
