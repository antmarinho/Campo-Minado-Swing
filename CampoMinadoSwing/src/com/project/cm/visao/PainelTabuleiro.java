package com.project.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.project.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel{
	
	public PainelTabuleiro(Tabuleiro tab) {
		
		setLayout(new GridLayout(tab.getLinhas(),tab.getColunas()));
		
		tab.paraCadaCampo(c -> add(new BotaoCampo(c)));
		
		tab.registrarObs(e -> {
			
			SwingUtilities.invokeLater(() -> {
			
				if(e.isGanhou()) 
					JOptionPane.showMessageDialog(this, "Ganhou");
				else
					JOptionPane.showMessageDialog(this, "Perdeu");
				
				tab.reiniciar();
				
			});
				
		});
		
	}

}
