package com.nishtahir

import org.gradle.process.ExecSpec

open class CargoExtension {
    var module: String = ""
    var targets: List<String> = emptyList()

    /**
     * The Android NDK API level to target.  Defaults to the minimum SDK version of the Android
     * project's default configuration.
     */
    var apiLevel: Int? = null

    /**
     * The library name produced by Cargo.
     *
     * Right now, `libname` is used to determine the ELF SONAME to declare in the Android libraries
     * produced by Cargo.  Different versions of the Android system linker [depend on the ELF
     * SONAME](https://android-developers.googleblog.com/2016/06/android-changes-for-ndk-developers.html).
     */
    var libname: String = ""

    /**
     * The Cargo [release profile](https://doc.rust-lang.org/book/second-edition/ch14-01-release-profiles.html#customizing-builds-with-release-profiles) to build.
     *
     * Defaults to `"debug"`.
     */
    var profile: String = "debug"

    /**
     * The target directory into Cargo which writes built outputs.
     *
     * Defaults to `${module}/target`.
     */
    var targetDirectory: String? = null

    /**
     * Which Cargo built outputs to consider JNI libraries.
     *
     * Defaults to `["$libname.so", "$libname.dylib", "$libname.dll"]`.
     */
    var targetIncludes: Array<String>? = null

    /**
     * Android toolchains know where to put their outputs; it's a well-known value like
     * `armeabi-v7a` or `x86`.  The default toolchain outputs don't know where to put their output;
     * use this to say where.
     *
     * Defaults to `""`.
     */
    var defaultToolchainBuildPrefixDir: String? = ""

    // It would be nice to use a receiver here, but there are problems interoperating with Groovy
    // and Kotlin that are just not worth working out.  Another JVM language, yet another dynamic
    // invoke solution :(
    var exec: ((ExecSpec, Toolchain) -> Unit)? = null

    /**
     * List of cargo features to use to build the library. These are passed as
     * a space separated string to cargo with `--features`.
     *
     * It's an error to use this with `allFeatures = true`.
     */
    var features: Array<String>? = null

    /**
     * Whether or not the project's default features should be included.
     * Defaults to true. Setting this to false is equivalent to passing
     * `--no-default-features` to cargo build.
     *
     * It's an error to set this to `false` when using `allFeatures = true`.
     */
    var defaultFeatures: Boolean = true

    /**
     * Setting this to true is equivalent to passing `--all-features` to `cargo
     * build`.
     *
     * It's an error to use this with a list of `features` or when
    * `defaultFeatures = false`.
     */
    var allFeatures: Boolean = false

    /**
     * Set to true to execute `cargo build` with the `--verbose` flag
     */
    var verbose: Boolean = false
}
