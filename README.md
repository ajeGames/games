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

**Create Player**

`POST /service/picnic/player`

Form parameters

* playerName

Response

    playerSummary {
        key,
        name
    }

**Get Player**

`GET /picnic/player/{playerKey}`

Response

    playerSummary {
        key,
        name,
    activeGame {
        gameKey,
        basket {
            food*,
            drink*,
            supply*,
            prevention*
        }
    }

**Start Game**

`POST /picnic/game`

Form parameters

* playerKey

Response

    gameKey,
    description

**Get Game**

`GET /picnic/game/{gameKey}`

Response

    gameKey,
    description,
    hostName,
    status,
    players {
        playerSummary* {
            key,
            name
        }
    }

**List Games**

`GET /picnic/game/list-open`

Response

    games {
        gameSummary* {
            key,
            description
        }
    }

**Join Game**

`PUT /picnic/game/{gameKey}`

Form parameters

* playerKey

**Start Play**

`PUT /picnic/game/{gameKey}/play`

Can only be done manually by host

**Get Spinner**

`GET /picnic/spinner/{gameKey}`

Query parameters

* playerKey

Response

    spinToken

**Spin**

`POST /picnic/spinner/{spinToken}`

Response

    item,
    image,
    type,
    counteracts

**Quit**

`DELETE /picnic/game/{gameKey}/{playerKey}`

Removes player from game.  If player is the host, game is ended as well.
