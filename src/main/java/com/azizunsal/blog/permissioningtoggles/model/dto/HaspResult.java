package com.azizunsal.blog.permissioningtoggles.model.dto;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author azizunsal
 * Class to store HASP operation results.
 */
public class HaspResult {
    private final int statusCode;
    private final Set<String> licenses;

    private HaspResult(final Builder builder) {
        this.statusCode = builder.statusCode;
        this.licenses = builder.licenses;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Set<String> getLicenses() {
        return licenses;
    }

    @Override
    public String toString() {
        return "HaspResult{" +
                "statusCode=" + statusCode +
                ", licenses=" + licenses +
                '}';
    }

    /**
     * Builder for HaspResult class.
     */
    public static class Builder {
        private final int statusCode;
        private Set<String> licenses = new HashSet<>();

        public Builder(final int statusCode) {
            this.statusCode = statusCode;
        }

        public Builder withLicense(final String license) {
            if (!StringUtils.isEmpty(license)) {
                this.licenses.add(license);
            }
            return this;
        }

        public HaspResult build() {
            return new HaspResult(this);
        }
    }
}
