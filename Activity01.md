
# Activity 01 - Switch statements

## Overview
In this activity you will learn about switch statements by creating a multi-purpose banking system.

Our command will look and behave like this:

`/wallet send eric 1000` - Sends `eric` $1000
`/wallet donate 1000` - "Donates" $1000 to the server
`/wallet balance` - Tells the player their current balance
`/wallet help` - Tells the player how they can use the `/wallet` command

## Steps
1. Create a command with the class name being `WalletCommand`
	- The command name should be `wallet`
	- The usage should be `Usage: /wallet <send | balance | donate> [person | amount] [amount]`
	- The description should be `Interact with your wallet`
2. Within your command logic, you will be using `switch` statements to determine what to do. Your switch statement should look something like this:
```java
switch (args[0]) {
  case "send":
	  // send eric $1000
  case "donate":
	  // take $1000 from eric
  case "balance":
	  // tell eric his balance
  default:
	  // tell eric what to do
}
```
3. Using what you've learned this year, your peers and the [Java Docs](https://docs.oracle.com/javase/8/docs/api/), implement the command logic.

<details>
<summary>For reference, this is a boilerplate `WalletCommand` class to get you started if you need help.</summary>

```java
package org.apcs.mc.commands;  
  
import org.bukkit.command.Command;  
import org.bukkit.command.CommandExecutor;  
import org.bukkit.command.CommandSender;  
  
public class WalletCommand implements CommandExecutor {  
    @Override  
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {  
        switch (args[0]) {  
            case "send":  
                // send eric $1000  
  case "donate":  
                // take $1000 from eric  
  case "balance":  
                // tell eric his balance  
  default:  
                // tell eric what to do  
  }  
        return true;  
  }  
}
```
</details>

## Testing your plugin
Now its time to run the Bukkit server and see how your plugin performs.

1. Go to the `APCS Server` folder and double click the `server.bat` file (it might also just say `server` if you cannot see extensions)
2. Click on the **green** bug icon in the top right of your IntelliJ Window

> What we just did sets up a debug session for your Bukkit server. Any code that you change in IntelliJ will automagically be reflected in your Bukkit plugin without you needing to re-build the project.

3. Open the Minecraft launcher and connect to your server
4. Type `/wallet` and you should see your help screen. You should be able to send, donate and see balances. If you did, you've completed this activity!
