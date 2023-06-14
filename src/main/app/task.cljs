(ns app.task
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom :refer [div p]]))



(defsc Task [this {:task/keys [title]}]
       (dom/div {:className "task" :id "id" :style {:color "purple"}}
                (dom/p {:className "Task_title"}
                       (dom/a {:href "#" :id "uniqueTagId"} "Hello broq"))))


(def ui-task (comp/factory Task))