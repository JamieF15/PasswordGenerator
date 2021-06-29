import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame{

	ImageIcon icon = new ImageIcon("favicon-16x16.png");
	final SystemTray tray = SystemTray.getSystemTray();
	final TrayIcon trayIcon = new TrayIcon(icon.getImage(), "Tray Image");


	MenuItem aboutItem = new MenuItem("About");
	MenuItem exitItem = new MenuItem("Exit");
	MenuItem genAndCopyItem = new MenuItem("Generate & Copy Password");

	public MainWindow(){

		this.setName("MainWindow");
		this.setExtendedState(JFrame.NORMAL);
		setBounds(200, 200, 300, 350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconImage(icon.getImage());
		addToSystemTray();
	}

	private void addToSystemTray(){

		if(!SystemTray.isSupported()){
			System.out.println("System try is not supported");
		}else{

			final PopupMenu popupMenu = new PopupMenu();

			popupMenu.add(genAndCopyItem);
			popupMenu.addSeparator();
			popupMenu.add(aboutItem);
			popupMenu.add(exitItem);
			trayIcon.setPopupMenu(popupMenu);

			try {
				tray.add(trayIcon);
			}catch (AWTException e){
				e.printStackTrace();
			}

			addListeners();
		}
	}

	private void windowExists(String windowName){

		Window[] children = MainWindow.getWindows();

		for(Window win : children){

			if(win.getName().equals(windowName) && win.isVisible()){
				win.toFront();
				((JFrame)win).setState(JFrame.NORMAL);
			}
		}
	}

	private void checkIconMouseClicks (){

		trayIcon.addMouseListener(new java.awt.event.MouseListener(){

			int neededClicks = 2;

			@Override
			public void mouseClicked(MouseEvent e) {


			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount() == neededClicks && e.getButton() == MouseEvent.BUTTON1){
					windowExists("MainWindow");

				}
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
	private void addListeners(){

		trayIcon.addActionListener(new java.awt.event.ActionListener(){

			public void actionPerformed(ActionEvent e) {
				checkIconMouseClicks();
			}
		});
		genAndCopyItem.addActionListener(new java.awt.event.ActionListener(){

			public void actionPerformed(ActionEvent e){
			Generator.generatePassword(FrameContents.getDEFAULTPASSWORDLENGTH());
			FrameContents.updateContainerText();
			FrameContents.copyPassword();
			FrameContents.setPasswordContainer(null);
			}
		});

		exitItem.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Runtime.getRuntime().exit(1);
			}
		});
	}
}
