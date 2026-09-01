package com.gtnewhorizon.cropsnh;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

public class IntRangeValueSource implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) {
        IntRangeBounds annotation = parameters.getFirst() // assuming you only have one parameter
            .map(
                param -> param.getAnnotatedElement()
                    .getAnnotation(IntRangeBounds.class))
            .orElseThrow(() -> new IllegalStateException("No @IntRangeBounds annotation found"));

        final int start = annotation.start();
        final int end = annotation.end() + (annotation.endInclusive() ? 1 : 0);
        if (start >= end) throw new IllegalStateException("start can't be greater than equal to end!");
        int[] array = new int[end - start];
        for (int i = 0; i < array.length; i++) {
            array[i] = start + i;
        }
        return Arrays.stream(array)
            .mapToObj(Arguments::of);
    }
}
