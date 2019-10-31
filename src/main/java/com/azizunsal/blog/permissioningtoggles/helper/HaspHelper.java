package com.azizunsal.blog.permissioningtoggles.helper;

import Aladdin.Hasp;
import Aladdin.HaspStatus;
import com.azizunsal.blog.permissioningtoggles.enums.ApplicationFeature;
import com.azizunsal.blog.permissioningtoggles.model.dto.HaspResult;
import com.azizunsal.blog.permissioningtoggles.model.dto.HaspResult.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author azizunsal
 */
public class HaspHelper {
    private static final Logger logger = LoggerFactory.getLogger(HaspHelper.class);
    private static final String SCOPE1 = "<haspscope />\n";
    private static final String VIEW =
            "<haspformat root=\"my_custom_scope\">\n" +
                    "  <hasp>\n" +
                    "    <attribute name=\"id\" />\n" +
                    "    <attribute name=\"type\" />\n" +
                    "    <feature>\n" +
                    "      <attribute name=\"id\" />\n" +
                    "      <element name=\"concurrency\" />\n" +
                    "      <element name=\"license\" />\n" +
                    "      <session>\n" +
                    "        <element name=\"username\" />\n" +
                    "        <element name=\"hostname\" />\n" +
                    "        <element name=\"ip\" />\n" +
                    "        <element name=\"apiversion\" />\n" +
                    "      </session>\n" +
                    "    </feature>\n" +
                    "  </hasp>\n" +
                    "</haspformat>\n";
    private static final String VENDOR_CODE =
            "d35hZZ4U6RMivX0Q9fQBE8ZQzraYtHMTJ3EneRtQ3+OEG6/glFOXeVwHegeJZ/dg" +
                    "+Etc4chL7AQ/kb7ocPWx18hSJa4wbrpRtBlAVHbTCPPEa6dBMZ/tO8MMIrS8dSfVNeo9J" +
                    "tFt8JnlGu1LKi8AXfCHDVRgGabkQrRuXKjF++JpMY7oW6w5TO0f1qFeDFd8fhruu+p5Lh" +
                    "Bohj7DFr5K1WhL/cT92kQRwB8j+jv+6X+IgginB4sLl847IlQb/AigfJ9XLBZ8DlXi2y/" +
                    "nGsQ5fLoATMyydbgDIjC/cnz1CAco376y3FVCRsv3SpGZzI0fK8Sb0PxDX5UT45jdQYlE" +
                    "sgNfv/+r73OsXzDSa8nAIqV23xRB7IIpeTi5IZeRnSJXewPkk3HUmMYuMELhqk0dx3imN" +
                    "JVaJviokaYwQwgR3qhkDd+yghwH2WboD2JlG+9IrNU72XeOZc7ohayyCWCWQi51udRDyB" +
                    "7OiGlfaaCzY/gfm3yp+eJ56RXa0DV6j35ejQsYV6dJ+CIWK3/NX9r23Sar5OKZuqRBvxE" +
                    "yIUcnutKQ80Iupl8THhqycb8PA3xeivY4Xg2zXKgu2ov29MxRsPhG5N5Wt25Zb1vjUNMV" +
                    "YLNUlt+YodAiyq/T73au+ksi8E4A95vCY5KgPTggG76dbR3xtrUtwcBz0b6PFo9sLZkni" +
                    "3gzIicY0Svy7vSnmU+d+1EOBManqWzf545HFX0NL7A6bjpqmTwhYGnBJ8Zqgh9yC6e1yJ" +
                    "ql4IRE2yQS1f4JR1YfYvluuEZflXcvrLMhARHJWdwiCAKgaUaLQNOOVEyO5Un/4rVWsCK" +
                    "j35ejQsYV6dJ+CIWK3/NX9r23Sar5OKZuqRBvxE1MbJ+3kK7uZ1bGpNq8lANrGfJau32E" +
                    "L9YXGTHI772sI2VXfXPTfobxp9NYxNOcSIoeOQegFewUfwCVBgm2JjYJOeBDAHKwZZ5oQ" +
                    "GGTViLgz0qJ2bk0LRq3gA==";

    public static HaspResult checkLicense() {
        // Check the presence of the vendor specific Sentinel protection key.
        Hasp hasp = new Hasp(Hasp.HASP_DEFAULT_FID);
        boolean haspLoginResult = hasp.login(VENDOR_CODE);
        logger.debug("HASP login result for {} is {}", Hasp.HASP_DEFAULT_FID, haspLoginResult);
        int statusCode = hasp.getLastError();
        if (statusCode != HaspStatus.HASP_STATUS_OK) {
            getHaspError(statusCode);
            return new Builder(statusCode).build();
        }
        return applyAdditionalFeatures(new Builder(statusCode));
    }

    private static HaspResult applyAdditionalFeatures(Builder builder) {
        builder.withLicense(checkAndLoginToFeature(ApplicationFeature.CAMERA));
        builder.withLicense(checkAndLoginToFeature(ApplicationFeature.VIDEO));
        return builder.build();
    }

    private static String checkAndLoginToFeature(final ApplicationFeature applicationFeature) {
        Hasp hasp = new Hasp(applicationFeature.featureId());
        boolean haspLoginResult = hasp.login(VENDOR_CODE);
        logger.debug("HASP login result for '{}' is '{}'", applicationFeature.featureId(), haspLoginResult);
        int statusCode = hasp.getLastError();
        if (statusCode != HaspStatus.HASP_STATUS_OK) {
            getHaspError(statusCode);
            return null;
        }

        return applicationFeature.licenseString();
    }

    private static void getHaspError(final int statusCode) {
        final String statusDescription;
        switch (statusCode) {
            case HaspStatus.HASP_STATUS_OK:
                statusDescription = "HASP is ok. Sentinel key attributes retrieved.";
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                statusDescription = "Requested Feature not found!";
                break;
            case HaspStatus.HASP_HASP_NOT_FOUND:
                statusDescription = "Sentinel key not found!";
                break;
            case HaspStatus.HASP_OLD_DRIVER:
                statusDescription = "Outdated driver version or no driver installed!";
                break;
            case HaspStatus.HASP_NO_DRIVER:
                statusDescription = "Sentinel driver not installed!";
                break;
            case HaspStatus.HASP_INV_VCODE:
                statusDescription = "Invalid vendor code!";
                break;
            case HaspStatus.HASP_INV_HND:
                statusDescription = "Handle not active!";
                break;
            case HaspStatus.HASP_INV_FORMAT:
                statusDescription = "Unrecognized format!";
                break;
            case HaspStatus.HASP_NO_API_DYLIB:
                statusDescription = "Sentinel API dynamic library not found!";
                break;
            case HaspStatus.HASP_INV_API_DYLIB:
                statusDescription = "Sentinel API dynamic library is corrupt";
                break;
            case HaspStatus.HASP_MEM_RANGE:
                statusDescription = "Request exceeds the Sentinel protection key memory range";
                break;
            case HaspStatus.HASP_INV_PROGNUM_OPT:
                statusDescription = "Legacy HASP HL Run-time API: Unknown/Invalid Feature ID option";
                break;
            case HaspStatus.HASP_TMOF:
                statusDescription = "Too many open sessions exist";
                break;
            case HaspStatus.HASP_ACCESS_DENIED:
                statusDescription = "Access to Feature was denied";
                break;
            case HaspStatus.HASP_TS_DETECTED:
                statusDescription = "Program is running on a terminal server";
                break;
            case HaspStatus.HASP_FEATURE_TYPE_NOT_IMPL:
                statusDescription = "Requested Feature type is not implemented";
                break;
            case HaspStatus.HASP_UNKNOWN_VCODE:
                statusDescription = "Vendor Code is not recognized by API";
                break;
            case HaspStatus.HASP_FEATURE_EXPIRED:
                statusDescription = "Feature expired or no executions remain";
                break;
            case HaspStatus.HASP_DEVICE_ERR:
                statusDescription = "For a Sentinel SL key, an input/output error occurred in the secure storage area" +
                        " or for a Sentinel HL key, a USB communication error occurred";
                break;
            case HaspStatus.HASP_NO_VLIB:
                statusDescription = "The customized vendor library (haspvlib.vendorID.*) cannot be located";
                break;
            case HaspStatus.HASP_HASP_INACTIVE:
                statusDescription = "Specified Key ID is in Inactive state";
                break;
            case HaspStatus.HASP_INT_ERR:
                statusDescription = "Internal error occurred in the API";
                break;
            default:
                statusDescription = "";
        }
        logger.info("The HASP error description for code '{}' is '{}'", statusCode, statusDescription);
    }
}
