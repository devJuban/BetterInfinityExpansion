package io.github.mooy1.infinityexpansion.items.storage;

import io.github.mooy1.infinityexpansion.InfinityExpansion;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class StorageUnitAPI {

    private static final NamespacedKey ITEM_KEY = InfinityExpansion.createKey("item");
    private static final NamespacedKey AMOUNT_KEY = InfinityExpansion.createKey("stored");

    private static final Map<Location, StorageCache> caches = new HashMap<>();

    public static int getAmountOfItems(@Nonnull Block b){
        return getAmountOfItems(b.getLocation());
    }

    public static int getAmountOfItems(@Nonnull Location l){
        if (isUnit(l)){
            return caches.get(l).get();
        }

        return 0;
    }

    public void withdrawFromUnit(@Nonnull Block b, int amount){
        withdrawFromUnit(b.getLocation(), amount);
    }

    public static void withdrawFromUnit(@Nonnull Location l, int amount){
        if (isUnit(l)) {
            caches.get(l).remove(amount);
        }
    }

    public static void emptyUnit(@Nonnull Block b){
        emptyUnit(b.getLocation());
    }

    public static void emptyUnit(@Nonnull Location l){
        if (isUnit(l)) {
            caches.get(l).set(0);
        }
    }

    public static StorageUnit getUnit(@Nonnull Block b){
        return getUnit(b.getLocation());
    }

    public static StorageUnit getUnit(@Nonnull Location l){
        StorageUnit storageUnit = (StorageUnit) BlockStorage.check(l);
        BlockMenu menu = BlockStorage.getInventory(l);

        if (caches.get(l) == null){
            caches.put(l, new StorageCache(storageUnit, menu));
        }

        return storageUnit;
    }

    public static boolean isUnit(@Nonnull Block b){
        return isUnit(b.getLocation());
    }

    public static boolean isUnit(@Nonnull Location l) {
        return BlockStorage.check(l) instanceof io.github.mooy1.infinityexpansion.items.storage.StorageUnit;
    }

    public static ItemStack[] getContents(@Nonnull Block b) {return getContents(b.getLocation());};

    public static ItemStack[] getContents(@Nonnull Location l) {
        return caches.get(l).getContents();
    }
}
