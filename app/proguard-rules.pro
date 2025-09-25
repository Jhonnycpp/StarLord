# proguard
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses, EnclosingMethod
-keepattributes *Annotation*

# Koin
-keep @org.koin.core.annotation.KoinInternalApi class *
-keepclassmembers,allowobfuscation class * {
    @org.koin.core.annotation.KoinInternalApi *;
}

-keep class org.koin.core.instance.* { *; }
-keep class org.koin.core.definition.* { *; }
-keep class org.koin.core.module.Module { *; }
-keep public class * extends org.koin.core.module.Module { *; }
-keep class org.koin.core.scope.* { *; }
-keep class org.koin.core.registry.* { *; }
-keep class org.koin.androidx.viewmodel.ext.android.ViewModelFactoryKt { *; }
-keep class org.koin.androidx.viewmodel.ViewModelParameter { *; }

-keep @org.koin.core.annotation.KoinReflectAPI class *
-keepclassmembers,allowobfuscation class * {
    @org.koin.core.annotation.KoinReflectAPI *;
}

#-keep class br.com.jhonny.starlord.di.** { *; }

# OkHttp
-keep class okhttp3.OkHttpClient { *; }
-keep class okhttp3.Request { *; }
-keep class okhttp3.Response { *; }
-keep class okhttp3.ResponseBody { *; }
-keep class okhttp3.RequestBody { *; }
-keep class okhttp3.Headers { *; }
-keep class okhttp3.MediaType { *; }
-keep class okhttp3.Call { *; }
-keep interface okhttp3.Interceptor { *; }
-keep interface okhttp3.Authenticator { *; }
-keep interface okhttp3.EventListener { *; }
-keep class okhttp3.ConnectionSpec { *; }
-keep class okhttp3.TlsVersion { *; }
-keep class okhttp3.CipherSuite { *; }
-keep class okhttp3.WebSocket { *; }
-keep interface okhttp3.WebSocketListener { *; }
-keep class okhttp3.Protocol { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.Platform$Java8
-keepclassmembers interface * {
    @retrofit2.http.* <methods>;
}

# Kotlin reflect
-keep class kotlin.reflect.** { *; }
-dontwarn kotlin.reflect.jvm.internal.**

# Kotlinx Serialization
-keepclasseswithmembers class kotlinx.serialization.internal.* { *; }
-keepclasseswithmembers class * {
    @kotlinx.serialization.Serializable <fields>;
    @kotlinx.serialization.Serializable <methods>;
    @kotlinx.serialization.Serializable <init>(...);
}
-keep class **$$serializer { *; }
-keepclassmembers class * {
    public static final kotlinx.serialization.KSerializer serializer(...);
}
-dontwarn kotlinx.serialization.**
