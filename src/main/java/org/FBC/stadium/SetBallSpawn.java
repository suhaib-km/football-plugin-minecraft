/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 */
package org.FBC.stadium;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.FBC.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

public class SetBallSpawn {
    private Main plugin;

    public SetBallSpawn(String loc, String StadName, Main p) {
        this.plugin = p;
        File StadFile = this.getStadFile(StadName);
        YamlConfiguration StadConfig = YamlConfiguration.loadConfiguration((File)StadFile);
        StadConfig.set("StadData.Spawns.ballSpawn", (Object)loc);
        try {
            StadConfig.save(StadFile);
        }
        catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Could not save the file for the stadium " + StadName);
            return;
        }
    }

    public File getStadFile(String StadName) {
        PluginDescriptionFile pdfFile = this.plugin.getDescription();
        File folder = new File(Bukkit.getServer().getPluginManager().getPlugin(pdfFile.getName()).getDataFolder() + "/Stads/");
        File f = new File(folder, String.valueOf(File.separator) + StadName + ".yml");
        return f;
    }
}

