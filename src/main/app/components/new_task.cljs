(ns app.components.new-task
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom :refer [div p]]))

(defsc NewTask [this props]
       {}
       (dom/div
         (dom/i "+")
         (dom/input {:placeholder "New task" })
         ))

(def ui-new-task (comp/factory NewTask))