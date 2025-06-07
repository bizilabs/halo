import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    // android
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    // kotlin
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    // compose
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    // others
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kover)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        enableExperimentalRules.set(true)
        additionalEditorconfig.set(
            mapOf(
                "ktlint_standard_package-naming" to "disabled",
            ),
        )
        reporters {
            reporter(ReporterType.JSON)
        }
        filter {
            exclude {
                projectDir
                    .toURI()
                    .relativize(it.file.toURI())
                    .path
                    .contains("/generated/")
            }
            exclude {
                projectDir
                    .toURI()
                    .relativize(it.file.toURI())
                    .path
                    .contains("/build/")
            }
            exclude {
                projectDir
                    .toURI()
                    .relativize(it.file.toURI())
                    .path
                    .contains("MainViewController")
            }
        }
    }
}

kover {
    reports {
        total {
            xml {
                onCheck = true
                xmlFile = layout.projectDirectory.file("kover.xml")
            }
        }
        filters {
            excludes {
                androidGeneratedClasses()
                classes("**/build/**")
            }
        }
        verify {
            rule {
                bound {
                    minValue = 50
                    maxValue = 75
                }
            }
        }
    }
}
