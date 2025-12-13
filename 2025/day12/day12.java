package aoc.y2025.day12;

import java.nio.file.Files;
import java.nio.file.Path;

public class day12
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Provide filename as argument");
            return;
        }

        try {
            String filename = args[0];

            String input = Files.readString(Path.of(filename));
            String[] lines = input.split("\n\n");

            Shape[] shapes = new Shape[lines.length - 1];
            int i = 0;

            for (String s : lines)
            {
                if (s.indexOf("x") > 0)
                    break ;
                shapes[i] = new Shape(s);
                i++;
            }
            for (Shape s : shapes)
            {
                for (StringBuilder l : s.flipRight())
                    System.out.println(l);
                System.out.println();
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
