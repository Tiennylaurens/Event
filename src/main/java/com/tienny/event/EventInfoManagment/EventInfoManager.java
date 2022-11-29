package com.tienny.event.EventInfoManagment;

import com.tienny.event.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class EventInfoManager {

    private YamlConfiguration config;
    private File file;

    public EventInfoManager(Main main) {

            if (!main.getDataFolder().exists()) { //Create Folder if it doesn't exist yet
                main.getDataFolder().mkdir();
            }

            file = new File(main.getDataFolder(), "eventInfo.yml"); //Set File variable
            if (!file.exists()) { // Create eventInfoFile if it doesn't exist yet
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            config = YamlConfiguration.loadConfiguration(file); //Set Config variable
    }



    public void addReload() { //Keep track of the amount of reloads
        if (!config.contains("reloads")) { //Make a reload entry in the file if it doesn't exist yet
            config.set("reloads", 1);
        } else {
            config.set("reloads", getReloads() + 1); //Add 1 to the amount of reloads
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getReloads() { //Keep track of the amount of reloads
        return config.getInt("reloads");
    }

    public String getVersion() {
        if (config.contains("version")) {
            return config.getString("version");
        } else {
            return "???";
        }
    }
}
