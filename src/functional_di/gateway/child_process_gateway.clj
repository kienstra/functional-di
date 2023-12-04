(ns functional-di.gateway.child-process-gateway)

(defprotocol IChildProcessGateway
  (spawn! [this])
  (spawn-sync! [this]))

(deftype ChildProcessGateway []
  IChildProcessGateway
  (spawn! [_])
  (spawn-sync! [_]))
