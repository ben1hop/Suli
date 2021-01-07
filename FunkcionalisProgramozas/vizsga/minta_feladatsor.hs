import Prelude

import Data.Tuple

import Data.Char

import Data.List

import Data.Maybe

import Data.Function

------------------------------------------------------------------------------------------------------------
{-1. Pár rendezése (1 pont)
Készíts egy függvényt, amely egy pár elemeit növekvő sorba rendezi!-}

sortTuple :: Ord a => (a,a) -> (a,a)
sortTuple a
    | fst ( a ) < snd ( a )  = a
    | otherwise = swap a



------------------------------------------------------------------------------------------------------------
{-2. Betűméret-váltás (1 pont)
Definiálj egy függvényt, amely nagybetűkből kicsit, kisbetűkből nagyot csinál, 
egyéb jeleket pedig nem változtatja!-}

caseSwap :: Char -> Char
caseSwap x
    | isUpper x = toLower x
    | otherwise = toUpper x



------------------------------------------------------------------------------------------------------------
{-3. Hányszor szerepel egy elem? (1 pont)
Állapítsd meg, hogy egy elem hányszor szerepel egy véges listában!-}

count :: Eq a => a -> [a] -> Int
count x xs = length ( filter (== x) xs )



------------------------------------------------------------------------------------------------------------
{-4. Skaláris szorzat (2 pont)
Add meg két lista skaláris szorzatát, azaz elemenként szorozd össze őket, majd add össze a szorzatokat! 
Feltehető, hogy a két lista azonos hosszú.-}

listMul :: [Int] -> [Int] -> Int
listMul [] _ = 0 
listMul (x:xs) (y:ys) = x * y + listMul xs ys



------------------------------------------------------------------------------------------------------------
{-5. Azonos előjelek (2 pont)
Írj egy függvényt, amely megadja, hogy egy lista elemeinek az előjelei megegyeznek-e!
A 0 lehet akár negatív akár pozitív szám is (nem vesszük figyelembe).-}

sameSign :: [Int] -> Bool
sameSign xs
    | filter (>= 0) xs == xs = True
    | filter (<= 0) xs == xs = True
    | otherwise = False



------------------------------------------------------------------------------------------------------------
{-6. Dominóillesztés (2 pont)
Állapítsd meg, hogy dominók egy sorozata illeszhető-e, azaz minden dominó illeszkedik-e a szomszédaihoz!-}

isCorrect :: [(Int, Int)] -> Bool
isCorrect [] = True
isCorrect [x] = True
isCorrect (x:y:s)
    | snd (x) == fst (y) = isCorrect (y:s)
    | otherwise = False


------------------------------------------------------------------------------------------------------------
{-7. Szűrés (3 pont)
Készíts egy függvényt, amely egy listából kiválasztja azokat az elemeket, 
amelyekre minden kapott feltétel teljesül!-}

filterMany :: [a -> Bool] -> [a] -> [a]
filterMany [] _ = []
filterMany _ [] = []
filterMany (x:xs) y = filter x y ++ filterMany xs y



------------------------------------------------------------------------------------------------------------
{-8. Feltételes maximum (2 pont)
Egy lista adott feltételnek megfelelő elemei közül válaszd ki a legnagyobbat!
Mivel lehet, hogy nem is létezik ilyen elem, az eredményt Maybe-ként add meg!.-}

conditionalMax :: Ord a => (a -> Bool) -> [a] -> Maybe a

conditionalMax f xs
    | length ( filter f xs ) == 0 = Nothing
    | otherwise = Just (last ( sort ( filter f xs ) ))



------------------------------------------------------------------------------------------------------------
{-9. Hónapok (2 pont)
Definiálj egy Season adatszerkezetet az évszakokhoz!
A négy évszakhoz tartozó konstruktorok Winter, Spring, Summer és Autumn legyenek.
Legalább deriving (Eq) záradékot is kérj!
Definiálj egy függvényt, amely megadja a következő évszakot!-}

data Season
    = Winter
    | Spring
    | Summer
    | Autumn
    deriving (Eq)

nextSeason :: Season -> Season
nextSeason x
    | x == Winter = Spring
    | x == Spring = Summer
    | x == Summer = Autumn
    | x == Autumn = Winter



------------------------------------------------------------------------------------------------------------
{-10. Eltelt hónapok (2 pont)
Most január, és tél van. Írj egy függvényt, amelyik megadja hogy n >= 0 hónap múlva milyen évszak lesz!-}

seasonAfterMonths :: Int -> Season

seasonAfterMonths x
    | x `mod` 12 >= 2 && x `mod` 12 <= 4 = Spring
    | x `mod` 12 >= 5 && x `mod` 12 <= 7 = Summer
    | x `mod` 12 >= 8 && x `mod` 12 <= 10 = Autumn
    | x `mod` 12 == 0 || x `mod` 12 == 11 || x `mod` 12 == 1 = Winter



------------------------------------------------------------------------------------------------------------
{-11. Speciális karakterek (2 pont)
Távolíts el egy szövegből minden speciális karaktert, azaz mindent, amely nem az angol ábécé kis- vagy nagybetűje, szám vagy szóköz!-}

removeSpecial :: String -> String

removeSpecial x
    | length x == 0 = []
    | isAlphaNum (head ( x )) || head ( x ) == ' ' = [head ( x )]  ++ removeSpecial ( tail ( x ) )   
    | otherwise = removeSpecial ( tail ( x ) )

{- Nemtudtam az (x:xs) parameter formahoz megfelelo kilepo feltetelt adni a rekurzionak-}



------------------------------------------------------------------------------------------------------------
{-12. Részsorozat-e? (3 pont)
Döntsd el a paraméterként kapott első listáról, hogy szerepel-e a második listában részsorozatként!-}

isSublist :: Eq a => [a] -> [a] -> Bool

isSublist [] [] = True
isSublist _ [] = False 
isSublist [] _ = True
isSublist (x:xs) (y:ys)
    | x == y = isSublist xs (y:ys)
    | otherwise = isSublist (x:xs) ys



------------------------------------------------------------------------------------------------------------
{-13. Többször előforduló elemek (2 pont)
Add meg azokat az elemeket, amelyek többször előfordulnak egy listában, a legelső előfordulások sorrendjében!-}


{-TODO-}
{-multipleElems :: Eq a => [a] -> [a]

indexedList xs = zip [1..length(xs)] xs

multipleElems xs = snd ( last ( sortBy (compare `on` length) $ groupBy ((==) `on` snd) $ sortBy (compare `on` snd) ( indexedList xs ) ) ) -}



------------------------------------------------------------------------------------------------------------
{-14. Napi hőingadozás (2 pont)
Adott néhány napra a napi minimum és maximum hőmérséklet. A napi hőingadozás ezen értékek különbsége. Hányadik nap volt a legnagyobb a napi hőingadozás?
A napokat 1-től számozzuk. Napok közötti hőingadozást nem nézzük.-}

maxTempChange :: [(Int, Int)] -> Int

convertHo :: (Int, Int) -> Int
convertHo x = (snd (x)) - (fst (x))

maxTempChange y = fst $ last (  sortBy   (\(_,a) (_,b) -> compare a b)   $ zip [1..length(y)] ([ convertHo (x) | x <- y ])  )

{-
1. lepes - Csinalsz egy Int listat a parokbol ami a kulonbseguk     --> ([ convertHo (x) | x <- y ])
2. lepes - Zippelsz rajuk egy lista hosszu indexet                  --> $ zip [1..length(y)] es (1. lepes)
3. lepes - SortBy-al rendezed oket a masodik elemuk szerint         --> sortBy   (\(_,a) (_,b) -> compare a b) es (2. lepes)
4. lepes - A rendezett elemek elso indexnek tartott erteket vissza adod
-}



------------------------------------------------------------------------------------------------------------
{-15. Prím sorszám (3 pont)
Add meg egy lista prím sorszámú elemeit! Az elemek számozása induljon 1-től!-}

primeIndex :: [a] -> [a]

createIndexedList xs = zip [1..length(xs)] xs

isPrime 0 = False
isPrime 1 = False
isPrime 2 = True
isPrime x 
    | length [ y | y<-[2..x-1] , x `mod` y == 0 ] == 0 = True
    | otherwise = False

primeIndex xs = [ snd (x) | x <- createIndexedList xs , isPrime $ fst (x)]
