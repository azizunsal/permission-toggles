package com.azizunsal.blog.permissioningtoggles;

import Aladdin.HaspStatus;
import com.azizunsal.blog.permissioningtoggles.helper.HaspHelper;
import com.azizunsal.blog.permissioningtoggles.model.dto.HaspResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

/**
 * @author azizunsal
 */
@SpringBootApplication
public class PermissioningTogglesSampleApplication {
    private static final Logger logger = LoggerFactory.getLogger(PermissioningTogglesSampleApplication.class);

    public static void main(String[] args) {
        final HaspResult haspResult = HaspHelper.checkLicense();
        if (haspResult.getStatusCode() == HaspStatus.HASP_STATUS_OK) {
            logger.info("Sentinel HL check is ok, adding additional profiles {}", haspResult.getLicenses());
            SpringApplication app = new SpringApplication(PermissioningTogglesSampleApplication.class);
            app.setAdditionalProfiles(StringUtils.toStringArray(haspResult.getLicenses()));
            app.run(args);
        } else {
            logger.error("Sentinel HL check is NOT ok!");
        }
    }
}
