import Prelude
import Data.List

type Dictionary = [(Char, Integer)]

dictionary :: [Char] -> Dictionary
dictionary x = zip x [1..]

dictionary_az = dictionary ['a'..'z']

dictionary_az_AZ = dictionary (['a'..'z']  ++ ['A'..'Z'])




charToNum :: Dictionary -> Char -> Integer
{-- 1. FELADAT ----------------------------------------}
charToNum [] _ = 0
charToNum dic l 
    | fst (head (dic)) == l = snd (head (dic))
    | otherwise = charToNum (drop 1 dic) l
{-- 1. FELADAT ----------------------------------------}

{-
getSecond :: (Char, Integer) -> Integer
getSecond (x,y) = y
ez egyenlo a data.tuple.snd-vel

matchFirst x (y, _) = x == y
charToNum dic l = case (find ( matchFirst l ) dic) of
    Just n -> snd n :: Integer
    Nothing -> 0 :: Integer

-}




numToChar :: Dictionary -> Integer -> Char
{-- 2. FELADAT ----------------------------------------} 
numToChar [] _ = '*'
numToChar dic l
    | snd (head dic) == l = fst (head dic)
    | otherwise = numToChar (drop 1 dic) l                                                                 
{-- 2. FELADAT ----------------------------------------}
{-
getFirst :: (Char, Integer) -> Char
getFirst (x,y) = x
ez egyenlo a data.tuple.fst-vel

matchSecond y (_, x) = y == x
numToChar dic n = case (find ( matchSecond n ) dic) of
    Just n -> fst n :: Char
    Nothing -> '*' :: Char
-}



translate :: Dictionary -> String -> [Integer]
{-- 3. FELADAT ----------------------------------------}

translate dic text = [ charToNum dic x | x<-text ]

{-- 3. FELADAT ----------------------------------------}





encode :: Dictionary -> String -> Integer
{-- 4. FELADAT ----------------------------------------}

encode dic text = product [ (primeList!!y)^(charToNum dic (text!!y)) | y<-[0..(length text)-1]]

{--encode dic text = product [ (last (take (getIndex x text) primeList))^charToNum dic x | y<-[1..length text] x<-text ]  nemtudtam inkrementalni egy masodik valtozot az elsovel --}




primeFactorization :: Integer -> [Integer]
{-- 5. FELADAT ----------------------------------------}
seged :: Integer -> [Integer] -> [Integer]
seged _ [] = []
seged 1 _ = []
seged n (x:xs)
    | n `mod` x == 0 = x : seged (n `div` x) (x:xs)
    | otherwise = seged n xs

primeFactorization 1 = []
primeFactorization n = seged n primeList
{-- 5. FELADAT ----------------------------------------}



{-
primeFactorization = unfoldr firstFactor
  where
    firstFactor n = listToMaybe [(f, n `div` f) | f <- [2..n] , n `mod` f == 0]
-}






decode :: Dictionary -> Integer -> String
{-- 6. FELADAT ----------------------------------------}

megszamol :: Integer -> [Integer] -> Integer

megszamol _ [] = 0
megszamol n (x:xs) 
    | n == head (x:xs) = 1 + megszamol n (xs)
    | otherwise = megszamol n (xs)  

decode dic number = [ numToChar dic x | x<-( [ megszamol x (primeFactorization number) | x<-(nub (primeFactorization number)) ] )]

{-- 6. FELADAT ----------------------------------------}
{-
    1) Primtenyezos listat hozunk letre a number-bol az elozo fuggveny segitsegevel
    2) Ezzel a listaval meghivjuk a megszamol fuggvenyt ennek a listanak minden egyedi elemere (nub lista)
    ez megszamolja hany darab primtenyezom van mindegyik fajtabol es vissza ad egy listat
    3) Az ebből keletkező 3. lista minden eleme a betu kod lesz amin vegig iteralunk a numToChar fuggvennyel es megkapjuk az eredmenyt
-}
