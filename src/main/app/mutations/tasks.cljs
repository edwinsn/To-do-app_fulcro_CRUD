(ns app.mutations.tasks
  (:require [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]))

(defmutation delete-task
             "Mutation: Delete the task by Id from the list specified (can be completed or uncompleted tasks)"
             [{:keys [tasks-list task-id]}]
             (action [{:keys [state]}]
                     (let [path     (if (= "tasks_completed" task-list)
                                      [:tasks_completed :list/tasks]
                                      [:task_not_completed :list/tasks])
                           old-list (get-in @state path)
                           new-list (vec (filter #(not= (:task/id %) id) old-list))]
                          (swap! state assoc-in path new-list))))

(defmutation update-task
             "Mutation: Update the task name by Id"
             ;do I really need the list in here?
             [{:keys [tasks-list task-id new-name]}]
             (action [{:keys [state]}]
                     (let [path     (if (= "tasks_completed" task-list)
                                      [:tasks_completed :list/tasks]
                                      [:task_not_completed :list/tasks])
                           old-list (get-in @state path)
                           new-list (vec (map (generate_newtask_from_old % task-id new-name) old-list))]
                          (swap! state assoc-in path new-list))))

(defmutation add-task
             "Mutation: Add a task to the uncompleted-tasks list"
             [{:keys [ task-name]}]
             (action [{:keys [state]}]
                     (let [path     ([:task_not_completed :list/tasks])
                           old-list (get-in @state path)
                           ;check the syntax
                           new-list (vec (conj (generate-new-task task-name) old-list))]
                          (swap! state assoc-in path new-list))))

(defmutation change-task-status
             "Mutation: Update the task status by Id from the list specified (can be completed or uncompleted tasks)"
             [{:keys [tasks-list task-id]}]
             (action [{:keys [state]}]
                     (let [path_to_completed_tasks  [:tasks_completed :list/tasks]
                           path_to_not_completed_tasks  [:task_not_completed :list/tasks]
                           old-completed-tasks-list (get-in @state path_to_completed_tasks)
                           old-uncompleted-tasks-list (get-in @state path_to_not_completed_tasks)
                           ;is it necesary the get-task-by-id?
                           new-completed_tasks_list (vec (conj (get_task_by_id task-id) old-completed-tasks-list))
                           new-uncompleted_tasks_list (vec (filter #(not= (:task/id %) task-id) old-uncompleted-tasks-list))]
                          (swap! state assoc-in path_to_completed_tasks new-completed_tasks_list)
                          (swap! state assoc-in path_to_not_completed_tasks new-uncompleted_tasks_list)

                          )))

;Aux functions
(defn generate_newtask_from_old [old-task task-id new-name]
      {}
      )

(defn get_task_by_id [id]
      {}
      )