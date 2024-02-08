package first_practice;

public class first_practice {
    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(1, 1);
        ComplexNumber b = new ComplexNumber(2, -3);

        System.out.print("A = ");
        a.print();
        System.out.print("\nB = ");
        b.print();

        ComplexNumber c = a.plus(b);
        ComplexNumber d = a.minus(b);
        ComplexNumber e = a.multiply(b);
        ComplexNumber f = a.multiply(8);
        ComplexNumber g = a.divide(b);
        double module_a = a.module();

        System.out.print("\nA + B = ");
        c.print();
        System.out.print("\nA - B = ");
        d.print();
        System.out.print("\nA * B = ");
        e.print();
        System.out.print("\nA * 8 = ");
        f.print();
        System.out.print("\nA / B = ");
        g.print();
        System.out.print("\nModule A=");
        System.out.println(module_a);
        System.out.println();

        MatrixComplex p = new MatrixComplex(2, 2);
        p.set_value(0, 0, new ComplexNumber(1, 4));
        p.set_value(0, 1, new ComplexNumber(2, 3));
        p.set_value(1, 0, new ComplexNumber(3, 2));
        p.set_value(1, 1, new ComplexNumber(4, 1));

        MatrixComplex r = new MatrixComplex(2, 2);
        r.set_value(0, 0, new ComplexNumber(1, 1));
        r.set_value(0, 1, new ComplexNumber(2, 2));
        r.set_value(1, 0, new ComplexNumber(3, 3));
        r.set_value(1, 1, new ComplexNumber(4, 4));

        MatrixComplex q = new MatrixComplex(2, 1);
        q.set_value(0, 0, new ComplexNumber(1, -1));
        q.set_value(1, 0, new ComplexNumber(1, -2));

        System.out.println("Матрица P(2 на 2):");
        p.print();

        System.out.println("Матрица R(2 на 2):");
        r.print();

        System.out.println("Матрица Q(2 на 1):");
        q.print();

        System.out.println();
        System.out.println("P+R");
        MatrixComplex w = p.plus(r);
        w.print();

        System.out.println();
        System.out.println("P-R");
        MatrixComplex x = p.minus(r);
        x.print();

        System.out.println();
        System.out.println("P*R");
        MatrixComplex y = p.multiply(r);
        y.print();

        System.out.println();
        System.out.println("P*Q");
        MatrixComplex t = p.multiply(q);
        t.print();

        System.out.println();
        System.out.println("P*2");
        MatrixComplex multiply_p = p.multiply(2);
        multiply_p.print();

        System.out.println();
        System.out.print("Детерминант матрицы P = ");
        ComplexNumber det_p = p.determinant();
        det_p.print();

        MatrixComplex m = new MatrixComplex(4, 4);

        m.set_value(0, 0, new ComplexNumber(1, 0));
        m.set_value(0, 1, new ComplexNumber(2, 0));
        m.set_value(0, 2, new ComplexNumber(3, 0));
        m.set_value(0, 3, new ComplexNumber(4, 0));
        m.set_value(1, 0, new ComplexNumber(5, 0));
        m.set_value(1, 1, new ComplexNumber(1, 0));
        m.set_value(1, 2, new ComplexNumber(2, 0));
        m.set_value(1, 3, new ComplexNumber(3, 0));
        m.set_value(2, 0, new ComplexNumber(1, 0));
        m.set_value(2, 1, new ComplexNumber(2, 0));
        m.set_value(2, 2, new ComplexNumber(3, 0));
        m.set_value(2, 3, new ComplexNumber(3, 0));
        m.set_value(3, 0, new ComplexNumber(5, 0));
        m.set_value(3, 1, new ComplexNumber(4, 0));
        m.set_value(3, 2, new ComplexNumber(3, 0));
        m.set_value(3, 3, new ComplexNumber(2, 0));
        System.out.println();
        System.out.println();
        System.out.println("Матрица М(4 на 4):");
        m.print();
        System.out.print("Детерминант матрицы M = ");
        m.determinant().print();

    }
}
