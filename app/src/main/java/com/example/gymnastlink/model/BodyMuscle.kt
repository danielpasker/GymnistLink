package com.example.gymnastlink.model

enum class BodyMuscle(val muscleGroup: String) {
    UNKNOWN(""),
    // Upper Body Muscles
    DELTOID("Shoulders"),
    TRAPEZIUS("Upper Back"),
    PECTORALS("Chest"),
    BICEPS("Arms"),
    TRICEPS("Arms"),
    LATISSIMUS_DORSI("Back"),
    RHOMBOIDS("Upper Back"),
    FOREARMS("Arms"),

    // Lower Body Muscles
    QUADRICEPS("Legs"),
    HAMSTRINGS("Legs"),
    GLUTEUS_MAXIMUS("Glutes"),
    CALVES("Legs"),
    ADDUCTORS("Legs"),

    // Core Muscles
    RECTUS_ABDOMINIS("Core"),
    OBLIQUES("Core"),
    ERECTOR_SPINAE("Lower Back");

    override fun toString(): String {
        return "$name ($muscleGroup)"
    }
}