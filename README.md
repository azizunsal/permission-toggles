# Permissioning Toggles Example Application
This application demonstrates [my blog post](https://azizunsal.github.io/blog/post/feature-toggles-as-permissioning-toggles) about Feature Toggles and their different usage as Permissioning Toggles.

## Installation

First clone the repository, then go into the folder 

``` bash
$ ./gradlew assemble && java -jar build/libs/permissioningtoggles-0.0.1-SNAPSHOT.jar
```

## Notes

1. You should change the `VENDOR_CODE` to login the Sentinel HL dongle otherwise the application will not started.
   ```
   12:13:29.704 [main] DEBUG com.azizunsal.blog.permissioningtoggles.helper.HaspHelper - HASP login result for 0 is false
   12:13:29.710 [main] INFO com.azizunsal.blog.permissioningtoggles.helper.HaspHelper - The HASP error description for code '22' is 'Invalid vendor code!'
   12:13:29.711 [main] ERROR com.azizunsal.blog.permissioningtoggles.PermissioningTogglesSampleApplication - Sentinel HL check is NOT ok!
   ```
2. If you don't have a Sentinel HL dongle, you will need to make changes in `HaspHelper` *(in `checkLicense` method)* to be able to run the application.

 