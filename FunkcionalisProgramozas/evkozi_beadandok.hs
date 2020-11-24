{-1-}
triangleSides x y z = (x + y > z) && (x + z > y) && (y + z > x)

{-2-}
fractionalPart x = realToFrac ( x - fromIntegral ( truncate ( x ) ) ) :: Float 

{-3-}
isPrime :: Int -> Bool
isPrime x = (length [n | n <- [2 .. x-1], mod x n == 0]) == 0 :: Bool
primesUntil :: Int -> [Int]
primesUntil x = [n | n <- [2 .. x-1], isPrime ( n )]

{-4-}
isLeapYear :: Integer -> Bool
isLeapYear x = ( mod x 4 == 0 && not (mod x 100 == 0) ) || mod x 400 == 0 :: Bool

{-5-}
import Data.Char
toUpperFirst (x:xs) = toUpper x : xs
toUpperLongs xs = unwords [ if length w > 3 then toUpperFirst w else w | w <-words xs]

{-6-}
slice :: [Int] -> [a] -> [[a]]
slice [] n = []
slice (n:ns) l = take n l : slice ns (drop n l)

{-7-}
pack xs n 
    | n == 0 = [[]]
    | n < 0 = []
    | n > sum xs = []
pack (x:xs) n = pack xs n ++ [ x:cs | cs <- pack xs (n-x) ]

{-8-}
atfogo :: [Double] -> [Double] -> [Double]
atfogo [] _ = []
atfogo _ [] = []
atfogo (x:xs) (y:ys) = sqrt (x^2+y^2) : atfogo xs ys

{-9-}
import Prelude hiding (until)
until :: (a -> Bool) -> (a -> a) -> a -> a
until cond step start
    | cond start = start
    | otherwise = until cond step (step start)
