package aoc.y2025.day12;

class Shape {
    StringBuilder[] shape;

    public Shape(String in)
    {
        String[] raws = in.split("\n");
        this.shape = new StringBuilder[raws.length - 1];
        for (int i = 0;i < raws.length - 1; i++)
            this.shape[i] = new StringBuilder(raws[i + 1]);
    }

    public StringBuilder[] getShape() {
        return (this.shape);
    }
    public StringBuilder[] getShape(Direction dir) {
        switch (dir) {
            case UP:
                return (this.shape);
            case RIGHT:
                return (rotateShape(this.shape));
            case DOWN:
                return (rotateShape(rotateShape(this.shape)));
            case LEFT:
                return (rotateShape(rotateShape(rotateShape(this.shape))));
        }
        return (this.shape);
    }

    public static StringBuilder[] rotateShape(StringBuilder[] shape)
    {
        StringBuilder[] rotated = new StringBuilder[shape[0].length()];
        for (int i = 0; i < rotated.length; i++)
        {
            rotated[i] = new StringBuilder();
            rotated[i].setLength(shape.length);
        }
        int r;
        for (int i = 0; i < rotated.length; i++)
        {
            r = shape[0].length() - 1;
            for (int j = 0; j < rotated[i].length(); j++)
            {
                rotated[i].setCharAt(j, shape[r].charAt(i));
                r--;
            }
        }
        return (rotated);
    }

    enum Direction {
        UP, RIGHT, LEFT, DOWN
    }

}