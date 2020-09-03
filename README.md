# Tennis

Java Project to implement typical tennis game using test-driven development approach. 

## Problem Description

1. A game is won by the first player if first player has won at least four points in total and at least two points more than the opponent.
2. The running score of each game is described in a manner peculiar to tennis: scores from zero to three points are described as “love”, “fifteen”, “thirty”, and “forty” respectively.
3. If at least three points have been scored by each player, and the scores are equal, the score is “deuce”.
4. If at least three points have been scored by each side, and a player has one more point than his opponent, the score of the game is “advantage” for the player in the lead.
5. If more than three points have been scored by any side, and a player has two or more points than his opponent, the player in the lead is "winner".


## Prerequisites

```
1. Java 1.8.0 JDK or above
2. Apache Maven 3.3.1 or above
3. Git
```

## Install

#### IDE
```
1. Clone repository 'https://github.com/2020-DEV-107/Tennis.git' from github
2. Import Project as maven project into your IDE
3. Navigate to maven menu and select install lifecycle
```

#### Command Prompt
```
1. git clone 'https://github.com/2020-DEV-107/Tennis.git'
2. Run 'mvn clean install'
```

## Run Tests

#### IDE
```
1. Navigate to maven menu for the project
2. Select test lifecycle
```

#### Command Prompt
```
2. Run 'mvn test'
```

## Run Application
```
1. Run com.bnppf.kata.TennisSimulator class as java application
2. Follow the instruction in console to play the game