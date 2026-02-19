package com.gtnewhorizon.cropsnh.compatibility.forestry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.google.common.collect.ImmutableList;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.FarmDirection;
import forestry.api.farming.Farmables;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.core.utils.vect.IVect;
import forestry.core.utils.vect.MutableVect;
import forestry.core.utils.vect.Vect;
import forestry.farming.logic.Crop;
import forestry.farming.logic.FarmLogic;
import forestry.farming.logic.FarmableReference;

public class CropsNHFarmLogic extends FarmLogic {

    protected Collection<IFarmable> farmables;
    protected final HashMap<Vect, Integer> lastExtents = new HashMap<>();

    public CropsNHFarmLogic(IFarmHousing housing) {
        super(housing);
        this.farmables = Farmables.farmables.get(FarmableReference.IC2Crops.get());
    }

    @Override
    public int getFertilizerConsumption() {
        return 40;
    }

    @Override
    public int getWaterConsumption(float hydrationModifier) {
        return (int) (40 * hydrationModifier);
    }

    @Override
    public boolean isAcceptedResource(ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean isAcceptedGermling(ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean isAcceptedWindfall(ItemStack stack) {
        return false;
    }

    // object 2 int does not work you _need_ to pivot based on the given int
    private int lastRowIndex = 0;
    boolean canSetTargetIndex = false;
    private int targetIndex = Integer.MAX_VALUE;

    private enum QueryShape {

        // non-last target, non final check
        // only check blocks above the current position
        Single(new int[][] { { 0, 0 } }),
        // non-last target, final check
        // check blocks on the current position + a 6x5 ┐ in front.
        L(getLShape()),
        // final target, final check
        // check in a 6x6 area with the current position anchored at the bottom-right
        Square(getSquareShape()),
        // final target
        // check in a 6x1 area with the current position anchored to the right
        Line(getLineShape());

        private static final int EXTENSION_SIZE = 5;

        private static int[][] getLineShape() {
            int[][] ret = new int[EXTENSION_SIZE + 1][2];
            for (int i = 0; i <= EXTENSION_SIZE; i++) {
                ret[i][0] = -i;
                ret[i][1] = 0;
            }
            return ret;
        }

        private static int[][] getLShape() {
            int[][] ret = new int[EXTENSION_SIZE * 2 + 1][2];
            ret[0][0] = 0;
            ret[0][1] = 0;
            int pos = 1;
            for (int i = 1; i <= EXTENSION_SIZE; i++) {
                ret[pos][0] = 0;
                ret[pos][1] = i;
                pos++;
            }
            for (int i = 1; i <= EXTENSION_SIZE; i++) {
                ret[pos][0] = -i;
                ret[pos][1] = EXTENSION_SIZE;
                pos++;
            }
            return ret;
        }

        private static int[][] getSquareShape() {
            int[][] ret = new int[(EXTENSION_SIZE + 1) * (EXTENSION_SIZE + 1)][2];
            int i = 0;
            for (int x = 0; x >= -EXTENSION_SIZE; x--) {
                for (int z = 0; z <= EXTENSION_SIZE; z++) {
                    ret[i][0] = x;
                    ret[i][1] = z;
                    i++;
                }
            }
            return ret;
        }

        public final int[][] EAST_OFFSETS;
        public final int[][] NORTH_OFFSETS;
        public final int[][] WEST_OFFSETS;
        public final int[][] SOUTH_OFFSETS;

        QueryShape(int[][] shapes) {
            WEST_OFFSETS = shapes;
            NORTH_OFFSETS = new int[shapes.length][2];
            EAST_OFFSETS = new int[shapes.length][2];
            SOUTH_OFFSETS = new int[shapes.length][2];
            for (int i = 0; i < shapes.length; i++) {
                // -X -> +X
                // +Z -> -Z
                EAST_OFFSETS[i][0] = -shapes[i][0];
                EAST_OFFSETS[i][1] = -shapes[i][1];

                // -X -> +Z
                // +Z -> +X
                NORTH_OFFSETS[i][0] = shapes[i][1];
                NORTH_OFFSETS[i][1] = -shapes[i][0];

                // -X -> -Z
                // +Z -> -X
                SOUTH_OFFSETS[i][0] = -shapes[i][1];
                SOUTH_OFFSETS[i][1] = shapes[i][0];
            }
        }
    }

    @Override
    public Collection<ItemStack> collect() {
        if (canSetTargetIndex) {
            this.targetIndex = this.lastRowIndex;
            this.canSetTargetIndex = false;
        }
        this.lastRowIndex = 0;
        return null;
    }

    @Override
    public boolean cultivate(int x, int y, int z, FarmDirection direction, int extent) {
        return false;
    }

    public static class CropBasicCropsNHCrop extends Crop {

        private final ICropStickTile cropTE;

        public CropBasicCropsNHCrop(World world, ICropStickTile tileEntity, Vect position) {
            super(world, position);
            this.cropTE = tileEntity;
        }

        @Override
        protected boolean isCrop(IVect pos) {
            return cropTE.canHarvest();
        }

        private static final Collection<ItemStack> NO_DROPS = new ImmutableList.Builder<ItemStack>().build();

        @Override
        protected Collection<ItemStack> harvestBlock(IVect pos) {
            Collection<ItemStack> ret = cropTE.harvest();
            return ret == null ? NO_DROPS : ret;
        }
    }

    @Override
    public Collection<ICrop> harvest(int x, int y, int z, FarmDirection direction, int extent) {

        lastRowIndex++;
        canSetTargetIndex = true;

        Vect start = new Vect(x, y, z);
        if (!lastExtents.containsKey(start)) {
            lastExtents.put(start, 0);
        }

        int lastExtent = lastExtents.get(start);
        if (lastExtent > extent) {
            lastExtent = 0;
        }

        boolean isOnLastTarget = --targetIndex == 0;
        boolean isOnLastPosition = lastExtent == extent;
        QueryShape shape;
        if (isOnLastTarget) {
            shape = isOnLastPosition ? QueryShape.Square : QueryShape.Line;
        } else {
            shape = isOnLastPosition ? QueryShape.L : QueryShape.Single;
        }
        int[][] offsets = switch (direction) {
            case NORTH -> shape.EAST_OFFSETS;
            case WEST -> shape.SOUTH_OFFSETS;
            case SOUTH -> shape.WEST_OFFSETS;
            case EAST -> shape.NORTH_OFFSETS;
        };

        Vect pos = translateWithOffset(x, y, z, direction, lastExtent);
        MutableVect mutable = new MutableVect(pos);
        Collection<ICrop> crops = new ArrayList<>(offsets.length);
        World world = this.getWorld();
        for (int[] offset : offsets) {
            mutable.x = pos.x + offset[0];
            mutable.y = pos.y;
            mutable.z = pos.z + offset[1];
            for (int i = 0; i < 3; i++) {
                mutable.y++;

                TileEntity tileEntity = world.getTileEntity(mutable.x, mutable.y, mutable.z);
                if (isCrop(world, mutable, tileEntity)) {
                    ICrop crop = getCrop(world, mutable, tileEntity);
                    if (crop == null) break;
                    crops.add(crop);
                    break;
                } else if (i == 1 && !SoilRegistry.instance.isRegistered(world, mutable.x, mutable.y, mutable.z)) {
                    break;
                } else if (i == 2 && world.isAirBlock(mutable.x, mutable.y, mutable.z)) {
                    break;
                }
            }
        }
        lastExtents.put(start, ++lastExtent);
        return crops;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon() {
        return CropsNHItemList.cropSticks.getItem()
            .getIconFromDamage(0);
    }

    @Override
    public String getName() {
        return "Crops";
    }

    private boolean isCrop(World world, IVect position, TileEntity te) {
        for (IFarmable farmable : farmables) {
            if (farmable.isSaplingAt(world, position.getX(), position.getY(), position.getZ())) {
                return true;
            }
        }

        return false;
    }

    private ICrop getCrop(World world, IVect position, TileEntity tile) {
        for (IFarmable seed : farmables) {
            ICrop crop = seed.getCropAt(world, position.getX(), position.getY(), position.getZ());
            if (crop != null) {
                return crop;
            }
        }
        return null;
    }
}
