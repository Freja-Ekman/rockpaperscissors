# Rock Paper Scissors
This application implements a rock-paper-scissors game between two players with unique
names. A game have a unique id that could be shared between two players to join a game
and make their moves. Once a move is made it can not be changed, and a new game shall be
started to compete once again with another move. May the best man win!

## Getting started
To build and run the application JDK and JRE 11 is required, but the application is
also tested with version 13.

To build the application run the following command from the projects root
directory:<br>
`./gradlew build`.

To start a local server run the following command from the projects root
directory:<br>
`./gradlew bootRun`.

## API-design
### GET /api/games/{id}
Returns the defined games' status in terms of the state of the game, the players and their
moves. The state could be either *PLAYER1WON*, *PLAYER2WON*, *EQUAL* or *NOT_FINISHED*.
If a player has not joined yet, it is set to *unknown*. Lastly, the players' moves are set
to *HIDDEN* until the game has ended.

#### Example of return
New game with no players:
`{"state":"NOT_FINISHED","player1":"Unknown","player2":"Unknown","movePlayer1":"HIDDEN","movePlayer2":"HIDDEN"}`

Game with one player:
`{"state":"NOT_FINISHED","player1":"Testsson","player2":"Unknown","movePlayer1":"HIDDEN","movePlayer2":"HIDDEN"}`

Finished game:
`{"state":"PLAYER1WON","player1":"Testsson","player2":"Tester","movePlayer1":"PAPER","movePlayer2":"ROCK"}`

#### Error handling
- 404 Error: Not Found - If the there are not a game with the specified id.
- 400 Error: Bad Request - If the specified id has the wrong format, i.e not a UUID.

### POST /api/games
Creates a game and returns the game-id.

#### Example of return
`{"game_id":"9975a9d4-dd5c-4153-a0fe-6d98100be767"}`

### POST /api/games/{id}/join
Joins the specified game. Define the player name in the request body.

#### Example of JSON body
`{"name":"Testsson"}`

#### Example of return
No returns, use `GET /api/games/{id}` to see result.

#### Error handling
- 404 Error: Not Found - If the there are not a game with tha specified id.
- 400 Error: Bad Request - If the specified id has the wrong format, i.e is not a UUID.
- 412 Error: Precondition Failed - If the players already is added.

### POST api/games/{id}/move
Makes a move. Define the player name and the move in the request body. The move could be
either *ROCK*, *SCISSOR* or *PAPER*.

#### Example of JSON body
`{"name":"Testsson","move":"PAPER"}`

#### Example of return
No returns, use `GET /api/games/{id}` to see result.

#### Error handling
- 404 Error: Not Found - If either the player or game does not exist.
- 400 Error: Bad Request - If the specified id has the wrong format, i.e is not a UUID, or 
if the move is set to *HIDDEN*.
- 412 Error: Precondition Failed - If player already has made its move.


## cURL command examples
To start a game and get the id use the following cURL command:<br>
`curl -X POST -H "Content-Type: application/json" http://localhost:8080/games`

To check the status of a game use the following cURL command with the id
generated in the previous command:<br>
`curl -X GET -H "Content-Type: application/json" http://localhost:8080/games/{id}`

To join a game use the following cURL command with the generated id of a game and a 
body with a name, here *Name*:<br>
`curl -X POST -H "Content-Type: application/json" --data '{"name":"Name"}'  http://localhost:8080/games/{id}/join`

To make a move in a joined game use the following cURL command with the generated id of a
game and a body with the players name and move:<br>
`curl -X POST -H "Content-Type: application/json" --data '{"name":"Name","move":"PAPER"}' http://localhost:8080/games/{id}/move`

## Possible improvements
- Make the application more maintainable by adding tests to the RockPaperScissorsController.
- Give an id to players so that name does not have to be unique.