package com.cnluminous.kookticketbot.cardmessages;

import com.cnluminous.kookticketbot.Config;
import com.cnluminous.kookticketbot.Main;
import snw.jkook.entity.abilities.Accessory;
import snw.jkook.message.component.card.CardBuilder;
import snw.jkook.message.component.card.MultipleCardComponent;
import snw.jkook.message.component.card.Size;
import snw.jkook.message.component.card.Theme;
import snw.jkook.message.component.card.element.ButtonElement;
import snw.jkook.message.component.card.element.PlainTextElement;
import snw.jkook.message.component.card.module.DividerModule;
import snw.jkook.message.component.card.module.SectionModule;

import java.util.ArrayList;
import java.util.List;

public class CreateTicketMessage {
    static List<String> type = Config.classification;
    static CardBuilder cardBuilder = new CardBuilder().setSize(Size.LG).setTheme(Theme.SECONDARY);
    private static final MultipleCardComponent card;
    static {
        PlainTextElement successTicketTest = new PlainTextElement("确认发起");
        for (String t:type){
            PlainTextElement textElement = new PlainTextElement("请点击右侧按钮发起「"+t+"」工单");
            Accessory accessory = new ButtonElement(Theme.PRIMARY,"TICKET_CREATE_"+t, ButtonElement.EventType.RETURN_VAL,successTicketTest);
            cardBuilder.addModule(new SectionModule(textElement,accessory,Accessory.Mode.RIGHT));
            cardBuilder.addModule(DividerModule.INSTANCE);
        }
        card = cardBuilder.build();
    }
    public MultipleCardComponent getCard() {
        return card;
    }
}
