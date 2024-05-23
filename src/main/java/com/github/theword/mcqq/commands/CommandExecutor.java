package com.github.theword.mcqq.commands;

import com.github.theword.mcqq.MCQQ;
import com.github.theword.mcqq.commands.subCommands.HelpCommand;
import com.github.theword.mcqq.commands.subCommands.ReloadCommand;
import com.github.theword.mcqq.commands.subCommands.client.ReconnectAllCommand;
import com.github.theword.mcqq.commands.subCommands.client.ReconnectCommand;
import com.github.theword.mcqq.constant.BaseConstant;
import com.mojang.brigadier.Command;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import static com.github.theword.mcqq.utils.Tool.handleCommandReturnMessage;

@Mod.EventBusSubscriber(modid = MCQQ.MOD_ID)
public class CommandExecutor {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal(BaseConstant.COMMAND_HEADER)
                        .requires(source -> source.hasPermission(2))
                        .executes(context -> new HelpCommand().onCommand(context))
                        .then(Commands.literal("help")
                                .executes(context -> new HelpCommand().onCommand(context))
                        )
                        .then(Commands.literal("reload")
                                .executes(context -> new ReloadCommand().onCommand(context))
                        )
                        .then(Commands.literal("client")
                                .then(Commands.literal("reconnect")
                                        .executes(context -> new ReconnectCommand().onCommand(context))
                                        .then(Commands.literal("all")
                                                .executes(context -> new ReconnectAllCommand().onCommand(context)))
                                )
                        ).then(Commands.literal("server")
                                .executes(context -> {
                                    // TODO Websocket Server Command
                                    handleCommandReturnMessage.handleCommandReturnMessage(context, "Server command is not supported");
                                    return Command.SINGLE_SUCCESS;
                                })
                        )
        );
        ConfigCommand.register(event.getDispatcher());
    }
}