package com.qxy.NoError.user.bean;

/**
 * 用户的公开信息bean
 */
public class UserOpenInfo {

    private Data data;
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public static class Data {
        private String avatar;
        private String avatarLarger;
        private String captcha;
        private String city;
        private String clientKey;
        private String country;
        private String descUrl;
        private String description;
        private String district;
        private String eAccountRole;
        private Integer errorCode;
        private Integer gender;
        private String logId;
        private String nickname;
        private String openId;
        private String province;
        private String unionId;

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

        public String geteAccountRole() {
            return eAccountRole;
        }

        public void seteAccountRole(String eAccountRole) {
            this.eAccountRole = eAccountRole;
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
}
