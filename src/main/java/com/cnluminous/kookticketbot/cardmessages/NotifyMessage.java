package com.cnluminous.kookticketbot.cardmessages;

import snw.jkook.entity.abilities.Accessory;
import snw.jkook.message.component.card.CardBuilder;
import snw.jkook.message.component.card.MultipleCardComponent;
import snw.jkook.message.component.card.Size;
import snw.jkook.message.component.card.Theme;
import snw.jkook.message.component.card.element.MarkdownElement;
import snw.jkook.message.component.card.module.HeaderModule;
import snw.jkook.message.component.card.module.SectionModule;

public class NotifyMessage {
    String Tittle;
    String message;
    public NotifyMessage(String tittle, String message) {
        Tittle = tittle;
        this.message = message;
    }

    public MultipleCardComponent getCard() {
        return new CardBuilder()
                .setTheme(Theme.SECONDARY)
                .setSize(Size.LG)
                .addModule(new HeaderModule(Tittle))
                .addModule(new SectionModule(new MarkdownElement(message),null,null))
                .build();
    }
}
