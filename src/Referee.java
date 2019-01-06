package com.pl.edu.wat;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Referee extends Thread{
    ArrayList<Team> teams = new ArrayList<Team>();
    Semaphore ready;

    public Referee(ArrayList<Team> teams, Semaphore ready)
    {
        this.ready = ready;
        this.teams = teams;
    }

    @Override
    public void run()
    {
        try {
            // if all of the teams are "ready", 1st player can raise semaphore
            ready.acquire(teams.size());
            raisePlayersSemaphores();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void raisePlayersSemaphores() {
        for(Team team : teams)
            team.start_running();
    }
}
