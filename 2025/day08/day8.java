package aoc.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class day8 {
    public static void main(String[] args)
    {
        if (args.length == 0)
		{
			System.out.println("Provide filename as argument");
			return ;
		}

        try {
		    String filename = args[0];

            String input = Files.readString(Path.of(filename));
            String[] lines = input.split("\n");

            // Pos3d: coordinate
            List<Pos3d> positions = new ArrayList<>();

            for (String l : lines)
            {
                String[] numbers = l.split(",");
                positions.add(new Pos3d(
                    Double.parseDouble(numbers[0]),
                    Double.parseDouble(numbers[1]),
                    Double.parseDouble(numbers[2])
                ));
            }

            Map<Double, List<Pos3d>> pairs = new TreeMap();

            for (int i = 0; i < positions.size() - 1; i++)
            {
                for (int j = i + 1; j < positions.size(); j++)
                {
                    Pos3d left = positions.get(i);
                    Pos3d right = positions.get(j);
                    Double  distance = left.distanceTo(right);
                    if (pairs.get(distance) != null)
                    {
                        System.out.println("Duplicates");
                        return;
                    }
                    List    pair = new ArrayList();
                    pair.add(left);
                    pair.add(right);
                    pairs.put(distance, pair);
                }
            }

            // for (Double d : pairs.keySet())
            // {
            //     System.out.print(d + ": ");
            //     pairs.get(d).get(0).printValues();
            //     System.out.println();
            // }

            // set circuit numbers
            Map<Pos3d, Integer> circuits = new HashMap();
            int i = 1;
            int cir = 0;
            for (Double d : pairs.keySet())
            {
                Pos3d left = pairs.get(d).get(0);
                Pos3d right = pairs.get(d).get(1);
                // no register -> add to new circuit
                if (circuits.get(left) == null && circuits.get(right) == null)
                {
                    cir++;
                    circuits.put(left, cir);
                    circuits.put(right, cir);
                }
                // both register
                if (circuits.get(left) != null
                    && circuits.get(right) != null
                    && circuits.get(left) != circuits.get(right))
                {
                    int toSet = circuits.get(left);
                    int toChange = circuits.get(right);
                    for (Pos3d p : circuits.keySet())
                    {
                        if (circuits.get(p) == toChange)
                            circuits.put(p, toSet);
                    }
                }
                // left register
                if (circuits.get(left) != null)
                {
                    circuits.put(right, circuits.get(left));
                // right register
                } else {
                    circuits.put(left, circuits.get(right));
                }

                if (i == 1000)
                    break;
                i++;
            }

            Integer[] counter = new Integer[0];
            counter = circuits.values().toArray(counter);
            Arrays.sort(counter, Collections.reverseOrder());
            List countList = Arrays.asList(counter);
            System.out.println(Arrays.toString(counter));
            List<Integer> freq = new ArrayList();

            for (; cir != 0; cir--)
            {
                freq.add(Collections.frequency(countList, cir));
            }
            Collections.sort(freq, Collections.reverseOrder());
            for (Integer f : freq)
                System.out.println(f);
            // for (Pos3d p : circuits.keySet())
            // {
            //     p.printValues();
            //     System.out.println(" : " + circuits.get(p));
            // }

            System.out.println(pairs.size());
            System.out.println(freq.get(0) * freq.get(1) * freq.get(2));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}