package aoc.y2025.day09;

import java.nio.file.Files;
import java.nio.file.Path;

class day9
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Provide input filename");
            return ;
        }
        String filename = args[0];

        try {
            String input = Files.readString(Path.of(filename));

            String[] lines = input.split("\n");

            Pos2d[] all = new Pos2d[lines.length];

            int a = 0;
            for(String line : lines)
            {
                String[] coords = line.split(",");
                all[a] = new Pos2d(Long.parseLong(coords[0]), Long.parseLong(coords[1]));
                a++;
            }
            
            // set visual map with StringBuilder
            int length = (int)highestLineCount(all);
            StringBuilder[] visual = new StringBuilder[length];

            length = (int) highestLineLength(all);
            for (int i = 0; i < visual.length; i++)
            {
                visual[i] = new StringBuilder();
                visual[i].setLength(length);
                for (int b = 0; b < length; b++)
                    visual[i].setCharAt(b, '.');
            }

            // put red tiles
            for (Pos2d p : all)
                visual[(int)p.y - 1].setCharAt((int)p.x - 1, '#');

            // put green tiles on border
            Pos2d l = all[0];
            Pos2d d = all[1];
            boolean switchLeft = false;
            for (int i = 1; i < all.length; i++)
            {
                if (switchLeft)
                    l = all[i];
                else
                    d = all[i];
                switchLeft = !switchLeft;
                fillBorder(visual, l, d);
            }
            // connect first and last pos
            l = all[0];
            d = all[all.length - 1];
            fillBorder(visual, l, d);
            
            long size = 0;
            long result = 0;
            Pos2d left = null;
            Pos2d right = null;
            for(int i = 0; i < all.length; i++)
            {
                left = all[i];
                for (int j = i + 1; j < all.length;j++)
                {
                    right = all[j];
                    // put the condition that makes rectengular out of bonds
                    System.out.println(left.toString() + " " + right.toString() + " " + isRectangularValid(left, right));
                    size = left.getRectangular(right);
                    if (size > result)
                        result = size;
                }
            }
            System.out.println("Result is " + result);
            for(StringBuilder b : visual)
                System.out.println(b);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static boolean isRectangularValid(Pos2d l, Pos2d r)
    {
        return (true);
    }

    public static long highestLineCount(Pos2d[] all)
    {
        long val = 0;

        for (Pos2d p : all)
        {
            if (p.y > val)
                val = p.y;
        }
        return (val);
    }

    public static long highestLineLength(Pos2d[] all)
    {
        long val = 0;

        for (Pos2d p : all)
        {
            if (p.x > val)
                val = p.x;
        }
        return (val);
    }

    public static void fillBorder(StringBuilder[] visual, Pos2d l, Pos2d d)
    {
        if (l.y == d.y)
        {
            int start = l.x < d.x ? (int)l.x : (int)d.x;
            int end = l.x > d.x ? (int)l.x : (int)d.x;
            for (int p = start; p < end - 1; p++)
                visual[(int)l.y - 1].setCharAt(p, 'O');
            
        }
        // move d
        else if (l.x == d.x)
        {
            int start = l.y < d.y ? (int) l.y : (int)d.y;
            int end = l.y > d.y ? (int) l.y : (int)d.y;
            for (int p = start; p < end - 1; p++)
                visual[p].setCharAt((int)l.x - 1, 'D');
        }
    }
}