package com.gtnewhorizon.cropsnh.utility.exception;

public final class DuplicateCropPlantException extends Exception {
    private static final long serialVersionUID = 479990572339739801L;

    public DuplicateCropPlantException() {
        super("This plant is already registered");
    }
}
