(ns functional-di.gateway.stub-os-gateway
  (:require [functional-di.gateway.os-gateway :refer [IOsGateway]]))

(deftype StubOsGateway []
  IOsGateway
  (arch [_] "")
  (platform [_] "")
  (os-type [_] ""))
