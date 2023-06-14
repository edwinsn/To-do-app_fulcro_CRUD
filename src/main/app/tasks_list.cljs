(ns app.tasks-list
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    ;[app.services.tasks :refer [load-tasks]]
    ))

(defsc TasksList [this {:task-list/keys [tasks]}]
       {
        :query         [:task-list/tasks {:task/keys [title]}]
        :initial-state (fn [_] {:task-list/tasks []})
        :componentDidMount (fn [this]
                               (let [env (comp/any->app this)]
                                    (comp/transact! env [(load-tasks)])))
        }
       (dom/ul
         (mapv (fn [{:task/keys [title]}]
                   ;update this component with the task component
                   (dom/li title))
               tasks)))

(def ui-tasks-list (comp/factory TasksList))