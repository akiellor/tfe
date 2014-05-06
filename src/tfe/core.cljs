(ns tfe.core)

(def app-state (atom {:state :playing
                      :board [[nil nil nil nil]
                              [nil nil   2   2]
                              [nil nil nil   2]
                              [nil nil nil nil]]}))

(defn strip [row]
  (filter identity row))

(defn pad [row]
  (take 4 (concat row (repeat nil))))

(defn collapse [row]
  (cond
    (empty? row) []
    (= (first row) (-> row rest first)) (cons (* 2 (first row)) (collapse (-> row rest rest)))
    :else (cons (first row) (collapse (rest row)))))

(defn pack [board]
  (vec (map #(-> % strip collapse pad vec) board)))

(defn rotate [board]
  (apply map vector board))

(defn flip [board]
  (map (comp vec reverse) board))

(defn coordinates []
  (for [x (range 4) y (range 4)] [x y]))

(defn place [board]
  (let [index (first (shuffle (filter #(not (get-in board %)) (coordinates))))]
    (print index)
    (assoc-in board index 2)))

(defn update [board]
  (print board)
  (let [new-board (pack board)]
    (cond
      (= board new-board) new-board
      :else (place new-board))))

(defmulti next-board (fn [d b] d))

(defmethod next-board :left [_ board]
  (-> board update))

(defmethod next-board :right [_ board]
  (-> board flip update flip))

(defmethod next-board :up [_ board]
  (-> board rotate update rotate))

(defmethod next-board :down [_ board]
  (-> board rotate flip update flip rotate))

(defmethod next-board nil [_ board]
  board)

(defn next-state [board]
  (let [cells (vec (apply concat board))]
    (cond
      (contains? cells 2048) :won
      (every? number? cells) :lost
      :else :playing)))

(defmulti next-game (fn [d g] (:state g)))

(defmethod next-game :playing [direction game]
  (let [board (:board game)
        state (:state game)]
    (assoc game
           :board (next-board direction board)
           :state (next-state (next-board direction board)))))

(defmethod next-game :won [_ game]
  game)

(defmethod next-game :lost [_ game]
  game)

