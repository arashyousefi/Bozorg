package gamePanel;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import bozorg.common.objects.Player;
import bozorg.judge.Judge;

@SuppressWarnings("serial")
public class BozorgMenu extends JMenuBar {

	@SuppressWarnings("deprecation")
	public BozorgMenu(Judge judge) {
		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");
		ButtonGroup camera = new ButtonGroup();
		JRadioButtonMenuItem observer = new JRadioButtonMenuItem("Observer");
		JRadioButtonMenuItem saman = new JRadioButtonMenuItem("Saman");
		JRadioButtonMenuItem jafar = new JRadioButtonMenuItem("Jafar");
		JRadioButtonMenuItem reza = new JRadioButtonMenuItem("Rezar");
		JRadioButtonMenuItem hasin = new JRadioButtonMenuItem("Hasin");
		JRadioButtonMenuItem[] allPlayers = { saman, jafar, reza, hasin };

		ArrayList<Player> players = judge.getp();
		for (JRadioButtonMenuItem jRadioButtonMenuItem : allPlayers)
			jRadioButtonMenuItem.disable();
		for (Player player : players)
			allPlayers[player.getName()].enable();

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
