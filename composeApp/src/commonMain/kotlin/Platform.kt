interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun getCurrentThreadName(): String