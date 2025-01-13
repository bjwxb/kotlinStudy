package cn.wxb.kt.ui.home.activity

/**
 * AUTHOR: wuxiaobo
 * DATE:  2024/11/6
 * INTRODUCE: info
 */
enum class ActionState(val action: String) {
    DOOR_OPEN_LF("openLF"),
    DOOR_CLOSE_LF("closeLF"),
    DOOR_OPEN_LB("openLB"),
    DOOR_CLOSE_LB("closeLB"),

    DOOR_OPEN_RF("openRF"),
    DOOR_CLOSE_RF("closeRF"),
    DOOR_OPEN_RB("openRB"),
    DOOR_CLOSE_RB("closeRB"),
    DOOR_OPEN_TAIL("openTR"),
    DOOR_CLOSE_TAIL("closeTR"),

    WINDOW_OPEN_ROOF("openSL"),
    WINDOW_CLOSE_ROOF("closeSL"),
}