import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MainWindow extends JFrame{

	static Color backColour = new Color(0, 100, 0);
	static MainWindow window = new MainWindow();
	static FrameContents contents = new FrameContents();
	
	public static void main(String[] args) {

        window.add(contents);               
		window.setBounds(10, 10, 400, 400);
		window.setVisible(true);
		window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
