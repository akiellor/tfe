(ns tfe.core)

(def app-state (atom {:state :playing
                      :board [[nil nil nil nil]
                              [2 nil nil   2]
                              [nil nil nil   2]
                              [nil nil nil nil]]}))

(defn strip [row]
  (filter identity row))

(defn pad [row]
  (take 4 (concat row (repeat nil))))

(defn collapse [row]
  row)

(defn pack [board]
  (map #(-> % strip collapse pad) board))

(defn rotate [board]
  (apply map vector board))

(defn flip [board]
  (map (comp vec reverse) board))

(defmulti next-board (fn [d b] d))

(defmethod next-board :left [_ board]
  (pack board))

(defmethod next-board :right [_ board]
  (-> board flip pack flip))

(defmethod next-board :up [_ board]
  (-> board rotate pack rotate))

(defmethod next-board :down [_ board]
  (-> board rotate flip pack flip rotate))

(defmethod next-board nil [_ board]
  board)

(defn next-state [board]
  :playing)
