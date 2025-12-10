package aoc.y2025.day10;

import java.nio.file.Files;
import java.nio.file.Path;

class day10
{
    public static void main (String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Provide filename as argument");
            return ;
        }

        String filename = args[0];

        try {

            String input = Files.readString(Path.of(filename));

            String[] lines = input.split("\n");

            int result = 0;

            for (String line : lines)
            {
                Instruction i = new Instruction(line);
                // System.out.println(i.buttons.length);
                // result += minSwitches(i);
            }
            System.out.println("Works");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static int minSwitches(Instruction inst, int start)
    {
        int count = 0;

        return (inst.switchCount);
    }
}