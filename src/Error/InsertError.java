package Error;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

@SuppressWarnings("unused")
public class InsertError {

	JFrame insertErrorWindow;
	JLabel insert;
	JButton yes;
	public static boolean INSERT = false;
	
	public InsertError() {
		
		insertErrorWindow = new JFrame("输入错误");
		insertErrorWindow.setSize(300,200);
		insertErrorWindow.setResizable(false);
		insertErrorWindow.setLayout(null);
		Toolkit kit2 = Toolkit.getDefaultToolkit();
		Image image2 = kit2.getImage("src/Picture/picture3.JPG");
		insertErrorWindow.setIconImage(image2);
		
		String label[] = {"信息不完整","确定"};
		insert = new JLabel(label[0]);
		yes = new JButton(label[1]);
		
		insert.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
        yes.setFont(new Font("华康方圆体W7(P)",Font.PLAIN, 15));
		
        insertErrorWindow.add(insert);
        insertErrorWindow.add(yes);
		
        insert.setBounds(105, 50, 250, 30);
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
		
		INSERT = true;
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Dimension dim1 = kit1.getScreenSize();
		int x1 = (dim1.width-insertErrorWindow.getSize().width)/2;
		int y1 = (dim1.height-insertErrorWindow.getSize().height)/2;
		insertErrorWindow.setLocation(x1,y1);
		
		insertErrorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		insertErrorWindow.validate();
		insertErrorWindow.setVisible(true);	
		insertErrorWindow.setAlwaysOnTop(true);
		
	}
	
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == yes) {
				INSERT = false;
				insertErrorWindow.dispose();
			}			
		}
		
	}
	
}
