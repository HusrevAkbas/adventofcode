package aoc.y2025.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

class day8 {
    public static void main(String[] args)
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

            List<Pos3d> posList = new ArrayList<>();
            List<List<Pos3d>> circuitList = new ArrayList<>();

            for (String l : lines)
            {
                String[] numbers = l.split(",");
                posList.add(new Pos3d(
                    Double.parseDouble(numbers[0]),
                    Double.parseDouble(numbers[1]),
                    Double.parseDouble(numbers[2])
                ));
            }

            Pos3d[] poss = new Pos3d[2];
            while (getTotalSize(circuitList) < 11) {
                poss = findNextClosePos(posList, circuitList);
                if (poss[0] == null)
                    break ;

                addCircuitList(circuitList, poss);
            }

            for (int i = 0; i < circuitList.size(); i++)
            {
                System.out.println("List size " + circuitList.get(i).size() + " : ");
                circuitList.get(i).forEach( e -> e.printValues());
            }
            
        } catch (Exception e) {
            System.err.println("Something is wrong, check your code!");
        }
    }

    public static Pos3d[] findNextClosePos(List<Pos3d> list, List<List<Pos3d>> circuitList)
    {
        Double distance = 100000.0;
        Pos3d[] couple = new Pos3d[2];
        Pos3d[] result = new Pos3d[2];
        couple[0] = null;
        couple[1] = null;


        for (int i = 0; i < list.size() - 1; i++)
        {
            Pos3d left = list.get(i);
            for (int j = i + 1; j < list.size(); j++)
            {
                Pos3d   right = list.get(j);
                couple[0] = left;
                couple[1] = right;

                // skip if any linked list containes both of them
                if (!containsBoth(circuitList, couple) && left != right && left.distanceTo(right) < distance)
                {
                    result[0] = couple[0];
                    result[1] = couple[1];
                
                    distance = left.distanceTo(right);
                }
            }
        }
        return (result);
    }

    public static int getTotalSize(List<List<Pos3d>> list)
    {
        int size = 0;

        for (int i = 0; i < list.size(); i++)
            size += list.get(i).size();
        return (size);
    }

    public static boolean containsBoth(List<List<Pos3d>> circuitList, Pos3d[] couple)
    {
        if (couple[0] == null)
            return (false);
        for (int i = 0; i< circuitList.size(); i++)
        {
            if (circuitList.get(i).contains(couple[0]) && circuitList.get(i).contains(couple[1]))
                return (true);
        }  
        return (false);
    }

    public static boolean containsOne(List<List<Pos3d>> circuitList, Pos3d pos)
    {
        if (pos == null)
            return (false);
        for (int i = 0; i < circuitList.size(); i++)
        {
            if (circuitList.get(i).contains(pos))
            {
                return (true);
            }
        }
        return (false);
    }

    public static boolean addCircuitList(List<List<Pos3d>> circuitList, Pos3d[] couple)
    {
        List<Pos3d> sublist = null;
        List<Pos3d> othersublist = null;
        List<Pos3d> newlist = new LinkedList<>();

        if (circuitList.size() == 0)
        {
            newlist.add(couple[0]);
            newlist.add(couple[1]);
            circuitList.add(newlist);
            return (true);
        }

        for (int i = 0; i < circuitList.size(); i++)
        {
            sublist = circuitList.get(i);
            if (containsBoth(circuitList, couple))
            {
                System.out.println("eversee");
                return (false);
            }
            if (sublist.contains(couple[0]))
            {
                for (int u = 0; u < circuitList.size();u++)
                {
                    if (circuitList.get(u).contains(couple[1]))
                        othersublist = circuitList.get(u);
                }
                if (othersublist != null)
                {
                    sublist.addAll(othersublist);
                    circuitList.remove(othersublist);
                }
                else
                    sublist.add(couple[1]);
                return (true);
            }
            else if (sublist.contains(couple[1]))
            {
                for (int u = 0; u < circuitList.size();u++)
                {
                    if (circuitList.get(u).contains(couple[0]))
                        othersublist = circuitList.get(u);
                }
                if (othersublist != null)
                {
                    sublist.addAll(othersublist);
                    circuitList.remove(othersublist);
                }
                else
                    sublist.add(couple[0]);
                return (true);
            }
        }
        newlist.add(couple[0]);
        newlist.add(couple[1]);
        circuitList.add(newlist);
        return (true);
        // System.err.println("No op");
        // return (false);
    }
}