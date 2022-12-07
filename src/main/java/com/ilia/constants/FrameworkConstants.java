package com.ilia.constants;

public final class FrameworkConstants {
    private static final String CHROME_DRIVER_PATH = System.getProperty("user.dir")
            + "/src/test/resources/executables/chromedriver.exe";

    //class set final to prevent from extends and private constructor will not allow crete
    // objects of this class
    private FrameworkConstants() {
    }

    public static String getChromeDriverPath() {
        return CHROME_DRIVER_PATH;
    }
}
