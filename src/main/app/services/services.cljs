;why cant i put this file in a subfolder
(ns app.services
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.mutations :refer [defmutation]]
    ))

;;The mutation to get the tasks from backend and update the state
;(defmutation load-tasks []
;             (action [{:keys [state]}]
;                     (let [response (-> (get-the-tasks)
;                                        (.then #(.json %))
;                                        (.then (fn [x] (doall (map #(assoc % :ui/id (gensym)) x)))))]
;                          (swap! state assoc-in [:task-list/tasks] response))))

;(defmutation load-tasks []
;             (action [{:keys [state]}]
;                     ;; your mutation code here
;                     ))


(defn get-the-tasks [] [
                        {:title "Create a clojure CRUD"}
                        {:title "Get feedback from the team"}
                        ])
;js/fetch "/api/tasks"

(defn load-tasks [] (print (get-the-tasks)) (get-the-tasks))