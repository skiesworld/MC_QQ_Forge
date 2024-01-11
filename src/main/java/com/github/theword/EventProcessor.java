package com.github.theword;


import com.github.theword.event.ForgeCommandEvent;
import com.github.theword.event.ForgePlayerLoggedInEvent;
import com.github.theword.event.ForgeServerChatEvent;
import com.github.theword.event.ForgeServerPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

import static com.github.theword.MCQQ.wsClient;
import static com.github.theword.Utils.getEventJson;
import static com.github.theword.Utils.getPlayer;

public class EventProcessor {


    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        if ((Boolean) ConfigReader.config().get("enable_mc_qq") && !event.isCanceled()) {
            ForgeServerChatEvent forgeServerChatEvent = new ForgeServerChatEvent("", getPlayer(event.getPlayer()), event.getMessage().toString());
            wsClient.sendMessage(getEventJson(forgeServerChatEvent));
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerLoggedInEvent event) {
        if ((Boolean) ConfigReader.config().get("join_quit") && !event.isCanceled()) {
            ForgePlayerLoggedInEvent forgePlayerLoggedInEvent = new ForgePlayerLoggedInEvent(getPlayer((ServerPlayer) event.getEntity()));
            wsClient.sendMessage(getEventJson(forgePlayerLoggedInEvent));
        }
    }

    @SubscribeEvent
    public void onPlayerQuit(PlayerLoggedOutEvent event) {
        if ((Boolean) ConfigReader.config().get("join_quit") && !event.isCanceled()) {
            ForgePlayerLoggedInEvent forgePlayerLoggedInEvent = new ForgePlayerLoggedInEvent(getPlayer((ServerPlayer) event.getEntity()));
            wsClient.sendMessage(getEventJson(forgePlayerLoggedInEvent));
        }
    }

    @SubscribeEvent
    public void onPlayerCommand(CommandEvent event) {
        if ((Boolean) ConfigReader.config().get("enable_mc_qq") && !event.isCanceled()) {
            if (event.getParseResults().getContext().getSource().isPlayer()) {
                String command = event.getParseResults().getReader().getString();

                if (!command.startsWith("l ") && !command.startsWith("login ") && !command.startsWith("register ") && !command.startsWith("reg ")) {
                    ForgeServerPlayer player = getPlayer(Objects.requireNonNull(event.getParseResults().getContext().getSource().getPlayer()));
                    ForgeCommandEvent forgeCommandEvent = new ForgeCommandEvent("", player, command);
                    wsClient.sendMessage(getEventJson(forgeCommandEvent));
                }
            }
        }
    }
}
