package com.github.theword.mcqq.commands;

import com.github.theword.mcqq.constant.BaseConstant;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = BaseConstant.COMMAND_HEADER)
public class CommandExecutor extends CommandBase {

//    public static void registerCommands(RegisterCommandsEvent event) {
//        event.getDispatcher().register(
//                Commands.literal(BaseConstant.COMMAND_HEADER)
//                        .requires(source -> source.hasPermission(2))
//                        .executes(context -> new HelpCommand().onCommand(context))
//                        .then(Commands.literal("help")
//                                .executes(context -> new HelpCommand().onCommand(context))
//                        )
//                        .then(Commands.literal("reload")
//                                .executes(context -> new ReloadCommand().onCommand(context))
//                        )
//                        .then(Commands.literal("client")
//                                .then(Commands.literal("reconnect")
//                                        .executes(context -> new ReconnectCommand().onCommand(context))
//                                        .then(Commands.literal("all")
//                                                .executes(context -> new ReconnectAllCommand().onCommand(context)))
//                                )
//                        ).then(Commands.literal("server")
//                                .executes(context -> {
//                                    // TODO Websocket Server Command
//                                    handleCommandReturnMessage.handleCommandReturnMessage(context, "Server command is not supported");
//                                    return Command.SINGLE_SUCCESS;
//                                })
//                        )
//        );
//        ConfigCommand.register(event.getDispatcher());
//    }

    @Override
    public @NotNull String getName() {
        return "mcqq";
    }

    @Override
    public @NotNull String getUsage(@NotNull ICommandSender iCommandSender) {
        return "/mcqq";
    }

    @Override
    public void execute(@NotNull MinecraftServer minecraftServer, @NotNull ICommandSender iCommandSender, String @NotNull [] strings) {
        System.out.println(Arrays.toString(strings));
    }
}