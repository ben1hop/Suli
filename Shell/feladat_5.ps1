$input_data = Get-Content A:\ben1hop\Learning\Suli\1.felev\Szamalap\beadandok\bead3\input.txt

$targyak=''
$jegyek=0,0,0,0,0

$counter= 0,0,0,0,0

$i=0

for ($i = 0; $i -lt $input_data.Length ; $i++ )
{
    if ( $i -eq 0 )
    {
        $targyak = $input_data[$i].split(' ')
    }
    else{
        $jegyek = $input_data[$i].split(' ')
        for ( $j = 0 ; $j -lt $jegyek.Length ; $j++ )
        {
            switch ($jegyek[$j])
            {
                1 { $counter[0]++ }
                2 { $counter[1]++ }
                3 { $counter[2]++ }
                4 { $counter[3]++ }
                5 { $counter[4]++ }
                default {}
            }
        }

    }
}

$i=0
foreach ($jegy in $counter ){
    $i++
    echo "$i-es : $jegy"    
}