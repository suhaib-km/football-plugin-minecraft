/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 */
package org.FBC.stadium;

import java.io.File;
import org.FBC.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

public class CheckStadiumExists {
    private Main plugin;

    public boolean Check(String StadName, Main p) {
        this.plugin = p;
        File StadFile = this.getStadFile(StadName);
        if (StadFile.exists()) {
            YamlConfiguration StadConfig = YamlConfiguration.loadConfiguration((File)StadFile);
            return StadConfig.getConfigurationSection("StadData.") != null;
        }
        return false;
    }

    public File getStadFile(String StadName) {
        PluginDescriptionFile pdfFile = this.plugin.getDescription();
        File folder = new File(Bukkit.getServer().getPluginManager().getPlugin(pdfFile.getName()).getDataFolder() + "/Stadiums/");
        File f = new File(folder, String.valueOf(File.separator) + StadName + ".yml");
        return f;
    }
}

