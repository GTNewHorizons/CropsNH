package com.gtnewhorizon.cropsnh;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.PARAMETER) @interface IntRangeBounds {

    int end();

    int start() default 0;

    boolean endInclusive() default false;
}
