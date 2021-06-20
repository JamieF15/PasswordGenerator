import java.awt.Font;

import javax.swing.*;

public class FrameContents extends JPanel {

	
	public final int MIDDLE = 200;
	
	JLabel title = new JLabel("Generate Password");
	
	JTextField passwordContainer = new JTextField();
	
	JComboBox<Integer> passwordLength = new JComboBox<Integer>();
	
	int[] a = new int[10];
	
	public FrameContents() {
		setFrameContents();
		addComboBoxItems();
	} 
	
	public void addComboBoxItems() {
		
		for (int i = 0; i < 10; i++) {
			passwordLength.addItem(i);
			a[i] = i;
		}
		
		passwordContainer.setText(Integer.toString(a.length));
	}
	
	public void setFrameContents() {
		
		this.setLayout(null);
		title.setVisible(true);
		Font font = new Font ("Arial Black", Font.BOLD, 20);
		title.setFont(font);
		this.add(title);
		
		this.add(passwordContainer);
		
		title.setBounds(30, 1, 1000, 100);
		passwordContainer.setBounds(30, 300, 200, 30);
	}
}
