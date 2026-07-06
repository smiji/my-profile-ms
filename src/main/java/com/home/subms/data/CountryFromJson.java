package com.home.subms.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryFromJson {
    private int id;
    private String name;
    private String iso3;
    private String iso2;
    @JsonProperty("numeric_code")
    private String numericCode;
    private String phonecode;
    private String capital;
    private String currency;
    @JsonProperty("currency_name")
    private String currencyName;
    @JsonProperty("currency_symbol")
    private String currencySymbol;
    private String tld;
    @JsonProperty("native")
    private String nativeName;
    private Long population;
    private Long gdp;
    private String region;
    @JsonProperty("region_id")
    private Integer regionId;
    private String subregion;
    @JsonProperty("subregion_id")
    private Integer subregionId;
    private String nationality;
    @JsonProperty("area_sq_km")
    private Integer areaSqKm;
    @JsonProperty("postal_code_format")
    private String postalCodeFormat;
    @JsonProperty("postal_code_regex")
    private String postalCodeRegex;
    private List<Timezone> timezones;
    private Map<String, String> translations;
    private String latitude;
    private String longitude;
    private String emoji;
    private String emojiU;
    private String wikiDataId;

    // Getters and setters for all fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIso3() { return iso3; }
    public void setIso3(String iso3) { this.iso3 = iso3; }
    public String getIso2() { return iso2; }
    public void setIso2(String iso2) { this.iso2 = iso2; }
    public String getNumericCode() { return numericCode; }
    public void setNumericCode(String numericCode) { this.numericCode = numericCode; }
    public String getPhonecode() { return phonecode; }
    public void setPhonecode(String phonecode) { this.phonecode = phonecode; }
    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getCurrencyName() { return currencyName; }
    public void setCurrencyName(String currencyName) { this.currencyName = currencyName; }
    public String getCurrencySymbol() { return currencySymbol; }
    public void setCurrencySymbol(String currencySymbol) { this.currencySymbol = currencySymbol; }
    public String getTld() { return tld; }
    public void setTld(String tld) { this.tld = tld; }
    public String getNativeName() { return nativeName; }
    public void setNativeName(String nativeName) { this.nativeName = nativeName; }
    public Long getPopulation() { return population; }
    public void setPopulation(Long population) { this.population = population; }
    public Long getGdp() { return gdp; }
    public void setGdp(Long gdp) { this.gdp = gdp; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public Integer getRegionId() { return regionId; }
    public void setRegionId(Integer regionId) { this.regionId = regionId; }
    public String getSubregion() { return subregion; }
    public void setSubregion(String subregion) { this.subregion = subregion; }
    public Integer getSubregionId() { return subregionId; }
    public void setSubregionId(Integer subregionId) { this.subregionId = subregionId; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public Integer getAreaSqKm() { return areaSqKm; }
    public void setAreaSqKm(Integer areaSqKm) { this.areaSqKm = areaSqKm; }
    public String getPostalCodeFormat() { return postalCodeFormat; }
    public void setPostalCodeFormat(String postalCodeFormat) { this.postalCodeFormat = postalCodeFormat; }
    public String getPostalCodeRegex() { return postalCodeRegex; }
    public void setPostalCodeRegex(String postalCodeRegex) { this.postalCodeRegex = postalCodeRegex; }
    public List<Timezone> getTimezones() { return timezones; }
    public void setTimezones(List<Timezone> timezones) { this.timezones = timezones; }
    public Map<String, String> getTranslations() { return translations; }
    public void setTranslations(Map<String, String> translations) { this.translations = translations; }
    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }
    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public String getEmojiU() { return emojiU; }
    public void setEmojiU(String emojiU) { this.emojiU = emojiU; }
    public String getWikiDataId() { return wikiDataId; }
    public void setWikiDataId(String wikiDataId) { this.wikiDataId = wikiDataId; }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Timezone {
        private String zoneName;
        private Integer gmtOffset;
        private String gmtOffsetName;
        private String abbreviation;
        private String tzName;

        public String getZoneName() { return zoneName; }
        public void setZoneName(String zoneName) { this.zoneName = zoneName; }
        public Integer getGmtOffset() { return gmtOffset; }
        public void setGmtOffset(Integer gmtOffset) { this.gmtOffset = gmtOffset; }
        public String getGmtOffsetName() { return gmtOffsetName; }
        public void setGmtOffsetName(String gmtOffsetName) { this.gmtOffsetName = gmtOffsetName; }
        public String getAbbreviation() { return abbreviation; }
        public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }
        public String getTzName() { return tzName; }
        public void setTzName(String tzName) { this.tzName = tzName; }
    }
}

