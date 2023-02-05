package com.cnluminous.kookticketbot.commands;

import com.cnluminous.kookticketbot.Main;
import com.cnluminous.kookticketbot.cardmessages.CreateTicketMessage;
import snw.jkook.command.CommandExecutor;
import snw.jkook.command.CommandSender;
import snw.jkook.command.ConsoleCommandSender;
import snw.jkook.entity.User;
import snw.jkook.message.Message;
import snw.jkook.message.TextChannelMessage;

public class CreateTicket implements CommandExecutor {
    @Override
    public void onCommand(CommandSender commandSender, Object[] objects,Message message) {
        if (commandSender instanceof ConsoleCommandSender){
            Main.getInstance().getLogger().warn("此命令仅允许用户使用!");
            return;
        }
        if (message instanceof TextChannelMessage){
            TextChannelMessage textChannelMessage = (TextChannelMessage) message;
            textChannelMessage.reply(new CreateTicketMessage().getCard());

        }else {
            message.getSender().sendPrivateMessage("请在频道中使用此命令,私聊会话无法使用");
            return;
        }
    }
}
