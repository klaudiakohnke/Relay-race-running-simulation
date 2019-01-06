package com.pl.edu.wat;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Runner extends Thread{
    double x, y;
    int xInit, yInit;
    int playerNumber;
    Color color;
    int teamNumber;
    Semaphore start, player_run, ready;
    /*
    * start - can player go to the start line
    * player_run - can player start running
    * ready - declares readiness
    */
    Object monitor;
    private int refresh = 20;
    Random rand = new Random();

    public Runner(int x, int y, Color color, int teamNumber, int playerNumber, Semaphore start, Semaphore player_run, Object monitor, Semaphore ready) {
        this.ready = ready;
        this.player_run = player_run;
        this.monitor = monitor;
        this.start = start;
        this.playerNumber = playerNumber;
        xInit = x;
        yInit = y;
        this.x = x;
        this.y = 650;
        this.color = color;
        this.teamNumber = teamNumber;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run()
    {
        thread_sleep(playerNumber * 500);
        while(y > yInit)
        {
            y -= 3;
            thread_sleep(refresh);
        }

        setOnFinishLine();
        player_run();
        thread_sleep(200);
        goToStartPosition();
    }

    public void setOnFinishLine()
    {
        try {
            start.acquire();
            while(y > 25 * teamNumber)
            {
                y += move_player(y, 25 * teamNumber);
                thread_sleep(refresh);
            }

            while(x != 420)
            {
                x += move_player(x, 420);
                thread_sleep(refresh);
            }

            if(playerNumber == 0)
                ready.release();
            /*
             * raises semaphore only for 1st player
             * must raise the semaphore as many times as there are teams
             * then - referee raises semaphore = players can start
             */

            player_run.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public double move_player(double now, double goal)
    {
        if(Math.abs(now - goal) < 10)
            return goal - now;
        if(now > goal)
            return -3;
        else if(now < goal)
            return 3;

        return 0;
    }

    public void player_run()
    {
        synchronized(monitor)
        {
            player_run.release();
            start.release();
            RunningOnTheElipse();
        }
    }

    public void goToStartPosition()
    {
        color = color.darker().darker();

        while(y != yInit)
        {
            y += move_player(y, yInit);
            thread_sleep(refresh);
        }

        while(x != xInit)
        {
            x += move_player(x, xInit);
            thread_sleep(refresh);
        }
    }

    public void thread_sleep(int time)
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /* ELIPSE, DRAWING:
          x = h + a cos t
          y = k + b sin t
        where:
        h - the beginning of the ellipse on the X axis
        k - the beginning of the ellipse on the Y axis
        a - width of the ellipse
        b - height of the ellipse
        t - range of points on the ellipse; the whole ellipse closes at <0; 2 * PI>
     */
    public void RunningOnTheElipse()
    {
        /*
         * Every track has 25 units
         */
        int width = (850 - teamNumber * 50) / 2;
        int height = (550 - teamNumber * 50) / 2;

        int middleOfX = teamNumber * 25 + width;
        int middleOfY = teamNumber * 25 + height;

        double PI = 3.14;

        /*
         * Players start with 3/4 circle 3/4 * 2 PI = 1.5 PI
         * One, full lap - 1.5 PI + 2 PI = 3.5 PI
         */

        for (double t = 1.5 * PI; t < 3.5 * PI; t += 0.02)
        {
            x = middleOfX + width * Math.cos(t);
            y = middleOfY + height * Math.sin(t);

           // simulation of the animation
            thread_sleep(refresh + rand.nextInt(refresh * 2));
        }
    }

    public void Paint(Graphics g)
    {
        g.setColor(color);
        g.fillOval((int) x, (int) y , 20, 20);

        g.setColor(color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.drawString("" + playerNumber, (int) x + 6, (int) y + 14);
    }
}
