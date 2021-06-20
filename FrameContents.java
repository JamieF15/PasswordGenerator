import java.awt.Font;

import javax.swing.*;

public class FrameContents extends JPanel {

	
	public final int MIDDLE = 200;
	
	JLabel title = new JLabel("Generate Password");
	
	JTextField passwordContainer = new JTextField();
	
	public FrameContents() {
		setFrameContents();
	} 
	
	public void setFrameContents() {
		
		this.setLayout(null);
		title.setVisible(true);
		Font font = new Font ("Arial Black", Font.BOLD, 20);
		title.setFont(font);
		this.add(title);
		
		this.add(passwordContainer);
		
		passwordContainer.setBounds(50, MIDDLE, 200, 30);
		title.setBounds(50, MIDDLE, 1000, 100);

	}
}
