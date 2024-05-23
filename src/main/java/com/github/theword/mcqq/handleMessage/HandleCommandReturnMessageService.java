package com.github.theword.mcqq.handleMessage;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;

public class HandleCommandReturnMessageService implements HandleCommandReturnMessage {

    @Override
    @SuppressWarnings("unchecked")
    public void handleCommandReturnMessage(Object object, String message) {
        CommandContext<CommandSourceStack> context = (CommandContext<CommandSourceStack>) object;
        context.getSource().sendSuccess(new TextComponent(message), false);
    }
}
