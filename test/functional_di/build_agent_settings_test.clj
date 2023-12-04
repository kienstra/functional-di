(ns functional-di.build-agent-settings-test
  (:require [clojure.test :refer [deftest is testing]]
            [functional-di.composition-root :refer [comp-root]]
            [functional-di.composition-root-test :refer [gateways]]
            [functional-di.gateway.os-gateway :refer [IOsGateway]]
            [functional-di.gateway.child-process-gateway :refer [IChildProcessGateway]]))

(deftest build-agent-settings-test
  (testing "not an Intel Mac"
    (is (= false (.intel-mac? (:build-agent-settings (comp-root (gateways))))))
    (is (= false (.intel-mac?
                  (:build-agent-settings (comp-root
                                          (merge
                                           (gateways)
                                           {:os-gateway (reify
                                                          IOsGateway
                                                          (arch [_] "")
                                                          (platform [_] "")
                                                          (os-type [_] "Windows"))})))))))
  (testing "when not an Intel Mac, does not spawn a child process"
    (let [spawn-called? (atom false)]
      (.set! (:build-agent-settings
              (comp-root (merge
                          (gateways)
                          {:child-process-gateway (reify
                                                    IChildProcessGateway
                                                    (spawn! [_]
                                                      (swap! spawn-called? (fn [_] true)))
                                                    (spawn-sync! [_]))
                           :os-gateway            (reify
                                                    IOsGateway
                                                    (platform [_] "")
                                                    (arch [_] "x86")
                                                    (os-type [_] "Darwin"))}))))
      (is (= false @spawn-called?))))
  (testing "when an Intel Mac, spawns a child process"
    (let [spawn-called? (atom false)]
      (.set! (:build-agent-settings
              (comp-root (merge
                          (gateways)
                          {:child-process-gateway (reify IChildProcessGateway
                                                    (spawn! [_]
                                                      (swap! spawn-called? (fn [_] true)))
                                                    (spawn-sync! [_]))
                           :os-gateway             (reify
                                                     IOsGateway
                                                     (platform [_] "")
                                                     (arch [_] "x64")
                                                     (os-type [_] "Darwin"))}))))
      (is (= true @spawn-called?)))))
