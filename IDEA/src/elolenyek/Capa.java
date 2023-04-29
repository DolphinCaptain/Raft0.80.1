package elolenyek;

public class Capa {
    private int x, y;

    public Capa(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    /**
     * A capa mozgasat leiro metodus.
     */
    public void uszkalas() {
        if (x > 6) {
            if (y > 26) {
                this.x--;
            }
        } else {
            if (y > 7) {
                this.y--;
            }
        }
        if (y < 8) {
            if (x < 18) {
                this.x++;
            } else {
                this.y++;
            }
        } else {
            if (x > 17) {
                this.y++;
            }
        }

    }


}
