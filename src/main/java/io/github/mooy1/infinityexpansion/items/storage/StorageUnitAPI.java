package io.github.mooy1.infinityexpansion.items.storage;

import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StorageUnitAPI {

    public static int getAmountOfItems( Block b){
        return getAmountOfItems(b.getLocation());
    }
    public static int getAmountOfItems( Location l){
        if (isUnit(l)){
            return getCache(l).get();
        }

        return 0;
    }

    public static void withdrawFromUnit( Block b, int amount){
        withdrawFromUnit(b.getLocation(), amount);
    }
    public static void withdrawFromUnit( Location l, int amount){
        if (isUnit(l)) {
            getCache(l).remove(amount);
        }
    }

    public static void emptyUnit( Block b){
        emptyUnit(b.getLocation());
    }
    public static void emptyUnit( Location l){
        if (isUnit(l)) {
            getCache(l).emptyUnit();
        }
    }

    public static StorageUnit getUnit( Block b){
        return getUnit(b.getLocation());
    }
    public static StorageUnit getUnit( Location l){
        return (StorageUnit) BlockStorage.check(l);
    }

    public static boolean isUnit( Block b){
        return isUnit(b.getLocation());
    }
    public static boolean isUnit( Location l) {
        return BlockStorage.check(l) instanceof io.github.mooy1.infinityexpansion.items.storage.StorageUnit;
    }

    public static ItemStack getContents( Block b) { return getContents(b.getLocation()); }
    public static ItemStack getContents( Location l) {
        return getCache(l).getContents();
    }

    @Nullable
    public static StorageCache getCache(Block b){ return getCache(b.getLocation()); }
    @Nullable
    public static StorageCache getCache(Location l){
        return getUnit(l).getCache(l);
    }

    /**
     * Gets the StorageUnit by the sign
     * @param b The sign or StorageUnit
     * @return The StorageUnit and Location as a Pair
     */
    public static Pair<StorageUnit, Location> getUnitBySign(Block b) { return getUnitBySign(b.getLocation()); }
    /**
     * Gets the StorageUnit by the sign
     * @param l The location of the sign or StorageUnit
     * @return The StorageUnit and Location as a Pair
     */
    public static Pair<StorageUnit, Location> getUnitBySign(Location l) {
        Block b = l.getBlock();
        if (isUnit(l)) return new Pair<>(getUnit(l), l);

        if (b.getBlockData() instanceof WallSign){
            WallSign wallSign = (WallSign) b.getBlockData();
            Block unitBlock = b.getRelative((wallSign.getFacing().getOppositeFace()));
            if (isUnit(unitBlock)){
                return new Pair<>(getUnit(unitBlock), unitBlock.getLocation());
            }
        } else if (b.getState() instanceof Sign){
            Block unitBlock = b.getRelative(0,-1,0);
            if (isUnit(unitBlock)){
                return new Pair<>(getUnit(unitBlock), unitBlock.getLocation());
            }
        }

        return null;
    }
}
