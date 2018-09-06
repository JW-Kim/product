package com.product.luffy.utils.response;

public final class HashMapUtil {
    public static final float LOAD_FACTOR = 0.75F;

    private HashMapUtil() {
    }

    public static int getCapacity(int size) {
        return (int)((float)size / 0.75F) + 1;
    }
}
