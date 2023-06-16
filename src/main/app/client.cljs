(ns app.client
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [app.routes.tasks-list :refer [ui-tasks-list TasksList]]
    ))

(defonce app (app/fulcro-app))

(defsc Root [this  {:keys [ tasks_completed task_not_completed ]}]
       {
        :query [
                {:tasks_completed  (comp/get-query TasksList)}
                {:task_not_completed (comp/get-query TasksList)}
                ]
        :initial-state (
                         fn [params] {
                                      :tasks_completed (comp/get-initial-state TasksList {:title "Achievements"})
                                      :task_not_completed (comp/get-initial-state TasksList {:title "Steps to success"})
                                      }
                            )}
       (dom/div
         (ui-tasks-list { :list/title "Steps to success" :list/tasks [{:task/id 2 :task/title "Task 2" :key "2"}
                                      {:task/id 3 :task/title "Task 3" :key "3"}]})
         (ui-tasks-list { :list/title "Achievements" :list/tasks [{:task/id 1 :task/title "Task 1" :key "1"}]})
         )
       )


(defn ^:export init
      "Shadow-cljs sets this up to be our entry-point function. See shadow-cljs.edn `:init-fn` in the modules of the main build."
      []
      (app/mount! app Root "app")
      (js/console.log "Loaded"))

(defn ^:export refresh
      "During development, shadow-cljs will call this on every hot reload of source. See shadow-cljs.edn"
      []
      ;; re-mounting will cause forced UI refresh, update internals, etc.
      (app/mount! app Root "app")
      ;; As of Fulcro 3.3.0, this addition will help with stale queries when using dynamic routing:
      (comp/refresh-dynamic-queries! app)
      (js/console.log "Hot reload"))