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
public class IDSameError {
	
	JFrame IDSameErrorWindow;
	JLabel IDSame;
	JButton yes;
	public static boolean IDSAME = false;
	
	public IDSameError() {
		
		IDSameErrorWindow = new JFrame("重复错误");
		IDSameErrorWindow.setSize(300,200);
		IDSameErrorWindow.setResizable(false);
		IDSameErrorWindow.setLayout(null);
		Toolkit kit2 = Toolkit.getDefaultToolkit();
		Image image2 = kit2.getImage("src/Picture/picture3.JPG");
		IDSameErrorWindow.setIconImage(image2);
		
		String label[] = {"学生学号、教师编号或课程安排发生重复","确定"};
		IDSame = new JLabel(label[0]);
		yes = new JButton(label[1]);
		
		IDSame.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
        IDSameErrorWindow.add(IDSame);
        IDSameErrorWindow.add(yes);
		
        IDSame.setBounds(10, 50, 300, 30);
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
		
		IDSAME = true;
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Dimension dim1 = kit1.getScreenSize();
		int x1 = (dim1.width-IDSameErrorWindow.getSize().width)/2;
		int y1 = (dim1.height-IDSameErrorWindow.getSize().height)/2;
		IDSameErrorWindow.setLocation(x1,y1);
		
		IDSameErrorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		IDSameErrorWindow.validate();
		IDSameErrorWindow.setVisible(true);	
		IDSameErrorWindow.setAlwaysOnTop(true);
		
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				IDSAME = false;
				IDSameErrorWindow.dispose();
			}			
		}
		
	}

}
