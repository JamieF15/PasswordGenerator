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
	public static Generator generator = new Generator();

	private static JCheckBox[] checkBoxes;
	private static JLabel[] labels;

	//represents the middle of the window as it is not resizable
	public final int MIDDLE = 200;

	//the default length for a password to be generated
	public final int DEFAULTPASSWORDLENGTH = 16;

	//represents the max password length
	public final int MAXPASSWORDLENGTH = 31;

	JLabel title = new JLabel("Password Generator");
	JLabel lengthPrompt = new JLabel("Password length: ");
	JLabel includeUppercaseLettersPrompt = new JLabel("Include uppercase Letters: ");
	JLabel includeLowercaseLettersPrompt = new JLabel("Include lowercase Letters: ");
	JLabel includeSymbolsPrompt = new JLabel("Include special characters: ");
	JLabel includeEasyInputPasswordPrompt = new JLabel("Make password easy to type: ");
	JLabel includeNumbersPrompt = new JLabel("Include numbers: ");
	JLabel questionMark = new JLabel();

	JButton generatePassword = new JButton("Generate Password");
	JButton copyPassword = new JButton();
	JButton easyInputInfo = new JButton();

	ImageIcon copyIcon = new ImageIcon("copy.png");
	ImageIcon questionMarkIcon = new ImageIcon("questionmark.png");

	JTextField passwordContainer = new JTextField();
	
	JComboBox<Integer> passwordLengthBox = new JComboBox<Integer>();
	
	JCheckBox includeUppercaseLetters = new JCheckBox();	
	JCheckBox includeLowercaseLetters = new JCheckBox();
	JCheckBox includeSymbols = new JCheckBox();
	JCheckBox includeNumbers = new JCheckBox();
	JCheckBox includeEasyInput = new JCheckBox();

	public FrameContents() {
		this.setLayout(null);
		setPanelContents();
		addComboBoxItems();
	}

	public static JLabel[] getLabels(){
		return  labels;
	}

	public static JCheckBox[] getCheckBoxes(){
		return checkBoxes;
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

		setLabels();
	}
	
	//sets the fonts for the text in the UI
	void setFonts() {

		//font for the headings
		Font headingFont = new Font ("Arial Black", Font.BOLD, 20);

		//font for the body
		Font bodyFont = new Font ("Arial Black", Font.PLAIN, 12);
		
		title.setFont(headingFont);
		lengthPrompt.setFont(bodyFont);		
		includeUppercaseLettersPrompt.setFont(bodyFont);
		includeLowercaseLettersPrompt.setFont(bodyFont);
		includeSymbolsPrompt.setFont(bodyFont);
		includeNumbersPrompt.setFont(bodyFont);
		includeEasyInputPasswordPrompt.setFont(bodyFont);
	}

	//adds the UI elements to the interface
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
		this.add(questionMark);
		this.add(includeNumbersPrompt);
		this.add(includeNumbers);

		checkBoxes = new JCheckBox[]{includeUppercaseLetters,
									includeLowercaseLetters,
									includeSymbols,
									includeNumbers,
									includeEasyInput};

		labels = new JLabel[]{lengthPrompt,
				includeUppercaseLettersPrompt,
				includeLowercaseLettersPrompt,
				includeSymbolsPrompt,
				includeNumbersPrompt,
				includeEasyInputPasswordPrompt};
	}

	void setLabels(){

		int positionOffset = 0;
		int distanceDifference = 30;

		title.setBounds(30, 1, 1000, 30);
		questionMark.setBounds(10, 205, 20, 20);
		copyPassword.setBounds(230, 300, 30, 35);
		passwordContainer.setBounds(30, 310, 190, 20);
		generatePassword.setBounds(30, 275, 230, 20);
		passwordLengthBox.setBounds(160, 55, 50, 20);

		for(int i = 0; i < labels.length; i++){

			labels[i].setBounds(35, 50 + positionOffset, 1000, 30);
			positionOffset += distanceDifference;
		}

		positionOffset = 0;

		for(int i = 0; i < checkBoxes.length; i++){

			checkBoxes[i].setBounds(230, 85 + positionOffset, 20, 20);
			positionOffset += distanceDifference;
		}

		checkForImage();

		includeUppercaseLetters.setSelected(true);
		includeLowercaseLetters.setSelected(true);
		includeSymbols.setSelected(true);
		includeEasyInput.setSelected(false);
		includeNumbers.setSelected(true);
	}

	//adds listeners to the buttons in within UI
	public void addListeners(){

		//copy password button
		copyPassword.addActionListener(new ActionListener() {
			@Override
			//when the button is clicked, copy the contents of the password container to the clipboard
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(passwordContainer.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});

		//generate password button
		generatePassword.addActionListener(new ActionListener() {
			@Override

			//when the generate password button is clicked,
			public void actionPerformed(ActionEvent e) {

				if(checkIfABoxIsChecked()) {
					passwordContainer.setText(null);

					generator.generatePassword((Integer) passwordLengthBox.getSelectedItem());

					passwordContainer.setText(generator.getPassword());
				}else{
					passwordContainer.setText("Select password parameters");
				}
			}
		});
	}

	//checks if any check box is checked
	public boolean checkIfABoxIsChecked(){

		for(int i = 0; i < checkBoxes.length; i++){

			if(checkBoxes[i].isSelected()) {
				return true;
			}
		}
			return false;
	}
	//checks if the images for the buttons exist and sets them
	public void checkForImage() {

		//file that represents the copy icon
		File copyImageCheck = new File("copy.png");

		File questionMarkImageCheck = new File ("questionmark.png");

		//if the file exists, print that is has been found and assign it to the button
		if(copyImageCheck.exists()) {
			System.out.println("Copy image found");
			this.copyPassword.setIcon(copyIcon);
		}else {
			System.out.println("Copy image not found");
		}

		if(questionMarkImageCheck.exists()){
			System.out.println("Question mark found");
			questionMark.setIcon(questionMarkIcon);
		}else{
			System.out.println("Question mark not found");
		}
	}
}
