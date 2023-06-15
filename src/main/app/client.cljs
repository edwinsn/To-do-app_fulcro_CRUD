(ns app.client
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [app.routes.tasks-list :refer [ui-tasks-list]]
    ))

(defonce app (app/fulcro-app))

(defsc Root [this {:keys [tasks]}]
       (dom/div
         (print tasks)
         (dom/h1 "Steps to success")
         (ui-tasks-list {:list/tasks [{:task/id 1 :task/title "Task 1" :key "1"}
                                      {:task/id 2 :task/title "Task 2" :key "2"}]})
         (dom/p "No more tasks")
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