package com.pl.edu.wat;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Team {
    ArrayList<Runner> runers = new ArrayList<Runner>();
    int teamNumber;
    Random rand = new Random();

    // Current team players
    Semaphore start = new Semaphore(1); // if 1 - can move to line
    Semaphore run = new Semaphore(0); // if 0 - coulnt run
    Object monitor = new Object();

    public Team(int number, Color color, int teamNumber, Semaphore ready)
    {
        this.teamNumber = teamNumber;
        for(int i = 0; i < number; i++)
        {
            runers.add(new Runner(300 + teamNumber * 30, 160 + i * 30, color, teamNumber, i, start, run, monitor, ready));
        }

        for(Runner runner : runers)
            runner.start();
    }

    public void start_running()
    {
        run.release();
    }

    public void Paint(Graphics g)
    {
        for(Runner runner : runers)
            runner.Paint(g);
    }
}
