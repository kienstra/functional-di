(ns functional-di.gateway.fs-gateway)

(defprotocol IFsGateway
  (exists? [this file])
  (mkdir! [this file]))

(deftype FsGateway []
  IFsGateway
  (exists? [_ _] false)
  (mkdir! [_ _]))
