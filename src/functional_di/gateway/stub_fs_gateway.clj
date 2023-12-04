(ns functional-di.gateway.stub-fs-gateway
  (:require [functional-di.gateway.fs-gateway :refer [IFsGateway]]))

(deftype StubFsGateway []
  IFsGateway
  (exists? [_ _] false)
  (mkdir! [_ _]))
