import * as fs from 'fs';

let input = fs.readFileSync("input.txt", "ascii");

let sequence: string[] = input.split("\n");

if (!sequence || sequence.length == 0)
{
	console.log("Error: input could not split!");
	process.exit(1);
}

function findHighestNumber(line: string): number
{
	let tens: number = 0;
	let ones: number = 0;
	for (let i = 0; i < line.length - 1; i++)
	{
		if ( tens < Number(line[i]))
			tens = Number(line[i]);
	}
	for (let i = 1 + line.indexOf(String(tens)); i < line.length; i++)
	{
		if (ones < Number(line[i]))
			ones = Number(line[i]);
	}
	return (10 * tens + ones);
}

let total = 0;
for (let i = 0; i < sequence.length; i++)
{
	let res = 0;
	res = findHighestNumber(sequence[i]);
	total += res;
	// console.log("" + i + ": " + res + " ");
}
console.log(total);