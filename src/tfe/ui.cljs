(ns tfe.ui
  (:require [goog.events :as events]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [tfe.core :refer [next-game app-state]]))

(enable-console-print!)

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

(defn message [state]
  (case state
    :won "Congratulations!"
    :playing "Good Luck"
    :lost "Oh noes!"))

(defn cell [value]
  (dom/span #js {:className "cell"}  value))

(defn board-row [row]
  (apply dom/div #js {:className "row"} (map cell row)))

(defn board [app owner opts]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
        (dom/h1 nil (message (-> app :state)))
        (apply dom/div #js {:className (str "board " (name (-> app :state)))}
          (map board-row (-> app :board)))))))

(defn application [app owner]
  (om/build board app))

(defn ^:export main []
  (om/root application app-state
           {:target (.getElementById js/document "app")}))
