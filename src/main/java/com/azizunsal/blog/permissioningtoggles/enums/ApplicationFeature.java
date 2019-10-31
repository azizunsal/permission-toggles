package com.azizunsal.blog.permissioningtoggles.enums;

/**
 * @author azizunsal
 */
public enum ApplicationFeature {
    CAMERA("camera", 100),
    VIDEO("video", 200);

    private final String licenseString;
    private final int featureId;

    ApplicationFeature(final String licenseString, final int featureId) {
        this.licenseString = licenseString;
        this.featureId = featureId;
    }

    public String licenseString() { return licenseString; }

    public int featureId() { return featureId; }
}
