package com.github.shin_ideal.worldresetplugin;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.utils.FileUtils;

import java.io.File;

public class WorldManageUtil {
    public boolean resetWorld(String world) {
        WorldResetPlugin Instance = WorldResetPlugin.getInstance();
        MultiverseCore mv = Instance.getMultiverseCore();

        File worldFolder = new File(Instance.getDataFolder().getParentFile().getParentFile(), world);
        File backupWorldFolder = new File(Instance.getDataFolder(), "backup/" + world);

        if (!worldFolder.exists() || !backupWorldFolder.exists()) {
            return false;
        }

        mv.getMVWorldManager().unloadWorld(world);
        boolean deleteWorldFlag = FileUtils.deleteFolder(worldFolder);
        if (!deleteWorldFlag) {
            return false;
        }

        boolean copyWorldFlag = FileUtils.copyFolder(backupWorldFolder, worldFolder);
        if (!copyWorldFlag) {
            return false;
        }
        mv.getMVWorldManager().loadWorld(world);
        return true;
    }
}
