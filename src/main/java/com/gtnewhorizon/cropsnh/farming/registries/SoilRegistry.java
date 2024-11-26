package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.ISoilRegistry;

public class SoilRegistry implements ISoilRegistry {

    public final static SoilRegistry instance = new SoilRegistry();

    /**
     * The global registry of all soil types, any value registered in here will be treated as acceptable soil for a crop
     * stick.
     */
    private final HashMap<String, ISoilList> soilTypes = new HashMap<>();

    /**
     * A list of all soils registered in the registry.
     */
    private final ISoilList allSoils;

    public SoilRegistry() {
        // register the global soil list.
        this.allSoils = new SoilList(this, true);
        // default with a couple vanilla soil types
        this.soilTypes.put("farmland", new SoilList(this, false));
        this.soilTypes.put("sand", new SoilList(this, false));
        this.soilTypes.put("mycelium", new SoilList(this, false));
        this.soilTypes.put("soulsand", new SoilList(this, false));
    }

    /**
     * Used to register a soil that was registered to a child to the global soil list
     *
     * @param soils The soils to add to the global soil list
     * @see SoilList#SoilList(SoilRegistry, boolean)
     */
    void registerGlobalSoil(BlockWithMeta... soils) {
        this.allSoils.registerSoil(soils);
    }

    @Override
    public boolean isRegistered(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block.getMaterial() == Material.air) return false;
        int meta = world.getBlockMetadata(x, y, z);
        return isRegistered(block, meta);
    }

    @Override
    public boolean isRegistered(Block block, int meta) {
        return this.allSoils.isRegistered(block, meta);
    }

    @Override
    public ISoilList get(String type) {
        // append empty group if a new soil type is requested so that it can be filled in later if the response is
        // cached.
        if (!this.soilTypes.containsKey(type)) {
            this.soilTypes.put(type, new SoilList(this, false));
        }
        return this.soilTypes.get(type);
    }

    @Override
    public void register(String type, BlockWithMeta... soils) {
        if (!this.soilTypes.containsKey(type)) {
            this.soilTypes.put(type, new SoilList(this, false));
        }
        this.soilTypes.get(type)
            .registerSoil(soils);
    }
}
