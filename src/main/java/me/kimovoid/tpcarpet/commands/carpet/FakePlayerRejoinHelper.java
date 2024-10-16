package me.kimovoid.tpcarpet.commands.carpet;

public class FakePlayerRejoinHelper {
    public static final ThreadLocal<Boolean> isRejoin = ThreadLocal.withInitial(() -> false);
}
