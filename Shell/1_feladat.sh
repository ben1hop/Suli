#!/bin/bash

#kerdes h kell-e float
nev="$*"

# ellenorizzuk hogy letezik-e a parameter nev
if [ -z "$nev" ]; then echo "Ures nev"; exit 1; fi

input="$PWD/jegyek" #input file

letezik=false # tanulo letezese
tanulok=() # tanulo nevek tombje
i=0 # elso sor kihagyasa

while IFS=$'\t' read -r -a myArray # beolvasunk amug
do
	if [ $i -eq 0 ]; # ha az elso elem , skippeljuk
	then
		((i=i+1))
		continue;
	else  # ha nem hozzaadjuk a nevet a listahoz es megnezzuk hogy megegyezik-e a parameterrel
		tanulok+=("${myArray[0]}")
		if [ "${myArray[0]}" = "$nev" ];
		then # ha megtalalta kiszamolja az atlagot es igazra allitja a letezest
			let atlag="(${myArray[1]} + ${myArray[2]} + ${myArray[3]} + ${myArray[4]}) / 4"
			echo $atlag
			letezik=true	
		fi
	fi
done < "$input"

if [[ $letezik = false ]]; # ha futas utan is hamis a letezes , kiiratjuk a neveket
then printf "Nincs ilyen nev!\n A nevek listaja: \n";
	for item in ${!tanulok[*]}
	do
		printf "%s\n" "${tanulok[$item]}"
	done
fi


