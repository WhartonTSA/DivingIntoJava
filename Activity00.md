
# Activity 00 - Getting Started
The Bukkit API is a very rich and flexible ecosystem. In this series of activities you will learn Java fundamentals, including Maps, Lists, Streams, for-loops, conditionals, Strings, and object-oriented programming.

> The Bukkit documentation is available [here](https://hub.spigotmc.org/javadocs/spigot/overview-summary.html)

## Setting up your environment
Before we can begin, you must set your environment up. Make sure you have your `APCS Minecraft.zip` bundle downloaded and extracted.

1. Drag the `.minecraft` folder inside `APCS Minecraft` into the `Roaming` shortcut
2. Run the `jre-8u51-windows-x64.exe` file and install it
3. Run the `ideaIC-2018.1` file and install it
4. Open `IntelliJ IDEA` (it should be on your desktop)
5. Click `Import Project` on the menu that pops up
6. Find the folder `APCS Plugin` within the `APCS Minecraft` folder and select it
7. A popup window should open.
	1. Select `Create project from existing sources` and click the `Next` button
	2. Click the `Next` button again, when a popup opens saying `The project file C:\...\.idea already exists`, click yes
	3. Click next again
	4. Click next again
	5. When a popup opens saying `The module file 'C:\...\APCS Plugin.iml'`, click `Reuse`
	6. On the screen that says `Please select project SDK`, if you already see something similar to `jdk1.8.0` on the screen click next and continue. Otherwise, do the following:
		1. Click the green plus sign
		2. Click JDK
		3. Click the arrow next to `C:\`
		4. Click the arrow next to `Program Files`
		5. Click the arrow next to `Java`
		6. Select `jdk1.8.0_161`
		7. Click `Ok`
		8. Click `Next`
	7. Click `Finish`
8. Click on the `File` button on the top left of the toolbar
9. Click `Project Structure` towards the top
10. In the `Project` tab, make sure that the dropdown for `Project SDK` says `1.8` and that the `Project language level` dropdown says `8 - Lambdas, type annotations etc.`
11. Click on the `Artifacts` tab
	1. Click the green plus
	2. Click on JAR -> Module with dependencies
	3. Click `OK`
	4. Click on the three dots next to `Output directory`
	5. Find the `APCS Server` folder and then select the `plugins` folder within that
	6. Click `OK` and dismiss the window
12. Select the dropdown menu next to the `010101` button with a green arrow in the top right
	1. Select Edit configurations...
	2. In the new window, click on the green plus in the top left
	3. Select `Remote` towards the bottom
	4. Set the name to `APCS MC`
	5. Click the green plus sign below `Before launch: Build Artifacts`
		1. Click `Build Artifacts`
		2. Tick `APCS Plugin.jar`
		3. Click `OK`
	6. Click `Apply` then `OK`

## Creating your first command
Bukkit plugins are oriented around "commands", or directives provided by the user to the server. Your command can take any number of arguments and can give any type of response.

1. Right click on the `org.apcs.mc.commands` package
	1. Click `New` -> `Java Class`
	2. In the `Name` field, type `HelloWorld`
	3. Click `OK`
2. In your newly created class, have it `implement` `CommandExecutor`

At this point, your class should look like this:

```java
package org.apcs.mc.commands;  
  
import org.bukkit.command.CommandExecutor;  
  
public class HelloWorld implements CommandExecutor {
  
}
```

3. Now you must implement the methods that the `CommandExecutor` interface defines. For reference, here is the `CommandExecutor` interface:

```java
package org.bukkit.command;  
  
public interface CommandExecutor {  
    boolean onCommand(CommandSender commandSender, Command command, String label, String[] args);  
}
```

4. After implementing your methods, your class should look something like this:

```java
package org.apcs.mc.commands;  
  
import org.bukkit.command.Command;  
import org.bukkit.command.CommandExecutor;  
import org.bukkit.command.CommandSender;  
  
public class HelloWorld implements CommandExecutor {  
    @Override  
  public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {  
        return false;  
  }  
}
```

> If you are curious about the `@Override` above your method, this is called a `decorator`. Decorators are formatted as `@<name>` and tell the Java compiler something about your method, or decorates it!

Your command structure is now set up, but it is not ready to be used yet. While your class has implemented `CommandExecutor`, you still need to give it a behavior.

5. Within your `onCommand` method, send a message to `commandSender` saying `Hello World!`

> You may want to look at [this page](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/command/CommandSender.html) which documents all the methods on the `CommandSender` class.
