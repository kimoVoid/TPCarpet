# TPCarpet 1.15.2
A Fabric-Carpet mod extension for TecnicPhantoms SMP

## Commands
- `/setsb (scoreboard)` - Command for that any player can change sidebar.
- `/ping` - Command for see the ping of the player.
- `/cs` - Command for alternate between spectator and survival mode, saving the original survival position of the player.
- `/player xx rejoin` - Extension of /player command from carpet. Rejoin a player in the latest position they have been.
- `/log memory` Extension of /log command from carpet. Add a logger of the memory server.

# Rules

### ``yeetUpdateSuppressionCrash``
- Prevent the server from crashing due to `StackOverflowError`, `OutOfMemoryError` or `ClassCastException`
- Type: `boolean`
- Default: `false`

### ``stopCommandDoubleConfirmation``
- Add a double confirmation for /stop command to prevent stopping server accidentally. You need to enter ``/stop`` twice within 30 secondsor ``/stop confirm`` to stop the server
- Type: `boolean`
- Default: `false`
