package com.github.theword.mcqq.commands;

import com.github.theword.mcqq.commands.subCommands.HelpCommand;
import com.github.theword.mcqq.commands.subCommands.ReloadCommand;
import com.github.theword.mcqq.commands.subCommands.client.ReconnectCommand;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {

    private final List<ForgeSubCommand> subCommandList = new ArrayList<>();

    public CommandManager() {
        subCommandList.add(new HelpCommand());
        subCommandList.add(new ReloadCommand());
        subCommandList.add(new ReconnectCommand());
    }
}
