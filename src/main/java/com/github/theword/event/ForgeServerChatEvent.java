package com.github.theword.event;

public final class ForgeServerChatEvent extends ForgeMessageEvent {
    public ForgeServerChatEvent(ForgeServerPlayer player, String message) {
        super("ForgeServerChatEvent", "chat", player, message);
    }

}
