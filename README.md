# TPCarpet 1.15.2
A Fabric-Carpet mod extension for TecnicPhantoms SMP

## Commands
- `/setsb (scoreboard)` - Command for that any player can change sidebar.
- `/ping` - Command for see the ping of the player.
- `/cs` - Command for alternate between spectator and survival mode, saving the original survival position of the player.
- `/player xx rejoin` - Extension of /player command from carpet. Rejoin a player in the latest position they have been.
- `/log memory` Extension of /log command from carpet. Add a logger of the memory server.
- `/stop <confirm>` With the rule `stopCommandDoubleConfirmation` the command will need double activation in less than 30 seconds or `/stop confirm` for server being stopped

# Rules

### ``yeetUpdateSuppressionCrash``
- Prevent the server from crashing due to `StackOverflowError`, `OutOfMemoryError` or `ClassCastException`
- Type: `boolean`
- Default: `false`
