(ns app.components.task
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom :refer [div p]]))



(defsc Task [this {:task/keys [title id completed?]}]

       {:query         [:task/id :task/title :task/completed?]
        :ident         (fn [] [:task/id id])
        :initial-state (fn [{:keys [id title]}]
                           {:task/id        id
                            :task/title     title
                            :task/completed? false})}
       (dom/li
         (dom/input {:type     "checkbox"
                     :checked  completed?
                     :onChange #(comp/transact! this `[(toggle-completed! {:id ~id} )])})
         (dom/span (if completed? [:s title] title))
         (dom/button "Update")
         (dom/button "Delete")
         ))


(def ui-task (comp/factory Task))