package com.pl.edu.wat;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import javax.swing.JPanel;

public class Stadion extends JPanel{

    private int refresh = 20;
    private Timer timer;
    ArrayList<Team> teams = new ArrayList<Team>();
    int numberOfTeams;
    int numberOfPlayers;
    Referee referee;

    Color[] colorArray = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.ORANGE, Color.PINK, Color.MAGENTA};
    Semaphore ready = new Semaphore(0);

    public Stadion(int numberOfTeams, int numberOfPlayers)
    {
        this.numberOfTeams = numberOfTeams;
        this.numberOfPlayers = numberOfPlayers;
        setSize(600, 900);

        createTeams();
        createPlayers();

    }
    public void createTeams() {
        for(int i = 0; i < numberOfTeams; i++)
            teams.add(new Team(numberOfPlayers, colorArray[i % colorArray.length], i, ready));
    }
    public void createPlayers() {
        referee = new Referee(teams, ready);
        referee.start();
    }

    public void startSimulation()
    {
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, refresh);
    }


    class RemindTask extends TimerTask
    {
        public void run() {
            repaint();
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        for(int i = -1; i <= numberOfTeams; i++)
        {
            int width = (850 - i * 50) / 2;
            int height = (550 - i * 50) / 2;
            int middleX = i * 25 + width;
            int middleY = i * 25 + height;

            double PI = 3.14;
            for (double t = 0; t < 2 * PI; t += 0.1)
            {
                // draw lines from the current point (t) to the next (t + 1)
                g.drawLine((int) (middleX + width * Math.cos(t)),(int)  (middleY + height * Math.sin(t)),
                        (int) (middleX + width * Math.cos(t + 0.1)), (int) (middleY + height * Math.sin(t + 0.1)));
            }
        }
        // Start line
        g.fillRect(440, 0, 10, numberOfTeams * 25);
        // Draw players
        for(int i = 0; i < teams.size(); i++)
            teams.get(i).Paint(g);
    }

}
