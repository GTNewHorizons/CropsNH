package k4unl.minecraft.Hydraulicraft.api;

import codechicken.multipart.TMultiPart;
import cpw.mods.fml.common.Loader;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Koen Beckers (K-4U) & MineMaarten
 */
public class HCApi {
    private static IHCApi instance;

    public static IHCApi getInstance() {

        return instance;
    }

    public interface IHCApi {
        ITrolleyRegistrar getTrolleyRegistrar();

        IBaseClass getBaseClass(TileEntity target, int maxStorage);

        IBaseClass getBaseClass(TMultiPart target, int maxStorage);
    }

    /**
     * For internal use only, don't call it.
     *
     * @param inst
     */
    public static void init(IHCApi inst) {

        if (instance == null && Loader.instance().activeModContainer().getModId().equals("HydCraft")) {
            instance = inst;
        } else {
            throw new IllegalStateException("This method should be called from Hydraulicraft only!");
        }
    }
}
