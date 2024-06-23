package com.github.theword.mcqq.handleMessage;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.PlainTextContents.LiteralContents;

public class HandleCommandReturnMessageService implements HandleCommandReturnMessage {

    @Override
    @SuppressWarnings("unchecked")
    public void handleCommandReturnMessage(Object object, String message) {
        CommandContext<CommandSourceStack> context = (CommandContext<CommandSourceStack>) object;
        context.getSource().sendSystemMessage(MutableComponent.create(new LiteralContents(message)));
    }
}
