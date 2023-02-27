package com.android.rupesh.mynewsfeedapp.newsapp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class OurYtModel{

    @SerializedName("Youtube")
    @Expose
    private List<Youtube> youtube;

    /**
     * No args constructor for use in serialization
     *
     */
    public OurYtModel() {
    }

    /**
     *
     * @param youtube
     */
    public OurYtModel(List<Youtube> youtube) {
        super();
        this.youtube = youtube;
    }

    public List<Youtube> getYoutube() {
        return youtube;
    }

    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
    }

    @Generated("jsonschema2pojo")
    public class Youtube {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("channelId")
        @Expose
        private String channelId;

        /**
         * No args constructor for use in serialization
         *
         */
        public Youtube() {
        }

        /**
         *
         * @param pid
         * @param title
         * @param channelId
         */
        public Youtube(Integer pid, String title, String channelId) {
            super();
            this.pid = pid;
            this.title = title;
            this.channelId = channelId;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

    }

}
