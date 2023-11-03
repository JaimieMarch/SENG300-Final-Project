package com.thelocalmarketplace.software;

public class DeviceStatus 
{
	public static final DeviceStatus UN_START = new DeviceStatus(0, "Function unstart");
    public static final DeviceStatus START = new DeviceStatus(1, "Function started");
    public static final DeviceStatus ABLE = new DeviceStatus(2, "Function abled");
    public static final DeviceStatus UNABLE = new DeviceStatus(3, "Function unable");
  
    private final int code;
    private final String msg;

    private DeviceStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
