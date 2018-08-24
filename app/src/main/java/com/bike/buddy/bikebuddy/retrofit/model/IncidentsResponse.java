package com.bike.buddy.bikebuddy.retrofit.model;

import java.util.List;

public class IncidentsResponse {

    private List<Incident> incidents = null;

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public class Incident {

        private Integer id;
        private String title;
        private String description;
        private String address;
        private Integer occurredAt;
        private Integer updatedAt;
        private String url;
        private Source source;
        private Media media;
        private Object locationType;
        private Object locationDescription;
        private String type;
        private Object typeProperties;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getOccurredAt() {
            return occurredAt;
        }

        public void setOccurredAt(Integer occurredAt) {
            this.occurredAt = occurredAt;
        }

        public Integer getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Integer updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Media getMedia() {
            return media;
        }

        public void setMedia(Media media) {
            this.media = media;
        }

        public Object getLocationType() {
            return locationType;
        }

        public void setLocationType(Object locationType) {
            this.locationType = locationType;
        }

        public Object getLocationDescription() {
            return locationDescription;
        }

        public void setLocationDescription(Object locationDescription) {
            this.locationDescription = locationDescription;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getTypeProperties() {
            return typeProperties;
        }

        public void setTypeProperties(Object typeProperties) {
            this.typeProperties = typeProperties;
        }

    }

    public class Media {

        private Object imageUrl;
        private Object imageUrlThumb;

        public Object getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(Object imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Object getImageUrlThumb() {
            return imageUrlThumb;
        }

        public void setImageUrlThumb(Object imageUrlThumb) {
            this.imageUrlThumb = imageUrlThumb;
        }

    }

    public class Source {

        private String name;
        private String htmlUrl;
        private String apiUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

    }
}
