(ns app.services.tasks
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.mutations :refer [defmutation]]
    [app.mutations.tasks :as api]
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

;get the tasks
(defn load-tasks [] (print (get-the-tasks)) (get-the-tasks))

;delete a task
(defn delete-task [ev id tasks-list]
      (.preventDefault ev)
      (print (str "Deleting: " id))
      (def this "Where should i go???")
      (comp/transact! this [(api/delete-task {:tasks-list tasks-list :task-id id  })])
      )

;update a task handler
(def input-value (atom ""))

(defn onInputChange [event]
      (let [value (-> event .-target .-value)]
           (println "Input value changed:" value)
           (reset! input-value value)))

(defn update-task [event id]
      (.preventDefault event)
      ;; retrieve the value from the application database
      (let [value @input-value]
           (println "Updating task" id "with value" value)))

;mark a task as completed or uncompleted
(defn change-task-completion [ev id completed]
      (.preventDefault ev)
      (print (str "Changing task" id " with " completed))
      )