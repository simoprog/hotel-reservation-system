package hotel.enums;

public enum RoomType {
    STANDARD("standard"),
    JUNIOR("junior"),
    SUITE("suite");

    private final String value;

    RoomType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
