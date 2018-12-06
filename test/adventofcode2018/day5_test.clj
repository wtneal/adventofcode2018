(ns adventofcode2018.day5-test
  (:require [clojure.test :refer :all]
            [adventofcode2018.day5 :refer :all]))

(deftest challenge-a-test
  (testing "Test day 5 a challenge"
    (is (= (count "dabCBAcaDA")
           (challenge-a "dabAcCaCBAcCcaDA")))
    (is (= (count "dabCBDA")
           (challenge-a "dabAcCaCBACcaDA")))
    (is (= (count "")
           (challenge-a "abcdeEDCBA")))
    (is (= (count "aabAAB")
           (challenge-a "aabAAB")))))

(deftest challenge-b-test
  (testing "Test day 5 a challenge"
    (is (= 4 ; by letter c
           (challenge-b "dabAcCaCBAcCcaDA")))))
