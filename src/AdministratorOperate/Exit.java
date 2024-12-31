package AdministratorOperate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

@SuppressWarnings("unused")
public class Exit {
	
	JFrame exitWindow;
	JButton yes;
	JButton no;
	JLabel exitLabel;
	public static boolean EXIT = false;
	
	public Exit() {		
		
		exitWindow = new JFrame("退出");	
		exitWindow.setSize(300,200);
		exitWindow.setResizable(false);
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Image image1 = kit1.getImage("src/Picture/picture3.JPG");
		exitWindow.setIconImage(image1);
		exitWindow.setLayout(null);	
		
		String label[] = {"确定","取消","确认退出吗?"};
		yes = new JButton(label[0]);
		no = new JButton(label[1]);
		exitLabel = new JLabel(label[2]);
				
		yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		no.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		exitLabel.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));

		exitWindow.add(exitLabel);
		exitWindow.add(yes);
		exitWindow.add(no);	
		
		exitLabel.setBounds(100, 50, 100, 30);
		yes.setBounds(70, 100, 70, 40);
		no.setBounds(150, 100, 70, 40);		
		
		MyListener listen = new MyListener();
		yes.addActionListener(listen);
		no.addActionListener(listen);
		
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
		no.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
                no.setBackground(Color.lightGray);
            }
		});
		no.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                no.setBackground(null);
            }
		});
				
		yes.setContentAreaFilled(false);
		no.setContentAreaFilled(false);	
		
		yes.setFocusPainted(false);
		no.setFocusPainted(false);
		
	}
	
	public void show() {
		
		EXIT = true;
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Dimension dim1 = kit1.getScreenSize();
		int x1 = (dim1.width-exitWindow.getSize().width)/2;
		int y1 = (dim1.height-exitWindow.getSize().height)/2;
		exitWindow.setLocation(x1,y1);
		
		exitWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		exitWindow.validate();
		exitWindow.setVisible(true);	
		exitWindow.setAlwaysOnTop(true);
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				System.exit(0);				
			}
			else {
				EXIT = false;
				exitWindow.dispose();
			}
			
		}
		
	}
	
}
