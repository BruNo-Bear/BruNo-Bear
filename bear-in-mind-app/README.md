# Bear In Mind — Android app

A single Android app covering the four things the business needs on a phone:

| Tab | What it does |
| --- | --- |
| Home | Logo, about text, tap-to-call / email / map / website, opening hours |
| Services | Service list with prices, each with a "Book this" shortcut |
| Book | Appointment request form; saved on the device and sent to the business by email |
| Shop | Product catalogue with an enquiry email and a share button |
| Notes | Personal notes and reminders, stored on the device |

Nothing leaves the phone except the emails/calls the user chooses to send — there is no backend and no account.

## Editing the content

All text, prices, contact details and opening hours live in one file:
`app/src/main/java/com/bearinmind/app/BusinessInfo.kt`.

The launcher icon and the logo on the Home tab come from
`app/src/main/res/mipmap-*/ic_launcher*.png` and `app/src/main/res/drawable-nodpi/logo.png`.

## Building

Requires JDK 17 and the Android SDK (compileSdk 34, build-tools 34.0.0).

```bash
echo "sdk.dir=$HOME/Android/Sdk" > local.properties
./gradlew assembleDebug     # app/build/outputs/apk/debug/app-debug.apk
./gradlew assembleRelease   # app/build/outputs/apk/release/app-release.apk
```

The release build is currently signed with the local debug key, which is fine for
sideloading (Settings → install unknown apps) but not for Google Play. Publishing to Play
needs a real upload keystore configured in `app/build.gradle.kts`.
