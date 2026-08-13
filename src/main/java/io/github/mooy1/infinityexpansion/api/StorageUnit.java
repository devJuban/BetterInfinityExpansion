package io.github.mooy1.infinityexpansion.api;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class StorageUnit {

    public int getAmountOfItems(@NotNull Block b){
        Location l = b.getLocation();
        return Integer.parseInt(BlockStorage.getLocationInfo(l, "stored"));
    }

    public int getAmountOfItems(@NotNull Location l){
        return Integer.parseInt(BlockStorage.getLocationInfo(l, "stored"));
    }

    public void withdrawFromUnit(@NotNull Block b, int amount){
        int currentAmount = getAmountOfItems(b);
        if (amount > currentAmount) return;
        BlockStorage.addBlockInfo(b, "stored", String.valueOf(currentAmount - amount));
    }

    public void withdrawFromUnit(@NotNull Location l, int amount){
        int currentAmount = getAmountOfItems(l);
        if (amount > currentAmount) return;
        BlockStorage.addBlockInfo(l, "stored", String.valueOf(currentAmount - amount));
    }

    // Needs working on
    // requires MAX variable
//    public void depositToUnit(@NotNull Block b, int amount){
//        int currentAmount = getAmountOfItems(b);
//        if (amount > currentAmount) return;
//        BlockStorage.addBlockInfo(b, "stored", String.valueOf(currentAmount + amount));
//    }
//
//    public void depositToUnit(@NotNull Location l, int amount){
//        int currentAmount = getAmountOfItems(l);
//        if (amount > currentAmount) return;
//        BlockStorage.addBlockInfo(l, "stored", String.valueOf(currentAmount + amount));
//    }

    public void emptyUnit(@NotNull Block b){
        BlockStorage.addBlockInfo(b, "stored", "0");
    }

    public void emptyUnit(@NotNull Location l){
        BlockStorage.addBlockInfo(l, "stored", "0");
    }
}
