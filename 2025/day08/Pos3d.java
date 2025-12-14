package aoc.day08;

import java.lang.Math;
import java.util.List;

class Pos3d
{
    // I know those should be private
    Double   x;
    Double   y;
    Double   z;

    public Pos3d (Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void printValues()
    {
        System.out.print("(" + x + "," + y + "," + z + ")");
    }

    public  Double distanceTo(Pos3d other)
    {
        return (Math.sqrt(
            Math.pow(this.x - other.x, 2) +
            Math.pow(this.y - other.y, 2) +
            Math.pow(this.z - other.z, 2)
        ));
    }

    public boolean isEqual(Pos3d other)
    {
        return (this.x == other.x && this.y == other.y && this.z == other.z);
    }
}