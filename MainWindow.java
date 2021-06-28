import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

	final TrayIcon trayIcon;

	public  MainWindow(){

		ImageIcon icon = new ImageIcon("favicon-16x16.png");
		setBounds(200, 200, 300, 350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(icon.getImage());




	}

	private void setTrayIcon(){

		if(SystemTray.isSupported()){
			SystemTray tray = SystemTray.getSystemTray();

		}
	}
}
