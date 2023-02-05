package com.cnluminous.kookticketbot.cardmessages;

import com.cnluminous.kookticketbot.Config;
import snw.jkook.entity.User;
import snw.jkook.message.component.card.CardBuilder;
import snw.jkook.message.component.card.MultipleCardComponent;
import snw.jkook.message.component.card.Size;
import snw.jkook.message.component.card.Theme;
import snw.jkook.message.component.card.element.ButtonElement;
import snw.jkook.message.component.card.element.InteractElement;
import snw.jkook.message.component.card.element.MarkdownElement;
import snw.jkook.message.component.card.element.PlainTextElement;
import snw.jkook.message.component.card.module.ActionGroupModule;
import snw.jkook.message.component.card.module.SectionModule;

import java.util.ArrayList;

public class TicketCreateSuccessMessage {
    User user;
    String ticketId;
    String server;
    String tittle;
    String footer = "工单结束后,请点击下方\"关闭工单\"按钮关闭工单\n注意!工单关闭后此频道将会被永久删除,如有重要信息请自行备份";
    String body = "请先具体描述您的问题,以便管理员进行排查问题";
    ArrayList<Integer> adminRolS = Config.adminRol;

    public TicketCreateSuccessMessage(User user, String ticketId,String server) {
        this.user = user;
        this.ticketId = ticketId;
        this.server = server;
        this.tittle = "(met)"+user.getId()+"(met)发起了「"+server+"」工单,请等待管理员响应[工单编号:"+ticketId+"]";
    }
    public MultipleCardComponent getCard(){
        ArrayList<InteractElement> buttons = new ArrayList<>();
        ButtonElement closeButton = new ButtonElement(Theme.DANGER,"TICKET_CLOSE_"+ticketId, ButtonElement.EventType.RETURN_VAL,new PlainTextElement("关闭工单"));
        ButtonElement callButton = new ButtonElement(Theme.PRIMARY,"TICKET_CALL_"+ticketId, ButtonElement.EventType.RETURN_VAL,new PlainTextElement("呼叫管理"));
        buttons.add(closeButton);
        buttons.add(callButton);

        StringBuilder sb = new StringBuilder();
        for (Integer rol:adminRolS){
            sb.append("(rol)").append(rol).append("(rol)");
        }
        System.out.println(sb);
        return new CardBuilder().setSize(Size.LG).setTheme(Theme.SECONDARY)
                .addModule(new SectionModule(new MarkdownElement(tittle),null,null))
                .addModule(new SectionModule(new MarkdownElement(sb.toString()),null,null))
                .addModule(new SectionModule(new MarkdownElement(body),null,null))
                .addModule(new SectionModule(new MarkdownElement(footer),null,null))
                .addModule(new ActionGroupModule(buttons))
                .build();


    }
}
