plugins {
    //trick: for the same plugin versions in all sub-modules
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.androidLibrary).apply(false)
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.serialization).apply(false)
}
