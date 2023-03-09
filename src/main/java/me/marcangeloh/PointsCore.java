package me.marcangeloh;

import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.API.Util.ConfigurationUtil.WarpsUtil;
import me.marcangeloh.API.Util.GeneralUtil.*;
import me.marcangeloh.API.Util.TeleportUtil.HashMapUtil;
import me.marcangeloh.API.Util.TeleportUtil.TeleportUtil;
import me.marcangeloh.Commands.*;
import me.marcangeloh.Commands.PointCommands.PointCheckCommand;
import me.marcangeloh.Commands.PointCommands.PointsCoreCommands;
import me.marcangeloh.Commands.TeleportCommands.RandomTP;
import me.marcangeloh.Commands.TeleportCommands.TPA;
import me.marcangeloh.Commands.TeleportCommands.TPAConfirmation;
import me.marcangeloh.Events.*;
import me.marcangeloh.API.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.API.Util.ConfigurationUtil.Paths;
import me.marcangeloh.API.Util.SQLUtil.SQLManager;
import me.marcangeloh.Events.DiscordEvents.DiscordChatHandler;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class PointsCore extends JavaPlugin implements Paths {
    private DataManager dataManager;
    private String botToken;
    private JDA discord;
    private long channelID;

    private boolean isMySQLEnabled = false;
    private SQLManager sqlManager;
    private PointsCore plugin;
    public PlayerPoints playerPoints;
    public static DebugIntensity serverDebugIntensity;
    private final String pluginVersion ="1.1.92-SNAPSHOT";
    private boolean latest = true;
    public HashMapUtil hashMapUtil;
    private WarpsUtil warpManager;
    private HashMap<Player, TeleportUtil> noMoveTimeHome = new HashMap<>();
    private HashMap<Player, TeleportUtil> noMoveTimeSpawn = new HashMap<>();
    public void onDisable() {
        handleDiscordDisable();
        if(isMySQLEnabled) {
            sqlManager.saveData();
        } else{
            dataManager.saveAll();
        }
    }

    public void onEnable() {
        basicSetup();
        savingSetup();
        registerEvents();
        performPluginHooks(); //Hooking into other plugins
        registerCommands();
        updateChecker();
        MainRunnable mainRunnable = new MainRunnable(plugin, isMySQLEnabled, hashMapUtil, sqlManager,dataManager, noMoveTimeHome, noMoveTimeSpawn);
        mainRunnable.run();
    }

    private void registerCommands() {
        getCommand("pointcheck").setExecutor(new PointCheckCommand(plugin));
        getCommand("hologram").setExecutor(new Hologram());
        getCommand("points").setExecutor(new PointsCoreCommands(plugin));
        getCommand("randomtp").setExecutor(new RandomTP(plugin));
        getCommand("tpa").setExecutor(new TPA(plugin, hashMapUtil));
        getCommand("tpaccept").setExecutor(new TPAConfirmation(plugin, hashMapUtil));
        getCommand("fly").setExecutor(new Fly());
        getCommand("broadcast").setExecutor(new Broadcast(plugin));
        getCommand("god").setExecutor(new God());
        getCommand("heal").setExecutor(new Heal());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("message").setExecutor(new MessageCommand(plugin, hashMapUtil));
        getCommand("workbench").setExecutor(new WorkbenchCommand());
        getCommand("feed").setExecutor(new Feed());
        getCommand("enderchest").setExecutor(new EnderChestCommand());
        getCommand("spawn").setExecutor(new Spawn(plugin, noMoveTimeSpawn));
        getCommand("home").setExecutor(new Home(plugin, noMoveTimeHome));
    }

    public void removePoints(Tools tool, Player player, double amount) {
        playerPoints.removePointsToToolType(tool,player,amount);
    }

    public void addPoints(Tools tool, Player player, double amount) {
        playerPoints.addPointsToToolType(tool,player,amount);
    }

    private void updateChecker() {
        new UpdateChecker(this, 83263).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Message.errorMessage("PointsCore has a new update available, "+version, getServer().getConsoleSender());
                latest = false;
            }
        });
    }

    private void performPluginHooks() {
        //PlaceholderAPI Hook
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPILink(plugin, pluginVersion).register();
        }

        if(!getConfig().getBoolean("Points.ConnectWithVault", false)) {
            return;
        }

        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            if(Bukkit.getServer().getPluginManager().getPlugin("PointsCore").isEnabled()) {
                Bukkit.getServicesManager().register(Economy.class, new EconomySetup(this), this, ServicePriority.Normal);
                Message.notifyMessage("Economy has been registered to vault.", getServer().getConsoleSender());
            }
        }

    }

    /**
     * Handles the basic setup for the server
     */
    private void basicSetup() {
        plugin = this;
        int pluginId = 8682;
        Metrics metrics = new Metrics(plugin, pluginId);
        playerPoints = new PlayerPoints(plugin);
        getConfig().options().copyDefaults(true);
        saveConfig();
        serverDebugIntensity = getDebugIntensity();
        hashMapUtil = new HashMapUtil();
        warpManager = new WarpsUtil(plugin);
        handleDiscordEnable();
    }


    private void savingSetup() {
        warpManager.saveWarps();

        //If SQL is not enabled Initiates the data from the config and loads it
        if(!getConfig().getBoolean(pathIsSQLEnabled)) {
            dataManager = new DataManager();
            dataManager.onEnable(plugin);
            List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
            for(Player player: players) {
                playerPoints = dataManager.loadPlayerFromSaveFile(playerPoints, player);
            }

        } else {
            isMySQLEnabled = true;
            //MySQL Initialization
            try {
                sqlManager = new SQLManager(plugin, getConfig().getString(pathSQLUsername),getConfig().getString(pathSQLPassword),"POINTS",  getConfig().getString(pathSQLHostName), getConfig().getString(pathSQLDatabase));
                playerPoints = sqlManager.loadData().get();
            } catch (SQLException e) {
                e.printStackTrace();
                Message.errorMessage("SQL Was not successfully enabled, the details input must be incorrect.", getServer().getConsoleSender());
                isMySQLEnabled = false;
                savingSetup();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Message.errorMessage("SQL Was not successfully enabled, the details input must be incorrect.", getServer().getConsoleSender());
                isMySQLEnabled = false;
                savingSetup();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * To avoid code duplication
     */

    /**
     * Registers the events to the server
     */
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(plugin,discord), this);
        getServer().getPluginManager().registerEvents(new HologramEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(plugin, discord), this);
        getServer().getPluginManager().registerEvents(new ChatFormatter(plugin, discord), this);
        getServer().getPluginManager().registerEvents(new WeaponEvent(plugin), this); //Registers the armor points events
        getServer().getPluginManager().registerEvents(new ToolEvents(plugin), this); //Registers the tool points events
        getServer().getPluginManager().registerEvents(new ArmorEvent(plugin), this); //Registers the tool points events
        getServer().getPluginManager().registerEvents(new JoinEvent(plugin, discord, latest), this); // Registers the Join event
        getServer().getPluginManager().registerEvents(new MoveEvent(plugin, noMoveTimeSpawn, noMoveTimeHome), this); // Registers the move event, this is used for tpa
    }



    public void loadPlayerData(Player player) {
        if(isMySQLEnabled) {
            try {
                playerPoints = sqlManager.loadPlayerData(player).get() != null ? sqlManager.loadPlayerData(player).get() : playerPoints;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else {
            playerPoints = dataManager.loadPlayerFromSaveFile(playerPoints, player);
        }
    }

    public void savePlayerData(Player player) {
        if(isMySQLEnabled) {
            sqlManager.savePlayerData(player);
        } else {
            dataManager.addPlayerToSaveFile(player);
        }
    }


    /**
     * Gets the debug intensity for the messages of the server
     * @return the Debug intensity
     */
    public DebugIntensity getDebugIntensity() {
        if(getConfig().getString("Points.DebugMode").equalsIgnoreCase("INTENSE")) {

            return DebugIntensity.INTENSE;

        } else if(getConfig().getString("Points.DebugMode").equalsIgnoreCase("LIGHT")) {

            return DebugIntensity.LIGHT;

        } else {

            return DebugIntensity.NONE;

        }
    }

    private void handleDiscordEnable() {
        botToken = getConfig().getString("Discord.BotToken", null);
        channelID = getConfig().getLong("Discord.ChannelID", 0);
        if(channelID == 0)
            return;

        if(botToken == null)
            return;

        if(!getConfig().getBoolean("Discord.Enabled", true)) {
            return;
        }
        discord = JDABuilder.createLight(botToken)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.watching("Minecraft Chat"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        try {
            discord.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new DiscordChatHandler(this, discord);
        Message.debugMessage(" ====== Discord Instance ====== \n"+discord.toString(), DebugIntensity.INTENSE);
        MessageEmbed embed = new EmbedBuilder().setDescription("Server started!").addField("MOTD", getServer().getMotd(), true).setColor(new Color(3, 255, 0)).build();
        TextChannel textChannel = discord.getTextChannelById(channelID);
        textChannel.sendMessageEmbeds(embed).complete();

    }

    private void handleDiscordDisable() {
        if(botToken == null)
            return;

        if(!getConfig().getBoolean("Discord.Enabled", true)) {
            return;
        }
        MessageEmbed embed = new EmbedBuilder().setDescription("Server Stopped!").addField("MOTD", getServer().getMotd(), true).setColor(new Color(255, 60, 60)).build();
        TextChannel textChannel = discord.getTextChannelById(channelID);
        textChannel.sendMessageEmbeds(embed).queue();
    }
}
