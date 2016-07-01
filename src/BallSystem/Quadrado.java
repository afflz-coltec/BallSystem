package BallSystem;



public class Quadrado extends Desenho {

    private double lado;

    public Quadrado() {
        this(0.0, 0.0, 0, 0.01);
    }

    public Quadrado(Quadrado p) {
        this(p.getX(), p.getY(), p.getCor(), p.getLado());
    }

    public Quadrado(double a, double b, int i, double c) {
        super(a, b, i);
        setLado(c);
    }

    public void setQuadrado(double a, double b, int i, double c) {
        setX(a);
        setY(b);
        setCor(i);
        setLado(c);
    }

    public void setLado(double c) {
        lado = (c > 0) ? c : 0.01;
    }

    public double getLado() {
        return lado;
    }

    public void desenhar() {
        switch (getCor()) {
            case 0:
                StdDraw.setPenColor(StdDraw.RED);
                break;
            case 1:
                StdDraw.setPenColor(StdDraw.GREEN);
                break;
            case 2:
                StdDraw.setPenColor(StdDraw.YELLOW);
                break;
            case 3:
                StdDraw.setPenColor(StdDraw.CYAN);
                break;
            case 4:
                StdDraw.setPenColor(StdDraw.ORANGE);
                break;
        }
        StdDraw.filledSquare(getX(), getY(), lado / 2);
    }
}
