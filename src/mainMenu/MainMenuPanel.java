package mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	public MainMenuPanel(BozorgMenuFrame menuFrame) {
		this.setLayout(null);
		this.setSize(500, 500);
		JButton offline = new JButton("Offline Mode");
		JButton online = new JButton("Online Mode");
		JButton exit = new JButton("Exit");

		offline.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changeToOfflineMode();
			}
		});
		this.add(offline);
		offline.setSize(150, 50);
		offline.setLocation(175, 100);

		online.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changeToOnlineMode();
			}
		});
		this.add(online);
		online.setSize(150, 50);
		online.setLocation(175, 175);

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menuFrame.dispatchEvent(new WindowEvent(menuFrame,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		this.add(exit);
		exit.setSize(150, 50);
		exit.setLocation(175, 250);

	}
}
