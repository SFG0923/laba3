package Interfaces;

public class BeginStringFilter implements Filter {
    private String pattern;

    public BeginStringFilter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean apply(String str) {
        return str.startsWith(pattern);
    }
}