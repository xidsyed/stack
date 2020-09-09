plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.0-alpha10")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:9.3.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.12.0")
}
