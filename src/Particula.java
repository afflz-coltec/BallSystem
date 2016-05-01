
import java.io.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Particula extends Ponto {

    private double massa;
    private double vx;
    private double vy;
    private Color color;
    play p;

    public Particula() throws Exception {
        this(0.0, 0.0, 0, 0.5, 1, 0.75);
    }

    public Particula(double a, double b, int i, double d, double e, double f) throws Exception {
        super(a, b, i, d / 1000);
        setMassa(d);
        setVx(e);
        setVy(f);
        p = new play();
    }

    public void setMassa(double d) {
        massa = (d > 0) ? d : 0;
        setRaio(Math.sqrt(massa / Math.PI));
    }

    public void setVx(double e) {
        vx = e;
    }

    public void setVy(double e) {
        vy = e;
    }

    public double getMassa() {
        return massa;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void desenhar() {
        StdDraw.filledCircle(getX(), getY(), getRaio());
    }

    public void mover(boolean c) {
        verificarLimite(c);
        setX(getX() + vx);
        setY(getY() + vy);
        desenhar();
    }

    public void mover() {
        setX(StdDraw.mouseX());
        setY(StdDraw.mouseY());
        if (StdDraw.mousePressed()) {
            setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        }
        desenhar();
    }

    public void verificarLimite(boolean c) {
        double aux1 = getX() + vx + getRaio();
        double aux2 = getY() + vy + getRaio();
        if ((aux1 > 10.0) || (aux1 < -10.0)) {
            set(true, false, c, 0);
        }
        if ((aux2 > 10.0) || (aux2 < -10.0)) {
            set(false, true, c, 0);
        }
    }

    public double raio() {
        return getRaio();
    }

    /**
     *
     * @param x verificação de alteração positiva ou negativa
     * @param y verificação de alteração positiva ou negativa
     * @param c trocar/não trocar de cor
     * @param rate taxa de incremento
     */
    public void set(boolean x, boolean y, boolean c, float rate) {
        if (!x) {
            this.setVx(this.getVx() + (this.getVx() * rate));
        } else {
            this.setVx(-(this.getVx() + (this.getVx() * rate)));
        }
        if (!y) {
            this.setVy(this.getVy() + (this.getVy() * rate));
        } else {
            this.setVy(-(this.getVy() + (this.getVy() * rate)));
        }
        if (c) {
            this.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        }
    }

    public void verificarColisao_norm(Particula p1, Particula p2, boolean c) throws IOException, Exception {
        double dx = Math.pow((p1.getX() - (p2.getX())), 2);
        double dy = Math.pow((p1.getY() - (p2.getY())), 2);
        double d = Math.sqrt(dx + dy);
        double r = Math.sqrt(Math.pow(p1.raio(), 2) + Math.pow(p1.raio(), 2));
        // Ocorreu uma colisao !!
        if (d <= r) {

            if ((p1.getVx() > 0) == (p2.getVx() > 0)) {
                if ((p1.getVy() > 0) == (p2.getVy() > 0)) {
                    p1.set(false, false, c, 0);
                    p2.set(false, false, c, 0);
                } else if ((p1.getVy() > 0) != (p2.getVy() > 0)) {
                    p1.set(false, true, c, 0);
                    p2.set(false, true, c, 0);
                }
            } else if ((p1.getVx() > 0) != (p2.getVx() > 0)) {
                if ((p1.getVy() > 0) == (p2.getVy() > 0)) {
                    p1.set(true, false, c, 0);
                    p2.set(true, false, c, 0);
                } else if ((p1.getVy() > 0) != (p2.getVy() > 0)) {
                    p1.set(true, true, c, 0);
                    p2.set(true, true, c, 0);
                }
            }
            p1.p.soltaOSom();

        }
    }

    public static void main(String args[]) throws Exception {

        StdDraw.setXscale(-10.0, 10.0);
        StdDraw.setYscale(-10.0, 10.0);

        boolean c = true;

        Particula p1 = new Particula(-7.0, -5.0, 0, 2.0, 0.2, 0.5);
        Particula p2 = new Particula(3.0, 7.0, 0, 2.0, 0.5, 0.2);
        Particula p3 = new Particula(4.0, -9.0, 0, 5.0, 0.7, 0.4);

        p1.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        p2.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        p3.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        while (true) {
            StdDraw.clear();
            StdDraw.setPenColor(p1.getColor());
            p1.mover(c);
            StdDraw.setPenColor(p2.getColor());
            p2.mover(c);
            StdDraw.setPenColor(p3.getColor());
            p3.mover();
            p1.verificarColisao_norm(p1, p2, c);
            p2.verificarColisao_norm(p2, p3, c);
            p3.verificarColisao_norm(p3, p1, c);
            StdDraw.show(15);
        }
    }
}
