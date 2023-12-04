(ns functional-di.composition-root-test
  (:require [functional-di.gateway.stub-child-process-gateway :refer [->StubChildProcessGateway]]
            [functional-di.gateway.stub-editor-gateway :refer [->StubEditorGateway]]
            [functional-di.gateway.stub-fs-gateway :refer [->StubFsGateway]]
            [functional-di.gateway.stub-os-gateway :refer [->StubOsGateway]]))

(defn gateways []
  {:child-process-gateway (->StubChildProcessGateway)
   :editor-gateway        (->StubEditorGateway)
   :fs-gateway            (->StubFsGateway)
   :os-gateway            (->StubOsGateway)})
