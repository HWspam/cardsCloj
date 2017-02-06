(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
      {:suit suit
       :rank rank})))

(defn create-hand [deck]
  (set
    (for [a1 deck
          a2 (disj deck a1)
          a3 (disj deck a1 a2)
          a4 (disj deck a1 a2 a3)]
      #{a1 a2 a3 a4})))


(defn straightflush? [hand]
 (= 1 (count (set (map and :suit hand :rank deck)))))

(defn straight? [hand]
  (let [rank (map :rank hand)
        [a b c d] rank
        so (sort [a b c d])
        [w x y z] so]
    (and (= w (- x 1) (- y 2) (- z 3))
         (=1 (count (set (map :suit hand)))))))
 

(defn fourofakind? [hand]
  (= 1 (count (set (map :rank hand)))))

(defn threeofakind? [hand]
  (let [rank (map :rank hand)
        [a b c d] rank]
    (or (= a b c)
        (= a c d)
        (+ a b d)
        (= b d c))))

(defn two-pair [hand]
  (let [rank (map :rank hand)
        [a b c d] rank]
    (or (and (=a b ) (= c d))
        (and (= a d) (c b))
        (and (= a c) (= d b)))))

(def test-hand 
  #{{:suit :clubs
     :rank 2}
    {:suit :hearts
     :rank 3} 
    {:suit :hearts
     :rank 4}
    {:suit :hearts
     :rank 10}})

(defn tests []
  (straightflush? test-hand))

(defn -main []
  (let [deck (create-deck)
        hand (create-hand deck)
        straightflush (filter straight-flush? hand)
        straight (filter straight? hand)
        flush (filter flush? hand)
        fourofakind (filter fourofakind? hand)
        threeofakind (filter threeofakind? hand)
        two-pair (filter two-pair hand)
        x (filter fourofakind? hand)]
    (println (count flush)
      (count straightflush)
      (count straight)
      (count flush)
      (count fourofakind)
      (count threeofakind)
      (count two-pair))))
