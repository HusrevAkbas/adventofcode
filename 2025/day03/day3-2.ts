import * as fs from 'fs';

let input = fs.readFileSync("input.txt", "ascii");

let sequence: string[] = input.split("\n");
let limit = 12;

if (sequence === undefined)
{
	console.log("Error: input could not split!");
	process.exit(1);
}

function findHighestNumber(line: string, limit: number): number
{
	let total = 0;
	let res = 0;
	let prevStart = 0;
	let start = 0;
	for (let i = 0; i < limit; i++)
	{
		if (res)
		{
			start = line.indexOf(String(res), prevStart) + 1;
			prevStart = start;
		}
		res = findHighestDigit(line, start, limit - i - 1);
		// console.log(res);
		if (res)
			total += res * Math.pow(10, limit - i - 1);
	}
	return (total);
}
function findHighestDigit(line: string,start: number, end: number): number
{
	let greatest: number = 0;
	for (let i = start; i < line.length - end; i++)
	{
		if ( greatest < Number(line[i]))
			greatest = Number(line[i]);
	}
	return (greatest);
}

let total = 0;
for (let i = 0; i < sequence.length; i++)
{
	let res = 0;
	res = findHighestNumber(sequence[i], limit);
	total += res;
	// console.log("" + i + ": " + res + " ");
}
console.log(total);