package com.honeysense.magpie.utils.convert;

import com.honeysense.magpie.entity.MagpieException;

import java.util.HashMap;

public class MagpieIpConvert {
    public static long ip2long(String ip) {
        String[] p = ip.split("\\.");
        if (p.length != 4) {
            java.util.Map<String, String> map = new HashMap<>();
            map.put("ip", ip);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        int p1 = ((Integer.parseInt(p[0]) << 24) & 0xFF000000);
        int p2 = ((Integer.parseInt(p[1]) << 16) & 0x00FF0000);
        int p3 = ((Integer.parseInt(p[2]) << 8) & 0x0000FF00);
        int p4 = ((Integer.parseInt(p[3])) & 0x000000FF);

        return ((p1 | p2 | p3 | p4) & 0xFFFFFFFFL);
    }

    public static String long2ip(long ip) {
        return String.valueOf((ip >> 24) & 0xFF) + '.' +
                ((ip >> 16) & 0xFF) + '.' +
                ((ip >> 8) & 0xFF) + '.' +
                ((ip) & 0xFF);
    }
}
