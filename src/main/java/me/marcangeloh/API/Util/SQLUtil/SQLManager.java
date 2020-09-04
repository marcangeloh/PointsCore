package me.marcangeloh.API.Util.SQLUtil;

import me.marcangeloh.PointsCore;
import me.marcangeloh.API.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class SQLManager {
    private Connection connection;
    private final String username, password, table, host, database;
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;

    String columnUuid = "UUID",
            columnArmor = "ARMOR_POINTS",
            columnAxe = "AXE_POINTS",
            columnFishing = "FISHING_POINTS",
            columnHoe = "HOE_POINTS",
            columnMelee = "MELEE_WEAPON_POINTS",
            columnPickaxe = "PICKAXE_POINTS",
            columnRanged = "RANGED_WEAPON_POINTS",
            columnShovel = "SHOVEL_POINTS";

    public SQLManager(String username, String password, String table, String host, String database) throws SQLException, ClassNotFoundException {
        this.username = username;
        this.password = password;
        this.table = table;
        this.host = host;
        this.database = database;
        connect();
        createTable();
    }

    public void saveData() {
        checkSQLConnection();
        for (String uuid: getUUIDs()
             ) {
            String query = "INSERT INTO " + table + " ("+columnUuid+ ", " +columnArmor + ", "+ columnAxe+", " + columnFishing+", "+ columnHoe+", " +columnMelee+", "+columnPickaxe +", "+ columnRanged+", "+columnShovel+") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE "+ columnArmor + "=? ," + columnAxe +"=? ," + columnFishing+"=? ,"+ columnHoe+"=? ," +columnMelee+"=? ,"+columnPickaxe +"=? ,"+ columnRanged+"=? ,"+columnShovel+"=?;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, uuid);
                preparedStatement.setDouble(2, pointsCore.playerPoints.armorPoints.getPoints(uuid));
                preparedStatement.setDouble(3, pointsCore.playerPoints.axePoints.getPoints(uuid));
                preparedStatement.setDouble(4, pointsCore.playerPoints.fishingPoints.getPoints(uuid));
                preparedStatement.setDouble(5, pointsCore.playerPoints.hoePoints.getPoints(uuid));
                preparedStatement.setDouble(6, pointsCore.playerPoints.meleeWeaponPoints.getPoints(uuid));
                preparedStatement.setDouble(7, pointsCore.playerPoints.pickaxePoints.getPoints(uuid));
                preparedStatement.setDouble(8, pointsCore.playerPoints.rangedWeaponPoints.getPoints(uuid));
                preparedStatement.setDouble(9, pointsCore.playerPoints.shovelPoints.getPoints(uuid));
                preparedStatement.setDouble(10, pointsCore.playerPoints.armorPoints.getPoints(uuid));
                preparedStatement.setDouble(11, pointsCore.playerPoints.axePoints.getPoints(uuid));
                preparedStatement.setDouble(12, pointsCore.playerPoints.fishingPoints.getPoints(uuid));
                preparedStatement.setDouble(13, pointsCore.playerPoints.hoePoints.getPoints(uuid));
                preparedStatement.setDouble(14, pointsCore.playerPoints.meleeWeaponPoints.getPoints(uuid));
                preparedStatement.setDouble(15, pointsCore.playerPoints.pickaxePoints.getPoints(uuid));
                preparedStatement.setDouble(16, pointsCore.playerPoints.rangedWeaponPoints.getPoints(uuid));
                preparedStatement.setDouble(17, pointsCore.playerPoints.shovelPoints.getPoints(uuid));
                if(!preparedStatement.execute()) {
                    Message.debugMessage("An error has occurred during the execution of the mysql save statement.", DebugIntensity.LIGHT);
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Message.debugMessage("An error has occurred due to an SQL Exception.", DebugIntensity.LIGHT);
            }
        }
    }

    /**
     * Should only be initiated at the start of the plugin
     */
    public void loadData() {
        String query = "SELECT * FROM "+ table;
        PreparedStatement statement = null;
        checkSQLConnection();
        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                pointsCore.playerPoints.armorPoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnArmor));
                pointsCore.playerPoints.axePoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnAxe));
                pointsCore.playerPoints.fishingPoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnFishing));
                pointsCore.playerPoints.hoePoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnHoe));
                pointsCore.playerPoints.meleeWeaponPoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnMelee));
                pointsCore.playerPoints.pickaxePoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnPickaxe));
                pointsCore.playerPoints.rangedWeaponPoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnRanged));
                pointsCore.playerPoints.shovelPoints.addPointsToPlayer(rs.getString(columnUuid), rs.getDouble(columnShovel));
            }
            connection.close();
            return;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * A method to get all the uuids to save
     * @return the uuids to save
     */
    public ArrayList<String> getUUIDs() {
        ArrayList<String> uuids = new ArrayList<>();
        if(pointsCore.playerPoints.armorPoints.getArmorPoints() != null) {
            for (String uuid : pointsCore.playerPoints.armorPoints.getArmorPoints().keySet()
            ) {
                uuids.add(uuid);
            }
        }

        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.axePoints.getAxePoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.fishingPoints.getFishingPoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.hoePoints.getHoePoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.meleeWeaponPoints.getMeleeWeaponPoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.pickaxePoints.getPickaxePoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.rangedWeaponPoints.getRangedWeaponPoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.shovelPoints.getShovelPoints()));
        return uuids;
    }

    /**
     * Checks if the hashmap is null
     * @param uuids the uuid list
     * @param map the hashmap to check
     * @return the uuid list
     */
    private ArrayList<String> nullCheck(ArrayList<String> uuids, HashMap<String, Double> map) {
        if(map == null) {
            return new ArrayList<String>();
        } else {
            return checkUUID(uuids, map.keySet());
        }
    }

    /**
     * Checks if the should be added or if it's already contained
     * in the keyset
     * @param uuids the uuids
     * @param keySet the set of uuids to loop through
     * @return the uuids to add
     */
    private ArrayList<String> checkUUID(ArrayList<String> uuids, Set<String> keySet) {
        ArrayList<String> uuidsToAdd = new ArrayList<>();
        for (String uuid: keySet) {
            if(!uuids.contains(uuid)) {
                uuidsToAdd.add(uuid);
            }
        }
        return uuidsToAdd;
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + table + " ( "+
                columnUuid + " VARCHAR (50) NOT NULL, " +
                columnArmor + " DOUBLE PRECISION, "+
                columnAxe + " DOUBLE PRECISION, "+
                columnFishing + " DOUBLE PRECISION, "+
                columnHoe + " DOUBLE PRECISION, "+
                columnMelee + " DOUBLE PRECISION, "+
                columnPickaxe + " DOUBLE PRECISION, "+
                columnRanged + " DOUBLE PRECISION, "+
                columnShovel + " DOUBLE PRECISION, PRIMARY KEY ( "+columnUuid+" ))";
        checkSQLConnection();
        createTable(query);
    }

    /**
     * Creates a MYSQL table based on a Query
     *
     * @param query what to execute when creating the table
     */
    private void createTable(String query) {
        checkSQLConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            boolean executed = statement.execute();
            if(executed)
                Message.debugMessage(table + " has been created.", DebugIntensity.LIGHT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkSQLConnection() {
        try {
            if(getConnection().isClosed()) {
                reloadConnection();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void connect() throws SQLException, ClassNotFoundException {
        synchronized (this) {
            if (getConnection() != null && !getConnection().isClosed()) {
                return;
            }

            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", username);
            properties.setProperty("password", password);
            properties.setProperty("autoReconnect", "true");
            properties.setProperty("useSSL", "false");
            setConnection(DriverManager.getConnection("jdbc:mysql://" + host  + "/" + database, properties));
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MySQL has been connected and enabled.");
        }

    }

    /**
     * Reloads the connection
     */
    public void reloadConnection() throws SQLException, ClassNotFoundException {
        connection.close();
        connect();
    }

    /**
     * Getter for MySQL connection
     */
    public Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }
}
