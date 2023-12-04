(ns functional-di.core
  (:require [functional-di.composition-root :refer [comp-root gateways]]))

(defn -main []
  (:job (comp-root (gateways))))
