package eg.edu.alexu.csd.oop.game.sample;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, URISyntaxException, Exception {
                JMenuBar  menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		menu.add(newMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);
                JFXPanel j = new JFXPanel();
                MediaPlayer media = new MediaPlayer(new Media(new File("mzmar.mp3").toURI().toString()));
                media.play();
		final GameController gameController = GameEngine.start("Circus of plates", new eg.edu.alexu.csd.oop.game.sample.world.PlateWorld(700, 700), menuBar, Color.WHITE);
		newMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
                    try {
                        media.stop();
                        media.play();
                        gameController.changeWorld(new eg.edu.alexu.csd.oop.game.sample.world.PlateWorld(700, 700));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		}); 
	}
	
}
