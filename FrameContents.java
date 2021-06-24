import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FrameContents extends JPanel {
	
	//represents the middle of the window as it is not resizable
	public final int MIDDLE = 200;
	
	//the default length for a password to be generated
	public final int DEFAULTPASSWORDLENGTH = 16;
	
	public final int MAXPASSWORDLENGTH = 22;

	JLabel title = new JLabel("Generate Password");	
	JLabel lengthPrompt = new JLabel("Choose length: ");
	JLabel includeUppercaseLettersPrompt = new JLabel("Include uppercase Letters: ");
	JLabel includeLowercaseLettersPrompt = new JLabel("Include lowercase Letters: ");
	JLabel includeSymbolsPrompt = new JLabel("Include special characters: ");
	JLabel includeNumbersPrompt = new JLabel("Include numbers: ");

	
	JButton generatePassword = new JButton("Generate Password");
	JButton copyPassword = new JButton();
	
	ImageIcon copyIcon = new ImageIcon("copy.png");

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
		
		for (int i = 1; i < MAXPASSWORDLENGTH; i++) {
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
		includeSymbolsPrompt.setFont(bodyFont);
		includeNumbersPrompt.setFont(bodyFont);
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
	}
	
	void setItemBounds(){
		
		title.setBounds(30, 1, 1000, 30);		
		
		passwordLengthBox.setBounds(150, 50, 100, 30);
		lengthPrompt.setBounds(35, 50, 1000, 30);	
		
		includeUppercaseLetters.setBounds(210, 95, 20, 20);
		includeUppercaseLettersPrompt.setBounds(35, 80, 1000, 50);

		includeLowercaseLetters.setBounds(210, 125, 20, 20);		
		includeLowercaseLettersPrompt.setBounds(35, 85, 1000, 100);
		
		includeSymbols.setBounds(215, 155, 20 , 20);
		includeSymbolsPrompt.setBounds(35, 90, 1000, 150);
			
		generatePassword.setBounds(30, 220, 230, 20);		
	
		checkForImage();
		
		copyPassword.setBounds(230, 250, 30, 30);

		passwordContainer.setBounds(30, 250, 190, 30);

		//set the check boxes to true by default
		includeUppercaseLetters.setSelected(true);
		includeLowercaseLetters.setSelected(true);
		includeSymbols.setSelected(true);
		
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
