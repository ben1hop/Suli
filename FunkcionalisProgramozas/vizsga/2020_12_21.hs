import Prelude
import Data.List
import Data.Maybe

----------------------------------------------------------------------------------
{-1. idModDiv (1 pont)
Írd meg a idModDiv függvényt, mely vár két Int paramétert, 
és visszaad egy rendezett hármast! Ennek az első eleme az első a két kapott paraméter egy párban, 
a második a kettő osztási maradéka végül az utolsó pedig a két paraméter egész osztása.-}

idModDiv :: Int -> Int -> ((Int, Int), Int, Int)
idModDiv x y = ((x,y), x `mod` y , x `div` y)


----------------------------------------------------------------------------------
{-2. Bámulatos szövegek (1 pont)
Döntsd el egy szövegről, hogy bámulatos-e! Egy szöveg akkor bámulatos, ha “Clean”-el vagy “Haskell”-el kezdődik.
-}

isAmazing :: String -> Bool
isAmazing xs
    | take 5 xs == "Clean" = True
    | take 7 xs == "Haskell" = True
    | otherwise = False



----------------------------------------------------------------------------------
{-3. McCarthy 91 (1 pont)
Definiáld az alábbi függvényt Haskellben:
-}

mcCarthy91 :: Int -> Int
mcCarthy91 n
    | n > 100 = n - 10
    | n <= 100 = mcCarthy91 (mcCarthy91(n+11))




----------------------------------------------------------------------------------
{-4. Igazságtábla (2 pont) // TODO
Készítsd el egy Bool -> Bool -> Bool függvény igazságtábláját! Példa az and függvény igazságtáblájára:


truthTable :: (Bool -> Bool -> Bool) -> [[Bool]]-}




----------------------------------------------------------------------------------
{-5. Üres listák (2 pont)
Adjuk meg azt a függvényt, amely kiszámolja, hogy hány üres lista van egy listában! -}

{-Ha egy listanak takeljuk az elso elemet es az egy ures listat ad akkor az eredeti is egy ures lista.-}

countEmpties :: [[a]] -> Int
countEmpties [] = 0
countEmpties (x:xs)
    | length ( take 1 x ) == 0 = 1 + countEmpties xs
    | otherwise = countEmpties xs



----------------------------------------------------------------------------------
{-6. Minden szám összes többszöröse (2 pont)
Készíts egy listát, melyben $0$-tól kezdve az összes számhoz tartozik egy lista, 
amely az adott szám összes többszörösét sorolja fel. Vagyis a lista $i.$ eleme az $i$ szám összes többszörösét sorolja fel, 
ahol $i \geq 0$. Az így készített lista valahogy így néz ki tehát: [[0,0,0...],[0,1,2...],[0,2,4...],[0,3,6...]...]-}

everyMultiple :: [[Int]]
everyMultiple = [ [ x | x<-[0..] , x `mod` y == 0] | y<-[0..] ]



----------------------------------------------------------------------------------
{-7. Előfordulások (2 pont)
Számold meg hányszor fordul elő egy listában egy adott elem!-}

freq :: Eq a => a -> [a] -> Int
freq _ [] = 0
freq x (y:ys)
    | x == y = 1 + freq x ys
    | otherwise = freq x ys




----------------------------------------------------------------------------------
{-8. Nárcisztikus szám (2 pont)
Nárcisztikus számnak nevezünk egy $n$ számjegyű számot, ha számjegyeit $n.$ hatványra emelve, majd azokat összeadva $n$-t kapjuk.
Például $153$ nárcisztikus, mert $153 = 1^3 + 5^3 + 3^3$.
Vizsgáld meg egy $n$ egész számról, hogy nárcisztikus-e! Csak pozitív számok lehetnek nárcisztikusak.-}


narcissistic :: Int -> Bool
narcissistic x
    | x <= 0 = False
    | x == sum ( map (^ (length [x])) [x] ) = True
    | otherwise = False



----------------------------------------------------------------------------------
{-9. Palindromizálás (3 pont)
Egy listát alakíts palindrommá a lehető legrövidebb módon úgy, hogy a kibővítésnél csak az eredeti lista hátuljához fűzöd elemeket!-}

isPalindrom :: Eq a => [a] -> Bool
isPalindrom xs = xs == reverse xs

palindromize :: Eq a => [a] -> [a]
palindromize [] = []
palindromize xs
    | isPalindrom xs = xs
    | otherwise = [head xs] ++ palindromize (drop 1 xs) ++ [head xs]



----------------------------------------------------------------------------------
{-10. Szellősítés (2 pont)
A nem szóköz karakterek közé beszúrj egy-egy szóközt, valamint a szóközöket háromszorozd meg!-}

extraSpacing :: String -> String
extraSpacing [] = []
extraSpacing (x:xs)
    | x == ' ' = [x] ++ [' ',' '] ++ extraSpacing xs
    | length xs == 0 = [x] ++ []
    | x /= ' ' && head (xs) /= ' ' = [x] ++ [' '] ++ extraSpacing xs
    | x /= ' ' && head (xs) == ' ' = [x] ++ extraSpacing xs




----------------------------------------------------------------------------------
{-11. Feltételes leképző maximum (3 pont)
Készíts egy függvényt, amely egy predikátumot, egy leképezést és egy listát kap, 
majd a predikátumnak megfelelő elemek leképezéséből visszaadja a legnagyobb elemet! 
Amennyiben nincsen ilyen elem, Nothing legyen a függvény eredménye!-}


{-
1: filterezzuk a masodik parameterrel
2: vegrehajtuk rajta az elso parametert
3: ha ez ures akkor nothing ha nem akkor a maximum ertek
-}

maximumByWhen :: Ord b => (a -> Bool) -> (a -> b) -> [a] -> Maybe b

maximumByWhen f m list
    | null ( map m ( filter f list ) ) = Nothing
    | otherwise = Just ( maximum (map m ( filter f list ) ) )




----------------------------------------------------------------------------------
{-12. Számlánc (3 pont)
Egy számláncot úgy képzünk, hogy összeadjuk egy szám számjegyeinek négyzeteit, majd ugyanezt a kapott számmal folytatjuk.-}

digs :: Int -> [Int]
digs 0 = []
digs x = digs ( x `div` 10) ++ [ x `mod` 10]

szamlanc :: Int -> Int
szamlanc xs
    | m /= 1 && m /= 89 = szamlanc ( m )
    | otherwise = m
    where
        m = sum ( map (^2) (digs (xs)))

arriveAt1 :: Int -> Int
arriveAt1 n = length [ y | y<-[1..n] , szamlanc y == 1 ]




----------------------------------------------------------------------------------
{-13. 3D Vektor (1 pont)
Definiáld a Vector3 adattípust, mely 3 dimenziós vektorok ábrázolására lesz használatos.
Az egyetlen adatkonstruktor legyen V, és legyen három Int adattagja, mint a három komponens. 
Tedd mellé a deriving (Eq, Show) záradékot is!
-}


data Vector3 = 
    V Int Int Int 
        deriving (Eq, Show)





----------------------------------------------------------------------------------
{-14. Komponensek összege (1 pont)
Add össze három komponensét egy vektornak!-}

componentSum :: Vector3 -> Int
componentSum (V x y z) = x+y+z




----------------------------------------------------------------------------------
{-15. Vektoriális szorzat (2 pont)
Számold ki két, paraméterként kapott vektor vektoriális szorzatát!

Két vektor vektoriális szorzata az alábbi formulából számolható:
-}

crossProduct :: Vector3 -> Vector3 -> Vector3
crossProduct (V a1 a2 a3) (V b1 b2 b3) = V ((a2*b3) - (a3*b2)) ((a3*b1) - (a1*b3)) ((a1*b2) - (a2*b1))




----------------------------------------------------------------------------------
{-16. Vektor-lista összegzése (2 pont)
Add a össze vektorokat egy listában! Két vektort úgy adunk össze, hogy az azonos komponenseit összeadjuk.-}

getX (V x _ _) = x
getY (V _ y _) = y
getZ (V _ _ z) = z

vectorListSum :: [Vector3] -> Vector3
vectorListSum xs = V (sum( map (getX) xs)) (sum( map (getY) xs)) (sum( map (getZ) xs))