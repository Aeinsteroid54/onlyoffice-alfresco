package com.parashift.onlyoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
    Copyright (c) Ascensio System SIA 2019. All rights reserved.
    http://www.onlyoffice.com
*/
@Component(value = "webscript.onlyoffice.onlyoffice-config.get")
public class Config extends DeclarativeWebScript {

    @Autowired
    ConfigManager configManager;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("docurl", configManager.getOrDefault("url", "http://127.0.0.1/"));
        model.put("docinnerurl", configManager.getOrDefault("innerurl", ""));
        model.put("alfurl", configManager.getOrDefault("alfurl", ""));

        String cert = (String) configManager.getOrDefault("cert", "no");
        model.put("cert", cert.equals("true") ? "checked=\"\"" : "");
        model.put("webpreview", ((String)configManager.getOrDefault("webpreview", "")).equals("true") ? "checked=\"\"" : "");

        model.put("jwtsecret", configManager.getOrDefault("jwtsecret", ""));
        return model;
    }
}

