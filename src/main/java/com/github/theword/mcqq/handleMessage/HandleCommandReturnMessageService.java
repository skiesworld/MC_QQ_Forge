package com.github.theword.mcqq.handleMessage;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

public class HandleCommandReturnMessageService implements HandleCommandReturnMessage {

    @Override
    public void handleCommandReturnMessage(Object object, String message) {
        ICommandSender iCommandSender = (ICommandSender) object;
        iCommandSender.sendMessage(new TextComponentString(message));
    }
}
