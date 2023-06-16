(ns app.routes.tasks-list
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [app.services.tasks :refer [load-tasks delete-task update-task change-task-completion]]
    [app.components.task :refer [ui-task Task]]
    [app.components.new-task :refer [ui-new-task]]
    ))

(defsc TasksList [this {:list/keys [title tasks]}]
       {
        :query [ :list/title {:list/tasks (comp/get-query Task)}]
        :initial-state (fn [{:keys [title]}]
                           {
                            :list/title title
                            :list/tasks [(comp/get-initial-state Task {:id 1 :title "Third Task"})
                                         (comp/get-initial-state Task {:id 2 :title "Second task"})]})}
       (dom/div
         (dom/h1 title)
         (dom/ul

           (mapv (fn [p] (ui-task (comp/computed p {
                                               :onDelete delete-task
                                               :onNameUpdate update-task
                                               :onCompletionChange change-task-completion
                                               }))) tasks)
           (ui-new-task)
           ))
       )

(def ui-tasks-list (comp/factory TasksList))
