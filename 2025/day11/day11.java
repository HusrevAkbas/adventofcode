package aoc.y2025.day11;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class day11
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Please provide input file");
            return ;
        }

        try {
            String filename = args[0];

            String input = Files.readString(Path.of(filename));

            String[] lines = input.split("\n");

            Map<String, String[]>   dic = new HashMap<>();
            for (String line : lines)
            {
                String key = line.substring(0, line.indexOf(":"));
                String[] values = line.substring(5).split(" ");
                dic.put(key, values);
            }

            int result = roundTrip(dic, "you");
            System.out.println("Result: " + result);
            


        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public static int roundTrip(Map<String, String[]> map, String toLook)
    {
        int count = 0;
        String[] values = map.get(toLook);
        if (values[0].equals("out"))
            return (1);
        for (String val : values)
            count += roundTrip(map, val);
        return (count);
    }
}