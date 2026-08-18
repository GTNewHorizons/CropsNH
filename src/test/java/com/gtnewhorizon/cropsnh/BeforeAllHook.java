package com.gtnewhorizon.cropsnh;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.gtnewhorizon.cropsnh.reference.Reference;

public class BeforeAllHook implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Reference.IS_GAME_LOADED = false;
    }
}
