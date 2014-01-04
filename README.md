Picnic
======

Summary of DAP

* GET     /service/picnic                                         -- Entry point
* GET     /service/picnic/player/{playerKey}                      -- Show state of given player
* POST    /service/picnic/player?playerName={name}                -- Create new player using form parameters: playerName
* GET     /service/picnic/game/list-open                          -- Show open games to join
* GET     /service/picnic/game/{gameKey}                          -- Show state of given game
* POST    /service/picnic/game?playerKey={playerKey}              -- Start new game hosted by given player
* PUT     /service/picnic/game/{gameKey}?playerKey={playerKey}    -- Add player to game
* PUT     /service/picnic/game/{gameKey}/play                     -- Begin game play
* GET     /service/picnic/spinner/{gameKey}?playerKey={playerKey} -- If given player is up, return spin token
* POST    /service/picnic/spinner/{spinToken}                     -- Spin the spinner (consume the token) and see the result
