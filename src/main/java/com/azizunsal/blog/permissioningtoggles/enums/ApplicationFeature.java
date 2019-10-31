package com.azizunsal.blog.permissioningtoggles.enums;

/**
 * @author azizunsal
 */
public enum ApplicationFeature {
    CAMERA("camera", 1),
    VIDEO("video", 2);

    private final String licenseString;
    private final int featureId;

    ApplicationFeature(final String licenseString, final int featureId) {
        this.licenseString = licenseString;
        this.featureId = featureId;
    }

    public String licenseString() { return licenseString; }

    public int featureId() { return featureId; }
}
