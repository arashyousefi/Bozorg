package mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OnlineModePanel extends JPanel {

	public OnlineModePanel(BozorgMenuFrame menuFrame) {
		this.setLayout(null);
		this.setSize(500, 500);
		JButton server = new JButton("Create");
		JButton player = new JButton("Join");
		JButton back = new JButton("Back");
		server.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changeServerPanel();
			}
		});
		this.add(server);
		server.setSize(150, 50);
		server.setLocation(175, 100);

		player.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changePlayerPanel();
			}
		});
		this.add(player);
		player.setSize(150, 50);
		player.setLocation(175, 175);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changeToMainMenu();
			}
		});
		this.add(back);
		back.setSize(150, 50);
		back.setLocation(175, 250);

	}
}
