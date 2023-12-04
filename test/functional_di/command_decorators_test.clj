(ns functional-di.command-decorators-test
  (:require [clojure.test :refer [deftest is testing]]
            [functional-di.composition-root :refer [comp-root]]
            [functional-di.gateway.editor-gateway :refer [IEditorGateway]]
            [functional-di.composition-root-test :refer [gateways]]))

(deftest command-decorators-test
  (testing "no pre or post command"
    (is (= {:pre-command       ""
            :post-command      ""
            :eval-pre-command  ""
            :eval-post-command ""}
           ((:command-decorators (comp-root (gateways)))))))
  (testing "only a pre command"
    (is (= {:pre-command       "echo 'Please enter what you want to run before the job command (this will appear in stdout):'; read pre_command;"
            :post-command      ""
            :eval-pre-command  "eval $pre_command"
            :eval-post-command ""}
           ((:command-decorators (comp-root (merge
                                             (gateways)
                                             {:editor-gateway
                                              (reify
                                                IEditorGateway
                                                (workspace [_]
                                                  {:config {:local-ci {:command.job.enable-pre-command  true
                                                                       :command.job.enable-post-command false}}})
                                                (make-terminal! [_ _]))})))))))
  (testing "only a post command"
    (is (= {:pre-command       ""
            :post-command      "echo 'Please enter what you want to run after the job command, followed by enter (you will not see anything as you type):'; read -s post_command;"
            :eval-pre-command  ""
            :eval-post-command "eval $post_command"}
           ((:command-decorators (comp-root (merge
                                             (gateways)
                                             {:editor-gateway
                                              (reify
                                                IEditorGateway
                                                (workspace [_]
                                                  {:config {:local-ci {:command.job.enable-pre-command  false
                                                                       :command.job.enable-post-command true}}})
                                                (make-terminal! [_ _]))})))))))
  (testing "pre and post command"
    (is (= {:pre-command       "echo 'Please enter what you want to run before the job command (this will appear in stdout):'; read pre_command;"
            :post-command      "echo 'Please enter what you want to run after the job command, followed by enter (you will not see anything as you type):'; read -s post_command;"
            :eval-pre-command  "eval $pre_command"
            :eval-post-command "eval $post_command"}
           ((:command-decorators (comp-root (merge
                                             (gateways)
                                             {:editor-gateway
                                              (reify
                                                IEditorGateway
                                                (workspace [_]
                                                  {:config {:local-ci {:command.job.enable-pre-command  true
                                                                       :command.job.enable-post-command true}}})
                                                (make-terminal! [_ _]))}))))))))
