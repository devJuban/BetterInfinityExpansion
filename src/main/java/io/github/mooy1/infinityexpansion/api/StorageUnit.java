package io.github.mooy1.infinityexpansion.api;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class StorageUnit {
    public int getAmountOfItems(@NotNull Block block){
        Location l = block.getLocation();
        return Integer.parseInt(BlockStorage.getLocationInfo(l, "stored"));
    }

    public int getAmountOfItems(@NotNull Location l){
        return Integer.parseInt(BlockStorage.getLocationInfo(l, "stored"));
    }
}
