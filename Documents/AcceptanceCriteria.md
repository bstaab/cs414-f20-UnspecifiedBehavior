### I can create a new game 

* Acceptance Criteria: 

  * A user should be able to create a new game: PASS 

  * A user can invite another user to play the game: PASS 

  * The new game will have a chessboard with chess pieces in their starting position: PASS 

  * A user can create a game without inviting an opponent: FAIL 

### I would like to play by the rules of Portal Chess. 

* Acceptance Criteria:  

  * The user who created the game will move first: PASS 

  * A user in a game can move one of their own pieces legally on their turn: PASS 

  * A user in a game can move one of their opponent's pieces on their turn: FAIL 

  * A user in a game can move one of their own pieces on their opponent’s turn: FAIL 

  * A user can end the game by checkmating their opponent: PASS 

  * A stalemate will occur if a user has no legal moves left on their turn: PASS 

### I would like to register with the system to get access 

* Acceptance Criteria:  

  * A user with valid and unique email should register: PASS 

  * A user with a unique username should register: PASS 

  * A user with a duplicate email or username should be able to register: FAIL 

  * A user with a valid password of 8 characters or more should be allowed to register: PASS 

### I can log into the system to play a game 

* Acceptance Criteria:  

  * A registered user can enter their username and password to login to the home screen: PASS 

  * A registered user can create a new game from the home screen: PASS 

  * A registered user can continue a game from the home screen: PASS 

  * A non-registered user can login to the home screen: FAIL 

### I want to invite other registered users to play games 

* Acceptance Criteria 

  * A registered user can create a game and enter a registered username to invite to the game: PASS 

  * A registered user can create a game and enter a non-registered username to invite to the game: FAIL 

  * A registered user can see the invitations they have sent out to other users: PASS 

### I can reject or accept an invitation 

* Acceptance Criteria 

  * A registered user can open a table showing all pending invitations: PASS 

  * A registered user can accept or reject an invitation to a game from this table: PASS 

  * A rejected invitation is deleted from the table: PASS 

  * An accepted invitation creates the game and then deletes the invitation from the table: PASS 
  
### I want to play multiple games at a given time

* Acceptance Criteria
  
    * A registered user can open a table showing all current matches: PASS 
    
    * A registered user can select a specific game to play and have that game state loaded: PASS
    
### I want to quit a game at any time

* Acceptance Criteria

   * A registered user has option to quit a game: PASS
   
   * A quit game is deleted from list of current matches: PASS

### I want my game data stored 

* Acceptance Criteria
   
   * All game data associated with a user is automatically stored in the database: PASS
   
   * A user can view their game data and match history: PASS

###  I want to be able to search other registered users

* Acceptance Criteria
   
   * A registered user can search for other registered users: PASS
   
   * A registered user can search for unregistered users: FAIL
   
   * Another registered users game data is displayed when searched for: PASS

###  I want to be able to unregister

* Acceptance Criteria
  
   * A registered user can choose to unregister their account at any time: PASS
   
   * Once unregistered all game data will be deleted along with the account: PASS

### I want to be notified when the game is over

* Acceptance Criteria

   * Once a game ends both opponents are notified that the game is over: PASS
   
### I want to view my results once the game is over

* Acceptance Criteria

    * After a game is over, a registered user can view their results: PASS

### I can log out of the system

* Acceptance Criteria
  
  * A registered user can logout of the system at any time and be redirected to the login page: PASS

### As a user I would like to receive notifications about the response to an invitation

* Acceptance Criteria

    * A registered user can access notification about whether their invitation was accepted or not: PASS
