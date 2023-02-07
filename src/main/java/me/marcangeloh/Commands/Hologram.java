package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Hologram implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            Message.errorMessage("This command can only be used by a player.", sender);
            return true;
        }

        Player player = (Player) sender;

        if (!(command.getName().equalsIgnoreCase("hologram")) &&
                !(command.getName().equalsIgnoreCase("holo"))) {
            return true;
        }

        if (args.length < 1) {
            sendHelpMessage(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
            case "add":
                handleHologramCreate(args, player);
                return true;
            case "remove":
            case "delete":
                if(args.length < 2) {
                    sendHelpMessage(player);
                    return true;
                }
                handleDeleteHologram(args[1], player);
                return true;
            default:
                sendHelpMessage(player);
                return true;
        }

    }

    private void handleDeleteHologram(String arg, Player player) {
        int radius;
        try {
            radius = Integer.parseInt(arg);
        } catch (NumberFormatException exception) {
            Message.errorMessage("Correct command usage is: \n"+
                    "/hologram remove <radius>\n There was an issue with the number you inserted. ", player);
            return;
        }
        List<Entity> entities = player.getNearbyEntities(radius, radius, radius);

        for (Entity entity : entities) {
            if(!(entity instanceof ArmorStand))
                continue;

            ArmorStand armorStand = (ArmorStand) entity;
            if(!armorStand.isVisible()) {
                armorStand.remove();
            }
        }
    }

    private void handleHologramCreate(String[] args, Player player) {

        if(args.length <2) {
            sendHelpMessage(player);
            return;
        }

        ArrayList<String> messages = new ArrayList<>();
        int index = 0;
        int counter = 0;
        for (String arg: args
        ) {
            if(counter == 0) {
                counter++;
                continue;
            }

            if(index == 0 && counter ==1) {
                messages.add(arg);
                counter++;
                continue;
            }

            if (counter%4 == 0) {
                messages.add(arg);
                index++;
                counter++;
                continue;
            }

            messages.set(index, messages.get(index)+" "+ arg);
            counter++;

        }
        Message messager = new Message();

        counter = 0;

        for (String message: messages
        ) {
            counter++;
            messager.hologramAtLocation(player.getLocation().add(0.5, -0.35*counter, 0.5),Message.format(message));
        }

        Message.notifyMessage("Hologram created at location\n x: "+player.getLocation().getX()+" | y: "+player.getLocation().getY()+" | z: "+player.getLocation().getZ(), player);
    }

    private void sendHelpMessage(Player player) {
        Message.notifyMessage("Holograms use the following commands:\n" +
                "/hologram create <text>\n" +
                "/hologram remove <radius>" +
                "/hologram help",player);
    }
}
