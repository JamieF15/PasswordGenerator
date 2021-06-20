import java.awt.Font;

import javax.swing.*;

public class FrameContents extends JPanel {
	
	public final int MIDDLE = 200;
	public final int DEFAULTPASSWORDLENGTH = 16;
	
	JLabel title = new JLabel("Generate Password");	
	JLabel lengthPrompt = new JLabel("Choose length: ");
	JLabel includeUppercaseLettersPrompt = new JLabel("Include uppercase Letters: ");
	JLabel includeLowercaseLettersPrompt = new JLabel("Include lowercase Letters: ");


	JTextField passwordContainer = new JTextField();
	
	JComboBox<Integer> passwordLengthBox = new JComboBox<Integer>();
	
	JCheckBox includeUppercaseLetters = new JCheckBox();
	
	JCheckBox includeLowercaseLetters = new JCheckBox();
	
	JCheckBox includeSymbols = new JCheckBox();

	public FrameContents() {
		this.setLayout(null);
		setPanelContents();
		addComboBoxItems();
	} 
	
	public void addComboBoxItems() {
		
		for (int i = 1; i < 21; i++) {
			passwordLengthBox.addItem(i);
		}
		
		passwordLengthBox.setSelectedItem(DEFAULTPASSWORDLENGTH);
	}
	
	//sets the contents of the panel 
	public void setPanelContents() {
	
		setFonts();
		
		addItemsToPanel();
		
		setItemBounds();
	}
	
	
	void setFonts() {
		
		Font headingFont = new Font ("Arial Black", Font.BOLD, 20);
		Font bodyFont = new Font ("Arial Black", Font.PLAIN, 12);
		
		title.setFont(headingFont);
		lengthPrompt.setFont(bodyFont);		
		includeUppercaseLettersPrompt.setFont(bodyFont);
		includeLowercaseLettersPrompt.setFont(bodyFont);
	}
	
	void addItemsToPanel() {
		
		this.add(title);	
		this.add(passwordContainer);
		this.add(passwordLengthBox);
		this.add(lengthPrompt);
		this.add(includeUppercaseLetters);
		this.add(includeUppercaseLettersPrompt);
		this.add(includeLowercaseLetters);
		this.add(includeLowercaseLettersPrompt);
	}
	
	void setItemBounds(){
		
		title.setBounds(30, 1, 1000, 100);
		
		passwordContainer.setBounds(30, 300, 230, 30);
		
		passwordLengthBox.setBounds(150, 100, 100, 30);
		lengthPrompt.setBounds(35, 100, 1000, 30);	
		
		includeUppercaseLetters.setBounds(210, 145, 20, 20);
		includeUppercaseLettersPrompt.setBounds(35, 130, 1000, 50);
		
		includeLowercaseLetters.setBounds(210, 175, 20, 20);
		includeLowercaseLettersPrompt.setBounds(35, 135, 1000, 100);
		
	}
}
