package first_practice;

public class first_practice {
    public static void main(String[] args) {
        ComplexDigit a = new ComplexDigit(1, 1);
        ComplexDigit b = new ComplexDigit(2, -3);

        System.out.print("A = ");
        a.print();
        System.out.print("\nB = ");
        b.print();

        ComplexDigit c = a.plus(b);
        ComplexDigit d = a.minus(b);
        ComplexDigit e = a.multiply(b);
        ComplexDigit f = a.multiply(8);
        ComplexDigit g = a.divide(b);
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

        MatrixComplexDigits p = new MatrixComplexDigits(2, 2);
        p.set_value(0, 0, new ComplexDigit(1, 4));
        p.set_value(0, 1, new ComplexDigit(2, 3));
        p.set_value(1, 0, new ComplexDigit(3, 2));
        p.set_value(1, 1, new ComplexDigit(4, 1));

        MatrixComplexDigits r = new MatrixComplexDigits(2, 2);
        r.set_value(0, 0, new ComplexDigit(1, 1));
        r.set_value(0, 1, new ComplexDigit(2, 2));
        r.set_value(1, 0, new ComplexDigit(3, 3));
        r.set_value(1, 1, new ComplexDigit(4, 4));

        MatrixComplexDigits q = new MatrixComplexDigits(2, 1);
        q.set_value(0, 0, new ComplexDigit(1, -1));
        q.set_value(1, 0, new ComplexDigit(1, -2));

        System.out.println("Матрица P(2 на 2):");
        p.print();

        System.out.println("Матрица R(2 на 2):");
        r.print();

        System.out.println("Матрица Q(2 на 1):");
        q.print();

        System.out.println();
        System.out.println("P+R");
        MatrixComplexDigits w = p.plus(r);
        w.print();

        System.out.println();
        System.out.println("P-R");
        MatrixComplexDigits x = p.minus(r);
        x.print();

        System.out.println();
        System.out.println("P*R");
        MatrixComplexDigits y = p.multiply(r);
        y.print();

        System.out.println();
        System.out.println("P*Q");
        MatrixComplexDigits t = p.multiply(q);
        t.print();

        System.out.println();
        System.out.println("P*2");
        MatrixComplexDigits multiply_p = p.multiply(2);
        multiply_p.print();

        System.out.println();
        System.out.print("Детерминант матрицы P = ");
        ComplexDigit det_p = p.determinant();
        det_p.print();

        MatrixComplexDigits m = new MatrixComplexDigits(4, 4);

        m.set_value(0, 0, new ComplexDigit(1, 0));
        m.set_value(0, 1, new ComplexDigit(2, 0));
        m.set_value(0, 2, new ComplexDigit(3, 0));
        m.set_value(0, 3, new ComplexDigit(4, 0));
        m.set_value(1, 0, new ComplexDigit(5, 0));
        m.set_value(1, 1, new ComplexDigit(1, 0));
        m.set_value(1, 2, new ComplexDigit(2, 0));
        m.set_value(1, 3, new ComplexDigit(3, 0));
        m.set_value(2, 0, new ComplexDigit(1, 0));
        m.set_value(2, 1, new ComplexDigit(2, 0));
        m.set_value(2, 2, new ComplexDigit(3, 0));
        m.set_value(2, 3, new ComplexDigit(3, 0));
        m.set_value(3, 0, new ComplexDigit(5, 0));
        m.set_value(3, 1, new ComplexDigit(4, 0));
        m.set_value(3, 2, new ComplexDigit(3, 0));
        m.set_value(3, 3, new ComplexDigit(2, 0));
        System.out.println();
        System.out.println();
        System.out.println("Матрица М(4 на 4):");
        m.print();
        System.out.print("Детерминант матрицы M = ");
        m.determinant().print();

    }
}
