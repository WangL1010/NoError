package com.qxy.NoError.user.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;


/**
 * 用户的公开信息bean
 */
@Entity(tableName = "userOpenInfo", primaryKeys = {"openId", "unionId"})
public class UserOpenInfo {
    public String avatar;
    public String avatarLarger;
    public String captcha;
    public String city;
    public String clientKey;
    public String country;
    public String descUrl;
    public String description;
    public String district;
    public String eAccountRole;
    public Integer errorCode;
    public Integer gender;



    public String logId;
    public String nickname;
    @NonNull
    public String openId;
    public String province;
    @NonNull
    public String unionId;

    @Override
    public String toString() {
        return "Data{" +
                "avatar='" + avatar + '\'' +
                ", avatarLarger='" + avatarLarger + '\'' +
                ", captcha='" + captcha + '\'' +
                ", city='" + city + '\'' +
                ", clientKey='" + clientKey + '\'' +
                ", country='" + country + '\'' +
                ", descUrl='" + descUrl + '\'' +
                ", description='" + description + '\'' +
                ", district='" + district + '\'' +
                ", eAccountRole='" + eAccountRole + '\'' +
                ", errorCode=" + errorCode +
                ", gender=" + gender +
                ", logId='" + logId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", openId='" + openId + '\'' +
                ", province='" + province + '\'' +
                ", unionId='" + unionId + '\'' +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarLarger() {
        return avatarLarger;
    }

    public void setAvatarLarger(String avatarLarger) {
        this.avatarLarger = avatarLarger;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
