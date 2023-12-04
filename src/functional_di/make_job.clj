(ns functional-di.make-job
  (:require [clojure.string :refer [join trim]]))

(defn make-job [build-agent-settings
                command-decorators
                editor-gateway
                fs-gateway]
  (fn job! []
    (let [command (command-decorators)]
      (when
       (not (.exists? fs-gateway "/tmp/log"))
        (.mkdir! fs-gateway "/tmp/log"))
      (.set! build-agent-settings)
      (.make-terminal!
       editor-gateway
       (trim
        (join
         " "
         [(:pre-command command)
          (:post-command command)
          (:eval-pre-command command)
          "local-bin run;"
          (:eval-post-command command)]))))))
