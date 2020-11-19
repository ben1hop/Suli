import Prelude
import Data.List

list=[1,1,2,3,3,4]

megszamol :: Integer -> [Integer] -> Integer

megszamol _ [] = 0
megszamol n (x:xs) 
    | n == head (x:xs) = 1 + megszamol n (xs)
    | otherwise = megszamol n (xs)   

megszamol2 :: [Integer] -> [Integer]

megszamol2 (x:xs) = [ megszamol x list | x<-(nub list)] 
