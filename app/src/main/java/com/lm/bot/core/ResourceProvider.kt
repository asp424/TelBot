package com.lm.bot.core

class ResourceProvider {
    var botToken = ""
    var id = 0
    val wrong by lazy { Pair("Wrong token", "") }
    val init by lazy { Pair("", "") }
    val hi by lazy { "Hi there!" }
    val error by lazy { "Error. Try again." }
    val single by lazy { "single" }
    val start by lazy { "start" }
    val joke by lazy { "joke" }
    val channel by lazy { "Bot_channel" }
    val name by lazy { "Bot" }
    val key by lazy { "key"}
    val jokeUrl by lazy { "https://v2.jokeapi.dev/joke/" }
    val animeUrl by lazy { "https://api.jikan.moe/" }
    val memesUrl by lazy { "https://api.imgflip.com" }
}

