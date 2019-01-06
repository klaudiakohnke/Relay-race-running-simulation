package com.pl.edu.wat;

public class Main{

    public static void main(String[] args) {
        Frame f = new Frame();
    }

    /* Summary
     * Descriptiona:
     * The race begins as all players set up at the finish line
     * Player from a given team can run on one track at a time
     * Several players at once can wait for the start
     *
     * Threads:
     * Main - for drawing
     * Referee - starts the race
     * Runners - running
     *
     * Summary::
     * Communication:
     * Semaphore ready - raises runners, the referee waits until he reaches the value equal to the number of teams
     * Then the referee raises the semaphore in each team - the race begins
     *
     *
     * A set of semaphores in each team:
     * Semaphore start - protecting semaphore: only one thread at a time to wait at the start
     * Semaphore run - a semaphore determining whether the run has started
     *
     * Monitor - allows only one thread to run on the track at a time
     *
     */
}

