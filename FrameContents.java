import java.awt.Font;
import java.io.File;
import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class FrameContents extends JPanel {

	//contains the logic to generate password
	public static Generator generator = new Generator();

	//array containing the checkboxes
	private static JCheckBox[] checkBoxes;

	//array containing the labels
	private static JLabel[] labels;

	//the default length for a password to be generated
	private static final int DEFAULTPASSWORDLENGTH = 16;

	//represents the max password length
	private static final int MAXPASSWORDLENGTH = 31;

	JLabel title = new JLabel("Password Generator");
	JLabel lengthPrompt = new JLabel("Password length: ");
	JLabel includeUppercaseLettersPrompt = new JLabel("Include uppercase Letters: ");
	JLabel includeLowercaseLettersPrompt = new JLabel("Include lowercase Letters: ");
	JLabel includeSymbolsPrompt = new JLabel("Include special characters: ");
	JLabel includeEasyInputPasswordPrompt = new JLabel("Make password easy to type: ");
	JLabel includeNumbersPrompt = new JLabel("Include numbers: ");
	JLabel questionMark = new JLabel();

	static JButton generatePassword = new JButton("Generate Password");
	static JButton copyPassword = new JButton();
	static JButton easyInputInfo = new JButton();

	ImageIcon copyIcon = new ImageIcon("copy.png");
	ImageIcon questionMarkIcon = new ImageIcon("questionmark.png");

	static JTextField passwordContainer = new JTextField();

	static JComboBox<Integer> passwordLengthBox = new JComboBox<>();
	
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

	public static int getDEFAULTPASSWORDLENGTH(){
		return DEFAULTPASSWORDLENGTH;
	}

	public static void setPasswordContainer(String text){
		passwordContainer.setText(text);
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
	private void setPanelContents() {

		populateArrays();

		addListeners();

		setFonts();

		addItemsToPanel();

		setLabels();
	}
	
	//sets the fonts for the text in the UI
	private void setFonts() {

		//font for the headings
		Font headingFont = new Font ("Arial Black", Font.BOLD, 20);

		//font for the body
		Font bodyFont = new Font ("Arial Black", Font.PLAIN, 12);
		
		title.setFont(headingFont);

		for (JLabel label : labels) {
			label.setFont(bodyFont);
		}
	}

	//adds the UI elements to the interface
	private void addItemsToPanel() {
		
		this.add(title);
		this.add(passwordContainer);
		this.add(passwordLengthBox);
		this.add(generatePassword);
		this.add(copyPassword);
		this.add(easyInputInfo);
		this.add(questionMark);

		addComponents();
	}

	//populates the arrays of components
	private void populateArrays(){

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

	//adds the components from the arrays
	private void addComponents(){

		for (JLabel label : labels) {
			this.add(label);
		}

		for (JCheckBox checkBox : checkBoxes) {
			this.add(checkBox);
		}
	}

	//sets the labels on the window
	private void setLabels(){

		int positionOffset = 0;

		int distanceDifference = 30;

		title.setBounds(30, 1, 1000, 30);
		questionMark.setBounds(10, 205, 20, 20);
		copyPassword.setBounds(230, 270, 30, 35);
		passwordContainer.setBounds(30, 275, 190, 20);
		generatePassword.setBounds(30, 245, 230, 20);
		passwordLengthBox.setBounds(160, 55, 50, 20);

		for (JLabel label : labels) {

			label.setBounds(35, 50 + positionOffset, 1000, 30);
			positionOffset += distanceDifference;
		}

		positionOffset = 0;

		for (JCheckBox checkBox : checkBoxes) {

			checkBox.setBounds(230, 85 + positionOffset, 20, 20);
			positionOffset += distanceDifference;
		}

		assignImages();

		includeUppercaseLetters.setSelected(true);
		includeLowercaseLetters.setSelected(true);
		includeSymbols.setSelected(true);
		includeEasyInput.setSelected(false);
		includeNumbers.setSelected(true);
	}

	//copies the password to the clipboard
	public static void copyPassword(){
		StringSelection stringSelection = new StringSelection(passwordContainer.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	//updates the text in the password container
	public static void updateContainerText(){

		if(checkIfABoxIsChecked()) {
			passwordContainer.setText(null);

			Generator.generatePassword((Integer) passwordLengthBox.getSelectedItem());

			passwordContainer.setText(generator.getPassword());
		}else{
			passwordContainer.setText("Select password parameters");
		}
	}

	//adds listeners to the buttons in within UI
	public static void addListeners(){

		//copy password button
		//when the button is clicked, copy the contents of the password container to the clipboard
		copyPassword.addActionListener(e -> copyPassword());

		//generate password button
		//when the generate password button is clicked,
		generatePassword.addActionListener(e -> updateContainerText());
	}

	//checks if any check box is checked
	private static boolean checkIfABoxIsChecked(){

		//loop through the checkbox array
		for(int i = 0; i < checkBoxes.length; i++){

			//if check if the check box is selected and is the 'easy input' one
			if(checkBoxes[i].isSelected() && i != 4) {
				return true;
			}
		}
			return false;
	}

	//finds whether a file exists and is accessible
	private boolean findImage(File image){

		return image.exists();
	}

	//checks if the images for the buttons exist and sets them
	private void assignImages() {

		//file that represents the copy icon
		File copyImageCheck = new File("copy.png");

		//file that represents the question mark icon
		File questionMarkImageCheck = new File ("questionmark.png");

		//if the file exists, print that is has been found and assign it to the button
		if(findImage(copyImageCheck)){
			copyPassword.setIcon(copyIcon);
		}

		if(findImage(questionMarkImageCheck)){
			questionMark.setIcon(questionMarkIcon);
		}
	}
}
