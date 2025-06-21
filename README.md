This is a plugin for Minecraft beta that syncs the real world time with your in-game time.

## How it works
It will calculate and change the game time every second (that's 20 ticks and that can be changed in the config), it will use your computer's timezone as default if the config `timezone` value does not exist.

## Performance
I am not sure if this plugin has any significant performance hit, it runs code every few ticks (you can in/decrease the frequency in the config) and that is not the best approach for doing this, but it's the only that works in beta. Using it with `ticksBetweenUpdate: 1` does not seem to change the performance in any way. The specifications for the computer used for the tests:

```Host: 83AF IdeaPad 1 14IAU7
CPU: 12th Gen Intel i5-1235U (12) @ 1.300GHz
GPU: Intel Alder Lake-UP3 GT2 [Iris Xe Graphics]
Memory: 10097MiB / 15709MiB```
