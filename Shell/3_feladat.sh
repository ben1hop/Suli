#!/bin/bash

targy=$*
gnput="$PWD/jegyek"

tanulok=()
targyak=()
first=0
index=0
letezik=false

targyatlag=0
j=0

while IFS=$'\t' read -r -a line
do
	if [ $first -eq 0 ];
	then
		for i in ${!line[*]}
		do
			if [ $first -eq 0 ];
			then
				((first=first+1))
				continue
			else
				targyak+=("${line[$i]}")
				if [ $targy = ${line[$i]} ];
				then
					index=$i
					letezik=true
			
				fi
			fi
		done
	else # tanulo sorok
		if [ $index -gt 0 ];
		then
			((targyatlag=targyatlag+${line[$index]}))
			((j=j+1))
		else
			break
		fi
	fi
done < jegyek


if [ $letezik = "true" ];
then
	printf "Az atlag: %d\n" "`expr $targyatlag / $j`"
else
	printf "Nincs ilyen targy!\nA lista:\n"
	for ix in ${!targyak[*]}
	do
		printf "%s\n" "${targyak[$ix]}"
	done
fi
