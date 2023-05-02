package com.scareye.mcqq.event;


public class ForgeServerPlayer {
    private String nickname;
    private String uuid;
    private String ipAddress;
    private String level;
    private float speed;


    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ForgeServerPlayer() {
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public ForgeServerPlayer(String nickname, String uuid, String ipAddress, String level, float speed) {
        this.nickname = nickname;
        this.uuid = uuid;
        this.ipAddress = ipAddress;
        this.level = level;
        this.speed = speed;
    }
}
