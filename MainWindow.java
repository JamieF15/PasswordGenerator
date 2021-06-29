import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame{

	ImageIcon icon = new ImageIcon("favicon-16x16.png");
	final SystemTray tray = SystemTray.getSystemTray();
	final TrayIcon trayIcon = new TrayIcon(icon.getImage(), "Tray Image");
	final PopupMenu popupMenu = new PopupMenu();
	final MenuItem exitItem = new MenuItem("Exit");
	final MenuItem genAndCopyItem = new MenuItem("Generate & Copy Password");

	private static final String WINDOWNAME = "MainWindow";

	public MainWindow(){

		//set the name of the window
		this.setName(WINDOWNAME);

		//set the extended state of the frame to normal
		this.setExtendedState(JFrame.NORMAL);

		//set the bounds of the frame
		setBounds(200, 200, 300, 350);

		//set the window to not resizable
		setResizable(false);

		//maybe change
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		//set the favicon
		setIconImage(icon.getImage());

		//add the icon to the system tray
		addToSystemTray();
	}

	//add the application to the system tray
	private void addToSystemTray(){

		//check if the system tray is supported
		if(!SystemTray.isSupported()){
			System.out.println("System try is not supported");
		}else{

			popupMenu.add(genAndCopyItem);
			popupMenu.addSeparator();
			popupMenu.add(exitItem);
			trayIcon.setPopupMenu(popupMenu);

			try {
				tray.add(trayIcon);
			}catch (AWTException e){
				e.printStackTrace();
			}

			//add the listeners
			addListeners();
		}
	}

	//reveals the window when it is minimised
	private void revealWindow(String windowName){

		//array of windows
		Window[] children = MainWindow.getWindows();

		//loop through the array of window
		for(Window win : children){

			//if the window's name is equal to the this applciations name and it is visible
			if(win.getName().equals(windowName) && win.isVisible()){

				//put it to the front
				win.toFront();

				//set it state to normal
				((JFrame)win).setState(JFrame.NORMAL);
			}
		}
	}

	//checks if the
	private void checkIconMouseClicks(){

		trayIcon.addMouseListener(new java.awt.event.MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				//the amount of clicks needed to make the window appear
				int neededClicks = 2;

				//check if the amount of clicks performed is equal to the amount of needed clicks
				// and it was performed clicks were done by the right mouse button
				if(e.getClickCount() == neededClicks && e.getButton() == MouseEvent.BUTTON1){
					revealWindow(WINDOWNAME);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}

	//add the action and mouse listeners
	private void addListeners(){

		//tray icon click event
		trayIcon.addActionListener(e -> checkIconMouseClicks());

		//generate and copy click event
		genAndCopyItem.addActionListener(e -> {
		Generator.generatePassword(FrameContents.getDEFAULTPASSWORDLENGTH());
		FrameContents.updateContainerText();
		FrameContents.copyPassword();
		});

		//exit item click event
		exitItem.addActionListener(e -> Runtime.getRuntime().exit(1));
	}
}
