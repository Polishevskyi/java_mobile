package utils;

import com.github.javafaker.Faker;

public final class DataGenerator {

    private static final Faker FAKER = new Faker();

    private DataGenerator() {
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String password() {
        return FAKER.internet().password(
                Constants.DEFAULT_PASSWORD_MIN_LENGTH,
                Constants.DEFAULT_PASSWORD_MAX_LENGTH,
                Constants.INCLUDE_UPPERCASE,
                Constants.INCLUDE_SPECIAL_CHARS,
                Constants.INCLUDE_DIGITS
        );
    }
}
