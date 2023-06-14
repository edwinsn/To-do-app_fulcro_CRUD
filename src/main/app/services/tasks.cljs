(ns app.services.tasks
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    ))

;The mutation to get the tasks from backend
(defmutation load-tasks []
             (action [{:keys [state]}]
                     (let [response (-> (get-the-tasks)
                                        (.then #(.json %))
                                        (.then (fn [x] (doall (map #(assoc % :ui/id (gensym)) x)))))]
                          (swap! state assoc-in [:task-list/tasks] response))))


(defn get-the-tasks [] [
                        {:title "Create a clojure CRUD"}
                        {:title "Get feedback from the team"}
                        ])
;js/fetch "/api/tasks"