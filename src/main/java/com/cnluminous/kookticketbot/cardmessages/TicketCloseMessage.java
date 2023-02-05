package com.cnluminous.kookticketbot.cardmessages;

import snw.jkook.message.component.card.CardBuilder;
import snw.jkook.message.component.card.MultipleCardComponent;
import snw.jkook.message.component.card.Size;
import snw.jkook.message.component.card.Theme;
import snw.jkook.message.component.card.element.ButtonElement;
import snw.jkook.message.component.card.element.InteractElement;
import snw.jkook.message.component.card.element.PlainTextElement;
import snw.jkook.message.component.card.module.ActionGroupModule;

import java.util.ArrayList;

public class TicketCloseMessage {
    String ticketId;

    public TicketCloseMessage(String ticketId) {
        this.ticketId = ticketId;
    }

    public MultipleCardComponent getCard(){
        ArrayList<InteractElement> buttons = new ArrayList<>();
        ButtonElement closeButton = new ButtonElement(Theme.DANGER,"TICKET_CLOSE_SUCCESS_"+ticketId, ButtonElement.EventType.RETURN_VAL,new PlainTextElement("确认关闭工单,已知晓工单关闭后聊天记录将会清除"));
        buttons.add(closeButton);
        return new CardBuilder().setSize(Size.LG).setTheme(Theme.SECONDARY)
                .addModule(new ActionGroupModule(buttons)).build();
    }
}
