package net.superfastscyphozoa.wastelandwandering.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum ChickenVariant {
    BLACK(0),
    BROWN(1),
    WHITE(2),
    SEMIRAD(3),
    RAD(4);

    private static final ChickenVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            ChickenVariant::getId)).toArray(ChickenVariant[]::new);
    private final int id;

    ChickenVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ChickenVariant byId(int id){
        return BY_ID[id % BY_ID.length];
    }
}
