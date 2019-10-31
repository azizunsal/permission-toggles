package com.azizunsal.blog.permissioningtoggles.model.entity;

import javax.persistence.Entity;

/**
 * @author azizunsal
 */
@Entity
public class Video extends AbstractModel {
    private String name;
    private String path;

    public Video() { }

    private Video(final Builder builder) {
        this.name = builder.name;
        this.path = builder.path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                "} " + super.toString();
    }

    public static class Builder {
        private final String path;
        private String name;

        public Builder(final String path) {
            this.path = path;
        }

        public Builder withName(final String anme) {
            this.name = name;
            return this;
        }

        public Video build() {
            return new Video(this);
        }
    }
}
