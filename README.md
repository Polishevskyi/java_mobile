# 🚀 Mobile Test Automation Framework

**Professional Mobile test automation framework with Java, Appium, TestNG, and Allure reporting**

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![Appium](https://img.shields.io/badge/Appium-9.3.0-orange.svg)](https://appium.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red.svg)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.27.0-red.svg)](https://allure-framework.github.io/)

---

## 📋 Table of Contents

- [Project Description](#-project-description)
- [Architecture & Structure](#-architecture--structure)
- [Technology Stack](#-technology-stack)
- [Design Patterns](#-design-patterns)
- [Configuration & Secrets](#-configuration--secrets)
- [Quick Start](#-quick-start)
- [Test Execution](#-test-execution)
- [Reporting](#-reporting)
- [CI/CD Integration](#-cicd-integration)

---

## 🎯 Project Description

This project is a comprehensive mobile test automation framework that combines:

- **Mobile Testing** through BrowserStack and local Appium
- **Automated Reporting** through Allure Reports
- **Telegram Integration** for test result notifications
- **CI/CD Support** through GitHub Actions

The framework is built on clean architecture principles using modern design patterns to ensure scalability, maintainability, and reliability.

---

## 🏗️ Architecture & Structure

### 📁 Project Structure

```
java_mobile/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/
│   │   │   ├── 📁 screens/              # Page Object Model
│   │   │   │   ├── BaseScreen.java      # Base page with common methods
│   │   │   │   ├── LoginScreen.java     # Login page
│   │   │   │   ├── ProductsScreen.java  # Products page
│   │   │   │   ├── CartScreen.java      # Cart page
│   │   │   │   └── MenuScreen.java      # Menu page
│   │   │   └── 📁 utils/
│   │   │       ├── 📁 appium/
│   │   │       │   ├── 📁 driver/
│   │   │       │   │   ├── AppDriver.java           # Driver wrapper (Singleton)
│   │   │       │   │   ├── AppFactory.java          # Factory Pattern for driver creation
│   │   │       │   │   └── AppiumServerManager.java # Appium server management
│   │   │       │   ├── 📁 enums/
│   │   │       │   │   ├── Environment.java        # Cloud/Local enum
│   │   │       │   │   └── Platform.java           # Android/iOS enum
│   │   │       │   └── TestConfig.java             # Test configuration
│   │   │       ├── ConfigReader.java               # Configuration reader
│   │   │       ├── Constants.java                  # Constants
│   │   │       ├── DataGenerator.java              # Factory Pattern for test data
│   │   │       ├── 📁 listeners/
│   │   │       │   ├── AllureListener.java         # Allure reporting listener
│   │   │       │   └── BrowserStackListener.java   # BrowserStack listener
│   │   │       └── RetryAnalyzer.java              # Retry mechanism
│   │   └── 📁 resources/
│   │       └── 📁 apps/                            # APK/IPA files
│   │           ├── AppAndroid.apk
│   │           ├── AppiOSlocal.zip
│   │           └── AppiOSRemote.ipa
│   └── 📁 test/
│       ├── 📁 java/
│       │   └── 📁 tests/
│       │       ├── BaseTest.java                    # Base test class
│       │       ├── LoginTest.java                   # Login tests
│       │       ├── NegativeLoginTest.java          # Negative login tests
│       │       ├── CartTest.java                    # Cart tests
│       │       └── SortTest.java                    # Sort tests
│       └── 📁 resources/
│           └── test-suite.xml                      # TestNG suite configuration
├── 📁 target/
│   └── 📁 allure-results/                         # Allure results
├── config.properties                               # Configuration file
├── config.properties.example                      # Configuration template
├── pom.xml                                         # Maven configuration
└── send-telegram-notification.sh                  # Telegram notification script
```

### 🔄 Architectural Principles

- **Separation of Concerns** - clear separation of responsibilities
- **DRY (Don't Repeat Yourself)** - avoiding code duplication
- **SOLID Principles** - following object-oriented programming principles
- **Dependency Injection** - dependency inversion for testing

---

## 🛠️ Technology Stack

### 🎯 Core Technologies

| Category           | Technology        | Version | Purpose                      |
| ------------------ | ---------------- | ------- | ---------------------------- |
| **Language**       | Java             | 11      | Programming language         |
| **Build Tool**     | Maven            | 3.6+    | Dependency management        |
| **Testing**        | TestNG           | 7.10.2  | Test framework               |
| **Mobile Testing** | Appium           | 9.3.0   | Mobile app automation        |
| **WebDriver**      | Selenium         | 4.25.0  | WebDriver implementation     |
| **Assertions**     | TestNG           | 7.10.2  | Built-in assertions          |
| **Reporting**      | Allure           | 2.27.0  | Detailed test reporting      |
| **Cloud Testing**  | BrowserStack SDK | 1.18.0  | Cloud testing platform       |

### 🔧 Additional Tools

| Tool                  | Purpose                             |
| --------------------- | ----------------------------------- |
| **JavaFaker**         | Test data generation                |
| **Spotless Maven**    | Code formatting and quality control |
| **AspectJ Weaver**    | Allure integration                  |
| **Maven Surefire**    | Test execution                      |

### 🌐 Supported Platforms

- **Android** (primary platform)
- **iOS** (via Appium)
- **BrowserStack Cloud** (cloud testing)
- **Local Testing** (Appium Server)

---

## 🎨 Design Patterns

### 1. **Page Object Model (POM)**

```java
// src/main/java/screens/BaseScreen.java
public class BaseScreen {
    protected WebElement waitUntilElementPresent(By locator) {
        // Common wait logic
    }
    
    protected void tap(By locator) {
        // Common tap logic with Allure logging
    }
}

// src/main/java/screens/LoginScreen.java
public class LoginScreen extends BaseScreen {
    // Specific methods for login page
}
```

### 2. **Factory Pattern** (Driver Creation)

```java
// src/main/java/utils/appium/driver/AppFactory.java
public class AppFactory {
    public static void launchApp() throws MalformedURLException {
        if (TestConfig.platform == Platform.IOS) {
            driver = new IOSDriver(serverUrl.toURL(), iosOptions);
        } else {
            driver = new AndroidDriver(serverUrl.toURL(), androidOptions);
        }
        AppDriver.setDriver(driver);
    }
}
```

### 3. **Factory Pattern** (Data Generation)

```java
// src/main/java/utils/DataGenerator.java
public final class DataGenerator {
    private static final Faker FAKER = new Faker();
    
    public static String email() {
        return FAKER.internet().emailAddress();
    }
    
    public static String password() {
        return FAKER.internet().password(...);
    }
}
```

### 4. **Singleton Pattern** (Driver Management)

```java
// src/main/java/utils/appium/driver/AppDriver.java
public class AppDriver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static WebDriver getCurrentDriver() {
        return driver.get();
    }
    
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
}
```

### 5. **Strategy Pattern** (Different Testing Environments)

```java
// src/main/java/utils/appium/enums/Environment.java
public enum Environment {
    CLOUD(true),
    LOCAL(false);
    
    private final boolean isCloud;
    
    public boolean isCloud() {
        return isCloud;
    }
}
```

### 6. **Listener Pattern** (Allure Reporting)

```java
// src/main/java/utils/listeners/AllureListener.java
public class AllureListener implements ITestListener {
    public static <T> T logStep(String stepName, Supplier<T> action) {
        return Allure.step(stepName, () -> action.get());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot("Screenshot on failure");
    }
}
```

### 7. **Builder Pattern** (Test Configuration)

```java
// src/main/java/utils/appium/driver/AppFactory.java
private static UiAutomator2Options createAndroidOptions() {
    UiAutomator2Options options = new UiAutomator2Options();
    options.setDeviceName(...)
           .setPlatformVersion(...)
           .setAppPackage(...)
           .setAppActivity(...);
    return options;
}
```

### 8. **Retry Pattern** (Test Retry Mechanism)

```java
// src/main/java/utils/RetryAnalyzer.java
public class RetryAnalyzer implements IRetryAnalyzer {
    // Retry logic for failed tests
}
```

---

## 🔐 Configuration & Secrets

### 📝 Required Configuration

Create `config.properties` file based on `config.properties.example`:

```properties
# Platform Configuration
platform=android
isCloud=false

# Test Data
test.credentials.username=bob@example.com
test.credentials.password=10203040

# Appium Server Configuration
appium.jsPath=/opt/homebrew/lib/node_modules/appium/build/lib/main.js
appium.nodePath=/opt/homebrew/bin/node
appium.port=4723
appium.localUrl=http://127.0.0.1:4723/

# BrowserStack Configuration
browserstack.username=YOUR_BROWSERSTACK_USERNAME
browserstack.accessKey=YOUR_BROWSERSTACK_ACCESS_KEY
browserstack.appiumVersion=2.4.1
browserstack.hubUrl=http://hub-cloud.browserstack.com/wd/hub/

# Android Local Configuration
android.local.deviceName=Pixel 6 Pro API 34
android.local.platformVersion=14.0
android.local.appPackage=com.saucelabs.mydemoapp.rn
android.local.appActivity=.MainActivity
android.local.app=src/main/resources/apps/AppAndroid.apk

# Android Cloud Configuration
android.cloud.deviceName=Google Pixel 6 Pro
android.cloud.platformVersion=13.0
android.cloud.appPackage=com.saucelabs.mydemoapp.rn
android.cloud.appActivity=.MainActivity
android.cloud.app=bs://YOUR_ANDROID_APP_ID

# iOS Local Configuration
ios.local.deviceName=iPhone 16 Pro
ios.local.udid=YOUR_DEVICE_UDID
ios.local.platformVersion=18.6
ios.local.bundleId=com.saucelabs.mydemoapp.rn
ios.local.app=src/main/resources/apps/AppiOSRemote.ipa

# iOS Cloud Configuration
ios.cloud.deviceName=iPhone 16 Pro
ios.cloud.platformVersion=18.6
ios.cloud.bundleId=com.saucelabs.mydemoapp.rn
ios.cloud.app=bs://YOUR_IOS_APP_ID
```

### 🔑 GitHub Secrets

Configure the following secrets in GitHub (Settings → Secrets and variables → Actions):

| Secret                        | Description                      | Example                          | Required |
| ----------------------------- | ------------------------------- | -------------------------------- | -------- |
| **BrowserStack**              |                                 |                                  |          |
| `BROWSERSTACK_USERNAME`       | BrowserStack username           | `your_username`                  | ✅       |
| `BROWSERSTACK_ACCESS_KEY`     | BrowserStack access key         | `your_access_key`                | ✅       |
| `BROWSERSTACK_APPIUM_VERSION` | Appium version for BrowserStack | `2.4.1`                          | ✅       |
| **Test Credentials**          |                                 |                                  |          |
| `TEST_USERNAME`               | Test account username           | `bob@example.com`                | ✅       |
| `TEST_PASSWORD`               | Test account password           | `10203040`                       | ✅       |
| **Platform Configuration**    |                                 |                                  |          |
| `PLATFORM`                    | Default platform (optional)     | `android` or `ios`               | ⚪       |
| **Android Configuration**     |                                 |                                  |          |
| `ANDROID_DEVICE_NAME`          | Android device name             | `Google Pixel 6 Pro`             | ✅       |
| `ANDROID_PLATFORM_VERSION`    | Android platform version        | `13.0`                           | ✅       |
| `ANDROID_APP_PACKAGE`         | Android app package             | `com.saucelabs.mydemoapp.rn`     | ✅       |
| `ANDROID_APP_ACTIVITY`        | Android app activity            | `.MainActivity`                  | ✅       |
| `ANDROID_APP_ID`              | BrowserStack Android app ID     | `bs://your_android_app_id`       | ✅       |
| **iOS Configuration**         |                                 |                                  |          |
| `IOS_DEVICE_NAME`             | iOS device name                 | `iPhone 16 Pro`                  | ✅       |
| `IOS_PLATFORM_VERSION`        | iOS platform version            | `18.6`                           | ✅       |
| `IOS_BUNDLE_ID`               | iOS bundle identifier           | `com.saucelabs.mydemoapp.rn`     | ✅       |
| `IOS_APP_ID`                  | BrowserStack iOS app ID         | `bs://your_ios_app_id`           | ✅       |
| **Telegram Notifications**    |                                 |                                  |          |
| `TELEGRAM_BOT_TOKEN`          | Telegram bot token              | `123456789:ABC...`               | ⚪       |
| `TELEGRAM_CHAT_ID`            | Chat ID for notifications       | `-1001234567890`                 | ⚪       |

> **Note:** `GITHUB_TOKEN` is automatically provided by GitHub Actions and doesn't need to be configured manually.

### 🚀 How to Get Secrets

#### BrowserStack

1. Register at [browserstack.com](https://browserstack.com)
2. Go to Account → Settings
3. Copy Username and Access Key

#### Telegram Bot

1. Create bot via [@BotFather](https://t.me/botfather)
2. Get bot token
3. Add bot to group/channel
4. Get Chat ID via [@userinfobot](https://t.me/userinfobot)

---

## 🚀 Quick Start

### 📋 Prerequisites

- **Java** >= 11
- **Maven** >= 3.6
- **Node.js** (for Appium)
- **Appium** >= 2.0
- **BrowserStack account** (for cloud testing)
- **Android Studio** (for local Android testing)
- **Xcode** (for local iOS testing)

### ⚡ Installation

```bash
# Clone repository
git clone <repository-url>
cd java_mobile

# Copy configuration
cp config.properties.example config.properties

# Edit configuration
nano config.properties  # or any editor

# Install Appium (if not installed)
npm install -g appium
npm install -g appium-gestures-plugin

# Install dependencies (Maven will download automatically)
mvn clean install
```

### 🎯 First Run

```bash
# Run all tests locally (Android)
mvn clean test -Dplatform=android -DisCloud=false

# Run all tests on BrowserStack (Android)
mvn clean test -Dplatform=android -DisCloud=true

# Generate Allure report
mvn allure:serve
```

---

## 🧪 Test Execution

### 📱 Mobile Tests

```bash
# All mobile tests (Android, Local)
mvn clean test -Dplatform=android -DisCloud=false

# All mobile tests (Android, BrowserStack)
mvn clean test -Dplatform=android -DisCloud=true

# All mobile tests (iOS, Local)
mvn clean test -Dplatform=ios -DisCloud=false

# All mobile tests (iOS, BrowserStack)
mvn clean test -Dplatform=ios -DisCloud=true

# Specific test class
mvn test -Dtest=LoginTest

# Specific test method
mvn test -Dtest=LoginTest#validLoginTest
```

### ⚙️ Environment Configuration

```bash
# BrowserStack (cloud)
mvn test -Dplatform=android -DisCloud=true

# Local testing
mvn test -Dplatform=android -DisCloud=false

# With additional parameters
mvn test -Dplatform=android -DisCloud=false -DuseGesturePlugin=false -DchromeAutoDownloadDriver=false
```

### 🔄 Parallel Execution

Configure parallel execution in `test-suite.xml`:

```xml
<suite name="TestSuite" parallel="methods" thread-count="5">
    <test name="AllTests">
        <classes>
            <class name="tests.LoginTest"/>
            <class name="tests.CartTest"/>
        </classes>
    </test>
</suite>
```

---

## 📊 Reporting

### 🎨 Allure Reports

```bash
# Generate report
mvn allure:report

# Serve report locally
mvn allure:serve

# Generate and open report
mvn allure:report allure:serve
```

### 📈 Report Types

- **📊 Summary** - overall statistics
- **📋 Test Cases** - detailed results
- **📈 Trends** - execution trends
- **🔍 Behaviors** - behavioral tests
- **📱 Suites** - test groups

### 🔔 Telegram Notifications

Automatic notifications about results via `send-telegram-notification.sh`:

- 🟢 **Success** - all tests passed
- 🟡 **Partial** - some tests failed
- 🔴 **Failed** - critical errors

```bash
# Send notification manually
./send-telegram-notification.sh
```

---

## 🔄 CI/CD Integration

### 🚀 GitHub Actions

Automatic test execution on:

- **Push** to main/master/develop
- **Pull Requests**
- **Manual triggers** with test type selection

### 📋 Workflow Stages

1. **Setup** - install Java and Maven
2. **Lint** - code quality check (Spotless)
3. **Tests** - execute mobile tests
4. **Report** - generate Allure reports
5. **Notify** - send Telegram notifications

### 🎯 Matrix Testing

```yaml
strategy:
  matrix:
    platform: [android, ios]
    environment: [cloud, local]
```

### 📊 Artifacts

- **Allure Reports** - detailed reports
- **Screenshots** - error screenshots
- **Logs** - execution logs
- **Video** - test recordings (BrowserStack)

---

## 🎯 Key Benefits

### ✅ **Scalability**

- Modular architecture
- Easy addition of new tests
- Multi-platform support (Android/iOS)
- Parallel execution support

### ✅ **Reliability**

- Automatic retries
- Detailed logging
- Error handling
- Screenshot on failure

### ✅ **Usability**

- Simple API
- Automatic Allure logging
- Detailed documentation
- Clear structure

### ✅ **Integration**

- CI/CD ready
- Telegram notifications
- Allure reporting
- BrowserStack integration

---

## 📝 Code Formatting

The project uses **Spotless Maven Plugin** for code formatting:

```bash
# Format code
mvn spotless:apply

# Check code formatting
mvn spotless:check
```

---

## 🔧 Appium Setup

### Local Appium Server

```bash
# Start Appium server
appium

# Start with specific configuration
appium server -a 127.0.0.1 -pa /wd/hub --allow-cors

# Stop all Node.js processes (if Appium server hangs)
killall node
```

### Appium Inspector Configuration

#### Android Local
```json
{
  "platformName": "Android",
  "appium:deviceName": "Pixel 4",
  "appium:platformVersion": "14",
  "appium:automationName": "UiAutomator2"
}
```

#### iOS Local
```json
{
  "platformName": "iOS",
  "appium:deviceName": "iPhone 16 Plus",
  "appium:platformVersion": "18.6",
  "appium:automationName": "XCUITest",
  "appium:udid": "YOUR_DEVICE_UDID"
}
```

> **Note:** For iOS Local, you need to specify the `appium:udid` of your physical device or simulator. You can find the UDID using:
> - **Simulator:** `xcrun simctl list devices`
> - **Physical device:** Connect device and check in Xcode → Window → Devices and Simulators

---

## 📦 Dependencies

All dependencies are managed via Maven in `pom.xml`:

- **TestNG** - Test framework
- **Appium Java Client** - Appium integration
- **Selenium** - WebDriver implementation
- **JavaFaker** - Test data generation
- **BrowserStack SDK** - Cloud testing
- **Allure TestNG** - Reporting
- **AspectJ Weaver** - Allure integration

---

<div align="center">

**Made with ❤️ by Polishevskyi**

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Polishevskyi)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/polishevskyi/)

</div>

