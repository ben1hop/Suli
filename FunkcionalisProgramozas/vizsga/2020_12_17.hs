import Prelude

import Data.Maybe

import Data.List

import Data.Char

import Data.Data

----------------------------------------------------------------------------------------------
{-1. Keresés rendezett négyesben (1 pont)
Döntsd el, hogy egy érték szerepel-e egy rendezett négyesben!-}

elemQuartet :: Eq a => a -> (a, a, a, a) -> Bool

getfst (a,_,_,_) = a
getsnd (_,b,_,_) = b
getthird (_,_,c,_) = c
getfourth (_,_,_,d) = d

elemQuartet x tuple
    | getfst (tuple) == x = True
    | getsnd (tuple) == x = True
    | getthird (tuple) == x = True
    | getfourth (tuple) == x = True
    | otherwise = False




----------------------------------------------------------------------------------------------
{-2. Manhattan-távolság (1 pont)
Számold ki két pont között a horizontális és vertikális távolságok összegét!-}

manhattanDistance :: (Integer, Integer) -> (Integer, Integer) -> Integer

manhattanDistance xs ys = (abs ( fst (ys) - fst (xs) ) ) + ( abs ( snd (ys) - snd (xs) ) )




----------------------------------------------------------------------------------------------
{-3. Szorzás (1 pont)
Szorozz össze két Maybe Integer-t! A Nothing-ot tekintsd 1-nek!-}

mulMaybe :: Maybe Integer -> Maybe Integer -> Integer

mulMaybe a b
    | isJust a && isJust b = fromJust a * fromJust b 
    | isJust a && isNothing b = fromJust a
    | isNothing a && isJust b = fromJust b
    | otherwise = 1




----------------------------------------------------------------------------------------------
{-4. Nem nulla szorzat (2 pont)
Egy listában szorozd össze addig az elemeket, amíg a rákövetkező elem nem nulla! Üres lista esetén az eredmény 1.-}

notNullProduct :: (Eq a, Num a) => [a] -> a

notNullProduct x = product ( takeWhile (/=0) x )




----------------------------------------------------------------------------------------------
{-5. Egész szám-e (2 pont)
Döntsd el egy karakterláncról, hogy beolvasható-e egész számként! Az első karakter lehet - 
vagy + is (előjelek), ezen kívül azonban csak számjegyeket ábrázoló karakterek megengedettek a bemenetben. 
Az üres karakterlánc nem olvasható be egész számként. A függvény csak véges bemenet esetén szükséges, 
hogy működjön, végtelen String-gel nem kell foglalkozni.-}


isNum :: String -> Bool

lengthCompare :: String -> Bool
lengthCompare xs = length ( takeWhile isNumber xs )  == length ( xs )

isNum [] = False
isNum (x:xs)
    | length ( xs ) == 0  && not (isNumber (x)) = False
    | ( x == '-' || x == '+' ) && lengthCompare xs = True
    | lengthCompare xs = True
    | otherwise = False




----------------------------------------------------------------------------------------------
{-6. Keresés szövegben (2 pont) 
Keresd meg, hogy egy szöveg hol fordul elő egy másik szövegben! Az átfedés is számít. Eredményként adjuk vissza az előfordulások indexeit. Az első String véges, a másik lehet végtelen.
Megjegyzés: itt jól jön az isPrefixOf függvény (link) a Data.List modulból.-}


---- TODO ---------
{-findIndexes :: String -> String -> [Integer]
findIndexes xs str =  fromJust ( map (($ tails str) . findIndex . isPrefixOf) xs)

findInString :: String -> String -> [Integer]

findInString [] [] = []
findInString _ [] = []
findInString [] ys = zip [0..length(ys - 1)]
findInString xs ys
    | xs `isPrefixOf` ys = findIndexes xs ys
    | otherwise = []

findInString [] [] = []
findInString _ [] = []
findInString xs ys
    | xs == take (length xs) ys = [findIndex] ++  -}



----------------------------------------------------------------------------------------------
{-7. Alkalmazások (2 pont)
Adottak függvények és a függvényekhez tartozó bemenetek egy-egy listában. 
Alkalmazd mindegyik függvényt mindegyik bemenetre! A függvényeket alkalmazzuk először az összes listaelemre, 
tehát ha a bemenetek listája végtelen, akkor a függvények listájából az első függvényt alkalmazza mindig, 
hiszen mindig lesz bemenet (ezt írja le az utolsó teszteset).-}


applies :: [a -> b] -> [a] -> [b]
applies [] _ = []
applies _ [] = []
applies (x:xs) ys = map x ys ++ applies xs ys




----------------------------------------------------------------------------------------------
{-8. Kicsinyítés (2 pont)
Definiáljuk azt a függvényt, amely egy függvényt alkalmaz egy lista minden elemére, de csak akkor alkalmazza egy adott elemre a függvényt, 
ha az az adott elemet kisebbé teszi!-}

mapToSmaller :: Ord a => (a -> a) -> [a] -> [a]

mapToSmaller _ [] = []
mapToSmaller f (x:xs)
    | f (x) < x = [f (x)] ++ mapToSmaller f xs
    | otherwise = [x] ++ mapToSmaller f xs




----------------------------------------------------------------------------------------------
{-9. A rövidebb lista szorzata (2 pont)
Válaszd ki két lista közül a rövidebbet és számold ki a lista elemeinek szorzatát! Legalább az egyik lista véges, de nem feltétlenül mindkettő. 
Egyenlő hosszúságú listák esetén az első lista szorzatát számold ki! Ha a rövidebb lista üres, akkor a szorzat eredménye legyen 1.-}

{-Itt annyi volt a nagyotlet h mivel a ket listabol valamelyik biztosan nem vegtelen igy ha egyutt leptetjuk oket biztos teljesulni fog egy hossz relacio.
    Megoldas: vegyuk mindket lista elso 10 elemet , 
    ha ezek hossza egyenlo , ejtsuk ezt a 10et es a maradekkal addig ismeteljuk a relaciot 
    amig elnem dol melyik lista a veges vagy rovidebb
-}

szamolgato :: [a] -> [a] -> Bool
szamolgato [] ys = True
szamolgato xs [] = False
szamolgato xs ys
    | length ( take 10 xs ) == length ( take 10 ys ) = szamolgato (drop 10 xs) (drop 10 ys)
    | length ( take 10 xs ) > length ( take 10 ys ) = False
    | otherwise = True

productOfShorter :: Num a => [a] -> [a] -> a
productOfShorter xs ys = if szamolgato xs ys then product ( xs ) else product ( ys )




----------------------------------------------------------------------------------------------
{-10. Hármas csoportosítás (3 pont)
Alkoss rendezett hármasokat egy lista egymást követő elemeiből! 
A hármasok elemei legyenek Maybe-k. Ha egy hármasba háromnál kevesebb elem kerülne, 
úgy a hármasokat egészítsük ki Nothing-gal, azt jelezve, hogy nincs több elem.
-}

{-Itt letordeljuk 3asaval az elemeket a lista elejerol es a listToMaybe-vel atalakitjuk oket majd ossze fuzzuk a tagokat-}

makeTriplet :: [a] -> (Maybe a, Maybe a, Maybe a)
makeTriplet xs = ( listToMaybe (xs) , listToMaybe (drop 1 xs) , listToMaybe (drop 2 xs) )

triplets :: [a] -> [(Maybe a, Maybe a, Maybe a)]

triplets [] = []
triplets xs =  [makeTriplet (take 3 xs)] ++ triplets (drop 3 xs)





----------------------------------------------------------------------------------------------
{-11. Elhagyás (3 pont)
Egy lista elemeit szűrd meg úgy, hogy az egymás melletti elemekből, 
amelyekre teljesül egy predikátum, csak egyet hagyj meg!
-}

{-Itt nincs otletem a vegtelen listat hogy oldjuk meg 
(take 18 (trimBy even (cycle [1,2,2,4,8,3])) == [1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3]) -}

trimBy :: (b -> Bool) -> [b] -> [b]

{-trimBy f xs = map (head) (group (xs))-}

trimBy f [] = []
trimBy f (x:xs)
    | length (xs) == 0 = [x] ++ []
    | f x && f (head xs) = [x] ++ trimBy f ( dropWhile f xs )
    | otherwise = [x] ++ trimBy f xs





----------------------------------------------------------------------------------------------
{-12. Csere (2 pont)
Cseréld ki egy listának a megadott pozícióján levő elemét a paraméterben megadott elemre. Amennyiben a pozíció negatív, 
az elemet szúrd be a lista elejére. Amennyiben a pozíció legalább akkora, mint a lista elemszáma, az elemet a lista végére szúrd be. A listát 0-tól indexeljük.
-}


{-    
    | x >= length (list) = list ++ [y]
    A lengthet nemtudjuk hasznalni mivel lehet vegtelen listank is , viszont a maybe-vel igy megtudtuk kerulni.
    Ejtuk az elso indexnyi elemet a listabol es ha ez ures akkor az indexunk nagyobb mint a lista tehat a vegere fuzzuk.
-}

replaceAt :: Int -> a -> [a] -> [a]

replaceAt x y list
    | x < 0 = [y] ++ list
    | isNothing ( listToMaybe (drop x list) ) = list ++ [y]
    | otherwise = take x list ++ [y] ++ drop (x+1) list


----------------------------------------------------------------------------------------------
{-13. NLString definíciója (1 pont)
Definiáld az NLString adatszerkezetet, és tedd az Eq és Show típusosztályok példányává! Az adatkonstruktorai a következők legyenek:

N :: String -> NLString, ahol a String utolsó eleme '0'.
L :: Int -> String -> NLString ahol a két paraméter a hossz és a tárolt String.
-}


data NLString
    = N String
    | L Int String
        deriving  (Eq, Show)





----------------------------------------------------------------------------------------------
{-14. NLString hossza (1 pont)
Add meg egy NLString hosszát. Feltehető, hogy a String-ek a konstruktoroknak megfelelő módon vannak megadva.-}

nlLength :: NLString -> Int

nlLength (N xs) = (length xs) - 1 
nlLength (L a xs) = a





----------------------------------------------------------------------------------------------
{-15. NLString előállítása (2 pont)
Készíts egy függvényt, amely egy String-ből NLString-et készít a következő szabály alapján:

ha a String nem tartalmaz '0'-át, akkor legyen belőle '0' végű String,
különben tárold hosszát és az eredeti Stringet!
-}

fromString :: String -> NLString

fromString xs
    | dropWhile (/='0') xs == [] = N (xs ++ "0")
    | otherwise = L (length xs) xs



----------------------------------------------------------------------------------------------
{-16. NLString-ek összefűzése (3 pont)
Fűzz össze két NLString-et!
Ha két '0' végűt (N) füzünk össze, akkor az eredmény is legyen '0' végű (N), különben tároljuk a hosszal együtt (L)!
-}

nlConcat :: NLString -> NLString -> NLString

nlConcat (N xs) (N ys) = N (init(xs)++ys)
nlConcat (L x xs) (L y ys) = L (x + y) (xs++ys)
nlConcat (N xs) (L y ys) = L ( (length (xs) - 1) + y) (init(xs)++ys)
nlConcat (L x xs) (N ys) = L (x + (length (ys)-1) ) (xs++init(ys))
