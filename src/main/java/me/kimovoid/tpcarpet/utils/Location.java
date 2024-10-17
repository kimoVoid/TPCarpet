package me.kimovoid.tpcarpet.utils;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class Location {

    private final ServerWorld serverWorld;
    private Vec3d vec3d;
    private float yaw = 90;
    private float pitch = 0;

    public Location(ServerWorld serverWorld, Vec3d vec3d){
        this.serverWorld = serverWorld;
        this.vec3d = vec3d;
    }

    public Location(ServerWorld serverWorld, Vec3d vec3d, float yaw, float pitch){
        this.serverWorld = serverWorld;
        this.vec3d = vec3d;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Vec3d getVec3d() {
        return vec3d;
    }

    public ServerWorld getServerWorld() {
        return serverWorld;
    }

    public double getX(){
        return vec3d.getX();
    }

    public double getY(){
        return vec3d.getY();
    }

    public double getZ(){
        return vec3d.getZ();
    }

    public Location add(int x, int y, int z){
        vec3d = vec3d.add(x, y, z);
        return this;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}
