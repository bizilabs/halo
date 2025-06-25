import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.maven.publish)
}

fun Project.findProperties(file: String): Properties {
    val properties = Properties()
    properties.load(project.rootProject.file(file).reader())
    return properties
}

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                // compose
                api(compose.ui)
                api(compose.runtime)
                api(compose.material3)
                api(compose.foundation)
                implementation(compose.components.resources)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.uiToolingPreview)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "org.bizilabs.halo.charts"
    compileSdk =
        libs.versions.android.sdk.compile
            .get()
            .toInt()
    defaultConfig {
        minSdk =
            libs.versions.android.sdk.min
                .get()
                .toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
compose {
    resources {
        publicResClass = false
        packageOfResClass = "org.bizilabs.halo.generated.resources"
        generateResClass = auto
    }
}

group = "org.bizilabs.halo.charts"

publishing {
    val properties = findProperties("local.properties")
    repositories {
        maven {
            setUrl("https://maven.pkg.github.com/bizilabs/halo")
            credentials {
                username = properties["github.username"].toString()
                password = properties["github.token"].toString()
            }
        }
    }
}
