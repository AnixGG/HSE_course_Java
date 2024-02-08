package first_practice;

public class ComplexNumber {
    private double real_part;

    private double imaginary_part;

    public ComplexNumber() {
        this(0, 0);
    }

    public ComplexNumber(double a, double b) { // z = a + b * i
        this.real_part = a;
        this.imaginary_part = b;
    }

    public ComplexNumber(ComplexNumber other) {
        this(other.real_part, other.imaginary_part);
    }

    public ComplexNumber conjugate() {
        return new ComplexNumber(this.real_part, -this.imaginary_part);
    }

    public double module() {
        return Math.sqrt(this.real_part * this.real_part + this.imaginary_part * this.imaginary_part);
    }

    public ComplexNumber plus(ComplexNumber other) {
        return new ComplexNumber(this.real_part + other.real_part, this.imaginary_part + other.imaginary_part);
    }

    public ComplexNumber minus(ComplexNumber other) {
        return new ComplexNumber(this.real_part - other.real_part, this.imaginary_part - other.imaginary_part);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(this.real_part * other.real_part - this.imaginary_part * other.imaginary_part, this.real_part * other.imaginary_part + this.imaginary_part * other.real_part);
    }

    public ComplexNumber multiply(double n) {
        return new ComplexNumber(this.real_part * n, this.imaginary_part * n);
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other.imaginary_part == 0 && other.real_part == 0) {
            throw new ArithmeticException(" / 0");
        }
        ComplexNumber other_conj = other.conjugate();
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

    public boolean equal(ComplexNumber other){
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
