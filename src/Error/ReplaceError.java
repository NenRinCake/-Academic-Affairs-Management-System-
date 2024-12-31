package Error;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

@SuppressWarnings("unused")
public class ReplaceError {
	
	JFrame replaceErrorWindow;
	JLabel replace;
	JButton yes;
	public static boolean REPLACE = false;
	
	public ReplaceError() {
		
		replaceErrorWindow = new JFrame("修改错误");
		replaceErrorWindow.setSize(300,200);
		replaceErrorWindow.setResizable(false);
		replaceErrorWindow.setLayout(null);
		Toolkit kit2 = Toolkit.getDefaultToolkit();
		Image image2 = kit2.getImage("src/Picture/picture3.JPG");
		replaceErrorWindow.setIconImage(image2);
		
		String label[] = {"未进行修改选择或没有能修改的项目","确定"};
		replace = new JLabel(label[0]);
		yes = new JButton(label[1]);
		
        replace.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
		replaceErrorWindow.add(replace);
		replaceErrorWindow.add(yes);
		
		replace.setBounds(25, 50, 250, 30);
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
		
		REPLACE = true;
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Dimension dim1 = kit1.getScreenSize();
		int x1 = (dim1.width-replaceErrorWindow.getSize().width)/2;
		int y1 = (dim1.height-replaceErrorWindow.getSize().height)/2;
		replaceErrorWindow.setLocation(x1,y1);
		
		replaceErrorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		replaceErrorWindow.validate();
		replaceErrorWindow.setVisible(true);	
		replaceErrorWindow.setAlwaysOnTop(true);
		
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				REPLACE = false;
				replaceErrorWindow.dispose();
			}			
		}
		
	}

}
