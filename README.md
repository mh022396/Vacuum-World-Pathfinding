Vacuum World Pathfinding

[Project is based on problem from book: Russell, Stuart; Norvig, Peter. Artificial Intelligence (Pearson Series in Artifical Intelligence) (Chapter 2)]

A Vacuum world is a 2D grid where cells are either open, blocked or contain dirt. The objective to this problem is to use a single agent to vacuum all pieces of dirt. 
My solution provides a fully observable, deterministic, discrete, and semidynamic enviroment using a single agent.

Pathfinding algorithms include depth first seach, unifrom cost search (essentially breadth first seach), and A-star.

Created with Java 14. Make and Run files included. Commandline arguements for running:

./run.sh [algorithm] [heuristic] < *.vw
