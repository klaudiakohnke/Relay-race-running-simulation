package com.pl.edu.wat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener{

    MainPanel m;
    public Frame() {
        super("Symulacja - stadion - Kohnke H6X2S1 - Programowanie wspolbiezne");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 300);

        m = new MainPanel();
        getContentPane().add(m.getPanel());
        m.getButton().addActionListener(this);
        m.getPanel().revalidate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int players = m.getNumberOfPlayers();
        int teams = m.getNumberOfTeams();

        this.remove(m.getPanel());
        repaint();
        JPanel sim = new Stadion(teams, players);
        getContentPane().add(sim);
        this.setSize(900, 600);
        ((Stadion) sim).startSimulation();
    }
}
