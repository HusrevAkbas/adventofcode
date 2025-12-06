import * as fs from 'fs';

let input = fs.readFileSync("input.txt", "ascii");

let lines = input.split("\n\n");
let ranges = lines[0]?.split("\n");

let ranges2 = ranges?.map(s => s = s.split("-"));

let numarr: number[][] = [];

ranges2?.map(pair => numarr.push(pair.map(el => Number(el))));

numarr?.sort((a,b) => a[0] - b[0])

let merged :number[][] = [];
merged.push(numarr[0]);

numarr.forEach(n => {
    let last = merged.length - 1;
console.log("n 0 " + n[0] + " arr last 1 " + merged[last][1]);
    if (n[0] <= merged[last][1] + 1)
        merged[last][1] = n[1];
    else
        merged.push(n);
});

let result = 0;

merged.forEach(e => {
    result += e[1] - e[0] + 1;
});

console.log(merged);

console.log(result);