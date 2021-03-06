package gamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import bozorg.common.objects.Player;
import bozorg.judge.Judge;

@SuppressWarnings("serial")
public class BozorgMenuBar extends JMenuBar {

	@SuppressWarnings("deprecation")
	public BozorgMenuBar(Judge judge, GamePanel panel) {
		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");
		ButtonGroup camera = new ButtonGroup();
		JRadioButtonMenuItem observer = new JRadioButtonMenuItem("Observer");
		JRadioButtonMenuItem saman = new JRadioButtonMenuItem("Saman");
		JRadioButtonMenuItem jafar = new JRadioButtonMenuItem("Jafar");
		JRadioButtonMenuItem reza = new JRadioButtonMenuItem("Reza");
		JRadioButtonMenuItem hasin = new JRadioButtonMenuItem("Hasin");
		JRadioButtonMenuItem[] allPlayers = { saman, jafar, reza, hasin };

		ArrayList<Player> players = judge.getp();
		for (JRadioButtonMenuItem jRadioButtonMenuItem : allPlayers)
			jRadioButtonMenuItem.hide();
		observer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.setPlayer(null);
				panel.repaint();

			}
		});
		for (Player player : players) {
			allPlayers[player.getName()]
					.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setPlayer(player);
							panel.repaint();
						}
					});
			allPlayers[player.getName()].show();

		}

		observer.setSelected(true);
		camera.add(observer);
		camera.add(saman);
		camera.add(jafar);
		camera.add(reza);
		camera.add(hasin);

		viewMenu.add(observer);
		viewMenu.add(saman);
		viewMenu.add(jafar);
		viewMenu.add(reza);
		viewMenu.add(hasin);

		this.add(fileMenu);
		this.add(viewMenu);
	}

}
