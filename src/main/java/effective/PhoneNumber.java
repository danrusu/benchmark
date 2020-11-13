package effective;

import java.util.Comparator;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

public class PhoneNumber implements Formattable, Comparable<PhoneNumber> {

    public static final Comparator<PhoneNumber> PHONE_NUMBER_COMPARATOR = Comparator
            .comparingInt(PhoneNumber::getAreaCode)
            .thenComparing(PhoneNumber::getNumber);
    private final int areaCode;
    private final int number;
    private int hashCode;

    // com.google.common.base.Preconditions.checkArgument
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    private PhoneNumber(int areaCode, int number) {
        this.areaCode = areaCode;
        this.number = number;
        hashCode = Objects.hash(areaCode, number);
    }

    private static PhoneNumber of(int areaCode, int number) {
        checkArgument(areaCode > 100);
        checkArgument(number > 1000);

        return new PhoneNumber(areaCode, number);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof PhoneNumber) {
            PhoneNumber other = (PhoneNumber) obj;
            return this.areaCode == other.areaCode &&
                    Objects.equals(this.number, other.number);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode=" + areaCode +
                ", number=" + number +
                '}';
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%d-%d", areaCode, number);
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(PhoneNumber other) {
        return PHONE_NUMBER_COMPARATOR.compare(this, other);
    }

    public static void main(String[] args) {
        System.out.println(PhoneNumber.of(200, 2000));
        System.out.printf("%s", PhoneNumber.of(200, 2000));
    }
}
