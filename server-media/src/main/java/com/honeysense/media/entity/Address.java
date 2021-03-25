package com.honeysense.media.entity;

import com.honeysense.magpie.entity.MagpieObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Address {
    @Getter
    @Setter
    public static class Area extends MagpieObject {
        private Long id;
        private String name;
    }

    @Getter
    @Setter
    public static class City extends Area {
        private List<Area> areas;
    }

    @Getter
    @Setter
    public static class Province extends Area {
        private List<City> cities;
    }

    @Getter
    @Setter
    public static class Country extends Area {
        private List<Province> provinces;
    }

    @Getter
    @Setter
    @Builder
    public static class AreaObject {
        private Long id;
        private String country;
        private String province;
        private String city;
        private String area;
    }
}
