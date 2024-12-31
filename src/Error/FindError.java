package Error;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

@SuppressWarnings("unused")
public class FindError {
	
	JFrame findErrorWindow;
	JLabel find;
	JButton yes;
	public static boolean FIND = false;
	
	public FindError() {
		
		findErrorWindow = new JFrame("查找错误");
		findErrorWindow.setSize(300,200);
		findErrorWindow.setResizable(false);
		findErrorWindow.setLayout(null);
		Toolkit kit2 = Toolkit.getDefaultToolkit();
		Image image2 = kit2.getImage("src/Picture/picture3.JPG");
		findErrorWindow.setIconImage(image2);
		
		String label[] = {"查无此人","确定"};
		find = new JLabel(label[0]);
		yes = new JButton(label[1]);
		
        find.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		findErrorWindow.add(find);
		findErrorWindow.add(yes);
		
		find.setBounds(115, 50, 250, 30);
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
		
		FIND = true;
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Dimension dim1 = kit1.getScreenSize();
		int x1 = (dim1.width-findErrorWindow.getSize().width)/2;
		int y1 = (dim1.height-findErrorWindow.getSize().height)/2;
		findErrorWindow.setLocation(x1,y1);
		
		findErrorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		findErrorWindow.validate();
		findErrorWindow.setVisible(true);	
		findErrorWindow.setAlwaysOnTop(true);
		
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				FIND = false;
				findErrorWindow.dispose();
			}			
		}
		
	}

}
