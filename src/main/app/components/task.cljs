(ns app.components.task
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom :refer [div p]]))



(defsc Task [this {:task/keys [title id completed? beingEdited? ]}  {:keys [onDelete onNameUpdate onCompletionChange ]}]

       {:query         [:task/id :task/title :task/completed? :task/beingEdited?]
        :ident         (fn [] [:task/id id])
        :initial-state (fn [{:keys [id title beingEdited?]}]
                           {:task/id        id
                            :task/title     title
                            :task/completed? false
                            :beingEdited? false
                            })}
       (dom/li
         (dom/form
           {:onSubmit #(onNameUpdate % id)}
           ;task status
           (dom/input {:type     "checkbox"
                       :checked  completed?
                       :onChange #(comp/transact! this `[(toggle-completed! {:id ~id} )])})
           ;task title
           (if beingEdited?
             (dom/input {:type "text" :defaultValue title})
             (dom/span (if completed? [:s title] title))
             )
           ;update task
           (dom/button {
                        :type "submit"
                        }
                       (if beingEdited? "Save" "Update"))
           ;delete task
           (dom/button {:onClick #(onDelete % id)} "Delete")

           )

         ))


(def ui-task (comp/factory Task))