# Relay-race-running-simulation
## Table of contents
* [General info](#general-info)
* [About](#about)
* [Summary](#summary)

## General info
This project is simple relay race running simulation presented in a graphic manner. 
Used semaphores and monitors in Java.
	
## About
This project has been prepared for the needs of the subject "Concurrent Programming".

## Summary
- The race begins as all players set up at the finish line.
- Player from a given team can run on one track at a time.
- Several players at once can wait for the start
- Threads:
     - Main - for drawing
     - Referee - starts the race
     - Runners - running
- Communication:
     - Semaphore ready - raises runners, the referee waits until he reaches the value equal to the number of teams.
      Then the referee raises the semaphore in each team - the race begins
      A set of semaphores in each team:
     - Semaphore start - protecting semaphore: only one thread at a time to wait at the start
     - Semaphore run - a semaphore determining whether the run has started
     - Monitor - allows only one thread to run on the track at a time
