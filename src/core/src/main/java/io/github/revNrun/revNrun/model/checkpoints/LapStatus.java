package io.github.revNrun.revNrun.model.checkpoints;

public enum LapStatus {
    COMPLETE,       // Lap well completed, passed through all checkpoints
    INCOMPLETE,     // Lap completed badly, not passed through all checkpoints
    GOOD,           // Lap in progress, passed through all checkpoints until now
    WRONG           // Lap in progress, not passed through all required checkpoints
}
