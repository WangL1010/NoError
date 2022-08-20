package com.qxy.NoError.user.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 授权账号视频列表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoListData {

    private String title;
    private Boolean isTop;
    private Integer createTime;
    private Boolean isReviewed;
    private Integer videoStatus;
    private String shareUrl;
    private String itemId;
    private Integer mediaType;
    private String cover;
    private Statistics statistics;

    @Override
    public String toString() {
        return "VideoListData{" +
                "title='" + title + '\'' +
                ", isTop=" + isTop +
                ", createTime=" + createTime +
                ", isReviewed=" + isReviewed +
                ", videoStatus=" + videoStatus +
                ", shareUrl='" + shareUrl + '\'' +
                ", itemId='" + itemId + '\'' +
                ", mediaType=" + mediaType +
                ", cover='" + cover + '\'' +
                ", statistics=" + statistics +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean top) {
        isTop = top;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(Boolean reviewed) {
        isReviewed = reviewed;
    }

    public Integer getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Integer videoStatus) {
        this.videoStatus = videoStatus;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static class Statistics {
        private Integer forwardCount;
        private Integer commentCount;
        private Integer diggCount;
        private Integer downloadCount;
        private Integer playCount;
        private Integer shareCount;

        @Override
        public String toString() {
            return "Statistics{" +
                    "forwardCount=" + forwardCount +
                    ", commentCount=" + commentCount +
                    ", diggCount=" + diggCount +
                    ", downloadCount=" + downloadCount +
                    ", playCount=" + playCount +
                    ", shareCount=" + shareCount +
                    '}';
        }

        public Integer getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(Integer forwardCount) {
            this.forwardCount = forwardCount;
        }

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Integer getDiggCount() {
            return diggCount;
        }

        public void setDiggCount(Integer diggCount) {
            this.diggCount = diggCount;
        }

        public Integer getDownloadCount() {
            return downloadCount;
        }

        public void setDownloadCount(Integer downloadCount) {
            this.downloadCount = downloadCount;
        }

        public Integer getPlayCount() {
            return playCount;
        }

        public void setPlayCount(Integer playCount) {
            this.playCount = playCount;
        }

        public Integer getShareCount() {
            return shareCount;
        }

        public void setShareCount(Integer shareCount) {
            this.shareCount = shareCount;
        }
    }
}
