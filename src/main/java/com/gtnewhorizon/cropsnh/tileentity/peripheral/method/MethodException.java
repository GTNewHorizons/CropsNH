package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

public class MethodException extends Exception {

    private final IMethod method;
    private final String msg;

    public MethodException(IMethod method, String msg) {
        this.method = method;
        this.msg = msg;
    }

    public String getDescription() {
        return "Method '" + method.getName() + "' errored: " + msg;
    }
}
