package aoc.y2025.day12;

class Shape {
    String[] shape;

    public Shape(String in)
    {
        String[] raws = in.split("\n");
        this.shape = new String[raws.length - 1];
        for (int i = 0;i < raws.length - 1; i++)
            this.shape[i] = raws[i + 1];
    }

    public String[] getShape() {
        return (this.shape);
    }

    public StringBuilder[] flipRight(){
        StringBuilder[] flip = new StringBuilder[shape[0].length()];
        for (int i = 0; i < flip.length; i++)
        {
            flip[i] = new StringBuilder();
            flip[i].setLength(shape.length);
        }

        for (int i = 0; i < flip.length; i++)
        {
            int r = flip.length - 1;
            for (int j = 0; j < flip[i].length(); j++)
            {
                flip[i].setCharAt(j, shape[r].charAt(i));
                r--;
            }
        }
        

        return (flip);
    }

}