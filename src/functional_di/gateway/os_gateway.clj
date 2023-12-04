(ns functional-di.gateway.os-gateway)

(defprotocol IOsGateway
  (arch [this])
  (platform [this])
  (os-type [this]))

(deftype OsGateway []
  IOsGateway
  (arch [_] "")
  (platform [_] "")
  (os-type [_] ""))
