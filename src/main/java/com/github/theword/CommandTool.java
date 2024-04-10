package com.github.theword;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;

public class CommandTool {
    public static void sendResultComponent(CommandContext<CommandSourceStack> context, String text) {
        context.getSource().sendSuccess(new TextComponent(text), false);
    }
}
