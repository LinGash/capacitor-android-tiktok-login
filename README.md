# Capacitor Android TikTok Login Plugin

This Capacitor plugin enables TikTok authentication in Android applications. The plugin allows users to log in to TikTok using a simple API and handles the required configurations for the Android platform.

## Features

- **TikTok Login**: Authenticate users via TikTok seamlessly.
- **Android-Only**: This plugin currently supports only Android.

## Installation

```bash
npm install capacitor-android-tiktok-login
```

## Tutorial

You can check the tutorial [here](https://lingash.medium.com/how-to-implement-tiktok-login-in-capacitor-android-ed6dfa017015).

## Usage

Import the plugin and call the `login` method:

```typescript
import { TikTok } from 'capacitor-android-tiktok-login';

const login = async () => {
  try {
    const result = await TikTok.login({
      clientKey: 'YOUR_CLIENT_KEY',
      codeVerifier: 'YOUR_CODE_VERIFIER',
      redirectUri: 'https://YOUR_REDIRECT_URI'
    });

    console.log('Login Result:', result);
    // Example login result:
    // {
    //   "authCode": "KVFrxfUwFdu1c5V562BUdeotQpBqZglfPGX3MgslCWMqDOW2LP5O-OO0OEv-mxG1aotqJtuSiiGo8uxGkwHecz_TcdrH7VptHTxpuy_REBoJIuBK6gbtrlsFyFd5XtDVazPjpC2dKJrDhxytDkL1Jn6u-Ibh6ml9Len9vY1PGjvos21pevigy21hOvzb2LmTEn7f7h4F2KiI_D0_6rjkaCfN9lmJWT5u2R_anyYhIpF2NjRyaAdeg8EDp2NtP-bQ*2!6271.va",
    //   "grantedPermissions": "user.info.basic"
    // }
  } catch (error) {
    console.error('Error during TikTok login:', error);
  }
};
```

## Android Configuration

### 1. Add Deep Link Intent Filter

Add the following intent filter to your app's `AndroidManifest.xml` file:

```xml
<!-- Deep link intent filter -->
<intent-filter android:autoVerify="true">
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />
    <data android:scheme="https" android:host="YOUR_REDIRECT_URI" />
</intent-filter>
```
For `YOUR_REDIRECT_URI` , add your own domain like yourdomain.com

### 2. Update `build.gradle`

Add the following Maven repository in the **top-level** `build.gradle` file:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url "https://artifact.bytedance.com/repository/AwemeOpenSDK" }
    }
}
```

## Login Result

The `login` method returns an object with the following structure:

```json
{
  "authCode": "KVFrxfUwFdu1c5V562BUdeotQpBqZglfPGX3MgslCWMqDOW2LP5O-OO0OEv-mxG1aotqJtuSiiGo8uxGkwHecz_TcdrH7VptHTxpuy_REBoJIuBK6gbtrlsFyFd5XtDVazPjpC2dKJrDhxytDkL1Jn6u-Ibh6ml9Len9vY1PGjvos21pevigy21hOvzb2LmTEn7f7h4F2KiI_D0_6rjkaCfN9lmJWT5u2R_anyYhIpF2NjRyaAdeg8EDp2NtP-bQ*2!6271.va",
  "grantedPermissions": "user.info.basic"
}
```

## Notes

- Ensure you have your TikTok client key and redirect URI configured properly in the TikTok Developer Console.
- This plugin currently supports **Android only**.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Support

For any issues or feature requests, please file an issue on [GitHub](https://github.com/LinGash/capacitor-android-tiktok-login/issues).

