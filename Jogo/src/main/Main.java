package main;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Adventure");

		GamePanel painel = new GamePanel();
		window.add(painel);

		window.pack();

		window.setLocationRelativeTo(null); // Janela aparece no centro da tela
		window.setVisible(true); // Ativa a visualização da janela

		painel.startGameThread();
	}
}