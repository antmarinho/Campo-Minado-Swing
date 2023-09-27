package com.project.cm.visao;

import javax.swing.JFrame;

import com.project.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal() {
		
		Tabuleiro tab = new Tabuleiro(8, 8, 1);
		PainelTabuleiro pTab = new PainelTabuleiro(tab);
		
		add(pTab);
		
		setTitle("Campo Minado"); //titulo do menu
		setSize(690, 438); //tamanho da tela
		setLocationRelativeTo(null); //centralizar a tela
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //pra para o programa quando fechar
		setVisible(true); //cria janela
	}
	
	public static void main(String[] args) {
		
		new TelaPrincipal();
		
	}

}
