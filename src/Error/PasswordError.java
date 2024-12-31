package Error;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("unused")
public class PasswordError {
	
	JFrame PasswordErrorWindow;
	JLabel password;
	JButton yes;
	public static boolean PASSWORD = false;
	
	public PasswordError() {
		
		PasswordErrorWindow = new JFrame("密码不同");
		PasswordErrorWindow.setSize(300,200);
		PasswordErrorWindow.setResizable(false);
		PasswordErrorWindow.setLayout(null);
		Toolkit kit2 = Toolkit.getDefaultToolkit();
		Image image2 = kit2.getImage("src/Picture/picture3.JPG");
		PasswordErrorWindow.setIconImage(image2);
		
		String label[] = {"两次输入的密码不相同","确定"};
		password = new JLabel(label[0]);
		yes = new JButton(label[1]);
		
		password.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
        PasswordErrorWindow.add(password);
        PasswordErrorWindow.add(yes);
		
        password.setBounds(70, 50, 250, 30);
		yes.setBounds(110, 100, 70, 40);
		
		MyListener listen = new MyListener();
		yes.addActionListener(listen);
		
		yes.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                yes.setBackground(Color.lightGray);
            }
		});
		yes.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                yes.setBackground(null);
            }
		});
		
		yes.setContentAreaFilled(false);
		
		yes.setFocusPainted(false);
		
	}
	
	public void show() {
		
		PASSWORD = true;
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Dimension dim1 = kit1.getScreenSize();
		int x1 = (dim1.width-PasswordErrorWindow.getSize().width)/2;
		int y1 = (dim1.height-PasswordErrorWindow.getSize().height)/2;
		PasswordErrorWindow.setLocation(x1,y1);
		
		PasswordErrorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		PasswordErrorWindow.validate();
		PasswordErrorWindow.setVisible(true);	
		PasswordErrorWindow.setAlwaysOnTop(true);
		
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				PASSWORD = false;
				PasswordErrorWindow.dispose();
			}			
		}
		
	}

}

