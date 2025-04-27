package com.gtnewhorizon.cropsnh.crops;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

/**
 * The generic weed crop that appears when a crop spreads weeds or a crop stick is left sitting for too long.
 */
public class CropWeed extends NHCropCard {

    public CropWeed() {
        super("weed", Color.green, Color.green);
    }

    @Override
    public String getCreator() {
        return "The Evil One";
    }

    @Override
    public boolean spreadsWeeds(ICropStickTile te) {
        return te.getGrowthPercent() >= 0.5f;
    }

    @Override
    public int startsSpreadingWeedsAt() {
        return 0;
    }
}
