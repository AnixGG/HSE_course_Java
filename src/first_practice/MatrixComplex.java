package first_practice;

public class MatrixComplex {
    private int size_rows=0;
    private int size_columns=0;
    private ComplexNumber[][] matrix;

    public MatrixComplex(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException();
        }
        this.matrix = new ComplexNumber[rows][columns];
        this.size_rows = rows;
        this.size_columns = columns;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                this.matrix[i][j] = new ComplexNumber();
            }
        }
    }

    public int get_size_rows(){
        return this.size_rows;
    }

    public int get_size_columns(){
        return this.size_columns;
    }

    public void set_value(int ind_i, int ind_j, ComplexNumber value){
        if (ind_i<0 || ind_i >= this.size_rows || ind_j < 0 || ind_j >= this.size_columns){
            throw new IllegalArgumentException();
        }
        this.matrix[ind_i][ind_j] = new ComplexNumber(value);
    }

    public MatrixComplex transpose(){
        MatrixComplex t = new MatrixComplex(this.size_columns, this.size_rows);
        for (int i = 0; i < this.size_rows; ++i) {
            for (int j = 0; j < this.size_columns; ++j) {
                t.matrix[j][i] = new ComplexNumber(this.matrix[i][j]);
            }
        }
        return t;
    }

    public MatrixComplex plus(MatrixComplex other){
        if (this.size_columns != other.size_columns && this.size_rows != other.size_rows){
            throw new ArithmeticException();
        }
        MatrixComplex newM = new MatrixComplex(this.size_rows, this.size_columns);
        for (int i=0;i<this.size_rows;++i){
            for (int j=0;j<this.size_columns;++j){
                newM.set_value(i, j, this.matrix[i][j].plus(other.matrix[i][j]));
            }
        }
        return newM;
    }

    public MatrixComplex minus(MatrixComplex other){
        if (this.size_columns != other.size_columns && this.size_rows != other.size_rows){
            throw new ArithmeticException();
        }
        MatrixComplex newM = new MatrixComplex(this.size_rows, this.size_columns);
        for (int i=0;i<this.size_rows;++i){
            for (int j=0;j<this.size_columns;++j){
                newM.set_value(i, j, this.matrix[i][j].minus(other.matrix[i][j]));
            }
        }
        return newM;
    }

    public MatrixComplex multiply(MatrixComplex other){
        if (this.size_columns != other.size_rows){
            throw new ArithmeticException();
        }
        MatrixComplex newM = new MatrixComplex(this.size_rows, other.size_columns);
        for (int i=0;i<this.size_rows;++i){
            for (int j=0;j<other.size_columns;++j){
                for (int k=0;k<this.size_columns;++k){
                    newM.matrix[i][j] = newM.matrix[i][j].plus(this.matrix[i][k].multiply(other.matrix[k][j]));
                }
            }
        }
        return newM;
    }

    public MatrixComplex multiply(double n){
        MatrixComplex newM = new MatrixComplex(this.size_rows, this.size_columns);
        for (int i=0;i<this.size_rows;++i){
            for (int j=0;j<this.size_columns;++j){
                newM.set_value(i, j, this.matrix[i][j].multiply(n));
            }
        }
        return newM;
    }
    public MatrixComplex get_minor(int index_row, int index_column){
        MatrixComplex minor = new MatrixComplex(this.size_rows-1,this.size_columns-1);
        int ind_r = 0;
        int ind_c = 0;
        for (int i=0;i<this.size_rows;++i){
            if (i==index_row) continue;
            for (int j=0;j<this.size_columns;++j){
                if (j==index_column) continue;
                ind_r = i;
                ind_c = j;
                if (i > index_row) ind_r--;
                if (j > index_column) ind_c--;
                minor.matrix[ind_r][ind_c] = this.matrix[i][j];
            }
        }
        return minor;
    }
    public ComplexNumber determinant(){
        if (this.size_rows != this.size_columns){
            throw new ArithmeticException();
        }
        if (this.size_rows==1){
            return this.matrix[0][0];
        }
        ComplexNumber result = new ComplexNumber();
        for (int i=0;i<this.size_columns;++i){
            MatrixComplex minor = this.get_minor(0, i);
            result = result.plus(this.matrix[0][i].multiply(minor.determinant()).multiply(Math.pow(-1, (double) i)));
        }
        return result;
    }

    public void print(){
        for (int i=0;i<this.size_rows;++i){
            for (int j=0;j<size_columns;++j){
                this.matrix[i][j].print();
                System.out.print("| ");
            }
            System.out.println();
        }
    }
}
