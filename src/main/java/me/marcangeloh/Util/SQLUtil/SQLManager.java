package me.marcangeloh.Util.SQLUtil;

import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class SQLManager {
    private final String columnName = "NAME";
    private final String columnUUID = "UUID";
    private final String columnArmorPoints = "ARMOR_POINTS";
    private final String columnMeleeWeaponPoints = "MELEE_POINTS";
    private final String columnRangedWeaponPoints = "RANGED_POINTS";
    private final String columnHoePoints = "HOE_POINTS";
    private final String columnPickaxePoints = "PICKAXE_POINTS";
    private final String columnAxePoints = "AXE_POINTS";
    private final String columnShovelPoints = "SHOVEL_POINTS";
    private final String columnToolPoints = "TOOL_POINTS";
    private final String columnFishingPoints = "FISHING_POINTS";
    private Connection connection;
    private final String username, password, table, host, database;

    HashMap<String, SQLLoadUtil> loadUtilHashMap  = new HashMap<>();

    public SQLManager(String username, String password, String table, String host, String database) throws SQLException, ClassNotFoundException {
        this.username = username;
        this.password = password;
        this.table = table;
        this.host = host;
        this.database = database;
        connect();
        createTable();
    }


    public void loadData(String name, String uuid) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM  "+table+" WHERE "+columnUUID+" = ? AND "+columnName+" = ?");
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                PointsCore.playerPoints.armorPoints.addPointsToPlayer(uuid, resultSet.getDouble(columnArmorPoints));
                PointsCore.playerPoints.axePoints.addPointsToPlayer(uuid, resultSet.getDouble(columnAxePoints));
                PointsCore.playerPoints.fishingPoints.addPointsToPlayer(uuid, resultSet.getDouble(columnFishingPoints));
                PointsCore.playerPoints.hoePoints.addPointsToPlayer(uuid, resultSet.getDouble(columnHoePoints));
                PointsCore.playerPoints.meleeWeaponPoints.addPointsToPlayer(uuid, resultSet.getDouble(columnMeleeWeaponPoints));
                PointsCore.playerPoints.pickaxePoints.addPointsToPlayer(uuid, resultSet.getDouble(columnPickaxePoints));
                PointsCore.playerPoints.rangedWeaponPoints.addPointsToPlayer(uuid, resultSet.getDouble(columnRangedWeaponPoints));
                PointsCore.playerPoints.shovelPoints.addPointsToPlayer(uuid, resultSet.getDouble(columnShovelPoints));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private boolean saveUtil(HashMap<String, Double> toCheck, HashMap<String, Double> armorPoints, HashMap<String, Double> meleeWeaponPoints, HashMap<String, Double> rangedWeaponPoints, HashMap<String, Double> hoePoints, HashMap<String, Double> pickaxePoints, HashMap<String, Double> axePoints, HashMap<String, Double> fishingPoints, HashMap<String, Double> shovelPoints) {
        for(String player:  toCheck.keySet()) { //player is uuid
            Double melee=0.0, ranged=0.0, armor=0.0, tools=0.0, pickaxe=0.0, fishing=0.0, hoe=0.0, axe=0.0, shovel = 0.0;
            String name = "";
            if(meleeWeaponPoints.containsKey(player)) {
                melee = meleeWeaponPoints.get(player);
                meleeWeaponPoints.remove(player);
            }
            if(armorPoints.containsKey(player)) {
                armor = armorPoints.get(player);
                armorPoints.remove(player);
            }
            if(rangedWeaponPoints.containsKey(player)) {
                ranged = rangedWeaponPoints.get(player);
                rangedWeaponPoints.remove(player);
            }
            if(hoePoints.containsKey(player)) {
                hoe = hoePoints.get(player);
                hoePoints.remove(player);
            }
            if(pickaxePoints.containsKey(player)) {
                pickaxe = pickaxePoints.get(player);
                pickaxePoints.get(player);
            }
            if(axePoints.containsKey(player)) {
                axe = axePoints.get(player);
                axePoints.remove(player);
            }

            if(fishingPoints.containsKey(player)) {
                fishing = fishingPoints.get(player);
                fishingPoints.remove(player);
            }

            if(shovelPoints.containsKey(player)) {
                shovel = fishingPoints.get(player);
                shovelPoints.remove(player);
            }

            loadUtilHashMap.put(player, new SQLLoadUtil(player, name, armor, melee,ranged,tools,hoe,pickaxe,axe,fishing, shovel));
        }

        hashmapCheck(armorPoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);

        hashmapCheck(meleeWeaponPoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);

        hashmapCheck(rangedWeaponPoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);


        hashmapCheck(hoePoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);

        hashmapCheck(pickaxePoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);

        hashmapCheck(axePoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);

        hashmapCheck(fishingPoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);

        hashmapCheck(shovelPoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints,fishingPoints, shovelPoints);

        if(!isHashMapEmpty(armorPoints) && !isHashMapEmpty(meleeWeaponPoints) &&!isHashMapEmpty(rangedWeaponPoints)
        && !isHashMapEmpty(hoePoints) && !isHashMapEmpty(pickaxePoints) &&!isHashMapEmpty(axePoints) &&!isHashMapEmpty(fishingPoints) && !isHashMapEmpty(shovelPoints)) {
            return true;
        } else {
            return false;
        }
    }

    private void hashmapCheck(HashMap<String, Double> toCheck, HashMap<String, Double> armorPoints, HashMap<String, Double> meleeWeaponPoints, HashMap<String, Double> rangedWeaponPoints, HashMap<String, Double> hoePoints, HashMap<String, Double> pickaxePoints, HashMap<String, Double> axePoints, HashMap<String, Double> fishingPoints, HashMap<String, Double> shovelPoints) {
        if(isHashMapEmpty(toCheck)) {
            saveUtil(toCheck, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints, axePoints, fishingPoints, shovelPoints);
        }
    }

    /**
     * Returns the inverted value of HashMap.isEmpty()
     * @param hashMap the hashmap to check
     * @return boolean of true if it is not empty or false if it is
     */
    private boolean isHashMapEmpty(HashMap<String, Double> hashMap) {
        if(hashMap.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    public void saveData() {
        //Redefining data for change
        HashMap<String, Double> armorPoints= PointsCore.playerPoints.armorPoints.getArmorPoints(),
                meleeWeaponPoints = PointsCore.playerPoints.meleeWeaponPoints.getMeleeWeaponPoints(),
                rangedWeaponPoints=PointsCore.playerPoints.rangedWeaponPoints.getRangedWeaponPoints(),
                hoePoints=PointsCore.playerPoints.hoePoints.getHoePoints(),
                pickaxePoints=PointsCore.playerPoints.pickaxePoints.getPickaxePoints(),
                axePoints=PointsCore.playerPoints.axePoints.getAxePoints(),
                fishingPoints=PointsCore.playerPoints.fishingPoints.getFishingPoints(),
                shovelPoints=PointsCore.playerPoints.shovelPoints.getShovelPoints();

        if(saveUtil(armorPoints, armorPoints, meleeWeaponPoints, rangedWeaponPoints, hoePoints, pickaxePoints,axePoints, fishingPoints, shovelPoints)) {
            //Loaded successfully into loadUtilHashmap
            for (String player: loadUtilHashMap.keySet()
                 ) {
                try {
                    SQLLoadUtil loadUtil = loadUtilHashMap.get(player);
                    PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO "+table+" ("+columnUUID+", "+columnName+", "+columnArmorPoints+", "+columnMeleeWeaponPoints+", "+columnRangedWeaponPoints+", "+columnHoePoints+", "+columnPickaxePoints+", "+columnAxePoints+", "+columnFishingPoints+", "+shovelPoints+") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ON DUPLICATE KEY UPDATE "+columnArmorPoints+" = ?, "+columnMeleeWeaponPoints +" = ?, " +columnRangedWeaponPoints+ "= ?, "+columnToolPoints+"= ?, "+columnHoePoints+"= ?,"+columnPickaxePoints+"= ?,"+ columnAxePoints+"= ?"+columnFishingPoints+"= ?,"+columnShovelPoints+"= ? WHERE "+columnUUID+" = ?");
                    preparedStatement.setString(1, loadUtil.getUuid());
                    preparedStatement.setString(2, loadUtil.getPlayerName());
                    preparedStatement.setDouble(3, loadUtil.getArmorPoints());
                    preparedStatement.setDouble(4, loadUtil.getMeleeWeaponPoints());
                    preparedStatement.setDouble(5, loadUtil.getRangedWeaponPoints());
                    preparedStatement.setDouble(6, loadUtil.getToolPoints());
                    preparedStatement.setDouble(7, loadUtil.getHoePoints());
                    preparedStatement.setDouble(8, loadUtil.getPickaxePoints());
                    preparedStatement.setDouble(9, loadUtil.getAxePoints());
                    preparedStatement.setDouble(10, loadUtil.getFishingPoints());
                    preparedStatement.setDouble(11, loadUtil.getShovelPoints());preparedStatement.setDouble(3, loadUtil.getArmorPoints());
                    preparedStatement.setDouble(12, loadUtil.getMeleeWeaponPoints());
                    preparedStatement.setDouble(13, loadUtil.getRangedWeaponPoints());
                    preparedStatement.setDouble(14, loadUtil.getHoePoints());
                    preparedStatement.setDouble(15, loadUtil.getPickaxePoints());
                    preparedStatement.setDouble(16, loadUtil.getAxePoints());
                    preparedStatement.setDouble(17, loadUtil.getFishingPoints());
                    preparedStatement.setDouble(18, loadUtil.getShovelPoints());
                    preparedStatement.setString(19, loadUtil.getUuid());
                    preparedStatement.execute();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } else {
            //Not all data was loaded, an error has occurred.

        }
    }
    private void createTable() {
        String query="CREATE TABLE IF NOT EXISTS "+ table +
                " ( " +
                columnUUID + " UNSIGNED VARCHAR(36) NOT NULL, " +
                columnName + " VARCHAR(20) NOT NULL, " +
                columnArmorPoints + " DOUBLE, " +
                columnMeleeWeaponPoints + " DOUBLE, " +
                columnRangedWeaponPoints + " DOUBLE, " +
                columnHoePoints + " DOUBLE, " +
                columnPickaxePoints + " DOUBLE, " +
                columnAxePoints + " DOUBLE, " +
                columnToolPoints + " DOUBLE, " +
                columnShovelPoints + " DOUBLE, " +
                columnFishingPoints + " DOUBLE, " +
                "PRIMARY KEY ( "+columnUUID+" ));";
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
            Statement statement = getConnection().createStatement();
            int numOfRows = statement.executeUpdate(query);
            if(numOfRows != 0)
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + table + " has been created with "+numOfRows+" rows.");
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
