package com.qxy.NoError.user.bean;

/**
 * 用户的公开信息bean
 */
public class UserInfo {

    private DataDTO data;
    private String message;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
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

    public static class DataDTO {
        private String avatar;
        private String avatar_larger;
        private String captcha;
        private String city;
        private String client_key;
        private String country;
        private String desc_url;
        private String description;
        private String district;
        private String e_account_role;
        private Integer error_code;
        private Integer gender;
        private String log_id;
        private String nickname;
        private String open_id;
        private String province;
        private String union_id;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar_larger() {
            return avatar_larger;
        }

        public void setAvatar_larger(String avatar_larger) {
            this.avatar_larger = avatar_larger;
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

        public String getClient_key() {
            return client_key;
        }

        public void setClient_key(String client_key) {
            this.client_key = client_key;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDesc_url() {
            return desc_url;
        }

        public void setDesc_url(String desc_url) {
            this.desc_url = desc_url;
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

        public String getE_account_role() {
            return e_account_role;
        }

        public void setE_account_role(String e_account_role) {
            this.e_account_role = e_account_role;
        }

        public Integer getError_code() {
            return error_code;
        }

        public void setError_code(Integer error_code) {
            this.error_code = error_code;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getUnion_id() {
            return union_id;
        }

        public void setUnion_id(String union_id) {
            this.union_id = union_id;
        }

        @Override
        public String toString() {
            return "DataDTO{" +
                    "avatar='" + avatar + '\'' +
                    ", avatar_larger='" + avatar_larger + '\'' +
                    ", captcha='" + captcha + '\'' +
                    ", city='" + city + '\'' +
                    ", client_key='" + client_key + '\'' +
                    ", country='" + country + '\'' +
                    ", desc_url='" + desc_url + '\'' +
                    ", description='" + description + '\'' +
                    ", district='" + district + '\'' +
                    ", e_account_role='" + e_account_role + '\'' +
                    ", error_code=" + error_code +
                    ", gender=" + gender +
                    ", log_id='" + log_id + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", open_id='" + open_id + '\'' +
                    ", province='" + province + '\'' +
                    ", union_id='" + union_id + '\'' +
                    '}';
        }
    }
}