(ns app.routes.tasks-list
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    ;[app.services :refer [load-tasks]]
    [app.components.task :refer [ui-task]]
    [app.components.new-task :refer [ui-new-task]]
    ))


(defsc TasksList [this {:tasks-list/keys [tasks]}]
       {:initial-state (fn [_]
                           (println "TasksList :initial-state called")
                           {:tasks-list/tasks [{:id 1 :title "Task 1"}
                                               {:id 2 :title "Task 2"}]})}
       (dom/ul
         (print "Tasks" tasks)
         (mapv ui-task [{:task/id 1 :task/title "Task 12" :key "1"}
                        {:task/id 2 :task/title "Task 2" :key "2"}])
         (ui-new-task)
         )
       )


(def ui-tasks-list (comp/factory TasksList))