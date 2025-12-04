package utils.appium.enums;

import utils.Constants;

public enum Platform {
    ANDROID,
    IOS;

    public static Platform fromString(String platform) {
        if (platform == null || platform.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_PLATFORM_NULL);
        }
        try {
            return Platform.valueOf(platform.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(Constants.ERROR_PLATFORM_INVALID, platform));
        }
    }
}
