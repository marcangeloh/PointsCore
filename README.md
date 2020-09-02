# PointsCore
An open- and (hopefully) easy to use API for a neat point system in Minecraft Servers

**Quick integration (Maven)**:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.marcangeloh</groupId>
    <artifactId>PointsCore</artifactId>
    <version>LATEST</version>
</dependency>
```

After importing the project through maven include this to access the points:
```java
PointsCore pointsCore = (PointsCore) YourPlugin.getServer().getPluginManager().getPlugin("PointsCore");
```

To alter the points there are two methods, remove and add, which both return a boolean
to check on their success:
```java
//To remove points
pointsCore.playerPoints.removePointsToToolType(Tools tool, Player player, double amount);
//To add points
pointsCore.playerPoints.addPointsToToolType(Tools tool, Player player, double amount);
```

If you don't want to check through each tool yourself to get the toolType, you can
Use the ValueUtil class:
```java
Tools tool = new ValueUtil().getToolType(Material tool);
```
**How to use placeholderAPI?**
Just install the plugin and it should automatically add all of the points,
If you require a list of all points and their placeholders you may find them,
on the wiki.

**Saving and loading the points:**
This is done automatically by PointsCore, you don't have to save or load anything!

**Further Reading:**
You can check out the wiki for a more in-depth guide to the possibilities of PointsCore!

**Support:**
Currently I offer two forms of support, through my discord channel (Faster support):
https://discord.gg/qEhF9rA
Or directly on here by creating an issue.
