# Permissioning Toggles Example Application with Sentinel HASP / HL
This application demonstrates [my blog post](https://azizunsal.github.io/blog/post/feature-toggles-as-permissioning-toggles) about Feature Toggles and their different usage as Permissioning Toggles.

## Configuration
First clone this repository and open it with your favorite IDE/editor. 

Then;

### If You Have a Sentinel HASP Licensing Dongle
1. I assume Sentinel runtime & device driver are already installed.  
2. Sentinel JNI and runtime libraries should be set otherwise the application will throw this error and stopped;
   ```
   Exception in thread "main" java.lang.reflect.InvocationTargetException
   	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
   	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
   	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
   	at java.lang.reflect.Method.invoke(Method.java:498)
   	at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:48)
   	at org.springframework.boot.loader.Launcher.launch(Launcher.java:87)
   	at org.springframework.boot.loader.Launcher.launch(Launcher.java:51)
   	at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:52)
   Caused by: java.lang.UnsatisfiedLinkError: no HASPJava in java.library.path
   ``` 
   You can find these files in Sentinel Samples. You will need  `hasp_darwin_demo.dylib` and `libHASPJava.dylib` files under MacOSX. Files for Linux and/or Windows are provided in the samples too. Read [more](http://www.grossmatten.ch/share-freeware/Tools/Sentinel/Sentinel-LDK_SDK/MacOS/Samples/Runtime/Java/readme.html).
   
   And the runtime can be downloaded from [Sentinel web site](https://sentinelcustomer.gemalto.com/sentineldownloads/?s=&c=all&p=all&o=all&t=Runtime+%26+Device+Driver&l=all). 
3. You should change the `VENDOR_CODE` to login the Sentinel HL dongle otherwise the application will not started;
   ```
   12:13:29.704 [main] DEBUG com.azizunsal.blog.permissioningtoggles.helper.HaspHelper - HASP login result for 0 is false
   12:13:29.710 [main] INFO com.azizunsal.blog.permissioningtoggles.helper.HaspHelper - The HASP error description for code '22' is 'Invalid vendor code!'
   12:13:29.711 [main] ERROR com.azizunsal.blog.permissioningtoggles.PermissioningTogglesSampleApplication - Sentinel HL check is NOT ok!
   ```
   
### If You Don't Have a Sentinel HASP Licensing Dongle
1. Then you will need to make changes in `HaspHelper` *(in `checkLicense` method)* to be able to properly run the application.

## Run the Application
``` bash
$ ./gradlew assemble && java -jar build/libs/permissioningtoggles-0.0.1-SNAPSHOT.jar
```

Or if you want to use Docker container;

```bash
$ docker build -t azizunsal/permissioningtoggles .
$ docker run --privileged -d -p 8082:8082 azizunsal/permissioningtoggles
```

## Usage and Test

Let's run the application with the default and `camera` profiles
``` bash
INFO 32983 --- [           main] .p.PermissioningTogglesSampleApplication : The following profiles are active: camera
```
and then make a request to the `camera controller`; 

``` curl
azizunsal:~/ $ curl --request GET http://localhost:8082/cameras
[]%
```
We received an empty result. 

Now make a request to the `Video Controller`
```curl
azizunsal:~/ $ curl --request GET http://localhost:8082/videos 
{"timestamp":"2019-10-31T13:14:14.763+0000","status":404,"error":"Not Found","message":"No message available","path":"/videos"}%
```

As you see, if `video` permission couldn't be found then the related class won't be bootstrapped. 