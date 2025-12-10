package aoc.y2025.day10;

import java.util.List;
import java.util.ArrayList;

class Instruction
{
    boolean[]   lights;
    boolean[]   expectedLights;
    int[][]     buttons;
    int[]       joltages;
    int         switchCount;
    int         resultCount;

    public Instruction()
    {   System.out.println("Dont use Default constructed object!");}

    public Instruction(String input)
    {
        // set lights
        String  lightList = input.substring(input.indexOf("[") + 1, input.indexOf("]"));
        this.expectedLights = new boolean[lightList.length()];
        this.lights =  new boolean[lightList.length()];
        for (int i = 0; i < lightList.length(); i++)
        {
            this.expectedLights[i] = lightList.charAt(i) == '.' ? false : true;
            this.lights[i] = false;
        }

        // Set buttons
        String  buttonList = input.substring(input.indexOf("(") + 1, input.lastIndexOf(")"));
        String[] buttonsArr = buttonList.split("\\) \\(");
        this.buttons = new int[buttonsArr.length][0];
        for (int i = 0; i < buttonsArr.length; i++)
        {
            String[] part = buttonsArr[i].split(",");
            this.buttons[i] = new int[part.length];
            for (int k = 0; k < part.length; k++)
                this.buttons[i][k] = Integer.parseInt(part[k]);
        }

        // set joltage requirements
        String  joltageList = input.substring(input.indexOf("{") + 1, input.indexOf("}"));
        String[] joltageArr = joltageList.split(",");
        this.joltages = new int[joltageArr.length];
        for (int i = 0; i < joltageArr.length; i++)
            this.joltages[i] = Integer.parseInt(joltageArr[i]);

        this.switchCount = 0;
        this.resultCount = 0;
    }

    public void switchLights(int[] button)
    {
        for (int b : button)
            lights[b] = !lights[b];
        this.switchCount++;
        lightsStatus();
    }

    public void lightsStatus()
    {
        for (boolean l : lights)
            System.out.print(l ? "#" : ".");
        System.out.println();
    }

    public void updateSwitchCount()
    {
        if (this.switchCount == 0)
            return ;
        if (this.resultCount == 0 || this.resultCount > this.switchCount)
            this.resultCount = this.switchCount;
    }
}