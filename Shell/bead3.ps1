# Ez eltarolja a sorokat egy tombben
$file_data = Get-Content A:\ben1hop\Learning\Suli\1.felev\Szamalap\beadandok\bead3\input.txt

#elmentjuk a parameterbol kapott nevet
$name=$args[0]
$current_name=""


#iteralo feltetel
$found=$false
$i=0

$atlag=0
$osszeg=0

#iteralunk amig megnem talaljuk az elemet vagy a lista vegere erunk
DO
{
    # Az elso sor nemerdekel
    if ( $i -gt 0 )
    {
        # Feltetel vizsgalathoz felbontjuk a sort
        $elonev,$utonev,$jegyek=$file_data[$i].split(' ')
        $current_name=$elonev+' '+$utonev
        if ( $current_name -eq $name )
        {
            # Ha megtalaltuk kiszamoljuk az atlagot
            $found=$true
            foreach ($szam in $jegyek)
            {
                $osszeg+=$szam
            }
            $atlag=$osszeg/$jegyek.Length
        }

    }
    $i++

    
} While ( $i -lt $file_data.Length -and -Not ($found) )

if ( $found )
{
    echo 'Az atlaga:' $atlag
}
Else
{
    echo 'Nincs ilyen nev a listaban. A letezo nevek:'
    for ($j=1 ; $j -lt $file_data.Length; $j++){
        $elso,$masodik,$akarmi=$file_data[$j].split(' ')
        echo $elso' '$masodik
    }
}
