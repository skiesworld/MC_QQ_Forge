package com.github.theword.mcqq.commands;


import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

public interface ForgeSubCommand {

    String getName();

    String getDescription();

    String getUsage();

    int onCommand(@NotNull MinecraftServer minecraftServer, @NotNull ICommandSender iCommandSender, String @NotNull [] strings);

}
