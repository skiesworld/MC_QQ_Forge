package com.github.theword.mcqq.handleMessage;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class HandleCommandReturnMessageService implements HandleCommandReturnMessage {

    @Override
    @SuppressWarnings("unchecked")
    public void handleCommandReturnMessage(Object object, String message) {
        CommandContext<CommandSource> context = (CommandContext<CommandSource>) object;
        context.getSource().sendSuccess(new StringTextComponent(message), false);
    }
}
