package com.github.theword.mcqq;


import com.github.theword.mcqq.eventModels.forge.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

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
    public void onPlayerJoin(PlayerLoggedInEvent event) {
        if (config.isEnableJoinMessage() && !event.isCanceled()) {
            ForgePlayerLoggedInEvent forgePlayerLoggedInEvent = new ForgePlayerLoggedInEvent(getPlayer((ServerPlayerEntity) event.getEntity()));
            sendMessage(forgePlayerLoggedInEvent);
        }
    }

    @SubscribeEvent
    public void onPlayerQuit(PlayerLoggedOutEvent event) {
        if (config.isEnableQuitMessage() && !event.isCanceled()) {
            ForgePlayerLoggedOutEvent forgePlayerLoggedInEvent = new ForgePlayerLoggedOutEvent(getPlayer((ServerPlayerEntity) event.getEntity()));
            sendMessage(forgePlayerLoggedInEvent);
        }
    }

    @SubscribeEvent
    public void onPlayerCommand(CommandEvent event) {
        if (config.isEnableCommandMessage() && !event.isCanceled()) {
            if (event.getParseResults().getContext().getSource().getEntity() instanceof ServerPlayerEntity) {
                String command = event.getParseResults().getReader().getString();

                if (!command.startsWith("l ") && !command.startsWith("login ") && !command.startsWith("register ") && !command.startsWith("reg ") && !command.startsWith("mcqq ")) {
                    ForgeServerPlayer player = getPlayer((ServerPlayerEntity) Objects.requireNonNull(event.getParseResults().getContext().getSource().getEntity()));
                    ForgeCommandEvent forgeCommandEvent = new ForgeCommandEvent("", player, command);
                    sendMessage(forgeCommandEvent);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (config.isEnableDeathMessage() && !event.isCanceled()) {
            if (event.getEntity() instanceof ServerPlayerEntity) {
                ForgeServerPlayer player = getPlayer((ServerPlayerEntity) event.getEntity());
                ForgePlayerDeathEvent forgeCommandEvent = new ForgePlayerDeathEvent("", player, event.getSource().getLocalizedDeathMessage((LivingEntity) event.getEntity()).getString());
                sendMessage(forgeCommandEvent);
            }
        }
    }

    ForgeServerPlayer getPlayer(ServerPlayerEntity player) {
        ForgeServerPlayer forgeServerPlayer = new ForgeServerPlayer();
        forgeServerPlayer.setNickname(player.getName().getString());
        forgeServerPlayer.setDisplayName(player.getDisplayName().getString());

        forgeServerPlayer.setUuid(player.getUUID().toString());
        forgeServerPlayer.setIpAddress(player.getIpAddress());
        forgeServerPlayer.setSpeed(player.getSpeed());
        forgeServerPlayer.setGameMode(player.gameMode.getGameModeForPlayer().toString());
        forgeServerPlayer.setBlockX((int) player.getX());
        forgeServerPlayer.setBlockY((int) player.getY());
        forgeServerPlayer.setBlockZ((int) player.getZ());

        forgeServerPlayer.setSwimming(player.isSwimming());
        forgeServerPlayer.setSleeping(player.isSleeping());
        forgeServerPlayer.setBlocking(player.isBlocking());

        forgeServerPlayer.setFlying(player.isFallFlying());
        forgeServerPlayer.setFlyingSpeed(player.flyingSpeed);

        return forgeServerPlayer;
    }
}
