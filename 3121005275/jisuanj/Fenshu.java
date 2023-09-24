package jisuanj;

import java.util.StringTokenizer;

public class Fenshu {
    int numerator;
    int denominator;

    Fenshu() {
    }

    Fenshu(int a, int b) {
        if (a == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else if (b == -1) {
            this.numerator = a;
            this.denominator = 1;
        } else {
            this.setNumeratorAndDenominator(a, b);
        }

    }

    void setNumeratorAndDenominator(int a, int b) {
        int c = this.f(Math.abs(a), Math.abs(b));
        this.numerator = a / c;
        this.denominator = b / c;
        if (this.numerator < 0 && this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }

    }

    int getNumerator() {
        return this.numerator;
    }

    int getDenominator() {
        return this.denominator;
    }

    int f(int a, int b) {
        int r;
        if (a < b) {
            r = a;
            a = b;
            b = r;
        }

        for(r = a % b; r != 0; r = a % r) {
            a = b;
            b = r;
        }

        return b;
    }

    Fenshu add(Fenshu r) {
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = this.numerator * b + this.denominator * a;
        int newDenominator = this.denominator * b;
        Fenshu result = new Fenshu(newNumerator, newDenominator);
        return result;
    }

    Fenshu sub(Fenshu r) {
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = this.numerator * b - this.denominator * a;
        int newDenominator = this.denominator * b;
        Fenshu result = new Fenshu(newNumerator, newDenominator);
        return result;
    }

    Fenshu muti(Fenshu r) {
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = this.numerator * a;
        int newDenominator = this.denominator * b;
        Fenshu result = new Fenshu(newNumerator, newDenominator);
        return result;
    }

    Fenshu div(Fenshu r) {
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = this.numerator * b;
        int newDenominator = this.denominator * a;
        Fenshu result = new Fenshu(newNumerator, newDenominator);
        return result;
    }

    public boolean compute(String data1, String data2) {
        StringTokenizer fenxi = new StringTokenizer(data1, "/");
        int data1_1 = Integer.parseInt(fenxi.nextToken());
        int data1_2;
        if (fenxi.hasMoreTokens()) {
            data1_2 = Integer.parseInt(fenxi.nextToken());
        } else {
            data1_2 = 1;
        }

        fenxi = new StringTokenizer(data2, "/");
        int data2_1 = Integer.parseInt(fenxi.nextToken());
        int data2_2;
        if (fenxi.hasMoreTokens()) {
            data2_2 = Integer.parseInt(fenxi.nextToken());
        } else {
            data2_2 = 1;
        }

        Fenshu r1 = new Fenshu(data1_1, data1_2);
        Fenshu r2 = new Fenshu(data2_1, data2_2);
        Fenshu result = r1.sub(r2);
        int a = result.getNumerator();
        int b = result.getDenominator();
        return a < 0;
    }
}