plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mio.plugin.renderer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mio.plugin.renderer"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        configureEach {
            // 应用名
            resValue("string", "app_name", "MobileGL Renderer")
            // 包名后缀
            applicationIdSuffix = ".MobileGL"

            // 渲染器在启动器内显示的名称
            manifestPlaceholders["des"] = "MobileGL"
            // 渲染器定义：名称:渲染库:EGL库
            manifestPlaceholders["renderer"] = "MobileGL:libMobileGL.so:libMobileGL.so"

            // 特殊环境变量：预加载依赖库
              // 如果有多个依赖，用逗号分隔，如 "libglslang.so,libother.so"
            manifestPlaceholders["boatEnv"] = mutableMapOf<String, String>().apply {
                put("DLOPEN", "libglslang.so")
            }.run {
                var env = ""
                forEach { (key, value) -> env += "$key=$value:" }
                env.dropLast(1)  // 移除末尾冒号
            }

            manifestPlaceholders["pojavEnv"] = mutableMapOf<String, String>().apply {
                // 可添加其他环境变量，如 put("LD_LIBRARY_PATH", "...")
            }.run {
                var env = ""
                forEach { (key, value) -> env += "$key=$value:" }
                env.dropLast(1)
            }

            // 支持的 Minecraft 版本范围（可选）
            manifestPlaceholders["minMCVer"] = "1.16"
            manifestPlaceholders["maxMCVer"] = ""
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
}