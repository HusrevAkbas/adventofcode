package aoc.y2025.day11;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.Exception;

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

            // tracks records for svr,   dac,   fft in order
            boolean[] tracker = {false, false};

            int result = roundTrip(dic, "svr", tracker);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int roundTrip(Map<String, String[]> map, String toLook, boolean[] tracker)
    {
        int count = 0;
        String[] values = map.get(toLook);
        if (values[0].equals("out") )
        {
          System.out.print("out ");
            if (tracker[0] && tracker[1])
                return (1);
            return (0);
        }
        System.out.println();
        System.out.print(toLook + ": ");
        for (String val : values)
        {
            System.out.print(values + " ");
            if (toLook.equals("svr"))
            {
                tracker[0] = false;
                tracker[1] = false;
            }
            if (val.equals("dac"))
                tracker[0] = true;
            if (val.equals("fft"))
                tracker[1] = true;
            count += roundTrip(map, val, tracker);
        }
        return (count);
    }
}