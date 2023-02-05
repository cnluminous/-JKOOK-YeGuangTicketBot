package com.cnluminous.kookticketbot;

import com.cnluminous.kookticketbot.events.EUserClickButtonEvent;
import snw.jkook.command.JKookCommand;
import snw.jkook.plugin.BasePlugin;
import com.cnluminous.kookticketbot.commands.*;


public class Main extends BasePlugin {
    private static Main instance;
    public static Config config;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        new JKookCommand("发起工单")
                .addAlias("createTicket")
                .setDescription("向服务器管理组发起工单请求")
                .setHelpContent("向服务器管理组发起工单请求")
                .setExecutor(new CreateTicket())
                .register(this);

        getCore().getEventManager().registerHandlers(this,new EUserClickButtonEvent());

        getLogger().info("[+]工单系统已加载");

    }


}