// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorAndroidTiktokLogin",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapacitorAndroidTiktokLogin",
            targets: ["TikTokPluginPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "TikTokPluginPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/TikTokPluginPlugin"),
        .testTarget(
            name: "TikTokPluginPluginTests",
            dependencies: ["TikTokPluginPlugin"],
            path: "ios/Tests/TikTokPluginPluginTests")
    ]
)