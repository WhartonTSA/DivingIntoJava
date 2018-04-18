

# Activity 02 - Listeners

## Overview
In this activity you will learn about Bukkit's Listener system.

### Concepts:
- Privacy
- Getter/Setter
- Decorators
- Math.random()
- Constructors
- Casting
- Map

## Steps
1. Create a package `org.apcs.mc.listeners` and within it a class named `PlayerMoveListener` that implements `org.bukkit.event.Listener`

2. Within your listener class, add a method named `onPlayerMove`
	- It will take one paramter, and the type will be `PlayerMoveEvent`
	- It will be decorated with an `@EventHandler` decorator

3. Your listener class should have a constructor that takes one paramater, and that paramter's type would be `Main` (or whatever your main class is)

4. Register your listener on your `Main` class (`Main` is the `brain`!)

<details>
<summary>Stuck?</summary>

```java
this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
```
</details>

5. Add your listener logic
> Your listener should, on a random chance, give a player money whenever they move. In conjunction with your `WalletCommand`, add funds to the user's account.

## Testing your plugin
Now its time to run the Bukkit server and see how your plugin performs.

1. Go to the `APCS Server` folder and double click the `server.bat` file (it might also just say `server` if you cannot see extensions)
2. Click on the **green** bug icon in the top right of your IntelliJ Window

> What we just did sets up a debug session for your Bukkit server. Any code that you change in IntelliJ will automagically be reflected in your Bukkit plugin without you needing to re-build the project.

3. Open the Minecraft launcher and connect to your server
4. Move around and see if you earn money like expected.
