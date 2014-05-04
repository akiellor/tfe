(ns tfe.core
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:require [goog.events :as events]
            [cljs.core.async :refer [put! <! >! chan timeout]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:state :playing
                      :board [[nil nil nil nil]
                              [nil nil nil   2]
                              [nil nil nil   2]
                              [nil nil nil nil]]}))

(defn next-board [direction board]
  (vec (apply map vector board)))

(defn next-state [board]
  :playing)

(defn next-game [direction game]
  (let [board (:board game)
        state (:state game)]
    (assoc game
           :board (next-board direction board)
           :state (next-state (next-board direction board)))))

(defn code->direction [key-code]
  (case key-code
    37 :left
    38 :up
    39 :right
    40 :down
    nil))

(defn handle-keybindings [event]
  (let [keyCode (.-keyCode event)
        direction (code->direction keyCode)]
    (swap! app-state #(next-game direction %))))

(events/listen js/document "keyup" (comp println handle-keybindings))

(defn cell [value]
  (dom/span #js {:className "cell"}  value))

(defn board-row [row]
  (apply dom/div #js {:className "row"} (map cell row)))

(defn board [app owner opts]
  (reify
    om/IRender
    (render [_]
      (apply dom/div #js {:className "board"}
        (map board-row (-> app :board))))))

(defn application [app owner]
  (om/build board app))

(om/root application app-state
  {:target (.getElementById js/document "app")})
