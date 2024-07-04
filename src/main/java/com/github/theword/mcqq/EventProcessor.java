package com.github.theword.mcqq;


import com.github.theword.mcqq.eventModels.forge.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.github.theword.mcqq.utils.Tool.config;
import static com.github.theword.mcqq.utils.Tool.sendMessage;

public class EventProcessor {

    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        if (config.isEnableChatMessage()) {
            ForgeServerChatEvent forgeServerChatEvent = new ForgeServerChatEvent("", getPlayer(event.getPlayer()), event.getMessage());
            sendMessage(forgeServerChatEvent);
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (config.isEnableJoinMessage() && !event.isCanceled()) {
            ForgePlayerLoggedInEvent forgePlayerLoggedInEvent = new ForgePlayerLoggedInEvent(getPlayer((EntityPlayerMP) event.player));
            sendMessage(forgePlayerLoggedInEvent);
        }
    }

    @SubscribeEvent
    public void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
        if (config.isEnableQuitMessage() && !event.isCanceled()) {
            ForgePlayerLoggedOutEvent forgePlayerLoggedInEvent = new ForgePlayerLoggedOutEvent(getPlayer((EntityPlayerMP) event.player));
            sendMessage(forgePlayerLoggedInEvent);
        }
    }

    @SubscribeEvent
    public void onPlayerCommand(CommandEvent event) {
        if (config.isEnableCommandMessage() && !event.isCanceled()) {
            if (event.getSender().getCommandSenderEntity() instanceof EntityPlayerMP) {
                String command = event.getCommand().toString();

                if (!command.startsWith("l ") && !command.startsWith("login ") && !command.startsWith("register ") && !command.startsWith("reg ") && !command.startsWith("mcqq ")) {
                    ForgeServerPlayer player = getPlayer((EntityPlayerMP) event.getSender().getCommandSenderEntity());
                    ForgeCommandEvent forgeCommandEvent = new ForgeCommandEvent("", player, command);
                    sendMessage(forgeCommandEvent);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (config.isEnableDeathMessage() && !event.isCanceled()) {
            if (event.getEntity() instanceof EntityPlayerMP) {
                ForgeServerPlayer player = getPlayer((EntityPlayerMP) event.getEntity());
                ForgePlayerDeathEvent forgeCommandEvent = new ForgePlayerDeathEvent("", player, event.getSource().getDeathMessage(event.getEntityLiving()).toString());
                sendMessage(forgeCommandEvent);
            }
        }
    }

    ForgeServerPlayer getPlayer(EntityPlayerMP player) {
        ForgeServerPlayer forgeServerPlayer = new ForgeServerPlayer();
        forgeServerPlayer.setNickname(player.getName());
        forgeServerPlayer.setDisplayName(player.getDisplayName().toString());

        forgeServerPlayer.setUuid(player.getUniqueID().toString());
        forgeServerPlayer.setIpAddress(player.getPlayerIP());

        forgeServerPlayer.setSpeed(player.getAIMoveSpeed());
        forgeServerPlayer.setGameMode(player.interactionManager.getGameType().getName());
        forgeServerPlayer.setBlockX(player.getPosition().getX());
        forgeServerPlayer.setBlockY(player.getPosition().getY());
        forgeServerPlayer.setBlockZ(player.getPosition().getZ());

        forgeServerPlayer.setSwimming(false);
        forgeServerPlayer.setSleeping(player.isPlayerSleeping());
        forgeServerPlayer.setBlocking(player.isActiveItemStackBlocking());

        forgeServerPlayer.setFlying(player.capabilities.isFlying);
        forgeServerPlayer.setFlyingSpeed(player.capabilities.getFlySpeed());

        return forgeServerPlayer;
    }
}
