package aoc.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

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

            // Pos3d: coordinate, Integer: assign circuit number the pos belongs to
            Map<Pos3d, Integer> posMap = new LinkedHashMap<Pos3d, Integer>();
            List<List<Pos3d>> circuits = new ArrayList<>();

            for (String l : lines)
            {
                String[] numbers = l.split(",");
                posMap.put(new Pos3d(
                    Double.parseDouble(numbers[0]),
                    Double.parseDouble(numbers[1]),
                    Double.parseDouble(numbers[2])
                ), 0);
            }
            
            Pos3d[] pair = new Pos3d[2];

            int circuit = 1;

            for (int i = 0; i < 9; i++)
            {
                // check in function if both boxes are in same circuit
                pair = findNextClosePair(posMap);

                // both boxes are unassigned
                if (posMap.get(pair[0]) == 0 && posMap.get(pair[1]) == 0)
                {
                    posMap.put(pair[0], circuit);
                    posMap.put(pair[1], circuit);
                    circuit++;
                }
                // both boxes are already assigned
                else if (posMap.get(pair[0]) > 0 && posMap.get(pair[1]) > 0)
                    updateCircuit(posMap, posMap.get(pair[0]), posMap.get(pair[1]));
                // first box is assigned
                else if (posMap.get(pair[0]) > 0)
                    posMap.put(pair[1], posMap.get(pair[0]));
                // second box is assigned
                else
                    posMap.put(pair[0], posMap.get(pair[1]));
                System.out.print(i + " ");
                pair[0].printValues();
                System.out.print(" " + posMap.get(pair[0]) + " ");
                pair[1].printValues();
                System.out.println(" " + posMap.get(pair[1]) + " d: " + pair[0].distanceTo(pair[1]));
                System.out.println("------------- ");
            }


            // count how many boxes have circuits
            for (int i = 0; i < circuit; i++)
            {
                int count = 0;
                Integer[] allValues = new Integer[0];
                allValues = posMap.values().toArray(allValues);
                for (int num : allValues)
                {
                    if (num == i)
                        count++;
                }
                System.out.println(i + " is present " + count + " times");
            }

            // for (Pos3d p : posMap.keySet())
            // {
            //     p.printValues();
            //     System.out.println(" : " + posMap.get(p));
            // }


        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Pos3d[] findNextClosePair(Map<Pos3d, Integer> map)
    {
        Pos3d[] result = new Pos3d[2];
        Double distance = Double.MAX_VALUE;
        Pos3d close = null;
        
        for (Pos3d p : map.keySet())
        {
            close = findNextClosePos(map, p);
            if (close == null)
                return (null);

            // if (map.get(p) != 0 && map.get(close) == map.get(p))
            //     continue ;
            if (p.distanceTo(close) < distance)
            {
                result[0] = p;
                result[1] = close;
                distance = p.distanceTo(close);
            }
        }
        return (result);
    }

    public static Pos3d findPosWithValueZero(Map<Pos3d, Integer> map)
    {
        Set<Pos3d> keys = map.keySet();
        Pos3d   pos = null;
        
        Iterator<Pos3d> it = keys.iterator();
        while (it.hasNext())
        {
            pos = it.next();
            if (map.get(pos) == 0)
                return (pos);
        }
        return null;
    }

    public static void updateCircuit(Map<Pos3d, Integer> map, int oldValue, int newValue)
    {
        Set<Pos3d> keys = map.keySet();
        
        Iterator<Pos3d> it = keys.iterator();

        Pos3d pos = null;

        while(it.hasNext())
        {
            pos = it.next();
            if (map.get(pos) == oldValue)
                map.put(pos, newValue);
        }
    }

    public static Pos3d findNextClosePos(Map<Pos3d, Integer> map, Pos3d current)
    {
        if (current == null)
            return null;

        Set<Pos3d> keys = map.keySet();
        Iterator<Pos3d> it = keys.iterator();
        Pos3d pos = null;
        Pos3d target = null;

        while (it.hasNext())
        {
            pos = it.next();
            if (pos.isEqual(current))
                continue ;
            if (map.get(current) > 0 && map.get(pos) == map.get(current))
                continue ;
            if (target == null)
                target = pos;
            else if (current.distanceTo(pos) < current.distanceTo(target))
                target = pos;
        }
        return target;
    }
}