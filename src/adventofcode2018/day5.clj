(ns adventofcode2018.day5)

(def input (clojure.string/trim-newline (slurp "resources/day5.txt")))

(defn scan-polymer
  ([polymer]
   (scan-polymer polymer nil))
  ([polymer bad-unit]
   (loop [[a & rst] polymer
          acc '()]
     (let [b (first acc)]
       (cond
         ; done reverse the list and create the final string
         (nil? a) (clojure.string/join (reverse acc))
         ; if this is a bad character then skip it (for part b)
         (= (. Character toLowerCase a) bad-unit) (recur rst acc)
         (and 
           (not (nil? b))
           ; ascii upper and lower difference should be 32 
           (= 32 (. Math abs (- (int a) (int b))))) (recur rst (rest acc))
         :else (recur rst (conj acc a)))))))

(defn challenge-a
  [polymer]
  (count (scan-polymer polymer)))

(defn challenge-b
  " Brute force the reactions and find the minimum polymer produced "
  [polymer]
  (apply min (map #(count (scan-polymer polymer %))
                  "abcdefghijklmnopqrstuvwxyz")))
