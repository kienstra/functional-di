(ns functional-di.composition-root
  (:require [functional-di.gateway.child-process-gateway :refer [->ChildProcessGateway]]
            [functional-di.gateway.editor-gateway :refer [->EditorGateway]]
            [functional-di.gateway.fs-gateway :refer [->FsGateway]]
            [functional-di.gateway.os-gateway :refer [->OsGateway]]
            [functional-di.build-agent-settings :refer [->BuildAgentSettings]]
            [functional-di.command-decorators :refer [make-command-decorators]]
            [functional-di.make-job :refer [make-job]]))

(defn gateways []
  {:child-process-gateway (->ChildProcessGateway)
   :editor-gateway        (->EditorGateway)
   :fs-gateway            (->FsGateway)
   :os-gateway            (->OsGateway)})

(defn comp-root [gw]
  (let [child-process-gateway (:child-process-gateway gw)
        editor-gateway        (:editor-gateway gw)
        fs-gateway            (:fs-gateway gw)
        os-gateway            (:os-gateway gw)
        build-agent-settings  (->BuildAgentSettings child-process-gateway os-gateway)
        command-decorators    (make-command-decorators editor-gateway)
        job!                  (make-job command-decorators build-agent-settings editor-gateway fs-gateway)]
    {:child-process-gateway child-process-gateway
     :editor-gateway        editor-gateway
     :fs-gateway            fs-gateway
     :os-gateway            os-gateway
     :build-agent-settings  build-agent-settings
     :command-decorators    command-decorators
     :job                  job!}))
