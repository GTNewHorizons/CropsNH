package com.gtnewhorizon.cropsnh.utility.exception;

public class MissingArgumentsException extends Exception {
    private static final long serialVersionUID = 2227006499158912033L;

    public MissingArgumentsException() {
        super("Not all necessary parameters where given to create a new plant");
    }
}
