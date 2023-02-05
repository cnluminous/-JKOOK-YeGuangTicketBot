package com.cnluminous.kookticketbot.events;

import com.cnluminous.kookticketbot.Config;
import com.cnluminous.kookticketbot.Main;
import com.cnluminous.kookticketbot.cardmessages.NotifyMessage;
import com.cnluminous.kookticketbot.cardmessages.TicketCloseMessage;
import com.cnluminous.kookticketbot.cardmessages.TicketCreateSuccessMessage;
import snw.jkook.Permission;
import snw.jkook.entity.Guild;
import snw.jkook.entity.channel.TextChannel;
import snw.jkook.event.EventHandler;
import snw.jkook.event.Listener;
import snw.jkook.event.user.UserClickButtonEvent;
import java.util.ArrayList;

public class EUserClickButtonEvent implements Listener {
    String groupId = Config.groupId;
    ArrayList<Integer> adminRolS = Config.adminRol;
    @EventHandler
    public void onEvent(UserClickButtonEvent event) {
        if (event.getValue().startsWith("TICKET_CREATE_")){
            String server = event.getValue().substring(14);
            String ticketId = String.valueOf(System.currentTimeMillis() + (long) (Math.random() * 10000000L));
            Guild guild = event.getChannel().getGuild();
            TextChannel textChannel = guild.createTextChannel("工单"+ticketId,Main.getInstance().getCore().getHttpAPI().getCategory(groupId));
            textChannel.updatePermission(event.getUser(), Permission.SEE_CHANNELS.getValue(),1);
            for (Integer rol:adminRolS){
                textChannel.updatePermission(rol, Permission.SEE_CHANNELS.getValue(),1);
            }
            textChannel.sendComponent(new TicketCreateSuccessMessage(event.getUser(),ticketId,server).getCard());
            NotifyMessage card = new NotifyMessage("工单已发起#"+ticketId,"(met)"+event.getUser().getId()+"(met)发起了「"+server+"」工单,请前往子频道「(chn)"+textChannel.getId()+"(chn)」中进行处理");
            Main.getInstance().getLogger().info(event.getUser().getName()+"发起了工单,编号="+ticketId+"频道ID="+textChannel.getId());
            event.getChannel().sendComponent(card.getCard());
        }else if (event.getValue().startsWith("TICKET_CALL_")){
            String ticketId = event.getValue().substring(12);
            TextChannel textChannel = event.getChannel();
            StringBuilder sb = new StringBuilder();
            for (Integer rol:adminRolS){
                sb.append("(rol)").append(rol).append("(rol)");
            }
            textChannel.sendComponent(new NotifyMessage("呼叫管理",""+sb+"工单"+ticketId+"等待处理中,请及时处理").getCard());

        }else if (event.getValue().startsWith("TICKET_CLOSE_")){
            String ticketId = event.getValue().substring(13);
            if (event.getValue().startsWith("TICKET_CLOSE_SUCCESS_")) {
                TextChannel textChannel = event.getChannel();
                textChannel.delete();
                return;
            }
            TextChannel textChannel = event.getChannel();
            textChannel.sendComponent(new TicketCloseMessage(ticketId).getCard(),null, event.getUser());
        }
    }
}
