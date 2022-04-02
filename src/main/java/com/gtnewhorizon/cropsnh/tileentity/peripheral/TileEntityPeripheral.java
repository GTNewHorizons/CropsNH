package com.gtnewhorizon.cropsnh.tileentity.peripheral;

import com.gtnewhorizon.cropsnh.blocks.BlockCrop;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.tileentity.TileEntitySeedAnalyzer;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.IMethod;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodAnalyze;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodException;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetBaseBlock;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetBaseBlockType;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetBrightness;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetBrightnessRange;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetCurrentSoil;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetGrowthStage;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetNeededSoil;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetPlant;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetSpecimen;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodGetStats;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodHasJournal;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodHasPlant;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodHasWeeds;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodIsAnalyzed;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodIsCrossCrop;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodIsFertile;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodIsMature;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.method.MethodNeedsBaseBlock;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashMap;


@Optional.InterfaceList( value = {
        @Optional.Interface(modid = Names.Mods.computerCraft, iface = "dan200.computercraft.api.peripheral.IPeripheral"),
        @Optional.Interface(modid = Names.Mods.openComputers, iface = "li.cil.oc.api.network.SimpleComponent"),
        @Optional.Interface(modid = Names.Mods.openComputers, iface = "li.cil.oc.api.network.ManagedPeripheral")
})
public class TileEntityPeripheral extends TileEntitySeedAnalyzer implements IPeripheral, SimpleComponent, ManagedPeripheral {
    private static IMethod[] methods;
    private boolean mayAnalyze = false;
    /** Data to animate the peripheral client side */
    @SideOnly(Side.CLIENT)
    private int updateCheck;
    @SideOnly(Side.CLIENT)
    private HashMap<ForgeDirection, Integer> timers;
    @SideOnly(Side.CLIENT)
    private HashMap<ForgeDirection, Boolean> activeSides;

    public static final ForgeDirection[] VALID_DIRECTIONS = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
    public static final int MAX = 60;

    public TileEntityPeripheral() {
        super();
        initMethods();
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setBoolean(Names.NBT.flag, mayAnalyze);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        mayAnalyze = tag.hasKey(Names.NBT.flag) && tag.getBoolean(Names.NBT.flag);
    }

    private void initMethods() {
        if(methods ==null) {
            methods = methodList();
        }
    }

    public IMethod[] getMethods() {
        initMethods();
        return methods;
    }

    @Override
    public void updateEntity() {
        if(mayAnalyze) {
            if(this.hasSpecimen() && !isSpecimenAnalyzed()) {
                super.updateEntity();
            } else {
                reset();
            }
        } if(worldObj.isRemote) {
            if(updateCheck == 0) {
                checkSides();
            }
            for(ForgeDirection dir:VALID_DIRECTIONS) {
                int timer = timers.get(dir);
                timer = timer + (isSideActive(dir)?1:-1);
                timer = timer<0?0:timer;
                timer = timer>MAX?MAX:timer;
                timers.put(dir, timer);
            }
            updateCheck = (updateCheck+1)%1200;
        }
    }

    @SideOnly(Side.CLIENT)
    public int getTimer(ForgeDirection dir) {
        if(updateCheck == 0 || timers == null) {
            checkSides();
        }
        return timers.get(dir);
    }

    @SideOnly(Side.CLIENT)
    private boolean isSideActive(ForgeDirection dir) {
        return activeSides.containsKey(dir) && activeSides.get(dir);
    }

    @SideOnly(Side.CLIENT)
    public void checkSides() {
        for(ForgeDirection dir:VALID_DIRECTIONS) {
            checkSide(dir);
        }
        updateCheck = 0;
    }

    @SideOnly(Side.CLIENT)
    private void checkSide(ForgeDirection dir) {
        if(timers == null) {
            timers = new HashMap<>();
        }
        if(!timers.containsKey(dir)) {
            timers.put(dir, 0);
        }
        if(activeSides == null) {
            activeSides = new HashMap<>();
        }
        activeSides.put(dir, isCrop(dir));
    }

    private boolean isCrop(ForgeDirection dir) {
        return worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof BlockCrop;
    }

    public void startAnalyzing() {
        if(!mayAnalyze && this.hasSpecimen() && !this.isSpecimenAnalyzed()) {
            mayAnalyze = true;
            this.markForUpdate();
        }
    }

    @Override
    public void analyze() {
        super.analyze();
        reset();
    }

    private void reset() {
        if(mayAnalyze) {
            mayAnalyze = false;
            this.markForUpdate();
        }
    }

    @Override
    public boolean isRotatable() {
        return false;
    }

    public String getName() {
        return "cropsnh_peripheral";
    }

    public String[] getAllMethodNames() {
        String[] names = new String[methods.length];
        for(int i=0;i<names.length;i++) {
            names[i] = methods[i].getName();
        }
        return names;
    }

    public Object[] invokeMethod(IMethod method, Object... arguments) throws MethodException {
        return method.call(this, worldObj, xCoord, yCoord, zCoord, this.getJournal(), arguments);
    }

    private static IMethod[] methodList() {
        return new IMethod[] {
                new MethodAnalyze(),
                new MethodGetBaseBlock(),
                new MethodGetBaseBlockType(),
                new MethodGetBrightness(),
                new MethodGetBrightnessRange(),
                new MethodGetCurrentSoil(),
                new MethodGetGrowthStage(),
                new MethodGetNeededSoil(),
                new MethodGetPlant(),
                new MethodGetSpecimen(),
                new MethodGetStats(),
                new MethodHasJournal(),
                new MethodHasPlant(),
                new MethodHasWeeds(),
                new MethodIsAnalyzed(),
                new MethodIsCrossCrop(),
                new MethodIsFertile(),
                new MethodIsMature(),
                new MethodNeedsBaseBlock()
        };
    }

    //---------------------
    //ComputerCraft methods
    //---------------------
    @Override
    public String getType() {
        return getName();
    }

    @Override
    public String[] getMethodNames() {
        return getAllMethodNames();
    }

    @Override
    @Optional.Method(modid = Names.Mods.computerCraft)
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
        IMethod calledMethod = methods[method];
        try {
            return invokeMethod(calledMethod, arguments);
        } catch(MethodException e) {
            throw new LuaException(e.getDescription());
        }
    }

    @Override
    @Optional.Method(modid = Names.Mods.computerCraft)
    public void attach(IComputerAccess computer) {
    }

    @Override
    @Optional.Method(modid = Names.Mods.computerCraft)
    public void detach(IComputerAccess computer) {
    }

    @Override
    @Optional.Method(modid = Names.Mods.computerCraft)
    public boolean equals(IPeripheral other) {
        return other instanceof TileEntityPeripheral;
    }

    //---------------------
    //OpenComputers methods
    //---------------------
    @Override
    public String getComponentName() {
        return getName();
    }

    @Override
    public String[] methods() {
        return getAllMethodNames();
    }

    @Override
    @Optional.Method(modid = Names.Mods.openComputers)
    public Object[] invoke(String method, Context context, Arguments args) throws Exception {
        IMethod calledMethod = null;
        for(IMethod iMethod: methods) {
            if(iMethod.getName().equals(method)) {
                calledMethod = iMethod;
                break;
            }
        }
        if(calledMethod == null)  {
            return null;
        }
        try {
            return invokeMethod(calledMethod, args.toArray());
        } catch(MethodException e) {
            throw new Exception(e.getDescription());
        }
    }
}
