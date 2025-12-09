package aoc.y2025.day09;

import java.lang.Math;

class Pos2d
{
    public long x;
    public long y;

    public Pos2d(long x, long y)
    {
        this.x  = x;
        this.y  = y;
    }

    public long getRectangular(Pos2d other)
    {
        return ((Math.abs(other.x - this.x) + 1) * (Math.abs(other.y - this.y) + 1));
    }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }
}