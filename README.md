# SkTopia
![License](https://img.shields.io/github/license/Jazzkuh/SkTopia)
[![Build Status](https://jenkins.jazzkuh.com/job/SkTopia/badge/icon)](https://jenkins.jazzkuh.com/job/SkTopia/)
![Lines](https://img.shields.io/tokei/lines/github/Jazzkuh/SkTopia)

A simple skript addon which provides a number of utility expressions and events for the MinetopiaSDB plugin. To help you get on your way with this plugin i've inserted a few quick examples down below.

```css
command /stats:
    trigger:
        send "&3Level: &b%the minetopiasdb level of player%"
        send "&3Prefix: &b%the minetopiasdb prefix of player%"
        send "&3LuckyShards: &b%luckyshards of player%"
        send "&3GrayShards: &b%grayshards of player%"
        send "&3GoldShards: &b%goldshards of player%"
        send "&3GrayCoins: &b%graycoins of player%"
        send "&3Playtime: &b%the minetopiasdb playtime of player%"
        send "&3Fitness: &b%the minetopiasdb fitness of player%"
 
on minetopiasdb level change:
    broadcast "%player%'s level has been changed to %event-number%"
 
on minetopiasdb emergency call:
  broadcast "%player% has called 112: %event-string%"
 
on sdb atm open:
    if player is not holding a lime dye:
        cancel event
        send "You need to be holding a credit card to use this ATM!"
```

[![SkUnity](https://skunity.com/branding/buttons/get_on_forums.png)](https://forums.skunity.com/resources/sktopia-the-minetopiasdb-skript-addon.1340/)

## Suggestions
If you have any feature requests for more things to add to this addon please send me a direct message on the forums or on Discord.
