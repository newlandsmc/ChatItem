package com.semivanilla.chatitem.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component input = event.message().replaceText(
                TextReplacementConfig.builder().once().matchLiteral("[i]").replacement(itemComponent(player.getInventory().getItemInMainHand())).build());
        event.message(input);
    }

    private Component itemComponent(ItemStack item) {
        Component component = Component.text("[i]", NamedTextColor.AQUA);
        if(item.getType().equals(Material.AIR))
            return Component.text("[i]");
        if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            component = component.append(item.getItemMeta().displayName());
        } else {
            component = component.append(Component.text(materialToName(item.getType()), NamedTextColor.WHITE));
        }
        component = component.append(Component.text(" x" + item.getAmount(), NamedTextColor.AQUA));
        component = component.hoverEvent(item.asHoverEvent());
        return component;
    }

    // TODO : Code cleanup
    private String materialToName(Material m) {
        if (m.equals(Material.TNT)) {
            return "TNT";
        }
        String orig = m.toString().toLowerCase();
        String[] splits = orig.split("_");
        StringBuilder sb = new StringBuilder(orig.length());
        int pos = 0;
        for (String split : splits) {
            sb.append(split);
            int loc = sb.lastIndexOf(split);
            char charLoc = sb.charAt(loc);
            if (!(split.equalsIgnoreCase("of") || split.equalsIgnoreCase("and") ||
                    split.equalsIgnoreCase("with") || split.equalsIgnoreCase("on")))
                sb.setCharAt(loc, Character.toUpperCase(charLoc));
            if (pos != splits.length - 1)
                sb.append(' ');
            ++pos;
        }

        return sb.toString();
    }
}
