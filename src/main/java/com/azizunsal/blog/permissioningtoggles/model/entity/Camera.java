package com.azizunsal.blog.permissioningtoggles.model.entity;

import javax.persistence.Entity;

/**
 * @author azizunsal
 */
@Entity
public class Camera extends AbstractModel {
    private String name;
    private String ipAddress;

    public Camera() { }

    private Camera(final Builder builder) {
        this.name = builder.name;
        this.ipAddress = builder.ipAddress;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                "} " + super.toString();
    }

    /**
     * Builder class for Camera model.
     */
    public static class Builder {
        private final String ipAddress;
        private String name;

        public Builder(final String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Camera build() {
            return new Camera(this);
        }
    }
}
