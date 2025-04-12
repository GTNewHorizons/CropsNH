package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.tileentity.MTECropManager;

import gregtech.api.enums.VoltageIndex;

public class MTELoader {

    public static void init() {
        CropsNHItemList.CropManager_LV
            .set(new MTECropManager(28001, VoltageIndex.LV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_MV
            .set(new MTECropManager(28002, VoltageIndex.MV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_HV
            .set(new MTECropManager(28003, VoltageIndex.HV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_EV
            .set(new MTECropManager(28004, VoltageIndex.EV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_IV
            .set(new MTECropManager(28005, VoltageIndex.IV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_LuV
            .set(new MTECropManager(28006, VoltageIndex.LuV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_ZPM
            .set(new MTECropManager(28007, VoltageIndex.ZPM, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_UV
            .set(new MTECropManager(28008, VoltageIndex.UV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_UHV
            .set(new MTECropManager(28009, VoltageIndex.UHV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_UEV
            .set(new MTECropManager(28010, VoltageIndex.UEV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_UIV
            .set(new MTECropManager(28011, VoltageIndex.UIV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_UMV
            .set(new MTECropManager(28012, VoltageIndex.UMV, "Harvests the Cropsticks in around it").getStackForm(1L));
        CropsNHItemList.CropManager_UXV
            .set(new MTECropManager(28013, VoltageIndex.UXV, "Harvests the Cropsticks in around it").getStackForm(1L));
    }

}
