import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class FrameContents extends JPanel {

	//contains the logic to generate password
	static Generator generator = new Generator();

	//represents the middle of the window as it is not resizable
	public final int MIDDLE = 200;

	//the default length for a password to be generated
	public final int DEFAULTPASSWORDLENGTH = 16;

	//represents the max password length
	public final int MAXPASSWORDLENGTH = 22;

	JLabel title = new JLabel("Password Generator");
	JLabel lengthPrompt = new JLabel("Password length: ");
	JLabel includeUppercaseLettersPrompt = new JLabel("Include uppercase Letters: ");
	JLabel includeLowercaseLettersPrompt = new JLabel("Include lowercase Letters: ");
	JLabel includeSymbolsPrompt = new JLabel("Include special characters: ");
	JLabel includeEasyInputPasswordPrompt = new JLabel("Make password easy to type: ");

	JLabel includeNumbersPrompt = new JLabel("Include numbers: ");

	JButton generatePassword = new JButton("Generate Password");
	JButton copyPassword = new JButton();
	JButton easyInputInfo = new JButton();

	ImageIcon copyIcon = new ImageIcon("copy.png");

	JTextField passwordContainer = new JTextField();
	
	JComboBox<Integer> passwordLengthBox = new JComboBox<Integer>();
	
	JCheckBox includeUppercaseLetters = new JCheckBox();	
	JCheckBox includeLowercaseLetters = new JCheckBox();
	JCheckBox includeSymbols = new JCheckBox();
	JCheckBox includeEasyInput = new JCheckBox();

	public FrameContents() {
		this.setLayout(null);
		setPanelContents();
		addComboBoxItems();
	} 

	//adds the items numbers for password length to the combo box
	public void addComboBoxItems() {

		//loop for each number from 1 to the max password length
		for (int i = 1; i < MAXPASSWORDLENGTH; i++) {

			//add the number to the combo box
			passwordLengthBox.addItem(i);
		}

		//set the number in the combo box to the default password length
		passwordLengthBox.setSelectedItem(DEFAULTPASSWORDLENGTH);
	}
	
	//sets the contents of the panel 
	public void setPanelContents() {

		addListeners();

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
		includeSymbolsPrompt.setFont(bodyFont);
		includeNumbersPrompt.setFont(bodyFont);
		includeEasyInputPasswordPrompt.setFont(bodyFont);
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
		this.add(includeSymbols);
		this.add(includeSymbolsPrompt);
		this.add(generatePassword);
		this.add(copyPassword);
		this.add(includeEasyInputPasswordPrompt);
		this.add(includeEasyInput);
		this.add(easyInputInfo);
	}

	//this method sets the position and sizes for each UI element in the interface
	void setItemBounds(){
		
		title.setBounds(30, 1, 1000, 30);		
		
		passwordLengthBox.setBounds(165, 55, 70, 20);
		lengthPrompt.setBounds(35, 50, 1000, 30);	
		
		includeUppercaseLetters.setBounds(235, 95, 20, 20);
		includeUppercaseLettersPrompt.setBounds(35, 80, 1000, 50);

		includeLowercaseLetters.setBounds(235, 125, 20, 20);
		includeLowercaseLettersPrompt.setBounds(35, 85, 1000, 100);
		
		includeSymbols.setBounds(235, 155, 20 , 20);
		includeSymbolsPrompt.setBounds(35, 90, 1000, 150);

		includeEasyInput.setBounds(235, 185, 20, 20);
		includeEasyInputPasswordPrompt.setBounds(35, 95, 1000, 200);

		generatePassword.setBounds(30, 220, 230, 20);
	
		checkForImage();
		
		copyPassword.setBounds(230, 250, 30, 30);

		passwordContainer.setBounds(30, 250, 190, 30);

		//set the check boxes to true by default
		includeUppercaseLetters.setSelected(true);
		includeLowercaseLetters.setSelected(true);
		includeSymbols.setSelected(true);
	}

	public void addListeners(){

		copyPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(passwordContainer.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);

			}
		});

		generatePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordContainer.setText(passwordLengthBox.getSelectedItem().toString());
				generator.generatePassword(includeUppercaseLetters.isSelected(),
						includeLowercaseLetters.isSelected(),
						includeSymbols.isSelected(),
						includeEasyInput.isSelected(),
						(Integer) passwordLengthBox.getSelectedItem());
			}
		});
	}

	public void checkForImage() {
				
		File imageCheck = new File("copy.png");
		
		if(imageCheck.exists()) {
			System.out.println("Image found");
			this.copyPassword.setIcon(copyIcon);
		}else {
			System.out.println("Image not found");
		}		
	}
}
