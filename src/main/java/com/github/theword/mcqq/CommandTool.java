package com.github.theword.mcqq;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class CommandTool {
    public static void sendResultComponent(CommandContext<CommandSource> context, String text) {
        context.getSource().sendSuccess(new StringTextComponent(text), false);
    }
}
