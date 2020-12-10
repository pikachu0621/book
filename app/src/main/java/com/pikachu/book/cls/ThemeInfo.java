package com.pikachu.book.cls;

public class ThemeInfo{


    public ThemeInfo(int bgColor, int bgTextColor, int handColor, int handTitleTextColor, int handZTextColor, int handZJTextColor, int handDTextColor, int handLoadColor) {
        this.bgColor = bgColor;
        this.bgTextColor = bgTextColor;
        this.handColor = handColor;
        this.handTitleTextColor = handTitleTextColor;
        this.handZTextColor = handZTextColor;
        this.handZJTextColor = handZJTextColor;
        this.handDTextColor = handDTextColor;
        this.handLoadColor = handLoadColor;
    }

    int bgColor;//主背景色
    int bgTextColor;//主textColor
    int handColor;//侧滑头部背景
    int handTitleTextColor;//侧滑头部书名字体颜色
    int handZTextColor;//侧滑头部作者字体颜色
    int handZJTextColor;//侧滑头部章节字体颜色
    int handDTextColor;//侧滑头部到序字体颜色
    int handLoadColor;//侧滑刷新颜色



    public int getBgTextColor() {
        return bgTextColor;
    }

    public void setBgTextColor(int bgTextColor) {
        this.bgTextColor = bgTextColor;
    }
    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getHandColor() {
        return handColor;
    }

    public void setHandColor(int handColor) {
        this.handColor = handColor;
    }

    public int getHandTitleTextColor() {
        return handTitleTextColor;
    }

    public void setHandTitleTextColor(int handTitleTextColor) {
        this.handTitleTextColor = handTitleTextColor;
    }

    public int getHandZTextColor() {
        return handZTextColor;
    }

    public void setHandZTextColor(int handZTextColor) {
        this.handZTextColor = handZTextColor;
    }

    public int getHandZJTextColor() {
        return handZJTextColor;
    }

    public void setHandZJTextColor(int handZJTextColor) {
        this.handZJTextColor = handZJTextColor;
    }

    public int getHandDTextColor() {
        return handDTextColor;
    }

    public void setHandDTextColor(int handDTextColor) {
        this.handDTextColor = handDTextColor;
    }

    public int getHandLoadColor() {
        return handLoadColor;
    }

    public void setHandLoadColor(int handLoadColor) {
        this.handLoadColor = handLoadColor;
    }

}