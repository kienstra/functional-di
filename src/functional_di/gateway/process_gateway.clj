(ns functional-di.gateway.process-gateway)

(defprotocol IProcessGateway
  (env [this]))

(deftype ProcessGateway []
  IProcessGateway
  (env [_] {:path ""}))
