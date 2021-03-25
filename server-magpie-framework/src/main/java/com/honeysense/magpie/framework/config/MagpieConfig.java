package com.honeysense.magpie.framework.config;

import com.honeysense.magpie.framework.utils.MagpieJwt;
import com.honeysense.magpie.framework.utils.MagpieSnowFlake;

public class MagpieConfig {
    public static long snowFlakeWorkerId;
    public static long snowFlakeDataCenterId;
    public static String jwtTokenSecret;

    private static MagpieJwt magpieJwt;
    private static MagpieSnowFlake magpieSnowFlake;

    public static MagpieJwt jwt() {
        if (magpieJwt == null) {
            magpieJwt = new MagpieJwt(jwtTokenSecret);
        }

        return magpieJwt;
    }

    public static MagpieSnowFlake snowFlake() {
        if (magpieSnowFlake == null) {
            magpieSnowFlake = new MagpieSnowFlake(snowFlakeWorkerId, snowFlakeDataCenterId);
        }

        return magpieSnowFlake;
    }
}
