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
                    size = left.getRectangular(right);
                    if (size > result)
                        result = size;
                }
            }
            System.out.println("Result is " + result);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}