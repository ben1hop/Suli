#!/bin/bash

#kerjunk be egy tantargyat es adjuk meg hanyan es kik buktak meg belole
# ha nincs ilyen targy listazzuk ki oket && -help parancs

while getopts h name
do
	case $name in
		h) echo "Adjon meg egy tantárgyat!" >&2; exit 2;;
	esac
done


targy=$*

input="$PWD/jegyek"

bukottak=()
targyak=()
found=false
index=0
i=0


while IFS=$'\t' read -r -a myArray
do # megnezzuk az elso sort
	if [ $i -eq 0 ]; # elso sor
	then
		((i=i+1))
		for j in ${!myArray[*]}
		do
			if [ $j -gt 0 ]; # ossze szedjuk a letezo targyakat
			then
				targyak+=("${myArray[$j]}")
			fi

			if [ ${myArray[$j]} = $targy ]; # megkeressuk a beadott targyat es a sorat
			then
				found=true 
				index=$j
			fi
		done
	else # a tobbi sor
		if [ $index -gt 0 ];
		then
			if [ ${myArray[$index]} -eq 1 ]; # ha bukott elmentjuk a nevet
			then
				bukottak+=("${myArray[0]}")
			fi
		else
			break
		fi
	fi
done < jegyek

if [ $found = "true" ]; # ha van ilyen targy , hanyan buktak meg plusz nev lista
then
	printf "%d-n buktak meg. Nevszerint: \n" "${#bukottak[*]}"
	for ix in ${!bukottak[*]}
	do
		printf "%s\n" "${bukottak[$ix]}"
	done
else # ha nincs akkor kirjuk a targyakat
	printf "Nincs ilyen tantargy!\n A lista:\n"
	for ix in ${!targyak[*]}
	do
		printf "%s\n" "${targyak[$ix]}"
	done
fi

