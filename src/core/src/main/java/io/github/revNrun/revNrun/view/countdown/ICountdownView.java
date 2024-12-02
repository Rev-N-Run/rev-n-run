package io.github.revNrun.revNrun.view.countdown;

public interface ICountdownView {
    void draw(int number);
    void setShowRun(boolean show);
    void setTitleAlpha(float alpha);
    void setNumberAlpha(float alpha);
    void setRunAlpha(float alpha);
    void setScale(float scale);
}
