package ort.assi.blp.secure;

public enum SecurityLevel {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private final Integer level;

    SecurityLevel(Integer level) {
        this.level = level;
    }

    public Boolean dominates(SecurityLevel anotherSecurityLevel){
        return this.level - anotherSecurityLevel.level >= 0;
    }
}
