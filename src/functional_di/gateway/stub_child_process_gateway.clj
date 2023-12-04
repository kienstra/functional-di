(ns functional-di.gateway.stub-child-process-gateway
  (:require [functional-di.gateway.child-process-gateway :refer [IChildProcessGateway]]))

(deftype StubChildProcessGateway []
  IChildProcessGateway
  (spawn! [_])
  (spawn-sync! [_]))
