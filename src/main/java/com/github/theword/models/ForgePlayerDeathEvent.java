package com.github.theword.models;

public class ForgePlayerDeathEvent extends ForgeMessageEvent {

    public ForgePlayerDeathEvent(String messageId, ForgeServerPlayer player, String message) {
        super("ForgePlayerDeathEvent", "death", messageId, player, message);
    }
}
