package utils.appium.enums;

import utils.Constants;

public enum Environment {
    LOCAL,
    CLOUD;

    public static Environment fromString(String environment) {
        if (environment == null) {
            return LOCAL;
        }
        String normalized = environment.trim().toLowerCase();

        if ("true".equals(normalized)) {
            return CLOUD;
        }
        if ("false".equals(normalized)) {
            return LOCAL;
        }

        try {
            return Environment.valueOf(normalized.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(Constants.ERROR_ENVIRONMENT_INVALID, environment));
        }
    }

    public boolean isCloud() {
        return this == CLOUD;
    }
}
