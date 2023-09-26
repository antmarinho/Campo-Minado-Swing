package com.project.cm.visao;

import com.project.cm.modelo.Tabuleiro;

public class Temp {
	
	public static void main(String[] args) {
		
		Tabuleiro tab = new Tabuleiro(3, 3, 9);
		
		tab.registrarObs(e -> {
			if(e.isGanhou()) {
				
				System.out.println("ganhou");
			}
			else 
				System.out.println("perdeu");
		});
		
		tab.alterarMarcacao(0, 0);
		tab.alterarMarcacao(0, 1);
		tab.alterarMarcacao(0, 2);
		tab.alterarMarcacao(1, 0);
		tab.alterarMarcacao(1, 1);
		tab.alterarMarcacao(1, 2);
		tab.alterarMarcacao(2, 0);
		tab.alterarMarcacao(2, 1);
		tab.abrir(2, 2);
		tab.alterarMarcacao(2, 2);
		
	}

}
