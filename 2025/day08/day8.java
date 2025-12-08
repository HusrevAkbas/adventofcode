package aoc.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class day8
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.err.println("Please provide the filename as first argument");
            return ;
        }
        try {
            String filename = args[0];
            String input = Files.readString(Path.of(filename));
            Map<Pos3d, Integer> posMap = new HashMap<>();
            // List<Pos3d> posList = new ArrayList<>();
            
            String[] lines = input.split("\n");

            // Convert lines to list of 3D positions
            for (String line : lines)
            {
                String[] numbers = line.split(",");
                if (numbers.length > 3)
                    System.err.println("Error: Wrong count of numbers");
                posMap.put(new Pos3d(
                    Double.parseDouble(numbers[0]),
                    Double.parseDouble(numbers[1]),
                    Double.parseDouble(numbers[2])
                    ), 0);
            }

            Pos3d next = getFirstKeyHasValueZero(posMap);
            int circuit = 1;

            Pos3d[] keySet = new Pos3d[](0);
            keySet = posMap.keySet().toArray();
            
            while (next != null)
            {
                Pos3d prev = null;
                next.printValues();
                posMap.put(next, circuit);
                next = getFirstKeyHasValueZero(posMap);
                prev = next;
            }
            

        } catch (Exception e) {
            System.out.println("Something went wrong, double check your code");
        }
    }

    // first of next circuit
    public static Pos3d getFirstKeyHasValueZero(Map<Pos3d, Integer> map)
    {
        Pos3d next = null;
        Pos3d[] keySet = new Pos3d[0];
        keySet = map.keySet().toArray(keySet);
        for (Pos3d p : keySet)
        {
            if (map.get(p) == 0)
                return (p);
        }
        return (next);
    }

    public static Pos3d findNextClosestPoint(Pos3d[] keyArray, Pos3d current, Pos3d prev)
    {
        // Work this
        // Pos3d next = null;
        // for (Pos3d p : keyArray)
        // {
        //     if (p != current && p != prev &&
        //         current.distanceTo(p) )
        //         next = p;
        // }
        // return (next);
    }
}