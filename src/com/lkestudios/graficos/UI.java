package com.lkestudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.lkestudios.main.Game;

public class UI {
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("Moedas: " + Game.moedas_atual+ "/" + Game.moedas_contagem, 30, 30);
	}
}
