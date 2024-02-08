package first_practice;

public class ComplexDigit {
    private double real_part;
    private double imaginary_part;

    public ComplexDigit() {
        this(0, 0);
    }

    public ComplexDigit(double a, double b) { // z = a + b * i
        this.real_part = a;
        this.imaginary_part = b;
    }

    public ComplexDigit(ComplexDigit other) {
        this(other.real_part, other.imaginary_part);
    }

    public ComplexDigit conjugate() {
        return new ComplexDigit(this.real_part, -this.imaginary_part);
    }

    public double module() {
        return Math.sqrt(this.real_part * this.real_part + this.imaginary_part * this.imaginary_part);
    }

    public ComplexDigit plus(ComplexDigit other) {
        return new ComplexDigit(this.real_part + other.real_part, this.imaginary_part + other.imaginary_part);
    }

    public ComplexDigit minus(ComplexDigit other) {
        return new ComplexDigit(this.real_part - other.real_part, this.imaginary_part - other.imaginary_part);
    }

    public ComplexDigit multiply(ComplexDigit other) {
        return new ComplexDigit(this.real_part * other.real_part - this.imaginary_part * other.imaginary_part, this.real_part * other.imaginary_part + this.imaginary_part * other.real_part);
    }

    public ComplexDigit multiply(double n) {
        return new ComplexDigit(this.real_part * n, this.imaginary_part * n);
    }

    public ComplexDigit divide(ComplexDigit other) {
        if (other.imaginary_part == 0 && other.real_part == 0) {
            throw new ArithmeticException(" / 0");
        }
        ComplexDigit other_conj = other.conjugate();
        double d = 1 / (other.real_part * other.real_part + other.imaginary_part * other.imaginary_part);
        return this.multiply(other_conj).multiply(d);
    }

    public double getReal_part() {
        return real_part;
    }

    public double getImaginary_part() {
        return imaginary_part;
    }

    public void setReal_part(double new_value) {
        this.real_part = new_value;
    }

    public void setImaginary_part(double new_value) {
        this.imaginary_part = new_value;
    }

    public boolean equal(ComplexDigit other){
        return (this.real_part == other.real_part && this.imaginary_part == other.imaginary_part);
    }

    public void print(){
        if (this.imaginary_part < 0){
            System.out.printf("%.2f - %.2f * i ", this.real_part, -this.imaginary_part);
        } else{
            System.out.printf("%.2f + %.2f * i ", this.real_part, this.imaginary_part);
        }
    }
}
