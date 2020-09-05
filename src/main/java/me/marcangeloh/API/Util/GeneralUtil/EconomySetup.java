package me.marcangeloh.API.Util.GeneralUtil;

import me.marcangeloh.PointsCore;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EconomySetup extends AbstractEconomy {
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;

    public EconomySetup(PointsCore pointsCore) {
        if (this.pointsCore == null) {
            this.pointsCore = pointsCore;
        }
    }

    @Override
    public boolean isEnabled() {
        return pointsCore != null;
    }

    @Override
    public String getName() {
        return "PointsCore";
    }

    @Override
    public boolean hasBankSupport() {
        return PointsCore.plugin != null;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return "GPs";
    }

    @Override
    public String currencyNameSingular() {
        return "GP";
    }

    @Override
    public boolean hasAccount(String s) {
        Player player = Bukkit.getPlayer(s);
        if(player == null)
            return false;
        return pointsCore.playerPoints.getGeneralPoints(player) >0;
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return hasAccount(s);
    }

    @Override
    public double getBalance(String s) {
        if(Bukkit.getPlayer(s) == null)
            return 0;
        return pointsCore.playerPoints.getGeneralPoints(Bukkit.getPlayer(s));
    }

    @Override
    public double getBalance(String s, String s1) {
        return getBalance(s);
    }

    @Override
    public boolean has(String s, double v) {
        if(Bukkit.getPlayer(s) == null)
            return false;
        return pointsCore.playerPoints.getGeneralPoints(Bukkit.getPlayer(s)) >=v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return has(s,v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        if(Bukkit.getPlayer(s) == null)
            return new EconomyResponse(v, 0, EconomyResponse.ResponseType.FAILURE, "Couldn't get the player");
        pointsCore.playerPoints.removeFromGeneralPoints(Bukkit.getPlayer(s),v);
        return new EconomyResponse(v, pointsCore.playerPoints.getGeneralPoints(Bukkit.getPlayer(s)), EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return withdrawPlayer(s,v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        if(Bukkit.getPlayer(s) == null)
            return new EconomyResponse(v, 0, EconomyResponse.ResponseType.FAILURE, "Couldn't get the player");
        pointsCore.playerPoints.addToGeneralPoints(Bukkit.getPlayer(s),v);
        return new EconomyResponse(v, pointsCore.playerPoints.getGeneralPoints(Bukkit.getPlayer(s)), EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return depositPlayer(s,v);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "PointsCore does not support bank accounts!");
    }

    @Override
    public List<String> getBanks() {
        return new ArrayList<>();
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        if (!hasAccount(playerName)) {
            pointsCore.playerPoints.addToGeneralPoints(Bukkit.getPlayer(playerName), 0.0);
            return true;
        }
        return false;    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return createPlayerAccount(s);
    }
}
