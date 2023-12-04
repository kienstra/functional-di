(ns functional-di.make-job-test
  (:require [clojure.test :refer [deftest is testing]]
            [functional-di.composition-root :refer [comp-root]]
            [functional-di.gateway.editor-gateway :refer [IEditorGateway]]
            [functional-di.gateway.fs-gateway :refer [IFsGateway]]
            [functional-di.composition-root-test :refer [gateways]]))

(deftest make-job-test
  (testing "mkdir is called when directory does not exist"
    (let [mkdir-called? (atom false)]
      ((:job (comp-root (merge
                         (gateways)
                         {:fs-gateway
                          (reify
                            IFsGateway
                            (exists? [_ _] false)
                            (mkdir! [_ _]
                              (swap! mkdir-called? (fn [_] true))))}))))
      (is (= @mkdir-called? true))))
  (testing "mkdir is not called when directory already exists"
    (let [mkdir-called? (atom false)]
      ((:job (comp-root (merge
                         (gateways)
                         {:fs-gateway
                          (reify
                            IFsGateway
                            (exists? [_ _] true)
                            (mkdir! [_ _]
                              (swap! mkdir-called? (fn [_] true))))}))))
      (is (= @mkdir-called? false))))
  (testing "passes the command to the terminal"
    (let [terminal-arg (atom "")]
      ((:job (comp-root (merge
                         (gateways)
                         {:editor-gateway
                          (reify
                            IEditorGateway
                            (workspace [_]
                              {:config {:local-ci {:command.job.enable-pre-command  true
                                                   :command.job.enable-post-command true}}})
                            (make-terminal! [_ command]
                              (swap! terminal-arg (fn [_] command))))}))))
      (is (=
           "echo 'Please enter what you want to run before the job command (this will appear in stdout):'; read pre_command; echo 'Please enter what you want to run after the job command, followed by enter (you will not see anything as you type):'; read -s post_command; eval $pre_command local-bin run; eval $post_command"
           @terminal-arg)))))
